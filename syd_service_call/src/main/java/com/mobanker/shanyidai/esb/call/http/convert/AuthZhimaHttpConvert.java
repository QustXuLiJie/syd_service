package com.mobanker.shanyidai.esb.call.http.convert;

import com.mobanker.shanyidai.dubbo.dto.auth.ZhimaAuthParamDto;
import com.mobanker.shanyidai.esb.common.constants.ErrorConstant;
import com.mobanker.shanyidai.esb.common.exception.EsbException;

import java.util.HashMap;
import java.util.Map;


/**
 * @desc: 芝麻认证参数封装和验证
 * @author: xulijie
 * @create time: 2017/1/22 20:21
 */
public class AuthZhimaHttpConvert {
    /**
     * @param zhimaAuthParamDto
     * @return java.util.Map<java.lang.String,java.lang.String>
     * @description 验证和封装芝麻验证参数
     * @author xulijie
     * @time 2017/1/22 20:19
     * @method checkAndMapZhimaAuthParams
     */
    public static Map<String, String> checkAndMapZhimaAuthParams(ZhimaAuthParamDto zhimaAuthParamDto) {
        if (zhimaAuthParamDto == null) {
            throw new EsbException(ErrorConstant.ERROR_AUTH_ZHIMA);
        }
        Map<String, String> params = new HashMap<String, String>();
        params.put("name", zhimaAuthParamDto.getName());//用户真实姓名
        params.put("certNo", zhimaAuthParamDto.getCertNo());//用户身份证号
        params.put("code", zhimaAuthParamDto.getCode());//用户code唯一标示uuid
        params.put("channel", zhimaAuthParamDto.getChannel());//类别：app/microsite
        params.put("sences", zhimaAuthParamDto.getSences());//场景shanyidai、shoujidai、Uzu
        params.put("pageType", zhimaAuthParamDto.getPageType());//页面授权提供 页面展示方式h5
        return params;
    }
}
