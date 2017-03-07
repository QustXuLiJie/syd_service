/**
 * 
 */
package com.mobanker.shanyidai.esb.controller.v1_0_0;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mobanker.shanyidai.esb.common.constants.SydConstant;
import com.mobanker.shanyidai.esb.common.logger.LogManager;
import com.mobanker.shanyidai.esb.common.logger.Logger;
import com.mobanker.shanyidai.esb.common.packet.ResponseEntity;

/**
 * 测试类
 * 
 * @author chenjianping
 * @data 2016年12月16日
 */
@Controller
@RequestMapping(value = "test")
public class TestAction {
	private static final Logger log = LogManager.getLogger(TestAction.class);
	
	@RequestMapping(value = "/login", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ResponseEntity login(String userName, String password) {
		log.error("<==========登录测试成功=============>");
		return new ResponseEntity(SydConstant.RSP_STATUS_SUCCESS);
	}

}
