package com.mobanker.shanyidai.esb.call.http.impl;

import com.alibaba.fastjson.JSONObject;
import com.mobanker.framework.utils.HttpClientUtils;
import com.mobanker.shanyidai.dubbo.dto.auth.FaceAuthResultDto;
import com.mobanker.shanyidai.dubbo.dto.auth.FaceAuthUploadParamDto;
import com.mobanker.shanyidai.dubbo.dto.auth.WatermarkVerifyParamDto;
import com.mobanker.shanyidai.esb.call.http.FaceAuthHttp;
import com.mobanker.shanyidai.esb.call.http.convert.AuthHttpConvert;
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
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @desc:
 * @author: Richard Core
 * @create time: 2017/2/20 14:36
 */
@Service
public class FaceAuthHttpImpl implements FaceAuthHttp {
    public static final Logger LOGGER = LogManager.getSlf4jLogger(FaceAuthHttpImpl.class);

    /**
     * @param reqUrl
     * @param paramsDto
     * @param file
     * @return com.mobanker.shanyidai.dubbo.dto.auth.FaceAuthResultDto
     * @description 人脸识别上传服务
     * @author Richard Core
     * @time 2017/2/21 15:25
     * @method faceAuthUpload
     */
    @Override
    public FaceAuthResultDto faceAuthUpload(String reqUrl, FaceAuthUploadParamDto paramsDto, MultipartFile file) {
        //验证参数
        Map<String, String> params = getUploadParams(reqUrl, paramsDto);
        //文件流处理
        List<MultipartFormDataFile> files = getMultipartFormDataFiles(file);
        MultipartFormDataClient client = new MultipartFormDataClient(reqUrl);
        String rep = null;
        try {
            rep = client.doPost(params, files, false);
            FaceAuthResultDto resultDto = getFaceAuthUploadResultDto(rep);
            return resultDto;
        } catch (Exception e) {
            throw new EsbException(ErrorConstant.FACE_AUTH_UPLOAD_FAILD.getCode(), e.getMessage(), e);
        }

    }

    /**
     * @param rep
     * @return com.mobanker.shanyidai.dubbo.dto.auth.FaceAuthResultDto
     * @description 语音识别上传结果处理
     * @author Richard Core
     * @time 2017/2/21 15:18
     * @method getFaceAuthUploadResultDto
     */
    private FaceAuthResultDto getFaceAuthUploadResultDto(String rep) {
        if (StringUtils.isBlank(rep)) {
            throw new EsbException(ErrorConstant.FACE_AUTH_UPLOAD_FAILD);
        }
        ResponseEntity responseEntity = JSONObject.parseObject(rep, ResponseEntity.class);
        if (!ResponseBuilder.isSuccess(responseEntity)) {
            LOGGER.error("人脸识别上传文件失败" + responseEntity.getError() + ":" + responseEntity.getMsg());
            throw new EsbException(ErrorConstant.FACE_AUTH_UPLOAD_FAILD.getCode(), responseEntity.getMsg());
        }
        if (responseEntity.getData() == null) {
            throw new EsbException(ErrorConstant.FACE_AUTH_UPLOAD_FAILD.getCode(), "人脸识别上传失败，请检查您的网络连接，或稍后重试");
        }

        return getFaceAuthResultDto(rep);
    }

    /**
     * @param reqUrl
     * @param paramsDto
     * @return java.util.Map<java.lang.String,java.lang.String>
     * @description 验证参数
     * @author Richard Core
     * @time 2017/2/21 14:33
     * @method getUploadParams
     */
    private Map<String, String> getUploadParams(String reqUrl, FaceAuthUploadParamDto paramsDto) {
        if (StringUtils.isBlank(reqUrl)) {
            throw new EsbException(ErrorConstant.ERROR_RESTURL_FAILED);
        }
//        if (paramsDto == null || StringUtils.isBlank(paramsDto.getFilePath())) {
//            throw new EsbException(ErrorConstant.PARAM_REQUIRED);
//        }
        Map<String, String> params = new HashMap<String, String>();
        params.put("fileName", paramsDto.getFilePath());
        params.put("encoding", AuthHttpConvert.FILE_ENCODING);
        return params;
    }

    /**
     * @param file
     * @return java.util.List<com.mobanker.shanyidai.esb.call.util.MultipartFormDataFile>
     * @description 文件流处理
     * @author Richard Core
     * @time 2017/2/21 14:33
     * @method getMultipartFormDataFiles
     */
    private List<MultipartFormDataFile> getMultipartFormDataFiles(MultipartFile file) {
        MultipartFormDataFile formDataFile = null;
        try {
            formDataFile = new MultipartFormDataFile(AuthHttpConvert.VOICE_UPLOAD_FIELDNAME, file.getOriginalFilename()
                    , AuthHttpConvert.FACE_UPLOAD_CONTENTTYPE, file.getInputStream());
        } catch (Exception e) {
            throw new EsbException(ErrorConstant.SYSTEM_FAIL.getCode(), "文件流读取失败，请检查您的网络环境，稍后重试");
        }
        List<MultipartFormDataFile> files = new ArrayList<MultipartFormDataFile>();
        files.add(formDataFile);
        return files;
    }


