package com.mobanker.shanyidai.esb.model.dto.message;

import com.mobanker.shanyidai.esb.common.utils.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
public final class SmsMesageBean extends Entity {

	/**
	 * 唯一编码
	 */
	private String flagId;
	/**
	 * 消息模板ID. <br>
	 * 取值参考枚举类：TemplateId<br>
	 */
	private String templateId;
	/**
	 * 手机号
	 */
	private String phone;
	/**
	 * 系统ID
	 */
	private String systemId;
	/**
	 * 系统编码
	 */
	private String systemCode;


}
