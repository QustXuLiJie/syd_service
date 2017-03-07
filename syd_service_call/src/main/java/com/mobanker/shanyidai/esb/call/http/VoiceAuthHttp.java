package com.mobanker.shanyidai.esb.call.http;

import com.mobanker.shanyidai.dubbo.dto.auth.VoiceAuthResultDto;
import com.mobanker.shanyidai.esb.call.util.MultipartFormDataFile;
import com.mobanker.shanyidai.esb.common.packet.ResponseEntity;

import java.util.Map;

/**
 * @author liuhanqing
 * @description 语音识别
 * @time 2017/2/8.
 */
public interface VoiceAuthHttp {
    /**
     * @param restUrl
     * @param orderId
     * @return com.mobanker.shanyidai.esb.common.packet.ResponseEntity
     * @description 获取语音解析结果
     * @author Richard Core
     * @time 2017/2/10 10:25
     * @method findVoiceOrder
     */
    public ResponseEntity findVoiceOrder(String restUrl, String orderId);
    /**
     * @description 语音识别
     * @author liuhanqing
     * @time 2017/2/8. 20:51
     * @method voiceAuth
     * @param reqUrl
     * @param params
     * @return java.lang.String
     */
    public VoiceAuthResultDto voiceAuth(String reqUrl, Map<String, String> params, MultipartFormDataFile file);
}

