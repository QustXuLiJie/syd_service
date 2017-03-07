package com.mobanker.shanyidai.dubbo.service.auth;

import com.mobanker.shanyidai.dubbo.dto.auth.VoiceAuthDto;
import com.mobanker.shanyidai.esb.common.annotation.NoEELog;
import com.mobanker.shanyidai.esb.common.packet.ResponseEntity;

/**
 * @author liuhanqing
 * @description 识别相关业务，语音识别和人脸识别
 * @time 2017/2/14.
 */
@NoEELog
public interface RecognizeDubboService {

    /**
     * @param paramsDto
     * @return ResponseEntity
     * @description 语音识别
     * @author liuhanqing
     * @time 2017/2/09 17:59
     * @method voiceAuth
     */
    public ResponseEntity voiceAuth(VoiceAuthDto paramsDto);

    /**
     * @param orderId
     * @return com.mobanker.shanyidai.esb.common.packet.ResponseEntity
     * @description 获取语音解析结果
     * @author Richard Core
     * @time 2017/2/10 10:46
     * @method findVoiceOrder
     */
    public ResponseEntity findVoiceOrder(String orderId);

    /**
     * @param orderNo
     * @return com.mobanker.shanyidai.esb.model.entity.auth.AuthVoiceEntity
     * @description 根据订单号查询语音识别记录
     * @author Richard Core
     * @time 2017/2/16 14:41
     * @method findVoiceAuthProcessByOrderNo
     */
    public ResponseEntity findVoiceAuthProcessByOrderNo(String orderNo);

    /**
     * @param userId
     * @return com.mobanker.shanyidai.esb.common.packet.ResponseEntity
     * @description 查询用户语音识别进度
     * @author Richard Core
     * @time 2017/2/21 9:49
     * @method findVoiceAuthProcessByUserId
     */
    public ResponseEntity findVoiceAuthProcessByUserId(Long userId);

    /**
     * @param userId
     * @return int
     * @description 获取用户当天语音验证次数
     * @author Richard Core
     * @time 2017/3/4 15:47
     * @method getAuthVoiceTimesToday
     */
    public ResponseEntity getAuthVoiceTimesToday(Long userId);
}
