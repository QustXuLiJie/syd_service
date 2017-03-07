/**
 *
 */
package com.mobanker.shanyidai.esb.common.constants;


/**
 * @author Richard Core
 * @description 异常或者返回码定义 闪宜贷综合服务 todo 范围待定
 * 1、返回码8位
 * 2、前两位00 为特殊返回码 00000000 为调用成功
 * 3、前两位76 为异常或者错误码
 * 4、固定保留 0-100 系统异常
 * 5、参数处理100-199  包括参数异常 数据库异常 服务器异常 解密错误
 * 6、用户 200-299 用户判断 商户判断 短信判断
 * 7、业务 300-399 借款 还款
 * 8、闪宜贷从400-1000
 * @date 2016/12/22 15:10
 */
public enum ErrorConstant {

    CODE_PREFIX("37", "CODE_PREFIX"),//ERROR_HEAD 异常开头


    //固定保留 0-100
    SUCCESS("00000000", "调用成功"),
    SYSTEM_FAIL(CODE_PREFIX.code + "000001", "系统错误请联系管理员！"),
    SERVICE_FAIL(CODE_PREFIX.code + "000002", "服务调用出错"),
    DB_TIMEOUT(CODE_PREFIX.code + "000003", "数据库连接超时"),
    CACHE_TIMEOUT(CODE_PREFIX.code + "000004", "缓存连接超时"),
    MQ_TIMEOUT(CODE_PREFIX.code + "000005", "消息队列连接超时"),
    DB_CONNECT_SPEND(CODE_PREFIX.code + "000006", "数据库连接数用尽"),
    SERVICE_VALID(CODE_PREFIX.code + "000007", "找不到服务"),
    PARAM_VALID(CODE_PREFIX.code + "000008", "参数异常"),
    PARAM_REQUIRED(CODE_PREFIX.code + "000009", "缺少必填参数"),
    PARAM_OUT_RANGE(CODE_PREFIX.code + "000010", "参数值不在取值范围"),
    SIGN_VERIFY_FAILED(CODE_PREFIX.code + "000011", "签名验证失败"),
    REQUEST_TIMEOUT(CODE_PREFIX.code + "000012", "请求超时"),
    UNAUTH_REQUEST(CODE_PREFIX.code + "000013", "无权调用"),
    INTER_VERSION_NONSUPPORT(CODE_PREFIX.code + "000014", "接口版本不支持"),
    LOGIN_TIME_OUT(CODE_PREFIX.code + "000015", "会话超时"),
    ERROR_TOKEN_EXPIRE(CODE_PREFIX.code + "000016", "登录授权码已过期"),
    ERROR_TOKEN_INVALID(CODE_PREFIX.code + "000017", "登录授权码无法识别"),
    ERROR_RESPONSE(CODE_PREFIX.code + "000018", "获取结果异常"),
    ERROR_RESTURL_FAILED(CODE_PREFIX.code + "000019", "接口URL为空"),
    ERROR_SERVICE_RESULT(CODE_PREFIX.code + "000020", "返回值转换失败"),
    ENUM_ERROR(CODE_PREFIX.code + "000021", "枚举实例转化失败"),
    ESB_RESPONSE_RESULT(CODE_PREFIX.code + "000022", "返回值类型异常"),

    //参数处理100-299  包括参数异常 数据库异常 服务器异常 解密错误
    PARAMS_ILLEGE(CODE_PREFIX.code + "000100", "参数错误"),
    PARAMS_VERISON(CODE_PREFIX.code + "000101", "应用版本号参数缺失!"),
    PARAMS_PRODUCT(CODE_PREFIX.code + "000102", "产品线参数缺失!"),
    PARAMS_CHANNEL(CODE_PREFIX.code + "000103", "渠道参数缺失!"),
    PARAMS_REQUESTIP(CODE_PREFIX.code + "000104", "请求源IP缺失!"),
    PARAMS_CAPTCHA(CODE_PREFIX.code + "000105", "验证码参数缺失!"),
    PARAMS_PHONE(CODE_PREFIX.code + "000106", "手机号参数缺失!"),
    PHONE_INVILID(CODE_PREFIX.code + "000107", "请输入正确的手机号!"),
    PARAMS_TEL(CODE_PREFIX.code + "000108", "电话参数缺失!"),
    TEL_INVILID(CODE_PREFIX.code + "000109", "请输入正确的电话号码!"),
    WRONG_PASSWORD(CODE_PREFIX.code + "000110", "密码错误!"),
    WRONG_VERIFYCODE(CODE_PREFIX.code + "000111", "验证码错误!"),
    CONFIG_DATA_NULL(CODE_PREFIX.code + "000112", "配置系统参数获取失败!"),
    BASE_ADD_PARAM_NULL(CODE_PREFIX.code + "000113", "添加渠道信息为空"),
    THREAD_NULL(CODE_PREFIX.code + "000114", "线程池获取失败!"),
    SAVE_DB_ERROR(CODE_PREFIX.code + "000115", "数据保存失败!"),

