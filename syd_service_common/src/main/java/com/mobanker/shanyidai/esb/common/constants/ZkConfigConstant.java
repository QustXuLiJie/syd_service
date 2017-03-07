package com.mobanker.shanyidai.esb.common.constants;

import com.mobanker.shanyidai.esb.common.exception.EsbException;
import org.apache.commons.lang3.StringUtils;

/**
 * @author hantongyang
 * @description
 * @time 2017/1/5 20:36
 */
public enum ZkConfigConstant {

    //配置中心参数
    SYD_APP_VERSION("shanyidai_app_version", "1.0"), //app版本号
    SYSTEM_TIMEOUT("system.timeout", "600"), //系统过期时间
    //    SMS_SYSTEM_CODE("UZONE-JAVA"),//发送验证码 系统编号
    USER_REALNAME_URL("realname.url", ""), //实名认证URL
    USER_ZHIMAAUTH_URL("auth_zhima_url",""),//芝麻认证URL
    USER_ZHIMACHECK_URL("check_zhima_url",""),//用户是否授权芝麻URL
    USER_ZHIMA_SCORE_URL("query_zhima_url", ""), //用户芝麻分
    USER_EDU_URL("edu.url", ""), //学历认证URL
    VERIFY_CODE_LENGTH("verify_code_length", "4"), //验证码长度
    BANK_CARD_VERIFY_URL("bank_card_verify_url", ""), //银行卡认证接口地址
    PHONE_FIXED_CONSTRAST("phone_fixed_constrast", ""), //固话反查接口
    BORROW_GET_INFO("borrowInfo_get_info", ""), //获取借款详情接口地址
    REPAY_CW_URL("repay_cw_url", ""), //财务系统还款支付接口地址
    OTHER_REPAY_CW_URL("other_repay_cw_url", ""), //财务系统百度钱包和易宝还款支付接口地址
    BORROW_ORDER_MQ_REP_COUNT("borrow_order_mq_repetition_count", "3"), //订单MQ重试次数
    BASEDATA_RELATION_TYPE("basedata_relation_type","L1,L2"),//联系人关系类型默认参数

    SYD_ESB_SERVICE_LOG_GATE("esb_service_log_gate","1"), //	综合服务工程业务成功日志是否记录闸口
    SYD_ESB_HTTP_LOG_GATE("esb_http_log_gate", "1"), //综合服务工程访问第三方Http接口成功日志是否记录闸口
    SYD_ESB_DUBBO_LOG_GATE("esb_dubbo_log_gate", "1"), //综合服务工程访问第三方Dubbo接口成功日志是否记录闸口
    AUTH_URL_KDXF_SUBMIT_ORDER("auth_url_kdxf_submit_order", ""), //认证系统上传语音并向科大讯飞下单服务地址
    AUTH_URL_KDXF_QUERY_ORDER("auth_url_kdxf_query_order", ""), //认证系统查询语音结果服务地址

    VOICE_AUTH_REQURL("auth_voice_requrl",""),//语音识别上传接口地址
    VOICE_AUTH_TIMEOUT("auth_voice_timeout","60"),//语音识别超时失败时长，从上传到当前时间，单位秒
    FACE_VOICE_UPLOAD_REQURL("face_voice_upload_requrl",""),//人脸识别上传接口地址
    FACE_VOICE_DETECT_REQURL("face_voice_detect_requrl",""),//人脸识别防伪接口地址
    FACE_VOICE_VERIFY_REQURL("face_voice_verify_requrl",""),//人脸识别比对接口地址
    ;

    private String zkValue;
    private String defaultValue;

    ZkConfigConstant(String zkValue, String defaultValue) {
        this.zkValue = zkValue;
        this.defaultValue = defaultValue;
    }

    public String getZkValue() {
        return zkValue;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    /**
     * @description 根据枚举的Key获取对应的枚举
     * @author hantongyang
     * @time 2017/1/17 10:07
     * @method getByKey
     * @param key
     * @return com.mobanker.shanyidai.api.common.constant.ZkConfigConstant
     */
    public static ZkConfigConstant getByKey(String key){
        if(StringUtils.isBlank(key)){
            throw new EsbException(ErrorConstant.ENUM_ERROR);
        }
        for(ZkConfigConstant config : ZkConfigConstant.values()){
            if(config.getZkValue().equals(key)){
                return config;
            }
        }
        throw new EsbException(ErrorConstant.ENUM_ERROR);
    }
}
