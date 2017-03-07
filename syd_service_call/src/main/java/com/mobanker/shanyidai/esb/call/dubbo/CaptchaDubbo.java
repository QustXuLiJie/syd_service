package com.mobanker.shanyidai.esb.call.dubbo;

import com.mobanker.message.contract.params.CaptchaTemplateWarpper;

/**
 * @author Richard Core
 * @description
 * @date 2016/12/28 9:22
 */
public interface CaptchaDubbo {
    /**
     * @param warpper
     * @return java.lang.String
     * @description 发送验证码
     * @author Richard Core
     * @time 2016/12/26 20:28
     * @method sendSmsCaptcha
     */
    public String sendSmsCaptcha(CaptchaTemplateWarpper warpper);

    /**
     * @param warpper
     * @return java.lang.String
     * @description 验证短信验证码
     * @author Richard Core
     * @time 2016/12/26 14:34
     * @method validateSmsCaptcha
     */
    public String validateSmsCaptcha(CaptchaTemplateWarpper warpper);
}