    //300-599 用户判断 商户判断 短信判断
    ERROR_EXIST_PHONE(CODE_PREFIX.code + "000300", "该手机号码已被注册!"),
    REGISTER_ERROR(CODE_PREFIX.code + "000301", "注册失败!"),
    PHONE_PASSWORD_INVALID(CODE_PREFIX.code + "000302", "账号或密码有误!"),
    CAPTCHA_USE_NULL(CODE_PREFIX.code + "000303", "验证码用途缺失!"),
    SMS_TEMPLATE_ID(CODE_PREFIX.code + "000304", "短信模板id缺失!"),
    SMS_SEND_FAIL(CODE_PREFIX.code + "000305", "验证码发送失败!"),
    GET_USER_INFO_ERROR(CODE_PREFIX.code + "000306", "获取用户信息失败!"),
    USER_REALNAME_ERROR(CODE_PREFIX.code + "000307", "实名认证失败!"),
    USER_EDU_ERROR(CODE_PREFIX.code + "000308", "学历认证失败!"),
    UPDATE_USER_INFO_ERROR(CODE_PREFIX.code + "000309", "更新用户信息失败!"),
    CAPTCHA_USE_INVALID(CODE_PREFIX.code + "000310", "不支持的验证码用途!"),
    LOGIN_ERROR(CODE_PREFIX.code + "000311", "登录失败!"),
    CHANGE_PWD_ERROR(CODE_PREFIX.code + "000312", "重置密码失败!"),
    FORGET_PWD_ERROR(CODE_PREFIX.code + "000313", "找回密码失败!"),
    CHANGE_PHONE_ERROR(CODE_PREFIX.code + "000314", "修改手机号失败!"),
    PHONE_UNMATCH(CODE_PREFIX.code + "000315", "手机号与预留手机号不匹配!"),
    PHONE_UNREGIST(CODE_PREFIX.code + "000316", "手机号没有注册过"),
    BANK_CARD_LIST_ERROR(CODE_PREFIX.code + "000317", "查询用户银行卡列表失败"),
    BANK_CARD_VERIFY(CODE_PREFIX.code + "000318", "银行卡认证失败"),
    BANK_CARD_NO_NULL(CODE_PREFIX.code + "000319", "银行卡号为空"),
    BANK_NAME_NULL(CODE_PREFIX.code + "000320", "发卡行为空"),
    ID_CARD_AND_PHONE_NULL(CODE_PREFIX.code + "000321", "身份证号和手机号至少传一个"),
    NO_REAL_NAME(CODE_PREFIX.code + "000322", "还没有进行过实名认证"),
    CARD_BIN_ERROR(CODE_PREFIX.code + "000323", "根据卡号查询发卡行信息出错"),
    BANK_CARD_TYPE_ERROR(CODE_PREFIX.code + "000324", "不支持的卡类型"),
    BANK_CARD_TYPE_NULL(CODE_PREFIX.code + "000325", "卡类型为空"),
    BANK_CARD_NO_PHONE_NULL(CODE_PREFIX.code + "000326", "银行卡号和手机号至少传一个"),
    BANK_CARD_NO_PHONE_ERROR(CODE_PREFIX.code + "000327", "根据手机号或者银行卡号查询银行卡信息"),
    BANK_INFO_NULL(CODE_PREFIX.code + "000328", "添加银行卡信息为空"),
    ERROR_AUTH_TEL(CODE_PREFIX.code + "000329", "联系电话认证失败"),
    ERROR_DATA_DICTIONARY_FAILED(CODE_PREFIX.code + "000330", "获取数据字典失败"),
    ERROR_AUTH_ZHIMAURL(CODE_PREFIX.code + "000331", "获取芝麻认证链接失败"),
    ERROR_AUTH_CHEXKZHIMAURL(CODE_PREFIX.code + "000332", "检查芝麻认证授权失败"),
    ERROR_AUTH_ZHIMA_SCORE(CODE_PREFIX.code + "000333", "获取芝麻分失败"),
    ERROR_GET_ZHIMA_SCORE(CODE_PREFIX.code + "000334", "获取芝麻信息异常"),
    ERROR_SAVE_ZHIMA_SCORE(CODE_PREFIX.code + "000335", "保存芝麻信息异常"),
    ERROR_AUTH_ZHIMA(CODE_PREFIX.code + "000336", "芝麻认证失败"),
    ERROR_USER_DEFAULT_CREDIT_BANK(CODE_PREFIX.code + "000337", "获取用户入账银行卡失败"),
    ERROR_SET_USER_DEFAULT_BANK(CODE_PREFIX.code + "000338", "设置用户入账银行卡失败"),
    ID_CARD_NULL(CODE_PREFIX.code + "000339", "身份证号为空"),
    GET_USER_INFO_BY_CARDNO_ERROR(CODE_PREFIX.code + "000340", "根据身份证号获取用户信息失败!"),

