package com.mobanker.shanyidai.esb.call.http.impl;

import com.alibaba.fastjson.JSONObject;
import com.mobanker.framework.utils.HttpClientUtils;
import com.mobanker.shanyidai.dubbo.dto.auth.VoiceAuthResultDto;
import com.mobanker.shanyidai.esb.call.http.VoiceAuthHttp;
import com.mobanker.shanyidai.esb.call.util.MultipartFormDataClient;
import com.mobanker.shanyidai.esb.call.util.MultipartFormDataFile;
import com.mobanker.shanyidai.esb.common.constants.ErrorConstant;
import com.mobanker.shanyidai.esb.common.exception.EsbException;
import com.mobanker.shanyidai.esb.common.logger.LogManager;
import com.mobanker.shanyidai.esb.common.logger.Logger;
import com.mobanker.shanyidai.esb.common.packet.ResponseBuilder;
import com.mobanker.shanyidai.esb.common.packet.ResponseEntity;
import com.mobanker.shanyidai.esb.common.utils.BeanUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liuhanqing
 * @description 语音识别
 * @time 2017/2/8.
 */
@Service
public class VoiceAuthHttpImpl implements VoiceAuthHttp {
    public static final Logger LOGGER = LogManager.getSlf4jLogger(VoiceAuthHttpImpl.class);
    public static final String VOICE_UPLOAD_ORDER_SUCCESS_STATUS = "1";
    public static final String VOICE_UPLOAD_ORDER_SUCCESS_TYPE = "4";

    /**
     * @param restUrl
     * @param orderId
     * @return com.mobanker.shanyidai.esb.common.packet.ResponseEntity
     * @description 获取语音解析结果
     * @author Richard Core
     * @time 2017/2/10 10:25
     * @method findVoiceOrder
     */
    @Override
    public ResponseEntity findVoiceOrder(String restUrl, String orderId) {
        if (StringUtils.isBlank(restUrl)) {
            throw new EsbException(ErrorConstant.ERROR_RESTURL_FAILED);
        }
        if (StringUtils.isBlank(orderId)) {
            throw new EsbException(ErrorConstant.QUERY_KDXF_ORDER_ID_NULL);
        }
        Map<String, String> params = new HashMap<String, String>();
        params.put("queryOrder", orderId);
        try {
            String response = HttpClientUtils.doPost(restUrl, params);
            if (StringUtils.isBlank(response)) {
                throw new EsbException(ErrorConstant.QUERY_KDXF_ORDER_ERROR);
            }
            ResponseEntity responseEntity = JSONObject.parseObject(response, ResponseEntity.class);
            if (!ResponseBuilder.isSuccess(responseEntity)) {
                LOGGER.error("获取语音验证结果失败" + responseEntity.getError() + ":" + responseEntity.getMsg());
                responseEntity.setError(ErrorConstant.QUERY_KDXF_ORDER_ERROR.getCode());
            }
            return responseEntity;
        } catch (Exception e) {
            throw new EsbException(ErrorConstant.QUERY_KDXF_ORDER_ERROR.getCode(), e.getMessage());
        }
    }

    @Override
    public VoiceAuthResultDto voiceAuth(String reqUrl, Map<String, String> params, MultipartFormDataFile file) {
        if (StringUtils.isBlank(reqUrl)) {
            throw new EsbException(ErrorConstant.ERROR_RESTURL_FAILED);
        }

        MultipartFormDataClient client = new MultipartFormDataClient(reqUrl);
        List<MultipartFormDataFile> files = new ArrayList<MultipartFormDataFile>();
        files.add(file);
        LOGGER.info(JSONObject.toJSONString(params));
        String rep = null;
        try {
            rep = client.doPost(params, files, false);
            if (StringUtils.isBlank(rep)) {
                throw new EsbException(ErrorConstant.SUBMIT_KDXF_ORDER_ERROR);
            }

            ResponseEntity responseEntity = JSONObject.parseObject(rep, ResponseEntity.class);
            if (!ResponseBuilder.isSuccess(responseEntity)) {
                LOGGER.error("语音验证接口失败" + responseEntity.getError() + ":" + responseEntity.getMsg());
                throw new EsbException(ErrorConstant.SUBMIT_KDXF_ORDER_ERROR.getCode(), responseEntity.getMsg());
            }
            if (responseEntity.getData() == null) {
                throw new EsbException(ErrorConstant.SUBMIT_KDXF_ORDER_ERROR.getCode(), "语音上传失败，请检查您的网络连接，或稍后重试");
            }
            JSONObject resultObject = JSONObject.parseObject(rep);
            JSONObject dataObject = resultObject.getJSONObject("data");
            dataObject.put("transerialsId", resultObject.getString("transerialsId"));
            dataObject.put("channel", resultObject.getString("channel"));
            VoiceAuthResultDto resultDto = BeanUtil.parseJson(dataObject.toString(), VoiceAuthResultDto.class);
            if (!VOICE_UPLOAD_ORDER_SUCCESS_STATUS.equals(resultDto.getOrderStatus()) ||
                    !VOICE_UPLOAD_ORDER_SUCCESS_TYPE.equals(resultDto.getOrderType())) {
                LOGGER.error("育婴上传的类型或者状态失败orderStatus=" + resultDto.getOrderStatus() + ",orderType=" + resultDto.getOrderType());
                throw new EsbException(ErrorConstant.SUBMIT_KDXF_ORDER_ERROR.getCode(), "语音上传失败，请检查您的网络连接，或稍后重试");
            }
            return resultDto;
        } catch (Exception e) {
            throw new EsbException(ErrorConstant.VOICE_AUTH_ERROR.getCode(), e.getMessage(), e);
        }

    }
}
