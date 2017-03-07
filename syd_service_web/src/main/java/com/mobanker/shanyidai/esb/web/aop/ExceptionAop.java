/**
 * 
 */
package com.mobanker.shanyidai.esb.web.aop;

import com.mobanker.shanyidai.esb.common.constants.ErrorConstant;
import com.mobanker.shanyidai.esb.common.constants.SydConstant;
import com.mobanker.shanyidai.esb.common.exception.EsbException;
import com.mobanker.shanyidai.esb.common.packet.ResponseEntity;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 异常处理切面
 * 
 * @author chenjianping
 * @data 2016年12月15日
 */
public class ExceptionAop {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	/**
	 * 处理运行异常的切面
	 * */
	public Object deal(ProceedingJoinPoint pjp) throws Throwable {
		Object returnMessage = null;
		try {
			returnMessage = pjp.proceed();
		} catch (EsbException e1) {
			returnMessage = new ResponseEntity(SydConstant.RSP_STATUS_FAIL, e1.errCode + "", e1.message);
		} catch (Exception e2) {
			returnMessage = new ResponseEntity(SydConstant.RSP_STATUS_FAIL, ErrorConstant.SYSTEM_FAIL.getCode(), ErrorConstant.SERVICE_FAIL.getDesc());
		}
		return returnMessage;
	}
}
