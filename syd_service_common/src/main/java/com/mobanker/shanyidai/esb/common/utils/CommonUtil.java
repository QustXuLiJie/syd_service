package com.mobanker.shanyidai.esb.common.utils;


import com.alibaba.fastjson.JSONObject;
import com.mobanker.framework.utils.HttpClientUtils;
import com.mobanker.shanyidai.esb.common.constants.ErrorConstant;
import com.mobanker.shanyidai.esb.common.exception.EsbException;
import com.mobanker.shanyidai.esb.common.packet.ResponseEntity;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonUtil {
    public static final Logger logger = LoggerFactory.getLogger(CommonUtil.class);

    /**
     * @param entity
     * @return void
     * @description 基本参数验证 包括 产品 渠道 请求ip 版本号
     * @author Richard Core
     * @time 2016/12/24 11:10
     * @method checkBaseParam
     */
    public static void checkBaseParam(Entity entity) {
        if (entity == null) {
            throw new EsbException(ErrorConstant.PARAMS_ILLEGE);
        }
        if (StringUtils.isBlank(entity.getChannel())) {
            throw new EsbException(ErrorConstant.PARAMS_CHANNEL);
        }
        if (StringUtils.isBlank(entity.getProduct())) {
            throw new EsbException(ErrorConstant.PARAMS_PRODUCT);
        }
        if (StringUtils.isBlank(entity.getVersion())) {
            throw new EsbException(ErrorConstant.PARAMS_VERISON);
        }
        if (StringUtils.isBlank(entity.getIp())) {
            throw new EsbException(ErrorConstant.PARAMS_REQUESTIP);
        }
    }


    /**
     * 验证电话号码 匹配格式：11位手机号码
     */
    public static void checkPhone(String str) throws EsbException {
        if (StringUtils.isEmpty(str)) {
            logger.error("手机号为空");
            throw new EsbException(ErrorConstant.PARAMS_PHONE);
        }
        String regex = "^(1(20|45|47)\\d{8})|(1(3|5|8)\\d{9})|(17\\d{9})$";
        if (!match(regex, str)) {
            logger.error("手机号格式错误",str);
            throw new EsbException(ErrorConstant.PHONE_INVILID);
        }
    }

    /**
     * 验证电话号码 匹配格式：8位数字，其余逻辑未定
     */
    public static void checkTel(String str) throws EsbException {
        if (StringUtils.isEmpty(str)) {
            throw new EsbException(ErrorConstant.PARAMS_TEL);
        }
        String regex = "^\\d{8}$";
        if (!match(regex, str)) {
            throw new EsbException(ErrorConstant.TEL_INVILID);
        }
    }

    /**
     * 校验密码
     */
    public static void checkPassword(String pwd) throws EsbException {
        if (pwd == null || pwd.length() != 32) {
            logger.error("密码为空或者格式错误");
            throw new EsbException(ErrorConstant.WRONG_PASSWORD);
        }
    }



    /**
     * 身份证验证
     *
     * @param str 待验证的字符串
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public static boolean isCardId(String str) {
        if (StringUtils.isEmpty(str)) {
            return false;
        }
        String regex = "([0-9]{17}([0-9]|X))|([0-9]{15})";
        return match(regex, str);
    }

    /**
     * @description 判断字符串是否都是数字
     * @author hantongyang
     * @time 2017/1/5 17:17
     * @method isDigit
     * @param strNum
     * @return boolean
     */
    public static boolean isDigit(String strNum) {
        if(StringUtils.isBlank(strNum)){
            return false;
        }
        String regex = "[0-9]{1,}";
        return match(regex, strNum);
    }

    /**
     * @param regex 正则表达式字符串
     * @param str   要匹配的字符串
     * @return 如果str 符合 regex的正则表达式格式,返回true, 否则返回 false;
     */
    private static boolean match(String regex, String str) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }


    /**
     * 版本号转换成数字
     *
     * @return
     * @author: liuyafei
     * @date 创建时间：2016年10月10日
     * @version 1.0
     * @parameter
     */
    public static int transVersionToNum(String version) {
        int num = 0;
        String[] msg = version.split("\\.");
        int len = msg.length;
        if (len >= 3) {
            num += Integer.parseInt(msg[2]);
        }
        if (len >= 2) {
            num += Integer.parseInt(msg[1]) * 10;
        }
        if (len >= 1) {
            num += Integer.parseInt(msg[0]) * 100;
        }
        return num;
    }

    /**
     * @description 验证Rest接口URL和参数是否为空
     * @author hantongyang
     * @time 2017/1/5 20:14
     * @method checkRest
     * @param restUrl
     * @param params
     * @return void
     */
    public static void checkRest(String restUrl, Map<String, String> params){
        if(StringUtils.isBlank(restUrl)){
            throw new EsbException(ErrorConstant.ERROR_RESTURL_FAILED);
        }
        if(params == null || params.isEmpty()){
            throw new EsbException(ErrorConstant.PARAM_REQUIRED);
        }
    }

    /**
     * @description 验证参数是否为空，并执行rest接口
     * @author hantongyang
     * @time 2017/1/5 20:25
     * @method rest
     * @param restUrl
     * @param params
     * @param error
     * @return com.mobanker.shanyidai.esb.common.packet.ResponseEntity
     */
    public static ResponseEntity doPost(String restUrl, Map<String, String> params, Map<String, String> header, ErrorConstant error){
        CommonUtil.checkRest(restUrl, params);
        try {
            if(header != null && !header.isEmpty()){
                HttpClientUtils.setHeader(header);
            }
            String response = HttpClientUtils.doPost(restUrl, params);
            if(StringUtils.isBlank(response)){
                throw new EsbException(error);
            }
            ResponseEntity responseEntity = JSONObject.parseObject(response, ResponseEntity.class);
            //如果接口返回值显示调用成功，但是error为空，则将SUCCESS的Code值赋给error
            if(responseEntity != null && StringUtils.isBlank(responseEntity.getError())){
                responseEntity.setError(ErrorConstant.SUCCESS.getCode());
            }else if(responseEntity != null && !ErrorConstant.SUCCESS.getCode().equals(responseEntity.getError())){
                logger.warn("======调用Http post类型服务异常", restUrl + "：" + responseEntity.getError() + "：" + responseEntity.getMsg());
                responseEntity.setError(error.getCode());
            }
            return responseEntity;
        } catch (Exception e) {
            throw new EsbException(error, e);
        }
    }

    /**
     * @description 验证参数是否为空，并执行rest接口
     * @author hantongyang
     * @time 2017/1/5 20:25
     * @method rest
     * @param restUrl
     * @param params
     * @param error
     * @return com.mobanker.shanyidai.esb.common.packet.ResponseEntity
     */
    public static ResponseEntity doGet(String restUrl, Map<String, String> params, ErrorConstant error){
        CommonUtil.checkRest(restUrl, params);
        try {
            String response = HttpClientUtils.doGet(restUrl, params);
            if(StringUtils.isBlank(response)){
                throw new EsbException(error);
            }
            ResponseEntity responseEntity = JSONObject.parseObject(response, ResponseEntity.class);
            //如果接口返回值显示调用成功，但是error为空，则将SUCCESS的Code值赋给error
            if(responseEntity != null && StringUtils.isBlank(responseEntity.getError())){
                responseEntity.setError(ErrorConstant.SUCCESS.getCode());
            }else{
                logger.warn("======调用Http get类型服务异常", restUrl + "：" + responseEntity.getError() + "：" + responseEntity.getMsg());
            }
            return responseEntity;
        } catch (Exception e) {
            throw new EsbException(error, e);
        }
    }


    /**
     * @param e
     * @return java.lang.String
     * @description 获取堆栈信息
     * @author Richard Core
     * @time 2017/2/18 16:35
     * @method getStackTrace
     */
    public static String getStackTrace(Throwable e) {
        if (e == null) {
            return null;
        }
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        e.printStackTrace(new PrintWriter(buf, true));
        return buf.toString();
    }
}
