package com.mobanker.shanyidai.esb.common.constants;

/**
 * @desc: 人脸识别置信度比对结果
 * @author: Richard Core
 * @create time: 2017/3/1 14:36
 */
public enum FaceVerifyEnum {
    COMPARESUCCES("comparesucces", "比对成功"),
    COMPAREFAILE("comparefaile", "比对失败"),
    COMPAREERROR("compareerror", "比对错误");
    private String verifyResult;
    private String desc;



    FaceVerifyEnum(String verifyResult, String desc) {
        this.verifyResult = verifyResult;
        this.desc = desc;
    }

    public String getVerifyResult() {
        return verifyResult;
    }

    public String getDesc() {
        return desc;
    }
}
