package com.mobanker.shanyidai.esb.common.constants;

import com.mobanker.shanyidai.esb.common.exception.EsbException;
import org.apache.commons.lang3.StringUtils;

/**
 * @author hantongyang
 * @description
 * @time 2017/2/28 11:58
 */
public enum BusinessFlowEnum {
    //账号相关
    LOGIN("login", "bsFlowLoginMapper", "登陆", "BsFlowLogin"),
    REGISTER("register", "bsFlowRegisterMapper", "注册", "BsFlowRegister"),
    UPDATE_PHONE("updatePhone", "bsFlowUpdatePhoneMapper", "修改手机号", "BsFlowUpdatePhone"),
    UPDATE_PASSWD("setPassword", "bsFlowUpdatePasswdMapper", "修改密码", "BsFlowUpdatePasswd"),
    FORGET_PASSWORD("forgetPassword", "bsFlowUpdatePasswdMapper", "忘记密码", "BsFlowUpdatePasswd"),
    //语音识别
    VOICE_AUTH("voiceAuth", "bsFlowVoiceMapper", "语音认证", "BsFlowVoice"),
    VOICE_PROCESS("getVoiceProcessing", "bsFlowVoiceMapper", "语音认证进度", "BsFlowVoice"),
    VOICE_FIND_RESULT("findVoiceResult", "bsFlowVoiceMapper", "语音结果查询", "BsFlowVoice"),
    //活体识别
    FACE_AUTH("faceAuth", "bsFlowFaceMapper", "人脸识别", "BsFlowFace"),
    FACE_PROCESS("getFaceProcessing", "bsFlowFaceMapper", "人脸识别进度", "BsFlowFace"),
    //用户相关
    CONTART_AUTH("addContact", "bsFlowContactJobMapper", "联系人信息认证", "BsFlowContactJob"),
    JOB_AUTH("addJob", "bsFlowContactJobMapper", "工作信息认证", "BsFlowContactJob"),
    //实名认证相关
    REALNAME_AUTH("addRealName", "bsFlowRealNameMapper", "实名认证", "BsFlowRealName"),
    REALNAME_FIND("getRealName", "bsFlowRealNameMapper", "获取实名认证信息", "BsFlowRealName"),
    COMPLETENESS("getCompleteness", "bsFlowRealNameMapper", "认证进度", "BsFlowRealName"),
    //银行卡相关
    BANKCARD_AUTH("addBankCard", "bsFlowBankCardMapper", "添加银行卡", "BsFlowBankCard"),
    BANKCARD_SETPAYCARD("setPayCard", "bsFlowBankCardMapper", "设置默认银行卡", "BsFlowBankCard"),
    //借款相关
    BORROW_CANBORROW("canBorrow", "bsFlowBorrowMapper", "是否能借款", "BsFlowBorrow"),
    BORROW_SYDBORROW("sydBorrowing", "bsFlowBorrowMapper", "是否在闪宜贷借款中", "BsFlowBorrow"),
    BORROW_ADDBORROW("addBorrow", "bsFlowBorrowMapper", "我要借款", "BsFlowBorrow"),
    //还款相关
    REPAY_CANREPAY("canRepay", "bsFlowRepayMapper", "是否能还款", "BsFlowRepay"),
    REPAY_ADDONEKEY("addOnekeyRepay", "bsFlowRepayMapper", "还款（一键还款）", "BsFlowRepay"),
    REPAY_ADDBAIDU("addBaiduPay", "bsFlowRepayMapper", "还款（百度支付）", "BsFlowRepay"),
    REPAY_ADDWECHAT("addWechatRepay", "bsFlowRepayMapper", "还款（微信支付）", "BsFlowRepay"),
    ;

    private String method;
    private String daoName;
    private String desc;
    private String entityName;

    BusinessFlowEnum(String method, String daoName, String desc, String entityName) {
        this.method = method;
        this.daoName = daoName;
        this.desc = desc;
        this.entityName = entityName;
    }

    public String getMethod() {
        return method;
    }

    public String getDaoName() {
        return daoName;
    }

    public String getDesc() {
        return desc;
    }

    public String getEntityName() {
        return entityName;
    }

    /**
     * @description 根据方法名获取枚举
     * @author hantongyang
     * @time 2017/2/28 17:01
     * @method getBusinessFlowEnum
     * @param method
     * @return com.mobanker.shanyidai.esb.common.constants.BusinessFlowEnum
     */
    public static BusinessFlowEnum getBusinessFlowEnum(String method){
        if (StringUtils.isBlank(method)) {
            throw new EsbException(ErrorConstant.ENUM_ERROR);
        }
        for (BusinessFlowEnum businessFlowEnum : BusinessFlowEnum.values()) {
            if (businessFlowEnum == null) {
                continue;
            }
            if (businessFlowEnum.getMethod().equals(method)) {
                return businessFlowEnum;
            }
        }
        throw new EsbException(ErrorConstant.ENUM_ERROR);
    }
}