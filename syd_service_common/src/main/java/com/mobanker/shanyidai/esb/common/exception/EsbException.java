/**
 * 
 */
package com.mobanker.shanyidai.esb.common.exception;

import com.mobanker.shanyidai.esb.common.constants.ErrorConstant;


/**
 * 业务异常处理类
 * 
 * @author chenjianping
 * @data 2016年12月15日
 */
public class EsbException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8768211064581546909L;

	/**
	 * 异常编码
	 */
	public final String errCode;

	/**
	 * 异常消息
	 */
	public final String message;

	public EsbException(Throwable e) {
		super(e);
		errCode = ErrorConstant.SYSTEM_FAIL.getCode();
		message = e.getMessage();
	}

	public EsbException(String errCode, String message) {
		super(message);
		this.errCode = errCode;
		this.message = message;
	}

	public EsbException(String errCode, String message, Throwable e) {
		super(message, e);
		this.errCode = errCode;
		this.message = message;
	}

	public EsbException(ErrorConstant returnCode) {
		this(returnCode.getCode(), returnCode.getDesc());
	}

	public EsbException(ErrorConstant returnCode, Throwable e) {
		this(returnCode.getCode(), returnCode.getDesc(), e);
	}

}
