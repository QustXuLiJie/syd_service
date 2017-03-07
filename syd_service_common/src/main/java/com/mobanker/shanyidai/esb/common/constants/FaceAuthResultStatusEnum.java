package com.mobanker.shanyidai.esb.common.constants;

import com.mobanker.shanyidai.esb.common.exception.EsbException;
import org.apache.commons.lang3.StringUtils;

/**
 * @desc: 活体识别结果状态处理
 * @author: Richard Core
 * @create time: 2017/2/20 17:27
 */
public enum FaceAuthResultStatusEnum {
    SUCCESS("OK", "参数非UTF-8编码",null),
    ENCODING_ERROR("ENCODING_ERROR", "参数非UTF-8编码",ErrorConstant.FACE_AUTH_ENCODING_ERROR),
    DOWNLOAD_TIMEOUT("DOWNLOAD_TIMEOUT", "网络地址图片获取超时",ErrorConstant.FACE_AUTH_DOWNLOAD_TIMEOUT),
    DOWNLOAD_ERROR("DOWNLOAD_ERROR", "网络地址图片获取失败",ErrorConstant.FACE_AUTH_DOWNLOAD_ERROR),
    WRONG_LIVENESS_DATA("WRONG_LIVENESS_DATA", "liveness_data 出错",ErrorConstant.FACE_AUTH_WRONG_LIVENESS_DATA),
    INVALID_ARGUMENT("INVALID_ARGUMENT", "请求参数错误，具体原因见 reason 字段内容",ErrorConstant.FACE_AUTH_INVALID_ARGUMENT),
    NOT_FOUND("NOT_FOUND", "请求路径错误",ErrorConstant.FACE_AUTH_NOT_FOUND),
    INTERNAL_ERROR("INTERNAL_ERROR","服务器内部错误",ErrorConstant.FACE_AUTH_INTERNAL_ERROR);


    private String status;
    private String statusDesc;
    private ErrorConstant errorConstant;

    public static FaceAuthResultStatusEnum getInstance(String status) {
        if (StringUtils.isBlank(status)) {
            throw new EsbException(ErrorConstant.ENUM_ERROR);
        }
        for (FaceAuthResultStatusEnum faceAuthResultStatusEnum : FaceAuthResultStatusEnum.values()) {
            if (faceAuthResultStatusEnum == null) {
                continue;
            }
            if (faceAuthResultStatusEnum.getStatus().equals(status)) {
                return faceAuthResultStatusEnum;
            }
        }
        throw new EsbException(ErrorConstant.ENUM_ERROR);
    }


    FaceAuthResultStatusEnum(String status, String statusDesc, ErrorConstant errorConstant) {
        this.status = status;
        this.statusDesc = statusDesc;
        this.errorConstant = errorConstant;
    }

    public String getStatus() {
        return status;
    }

    public String getStatusDesc() {
        return statusDesc;
    }

    public ErrorConstant getErrorConstant() {
        return errorConstant;
    }
}
