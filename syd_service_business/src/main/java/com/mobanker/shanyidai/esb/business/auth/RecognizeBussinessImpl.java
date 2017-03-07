package com.mobanker.shanyidai.esb.business.auth;

import com.mobanker.shanyidai.dubbo.dto.auth.AuthVoiceRecordDto;
import com.mobanker.shanyidai.dubbo.dto.auth.VoiceAuthDto;
import com.mobanker.shanyidai.dubbo.dto.auth.VoiceAuthResultDto;
import com.mobanker.shanyidai.dubbo.dto.auth.VoiceTranslateResultDto;
import com.mobanker.shanyidai.esb.business.common.EsbCommonBusiness;
import com.mobanker.shanyidai.esb.business.common.EsbRedisBusiness;
import com.mobanker.shanyidai.esb.call.http.VoiceAuthHttp;
import com.mobanker.shanyidai.esb.call.http.convert.AuthHttpConvert;
import com.mobanker.shanyidai.esb.call.util.MultipartFormDataFile;
import com.mobanker.shanyidai.esb.common.constants.*;
import com.mobanker.shanyidai.esb.common.exception.EsbException;
import com.mobanker.shanyidai.esb.common.logger.LogManager;
import com.mobanker.shanyidai.esb.common.logger.Logger;
import com.mobanker.shanyidai.esb.common.packet.ResponseEntity;
import com.mobanker.shanyidai.esb.common.utils.BeanUtil;
import com.mobanker.shanyidai.esb.common.utils.CommonUtil;
import com.mobanker.shanyidai.esb.dao.auth.AuthVoiceMapper;
import com.mobanker.shanyidai.esb.model.entity.auth.AuthVoiceEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liuhanqing
 * @description 用户联系人相关业务处理类
 * @time 2017/2/14.
 */
@Service
public class RecognizeBussinessImpl implements RecognizeBusiness {
    public static final Logger LOGGER = LogManager.getSlf4jLogger(RecognizeBussinessImpl.class);


    @Resource
    private VoiceAuthHttp voiceAuthHttp;

    @Resource
    private EsbCommonBusiness esbCommonBusiness;

    @Resource
    private AuthVoiceMapper authVoiceMapper;
    @Resource
    private EsbRedisBusiness esbRedisBusiness;

    /**
     * @param paramsDto
     * @description 语音认证
     * @author liuhanqing
     * @time 2017/2/09 16:59
     * @method voiceAuth
     */
    @Override
    public VoiceAuthResultDto voiceAuth(VoiceAuthDto paramsDto) {
        Map<String, String> params = new HashMap<>();
        params.put("duration", paramsDto.getDuration());
        params.put("fileName", paramsDto.getFileName());
        params.put("fileSize", paramsDto.getFileSize());
        //文件流处理
        MultipartFormDataFile formDataFile = null;
        try {
            formDataFile = new MultipartFormDataFile(AuthHttpConvert.VOICE_UPLOAD_FIELDNAME, paramsDto.getFile().getOriginalFilename()
                    , AuthHttpConvert.VOICE_UPLOAD_CONTENTTYPE, paramsDto.getFile().getInputStream());
        } catch (Exception e) {
            throw new EsbException(ErrorConstant.SYSTEM_FAIL.getCode(), "文件流读取失败，请检查您的网络环境，稍后重试");
        }
        //获取上传链接
//        String reqURL = esbCommonBusiness.getCacheSysConfig(ZkConfigConstant.AUTH_URL_KDXF_SUBMIT_ORDER.getZkValue(),
//                ErrorConstant.CONFIG_DATA_NULL.getCode(), "认证系统上传语音服务地址获取失败");
        String reqURL = "http://192.168.1.61:8080/auth/api/1.0.0/kdxf/submitOrder";// TODO: 2017/2/17  测试地址
        VoiceAuthResultDto resultDto = voiceAuthHttp.voiceAuth(reqURL, params, formDataFile);

        return resultDto;
    }