    //400-499 文件相关异常
    UPLOAD_FILE_ERROR(CODE_PREFIX.code + "000400", "上传文件失败"),
    FILE_TYPE_NOTNULL(CODE_PREFIX.code + "000401", "上传文件类型不能为空"),
    QUERY_FILE_ERROR(CODE_PREFIX.code + "000404","查询文件失败"),
    QUERY_KDXF_ORDER_ID_NULL(CODE_PREFIX.code + "000405","语音订单编号为空"),
    QUERY_KDXF_ORDER_ERROR(CODE_PREFIX.code + "000406","获取语音识别解析结果失败"),
    SUBMIT_KDXF_ORDER_ERROR(CODE_PREFIX.code + "000407","上传语音识别信息失败"),
    VOICE_AUTH_ERROR(CODE_PREFIX.code + "000408","调用语音识别基础服务异常"),
    SYS_MESSAGE_ERROR(CODE_PREFIX.code + "000409","调用语音识别基础服务异常"),


    //600-899 借还款相关异常
    ERROR_BORROW_CHECK_QL_BORROWING(CODE_PREFIX.code + "000600", "获取用户是否在前隆借款中失败"),
    ERROR_BORROW_GET_INFO(CODE_PREFIX.code + "000601", "获取借款详情失败"),
    PAY_BACK_ERROR(CODE_PREFIX.code + "000602", "查询还款信息失败"),
    BORROW_PARAM_NULL(CODE_PREFIX.code + "000603", "查询借款信息参数为空"),
    BORROW_QUERY_TYPE_NULL(CODE_PREFIX.code + "000604", "查询借款类型参数为空"),
    BORROW_INFO_ERROR(CODE_PREFIX.code + "000605", "查询借款信息失败"),
    BORROW_INFO_EMPTY(CODE_PREFIX.code + "000606", "没有借款信息"),
    ERROR_BORROW_NID_NULL(CODE_PREFIX.code + "000607", "借款单号为空"),
    ERROR_PRODUCT_ID_NULL(CODE_PREFIX.code + "000608", "产品模板为空"),
    ERROR_BORROW_DAYS(CODE_PREFIX.code + "000609", "借款单异常"),
    ERROR_ADD_ORDER(CODE_PREFIX.code + "000610", "保存借款单异常"),
    REPAY_PARAM_ERROR(CODE_PREFIX.code + "000611", "还款参数为空"),
    REPAY_TYPE_NULL(CODE_PREFIX.code + "000612", "还款参数产品类型为空"),
    REPAY_CHANNEL_NULL(CODE_PREFIX.code + "000613", "还款参数产品渠道为空"),
    REPAY_REPAY_TYPE_NULL(CODE_PREFIX.code + "000614", "还款参数支付方式为空"),
    REPAY_ERROR(CODE_PREFIX.code + "000615", "还款失败"),
    ERROR_BORROW_ADD_ORDER_RECORD(CODE_PREFIX.code + "000616", "借款单流水保存失败"),
    ERROR_BORROW_CALLBACK_RECORD(CODE_PREFIX.code + "000617", "借款单回调流水保存失败"),
    ERROR_BORROW_UPDATE_ORDER_RECORD(CODE_PREFIX.code + "000618", "借款单流水更新失败"),
    ERROR_BORROW_ADD_OLD_RECORD(CODE_PREFIX.code + "000619", "借款单旧订单中心保存失败"),
    pay_result_error(CODE_PREFIX.code + "000620", "查询支付结果失败"),
    ERROR_BORROW_PRODUCER_ORDER_LOG(CODE_PREFIX.code + "000621", "订单中心MQ调用流水保存失败"),

