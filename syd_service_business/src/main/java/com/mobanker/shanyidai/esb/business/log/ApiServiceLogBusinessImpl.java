package com.mobanker.shanyidai.esb.business.log;

import com.mobanker.framework.tracking.support.SpringContextUtils;
import com.mobanker.shanyidai.esb.common.constants.BusinessFlowEnum;
import com.mobanker.shanyidai.esb.common.constants.ErrorConstant;
import com.mobanker.shanyidai.esb.common.exception.EsbException;
import com.mobanker.shanyidai.esb.common.logger.LogManager;
import com.mobanker.shanyidai.esb.common.logger.Logger;
import com.mobanker.shanyidai.esb.common.utils.BeanUtil;
import com.mobanker.shanyidai.esb.dao.businessflow.BusinessFlowMapper;
import com.mobanker.shanyidai.esb.dao.log.ApiServiceLogMapper;
import com.mobanker.shanyidai.esb.model.entity.businessflow.BaseBusinessFlow;
import com.mobanker.shanyidai.esb.model.entity.log.ApiServiceLogEntity;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.mobanker.shanyidai.esb.call.dubbo.impl.BaseDataDubboImpl.logger;

/**
 * @desc:API系统service模块业务日志
 * @author: Richard Core
 * @create time: 2017/2/6 16:13
 */
@Service
public class ApiServiceLogBusinessImpl implements ApiServiceLogBusiness {
    public static final Logger LOG = LogManager.getSlf4jLogger(ApiServiceLogBusinessImpl.class);

    @Autowired
    private ApiServiceLogMapper apiServiceLogMapper;

    /**
     * @param logEntity
     * @return void
     * @description API系统service模块业务日志
     * @author Richard Core
     * @time 2017/2/6 16:34
     * @method saveLog
     */
    @Override
    public void saveLog(ApiServiceLogEntity logEntity) {
        apiServiceLogMapper.insert(logEntity);
    }

    /**
     * @description API系统保存业务流水
     * @author hantongyang
     * @time 2017/2/28 11:34
     * @method saveBusinessFlow
     * @param entity
     * @return void
     */
    @Override
    public void saveBusinessFlow(ApiServiceLogEntity entity) {
        BaseBusinessFlow flow = new BaseBusinessFlow();
        try {
            //1、根据方法名获取对应的枚举实体
            BusinessFlowEnum businessFlowEnum = BusinessFlowEnum.getBusinessFlowEnum(entity.getMethodName());
            if(businessFlowEnum == null){
                return;
            }
            //2、获取实体对应的全路径，包名+类名
            StringBuilder sb = new StringBuilder(flow.getClass().getPackage().getName());
            sb.append(".").append(businessFlowEnum.getEntityName());
            //3、封装实体
            flow = (BaseBusinessFlow) BeanUtil.cloneBean(entity, Class.forName(sb.toString()));
            flow.setResult(entity.getSuccessResult());
            flow.setReqMethod(entity.getMethodName());
            flow.setReqDesc(businessFlowEnum.getDesc());
            //4、保存
            BusinessFlowMapper bean = (BusinessFlowMapper) SpringContextUtils.getBean(businessFlowEnum.getDaoName());
            bean.insert(flow);
        } catch (Exception e) {
            LOG.error("保存业务流水异常", e);
            throw new EsbException(ErrorConstant.SAVE_DB_ERROR);
        }
    }
}
