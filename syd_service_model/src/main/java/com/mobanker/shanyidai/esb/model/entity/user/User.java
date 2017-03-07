package com.mobanker.shanyidai.esb.model.entity.user;

import com.mobanker.shanyidai.esb.common.utils.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = false)
public class User extends Entity {

	private static final long serialVersionUID = 6395554404636235238L;
	private Long userId;
	private String userName;
	private String sex;
	private String password;
	private String email;
	private String payPassword;
	private String question;
	private String answer;
	private Long loginTime;
	private String regIp;
	private Long regTime;
	private String upIp;
	private String lastIp;
	private Long lastTime;
	private Integer secondStatus;
	private Integer status;
	private String regFrom;
	private String channel;
	private String downloadChannel;
	private String phone;
	private String realName;

	private String product;
	private String promoterCode;// 地推编号
	private String version;//版本号
	//20160627 PHP端提注册时入users_info表ios设备字段，这里传参用
	private String regDeviceIdentify;
	/**
	 * 注册渠道来源，仅注册服务接口使用。 若该字段不为空，并且不同于channel，则以此渠道标识为准。
	 */
	private String micrositeChannel;
	
	 //==============以下参数为传参字段=================
	/**
	 * 用户请求cookie信息
	 * 风控需要的字段
	 */
	private String user_cookie;
	
	/**
	 * 用户请求头信息
	 * 风控需要的字段
	 */
	private String user_head;
	/**
	 * 邀请码
	 */
	private String invitationCode;
}
