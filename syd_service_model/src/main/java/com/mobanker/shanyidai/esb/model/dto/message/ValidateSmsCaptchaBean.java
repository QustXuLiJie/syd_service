package com.mobanker.shanyidai.esb.model.dto.message;

import com.mobanker.shanyidai.esb.common.utils.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
public final class ValidateSmsCaptchaBean extends Entity {


	/**
	 * 验证码用途.<br>
	 * 注册：register<br>
	 * 忘记密码：forgetPwd<br>
	 * 修改手机号：modifyPhone<br>
	 * 借款：borrow<br>
	 * 微信绑定手机号：bindWchat<br>
	 * 活动: activity<br>
	 * 找回支付密码：findPayPwd<br>
	 */
	private String captchaUse;
	/**
	 * 手机号
	 */
	private String phone;
	/**
	 * 唯一编码
	 */
	private String captcha;

	public String getCaptchaUse() {
		return captchaUse;
	}

	public void setCaptchaUse(String captchaUse) {
		this.captchaUse = captchaUse;
	}

	public String getCaptcha() {
		return captcha;
	}

	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}