    /**
     * @param restUrl
     * @param livenessDataId
     * @return com.mobanker.shanyidai.dubbo.dto.auth.FaceAuthResultDto
     * @description 活体识别-活体防伪
     * @author Richard Core
     * @time 2017/2/22 14:19
     * @method faceAuthHackDetect
     */
    @Override
    public FaceAuthResultDto faceAuthHackDetect(String restUrl, String livenessDataId) {
        if (StringUtils.isBlank(restUrl)) {
            throw new EsbException(ErrorConstant.ERROR_RESTURL_FAILED);
        }
        if (StringUtils.isBlank(livenessDataId)) {
            throw new EsbException(ErrorConstant.FACE_AUTH_DATA_ID_FAILD);
        }
        Map<String, String> params = new HashMap<String, String>();
        params.put("liveness_data_id", livenessDataId);
        params.put("encoding", AuthHttpConvert.FILE_ENCODING);
        try {
            String response = HttpClientUtils.doPost(restUrl, params);
            if (StringUtils.isBlank(response)) {
                throw new EsbException(ErrorConstant.FACE_AUTH_HACK_DETECT_FAILD.getCode(), "认证系统没有返回防伪结果");
            }
            ResponseEntity responseEntity = JSONObject.parseObject(response, ResponseEntity.class);
            if (!ResponseBuilder.isSuccess(responseEntity)) {
                LOGGER.error("人脸识别活体防伪失败" + responseEntity.getError() + ":" + responseEntity.getMsg());
                throw new EsbException(ErrorConstant.FACE_AUTH_HACK_DETECT_FAILD.getCode(), responseEntity.getMsg());
            }
            if (responseEntity.getData() == null) {
                throw new EsbException(ErrorConstant.FACE_AUTH_HACK_DETECT_FAILD.getCode(), "人脸识别活体防伪，请检查您的网络连接，或稍后重试");
            }
            return getFaceAuthResultDto(response);
        } catch (Exception e) {
            throw new EsbException(ErrorConstant.FACE_AUTH_HACK_DETECT_FAILD.getCode(), e.getMessage());
        }
    }

    /**
     * @param response
     * @return com.mobanker.shanyidai.dubbo.dto.auth.FaceAuthResultDto
     * @description 封装人脸识别返回结果
     * @author Richard Core
     * @time 2017/2/22 14:12
     * @method getFaceAuthResultDto
     */
    private FaceAuthResultDto getFaceAuthResultDto(String response) {
        JSONObject resultObject = JSONObject.parseObject(response);
        JSONObject dataObject = JSONObject.parseObject(resultObject.getString("data"));
        dataObject.put("transerialsId", resultObject.getString("transerialsId"));
        dataObject.put("channel", resultObject.getString("channel"));
        FaceAuthResultDto faceAuthResultDto = BeanUtil.parseJson(dataObject.toJSONString(), FaceAuthResultDto.class);
        faceAuthResultDto.setLivenessDataId(dataObject.getString("liveness_data_id"));
        faceAuthResultDto.setRequestId(dataObject.getString("request_id"));
        faceAuthResultDto.setHackScore(dataObject.getString("boolHackScore"));
        faceAuthResultDto.setScore(dataObject.getString("boolHackScore"));
        faceAuthResultDto.setVerifyScore(dataObject.getString("boolVerifyScore"));
        faceAuthResultDto.setImageId(dataObject.getString("image_id"));
        return faceAuthResultDto;
    }

    /**
     * @param restUrl
     * @param paramDto
     * @return com.mobanker.shanyidai.dubbo.dto.auth.FaceAuthResultDto
     * @description 活体数据与水印照片比对
     * @author Richard Core
     * @time 2017/2/22 17:21
     * @method faceAuthWatermarkVerify
     */
    @Override
    public FaceAuthResultDto faceAuthWatermarkVerify(String restUrl, WatermarkVerifyParamDto paramDto) {
        if (StringUtils.isBlank(restUrl)) {
            throw new EsbException(ErrorConstant.ERROR_RESTURL_FAILED);
        }
        if (paramDto == null || StringUtils.isBlank(paramDto.getLivenessDataId())) {
            throw new EsbException(ErrorConstant.FACE_AUTH_DATA_ID_FAILD);
        }
        if (StringUtils.isBlank(paramDto.getRealName()) || StringUtils.isBlank(paramDto.getIdCardNo())) {
            throw new EsbException(ErrorConstant.REAL_INFO_NULL);
        }
//        paramDto.setIdCardNo("370284199112043914");
//        paramDto.setRealName("刘汉卿");
        Map<String, String> params = new HashMap<String, String>();
        params.put("liveness_data_id", paramDto.getLivenessDataId());
        params.put("id_number", paramDto.getIdCardNo());
        params.put("name", paramDto.getRealName());
        params.put("encoding", AuthHttpConvert.FILE_ENCODING);
        try {
            String response = HttpClientUtils.doPost(restUrl, params);
            if (StringUtils.isBlank(response)) {
                throw new EsbException(ErrorConstant.FACE_AUTH_WATERMARK_VERIFY_FAILD.getCode(), "认证系统没有返回水印照片比对结果");
            }
            ResponseEntity responseEntity = JSONObject.parseObject(response, ResponseEntity.class);
            if (!ResponseBuilder.isSuccess(responseEntity)) {
                LOGGER.error("人脸识别水印照片比对失败" + responseEntity.getError() + ":" + responseEntity.getMsg());
                throw new EsbException(ErrorConstant.FACE_AUTH_WATERMARK_VERIFY_FAILD.getCode(), responseEntity.getMsg());
            }
            if (responseEntity.getData() == null) {
                throw new EsbException(ErrorConstant.FACE_AUTH_WATERMARK_VERIFY_FAILD.getCode(), "人脸识别水印照片比对失败，请检查您的网络连接，或稍后重试");
            }
            return getFaceAuthResultDto(response);
        } catch (Exception e) {
            throw new EsbException(ErrorConstant.FACE_AUTH_WATERMARK_VERIFY_FAILD.getCode(), e.getMessage());
        }
    }
}
