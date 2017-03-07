package com.mobanker.shanyidai.esb.common.constants;

import com.mobanker.shanyidai.esb.common.exception.EsbException;
import org.apache.commons.lang3.StringUtils;

/**
 * @desc: 语音解析结果状态
 * @author: Richard Core
 * @create time: 2017/2/16 16:58
 */
public enum MQVoiceTransStatusEnum {
    SUCCESS("1", "解析成功"),//0失败1成功2超时失败
    FAIL("0", "失败"),
    TIMEOUT("2","超时失败");

    private String status;
    private String desc;

    public static MQVoiceTransStatusEnum getInstance(String status) {
        if (StringUtils.isBlank(status)) {
            throw new EsbException(ErrorConstant.ENUM_ERROR);
        }
        for (MQVoiceTransStatusEnum mqVoiceTransStatusEnum : MQVoiceTransStatusEnum.values()) {
            if (mqVoiceTransStatusEnum == null) {
                continue;
            }
            if (mqVoiceTransStatusEnum.getStatus().equals(status)) {
                return mqVoiceTransStatusEnum;
            }
        }
        throw new EsbException(ErrorConstant.ENUM_ERROR);
    }

    MQVoiceTransStatusEnum(String status, String desc) {
        this.status = status;
        this.desc = desc;
    }
    public String getStatus() {
        return status;
    }

    public String getDesc() {
        return desc;
    }
}
