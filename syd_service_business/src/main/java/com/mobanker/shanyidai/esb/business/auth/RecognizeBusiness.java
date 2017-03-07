package com.mobanker.shanyidai.esb.business.auth;

import com.mobanker.shanyidai.dubbo.dto.auth.AuthVoiceRecordDto;
import com.mobanker.shanyidai.dubbo.dto.auth.VoiceAuthDto;
import com.mobanker.shanyidai.dubbo.dto.auth.VoiceAuthResultDto;
import com.mobanker.shanyidai.dubbo.dto.auth.VoiceTranslateResultDto;
import com.mobanker.shanyidai.esb.common.constants.EsbVoiceAuthStatusEnum;
import com.mobanker.shanyidai.esb.common.packet.ResponseEntity;
import com.mobanker.shanyidai.esb.model.entity.auth.AuthVoiceEntity;

/**
 * @author liuhanqing
 * @description 识别相关业务
 * @time 2017/2/14.
 */
public interface RecognizeBusiness {

    /**
     * @param dto
     * @return com.mobanker.shanyidai.esb.common.packet.ResponseEntity
     * @description 语音识别接口
     * @author liuhanqing
     * @time 2017/2/09 15:05
     * @method voiceAuth
     */
    public VoiceAuthResultDto voiceAuth(VoiceAuthDto dto);

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
     * @param entity
     * @return com.mobanker.shanyidai.esb.model.entity.auth.AuthVoiceEntity
     * @description 保存语音识别记录
     * @author Richard Core
     * @time 2017/2/16 14:04
     * @method saveVoiceRecord
     */
    public AuthVoiceEntity saveVoiceRecord(AuthVoiceEntity entity);

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
    public void saveVoiceUploadRecord(VoiceAuthDto paramsDto, VoiceAuthResultDto resultDto, EsbVoiceAuthStatusEnum authProcess, Throwable e);
    /**
     * @param orderNo
     * @return com.mobanker.shanyidai.esb.model.entity.auth.AuthVoiceEntity
     * @description 根据订单号查询语音识别记录
     * @author Richard Core
     * @time 2017/2/16 14:41
     * @method findByOrderNo
     */
    public AuthVoiceEntity findByOrderNo(String orderNo);

    /**
     * @param entity
     * @return void
     * @description 更新语音识别进度记录
     * @author Richard Core
     * @time 2017/2/16 14:43
     * @method updateVoiceRecord
     */
    public void updateVoiceRecord(AuthVoiceEntity entity);

    /**
     * @param resultDto
     * @return void
     * @description 更新语音解析Mq回调结果到语音识别进度中
     * @author Richard Core
     * @time 2017/2/16 17:08
     * @method saveVoiceTransResult
     */
    void saveVoiceTransResult(VoiceTranslateResultDto resultDto);

    /**
     * @param userId
     * @return AuthVoiceRecordDto
     * @description 查询用户语音识别进度
     * @author Richard Core
     * @time 2017/2/21 9:49
     * @method findVoiceAuthProcessByUserId
     */
    AuthVoiceRecordDto findVoiceAuthProcessByUserId(Long userId);

    /**
     * @param userId
     * @return int
     * @description 获取用户当天语音验证次数
     * @author Richard Core
     * @time 2017/3/4 15:47
     * @method getAuthFaceTimesToday
     */
    public int getAuthVoiceTimesToday(Long userId);
}
