package com.mobanker.shanyidai.esb.application.intercept;

import com.alibaba.fastjson.JSONObject;
import com.mobanker.shanyidai.dubbo.dto.log.BaseLogDto;
import com.mobanker.shanyidai.dubbo.dto.log.EsbHttpLogDto;
import com.mobanker.shanyidai.dubbo.service.log.EsbHttpLogDubboService;
import com.mobanker.shanyidai.esb.business.common.EsbCommonBusiness;
import com.mobanker.shanyidai.esb.common.constants.*;
import com.mobanker.shanyidai.esb.common.exception.EsbException;
import com.mobanker.shanyidai.esb.common.logger.LogManager;
import com.mobanker.shanyidai.esb.common.logger.Logger;
import com.mobanker.shanyidai.esb.common.thread.EsbThreadPoolUtil;
import com.mobanker.shanyidai.esb.common.utils.MapRunable;
import org.aspectj.lang.ProceedingJoinPoint;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadPoolExecutor;

import static com.mobanker.shanyidai.esb.application.intercept.util.FilterConvert.*;
import static com.mobanker.shanyidai.esb.common.constants.SydConstant.THREAD_POOL_NUM;

/**
 * @author hantongyang
 * @description
 * @time 2017/2/6 17:21
 */
public class HttpLogFilter {

    private Logger LOGGER = LogManager.getSlf4jLogger(this.getClass());

    @Resource
    private EsbHttpLogDubboService esbHttpLogDubboService;
    @Resource
    private EsbCommonBusiness esbCommonBusiness;

    //创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。
//    private static ExecutorService POOL = Executors.newFixedThreadPool(100);
    private ThreadPoolExecutor pool = EsbThreadPoolUtil.newInstanct(THREAD_POOL_NUM);

    /**
     * 处理运行异常的切面
     * */
    public Object deal(ProceedingJoinPoint pjp) throws Throwable {
        LOGGER.info("===============HTTP Log Filter Begin=================");
        Object returnMessage = null;
        //初始化参数
        String packageName = pjp.getSignature().getDeclaringType().getPackage().getName();
        String className = pjp.getSignature().getDeclaringType().getSimpleName();
        String methodName = pjp.getSignature().getName();
        Object[] params = pjp.getArgs();
        EsbHttpLogDto esbHttpLogDto = initBaseLogDto(packageName, className, methodName, new Date(), EsbHttpLogDto.class);
        try {
            returnMessage = pjp.proceed();
            setSuccessInfo(esbHttpLogDto, returnMessage);
        } catch (EsbException e1) {
            setErrorInfo(esbHttpLogDto, e1);
            throw new EsbException(e1.errCode, e1.message);
        } catch (Exception e2) {
            setErrorInfo(esbHttpLogDto, e2);
            throw new EsbException(ErrorConstant.SYSTEM_FAIL.getCode(), ErrorConstant.SERVICE_FAIL.getDesc());
        }finally {
            //启用多线程保存日志
            doSaveLog(esbHttpLogDto, params);
        }
        LOGGER.info("===============HTTP Log Filter End=================");
        return returnMessage;
    }

    /**
     * @param dto
     * @param params
     * @return void
     * @description 保存日志
     * @author hantongyang
     * @time 2017/2/7 11:41
     * @method doSaveLog
     */
    private void doSaveLog(BaseLogDto dto, Object [] params) {
        //判断业务调用是否正常，，如果业务调用是正常的，判断日志闸口是否需要记录正确的日志
        if(dto != null && SydConstant.RSP_STATUS_SUCCESS.equals(dto.getIsSuccess())){
            String cacheSysConfig = null;
            try {
                cacheSysConfig = esbCommonBusiness.getCacheSysConfig(ZkConfigConstant.SYD_ESB_HTTP_LOG_GATE.getZkValue());
            } catch (Exception e) {
                throw new EsbException(ErrorConstant.CONFIG_DATA_NULL);
            }
            //如果返回值为0表示不需要记录正常的日志
            if(SydConstant.RSP_STATUS_FAIL.equals(cacheSysConfig)){
                return;
            }
        }
        //封装参数保存日志
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("dto", dto);
        map.put("params", params);
        pool.execute(new MapRunable(map) {
            @Override
            public void run() {
                EsbHttpLogDto dto = (EsbHttpLogDto) map.get("dto");
                dto.setReqParam(JSONObject.toJSONString(map.get("params"))); //参数
                setDubboMonitorEnd(dto);
                esbHttpLogDubboService.saveLog(dto);
            }
        });
    }
}
