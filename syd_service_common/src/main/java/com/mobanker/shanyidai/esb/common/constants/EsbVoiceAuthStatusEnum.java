package com.mobanker.shanyidai.esb.common.constants;

import com.mobanker.shanyidai.esb.common.exception.EsbException;
import org.apache.commons.lang3.StringUtils;

/**
 * @desc: 语音识别订单状态
 * @author: Richard Core
 * @create time: 2017/2/13 20:01
 */
public enum EsbVoiceAuthStatusEnum {
    UPLOAD_FAIL("upload_fail", "上传语音失败"),
    UPLOADING("uploading", "语音正在上传"),
    UPLOADED("uploaded", "语音已上传，正在解析"),
    TRANS_FAIL("trans_fail", "语音解析失败"),
    TRANS_TIMEOUT("trans_timeout", "语音解析超时"),
    CONTRASTING("contrasting", "语音已解析完毕，正在对比"),
    SUCCESS("success", "语音对比成功"),
    MANUAL_SUCCESS("manual_success", "讯飞异常，批量成功"),
    FAIL("fail", "语音对比不通过"),
    TIMEOUT_FAIL("timeout_fail", "语音对比超时失败"),
    NOT_EXIST("not_exist", "没有记录");

    private String status;
    private String desc;

    EsbVoiceAuthStatusEnum(String status, String desc) {
        this.status = status;
        this.desc = desc;
    }
    public static EsbVoiceAuthStatusEnum getInstance(String status) {
        if (StringUtils.isBlank(status)) {
            throw new EsbException(ErrorConstant.ENUM_ERROR);
        }
        for (EsbVoiceAuthStatusEnum voiceOrderStatusEnum : EsbVoiceAuthStatusEnum.values()) {
            if (voiceOrderStatusEnum == null) {
                continue;
            }
            if (voiceOrderStatusEnum.getStatus().equals(status)) {
                return voiceOrderStatusEnum;
            }
        }
        throw new EsbException(ErrorConstant.ENUM_ERROR);
    }
    public String getStatus() {
        return status;
    }

    public String getDesc() {
        return desc;
    }
}
