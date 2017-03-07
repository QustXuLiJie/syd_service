package com.mobanker.shanyidai.esb.common.constants;

/**
 * @desc: 人脸识别防Hack得分
 * @author: Richard Core
 * @create time: 2017/3/1 14:36
 */
public enum FaceHackEnum {
    HACKNORMAL("hacknormal", "正常"),
    HACKFRAUD("hackfraud", "欺诈"),
    HACKERROR("hackerror", "防HACK错误");
    private String hackResult;
    private String desc;



    FaceHackEnum(String hackStr, String desc) {
        this.hackResult = hackStr;
        this.desc = desc;
    }

    public String getHackResult() {
        return hackResult;
    }

    public String getDesc() {
        return desc;
    }
}