    //app版本更新
    ERROR_GET_APPVERSION(CODE_PREFIX.code + "000650", "app版本更新失败"),
    ACTIVATION_DAO_FAIL(CODE_PREFIX.code + "000651", "设备激活保存或更新失败"),
    ACTIVATION_FAIL(CODE_PREFIX.code + "000652", "设备激活失败"),
    //语音识别
    AUTH_DAO_FAIL(CODE_PREFIX.code + "000700", "语音识别记录保存或更新失败"),
    INVALID_VOICE_ORDERNO(CODE_PREFIX.code + "000701", "无效的语音识别订单号"),
    VOICE_AUTH_PROCESS_FAILD(CODE_PREFIX.code + "000702", "语音识别进度查询失败"),
    VOICE_AUTH_TODAY_TIMES_FAILD(CODE_PREFIX.code + "000703", "语音识别当日认证阈值查询失败"),

    //活体识别 730-750
    FACE_AUTH_UPLOAD_FAILD(CODE_PREFIX.code + "000730", "人脸识别上传失败"),
    FACE_AUTH_HACK_DETECT_FAILD(CODE_PREFIX.code + "000731", "人脸识别活体防伪认证失败"),
    FACE_AUTH_WATERMARK_VERIFY_FAILD(CODE_PREFIX.code + "000732", "人脸识别水印照片比对失败"),
    FACE_AUTH_DATA_ID_FAILD(CODE_PREFIX.code + "000733", "人脸识别订单编号无效"),
    REAL_INFO_NULL(CODE_PREFIX.code + "000734", "实名认证信息为空"),
    UPDATE_AUTH_FACE_RECORD(CODE_PREFIX.code + "000735", "人脸识别进度记录失败"),
    FACE_AUTH_ENCODING_ERROR(CODE_PREFIX.code+"000736","参数非UTF-8编码"),
    FACE_AUTH_DOWNLOAD_TIMEOUT(CODE_PREFIX.code+"000737","网络地址图片获取超时"),
    FACE_AUTH_DOWNLOAD_ERROR(CODE_PREFIX.code+"000738","网络地址图片获取失败"),
    FACE_AUTH_WRONG_LIVENESS_DATA(CODE_PREFIX.code+"000739","liveness_data 出错"),
    FACE_AUTH_INVALID_ARGUMENT(CODE_PREFIX.code+"000740","请求参数错误"),
    FACE_AUTH_NOT_FOUND(CODE_PREFIX.code+"000741","请求路径错误"),
    FACE_AUTH_INTERNAL_ERROR(CODE_PREFIX.code+"000742","服务器内部错误"),
    FACE_AUTH_PROCESS_ERROR(CODE_PREFIX.code+"000743","人脸识别进度查询失败"),

    //基础数据
    ERROR_JOBTYPE_GET_INFO(CODE_PREFIX.code + "000800", "获取职业类型列表失败"),
    ERROR_RELATION_GET_INFO(CODE_PREFIX.code + "000801", "获取联系人关系列表失败"),
    ERROR_EDUCATION_GET_INFO(CODE_PREFIX.code + "000802", "获取学历列表失败"),

    ;


    private String code;//异常或者返回码
    private String desc;//描述

    private ErrorConstant(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public java.lang.String getCode() {
        return code;
    }

    public java.lang.String getDesc() {
        return desc;
    }

}