    /**
     * @param paramsDto
     * @param resultDto
     * @param authProcess
     * @param e
     * @return void
     * @description 保存语音识别记录
     * @author Richard Core
     * @time 2017/2/16 16:21
     * @method saveVoiceUploadRecord
     */
    @Override
    public void saveVoiceUploadRecord(VoiceAuthDto paramsDto, VoiceAuthResultDto resultDto, EsbVoiceAuthStatusEnum authProcess, Throwable e) {
        if (resultDto == null) {
            return;
        }
        try {
            AuthVoiceEntity entity = new AuthVoiceEntity();
            entity.setUserId(paramsDto.getUserId());
            entity.setFileSize(paramsDto.getFileSize());
            entity.setFileName(paramsDto.getFileName());
            entity.setFilePath(paramsDto.getFilePath());
            entity.setVoiceLast(paramsDto.getDuration());//文件时长
            entity.setOrderNo(resultDto.getOrderNo());
            entity.setStatus(authProcess.getStatus());
            entity.setUploadSid(resultDto.getTranserialsId());// 流水号 2017012315015794035
            Date uploadTime = new Date();
            entity.setUploadTime(uploadTime);
            entity.setUploadBeginTime(paramsDto.getUploadBeginDate());
            entity.setUploadDuration(uploadTime.getTime() - paramsDto.getUploadBeginDate().getTime());
            entity.setAskUserName(paramsDto.getRealName());
            entity.setAskUserPhone(paramsDto.getPhone());
            if (e != null) {
                if (e instanceof EsbException) {
                    EsbException esb = (EsbException) e;
                    entity.setDetailReason(esb.errCode + ":" + esb.message);
                } else {
                    entity.setDetailReason(CommonUtil.getStackTrace(e));
                }
            }
            this.saveVoiceRecord(entity);
        } catch (Exception e1) {
            LOGGER.warn("保存语音识别记录失败" + e1);
        }
    }

    /**
     * @param orderId
     * @return com.mobanker.shanyidai.esb.common.packet.ResponseEntity
     * @description 获取语音解析结果
     * @author Richard Core
     * @time 2017/2/10 10:46
     * @method findVoiceOrder
     */
    @Override
    public ResponseEntity findVoiceOrder(String orderId) {
        //获取服务链接
        String reqURL = esbCommonBusiness.getCacheSysConfig(ZkConfigConstant.AUTH_URL_KDXF_QUERY_ORDER.getZkValue(),
                ErrorConstant.CONFIG_DATA_NULL.getCode(), "认证系统获取语音解析结果服务地址获取失败");
        return voiceAuthHttp.findVoiceOrder(reqURL, orderId);
    }

    /**
     * @param entity
     * @return com.mobanker.shanyidai.esb.model.entity.auth.AuthVoiceEntity
     * @description 保存语音识别记录
     * @author Richard Core
     * @time 2017/2/16 14:04
     * @method saveVoiceRecord
     */
    @Override
    public AuthVoiceEntity saveVoiceRecord(AuthVoiceEntity entity) {
        if (entity == null || StringUtils.isBlank(entity.getOrderNo())) {
            throw new EsbException(ErrorConstant.PARAM_REQUIRED.getCode(), "语音认证信息参数为空");
        }
        int insert = authVoiceMapper.insert(entity);
        if (insert < 1) {
            throw new EsbException(ErrorConstant.AUTH_DAO_FAIL);
        }
        return entity;
    }

    /**
     * @param orderNo
     * @return com.mobanker.shanyidai.esb.model.entity.auth.AuthVoiceEntity
     * @description 根据订单号查询语音识别记录
     * @author Richard Core
     * @time 2017/2/16 14:41
     * @method findByOrderNo
     */
    @Override
    public AuthVoiceEntity findByOrderNo(String orderNo) {
        if (StringUtils.isBlank(orderNo)) {
            return null;
        }
        AuthVoiceEntity entity = authVoiceMapper.findByOrderNo(orderNo);
        return entity;
    }

    /**
     * @param entity
     * @return void
     * @description 更新语音识别进度记录
     * @author Richard Core
     * @time 2017/2/16 14:43
     * @method updateVoiceRecord
     */
    @Override
    public void updateVoiceRecord(AuthVoiceEntity entity) {
        if (entity == null || StringUtils.isBlank(entity.getOrderNo())) {
            throw new EsbException(ErrorConstant.PARAM_REQUIRED.getCode(), "语音认证信息参数为空");
        }
        int count = authVoiceMapper.updateByOrderNo(entity);
        if (count < 1) {
            throw new EsbException(ErrorConstant.AUTH_DAO_FAIL.getCode(), "更新语音识别进度失败");
        }
    }

