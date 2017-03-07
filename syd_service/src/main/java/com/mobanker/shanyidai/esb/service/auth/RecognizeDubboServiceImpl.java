package com.mobanker.shanyidai.esb.service.auth;

import com.github.pagehelper.StringUtil;
import com.mobanker.shanyidai.dubbo.dto.auth.AuthVoiceRecordDto;
import com.mobanker.shanyidai.dubbo.dto.auth.VoiceAuthDto;
import com.mobanker.shanyidai.dubbo.dto.auth.VoiceAuthResultDto;
import com.mobanker.shanyidai.dubbo.service.auth.RecognizeDubboService;
import com.mobanker.shanyidai.esb.business.auth.RecognizeBusiness;
import com.mobanker.shanyidai.esb.business.log.EsbServiceLogBusiness;
import com.mobanker.shanyidai.esb.common.constants.ErrorConstant;
import com.mobanker.shanyidai.esb.common.constants.EsbVoiceAuthStatusEnum;
import com.mobanker.shanyidai.esb.common.packet.ResponseBuilder;
import com.mobanker.shanyidai.esb.common.packet.ResponseEntity;
import com.mobanker.shanyidai.esb.common.utils.BeanUtil;
import com.mobanker.shanyidai.esb.model.entity.auth.AuthVoiceEntity;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author liuhanqing
 * @description 识别相关业务
 * @time 2017/2/14.
 */
public class RecognizeDubboServiceImpl implements RecognizeDubboService {

    @Resource
    RecognizeBusiness recognizeBusiness;
    @Resource
    private EsbServiceLogBusiness esbServiceLogBusiness;

    /**
     * @param paramsDto
     * @return ResponseEntity
     * @description 语音识别
     * @author liuhanqing
     * @time 2017/2/9 18:31
     * @method voiceAuth
     */

    @Override
    public ResponseEntity voiceAuth(VoiceAuthDto paramsDto) {
        Date funBeginTime = new Date();
        if (paramsDto == null || StringUtil.isEmpty(paramsDto.getDuration()) || StringUtil.isEmpty(paramsDto.getFileName())
                || StringUtil.isEmpty(paramsDto.getFileSize())) {
            return ResponseBuilder.errorResponse(ErrorConstant.PARAM_REQUIRED, this.getClass().getSimpleName(), "voiceAuth");
        }

        VoiceAuthDto cloneBean = null;
        VoiceAuthResultDto resultDto = null;
        try {
            cloneBean = BeanUtil.cloneBean(paramsDto, VoiceAuthDto.class);
            cloneBean.setFile(null);
            resultDto = recognizeBusiness.voiceAuth(paramsDto);
            esbServiceLogBusiness.saveEsbServiceSuccessLog(this.getClass(), "voiceAuth", funBeginTime, resultDto, cloneBean);
            //保存语音识别记录
            recognizeBusiness.saveVoiceUploadRecord(paramsDto, resultDto, EsbVoiceAuthStatusEnum.UPLOADED, null);
            return ResponseBuilder.normalResponse(resultDto);
        } catch (Exception e) {
            //保存语音识别记录
            recognizeBusiness.saveVoiceUploadRecord(paramsDto, resultDto, EsbVoiceAuthStatusEnum.UPLOAD_FAIL,e);
            esbServiceLogBusiness.saveEsbServiceErrorLog(this.getClass(), "voiceAuth", funBeginTime, resultDto, cloneBean, e);
            return ResponseBuilder.errorResponse(ErrorConstant.SUBMIT_KDXF_ORDER_ERROR, e, this.getClass().getSimpleName(), "voiceAuth");
        }

    }

    /**
     * @param userId
     * @return com.mobanker.shanyidai.esb.common.packet.ResponseEntity
     * @description 查询用户语音识别进度
     * @author Richard Core
     * @time 2017/2/21 9:49
     * @method findVoiceAuthProcessByUserId
     */
    @Override
    public ResponseEntity findVoiceAuthProcessByUserId(Long userId) {
        if (userId == null) {
            return ResponseBuilder.errorResponse(ErrorConstant.INVALID_VOICE_ORDERNO, this.getClass().getSimpleName(), "findVoiceAuthProcessByUserId");
        }
        try {
            AuthVoiceRecordDto resultDto = recognizeBusiness.findVoiceAuthProcessByUserId(userId);
            return ResponseBuilder.normalResponse(resultDto);
        } catch (Exception e) {
            return ResponseBuilder.errorResponse(ErrorConstant.VOICE_AUTH_PROCESS_FAILD, e, this.getClass().getSimpleName(), "findVoiceAuthProcessByUserId");
        }
    }

    /**
     * @param orderNo
     * @return com.mobanker.shanyidai.esb.model.entity.auth.AuthVoiceEntity
     * @description 根据订单号查询语音识别记录
     * @author Richard Core
     * @time 2017/2/16 14:41
     * @method findVoiceAuthProcessByOrderNo
     */
    @Override
    public ResponseEntity findVoiceAuthProcessByOrderNo(String orderNo) {
        if (StringUtils.isBlank(orderNo)) {
            return null;
        }
        try {
            AuthVoiceEntity entity = recognizeBusiness.findByOrderNo(orderNo);
            AuthVoiceRecordDto authVoiceRecordDto = BeanUtil.cloneBean(entity, AuthVoiceRecordDto.class);

            return ResponseBuilder.normalResponse(authVoiceRecordDto);
        } catch (Exception e) {
            return ResponseBuilder.errorResponse(ErrorConstant.VOICE_AUTH_PROCESS_FAILD, e, this.getClass().getSimpleName(), "findVoiceAuthProcessByOrderNo");
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
        ResponseEntity responseEntity = null;
        try {
            responseEntity = recognizeBusiness.findVoiceOrder(orderId);
        } catch (Exception e) {
            return ResponseBuilder.errorResponse(ErrorConstant.QUERY_KDXF_ORDER_ERROR, e, this.getClass().getSimpleName(), "findVoiceOrder");
        }
        return responseEntity;
    }
    /**
     * @param userId
     * @return int
     * @description 获取用户当天语音验证次数
     * @author Richard Core
     * @time 2017/3/4 15:47
     * @method getAuthVoiceTimesToday
     */
    @Override
    public ResponseEntity getAuthVoiceTimesToday(Long userId) {
        try {
            int timesToday = recognizeBusiness.getAuthVoiceTimesToday(userId);
            return ResponseBuilder.normalResponse(timesToday);
        } catch (Exception e) {
            return ResponseBuilder.errorResponse(ErrorConstant.VOICE_AUTH_TODAY_TIMES_FAILD, e, this.getClass().getSimpleName(), "getAuthVoiceTimesToday");
        }
    }
}