    /**
     * @param resultDto
     * @return void
     * @description 更新语音解析Mq回调结果到语音识别进度中
     * @author Richard Core
     * @time 2017/2/16 17:08
     * @method saveVoiceTransResult
     */
    @Override
    public void saveVoiceTransResult(VoiceTranslateResultDto resultDto) {
        if (resultDto == null || StringUtils.isBlank(resultDto.getOrderNo())) {
            LOGGER.warn("语音解析结果mq回调信息为空");
            return;
        }
        AuthVoiceEntity entity = findByOrderNo(resultDto.getOrderNo());
        if (entity == null) {
            throw new EsbException(ErrorConstant.INVALID_VOICE_ORDERNO);
        }
        entity.setSkuNumber(resultDto.getSkuNumber()); // 唯一标示
        entity.setBillNo(resultDto.getBillNo()); // 流水号
        entity.setAudioId(resultDto.getAudioId()); // 音频ID
        entity.setVoiceText(resultDto.getResult()); // 内容返回
        // 是否转换成功0失败1成功2超时失败
        entity.setMqResultStatus(resultDto.getStatus());
        MQVoiceTransStatusEnum instance = MQVoiceTransStatusEnum.getInstance(resultDto.getStatus());
        switch (instance) {
            case SUCCESS:
                entity.setStatus(EsbVoiceAuthStatusEnum.CONTRASTING.getStatus());
                break;
            case TIMEOUT:
                entity.setStatus(EsbVoiceAuthStatusEnum.TRANS_TIMEOUT.getStatus());
                break;
            default:
                entity.setStatus(EsbVoiceAuthStatusEnum.TRANS_FAIL.getStatus());
        }
        Date mqCallTime = new Date();
        entity.setMqCallTime(mqCallTime);
        Date transBegin = entity.getUploadTime();
        if (transBegin != null) {
            entity.setTransDuration(mqCallTime.getTime() - transBegin.getTime());
        }
        this.updateVoiceRecord(entity);

    }



    /**
     * @param userId
     * @return AuthVoiceRecordDto
     * @description 查询用户语音识别进度
     * @author Richard Core
     * @time 2017/2/21 9:49
     * @method findVoiceAuthProcessByUserId
     */
    @Override
    public AuthVoiceRecordDto findVoiceAuthProcessByUserId(Long userId) {
        if (userId == null) {
            return null;
        }
        AuthVoiceEntity entity = authVoiceMapper.findLastByUserId(userId);
        //判断是否超时失败
        if (entity != null && StringUtils.isNoneBlank(entity.getStatus())) {
            EsbVoiceAuthStatusEnum instance = EsbVoiceAuthStatusEnum.getInstance(entity.getStatus());
            switch (instance) {
                case UPLOADING://("uploading", "语音正在上传"),
                case UPLOADED://("uploaded", "语音已上传，正在解析"),
                case CONTRASTING://("contrasting", "语音已解析完毕，正在对比"),
                    String cacheSysConfig = esbCommonBusiness.getCacheSysConfig(ZkConfigConstant.VOICE_AUTH_TIMEOUT.getZkValue());
                    if (StringUtils.isNotBlank(cacheSysConfig) && entity.getUploadTime() != null) {
                        long timeOut = Long.valueOf(cacheSysConfig);
                        long lastLong = (System.currentTimeMillis() - entity.getUploadTime().getTime()) / 1000;
                        if (lastLong > timeOut) {
                            entity.setStatus(EsbVoiceAuthStatusEnum.TIMEOUT_FAIL.getStatus());
                            authVoiceMapper.update(entity);
                        }
                    }
            }
        }
        AuthVoiceRecordDto recordDto = BeanUtil.cloneBean(entity, AuthVoiceRecordDto.class);
        return recordDto;
    }

    /**
     * @param userId
     * @return int
     * @description 获取用户当天语音验证次数
     * @author Richard Core
     * @time 2017/3/4 15:47
     * @method getAuthFaceTimesToday
     */
    @Override
    public int getAuthVoiceTimesToday(Long userId) {
        if (userId == null) {
            return 0;
        }
        List<AuthVoiceEntity> authList = authVoiceMapper.findTodayListByUserId(userId);
        if (authList == null || authList.isEmpty()) {
            return 0;
        }
        return authList.size();
    }

}
