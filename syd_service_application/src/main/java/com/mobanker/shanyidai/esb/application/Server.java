/**
 * 
 */
package com.mobanker.shanyidai.esb.application;

import com.mobanker.framework.server.MobankerApplication;
import com.mobanker.framework.server.netty.annotation.NettyBootstrap;

/**
 * 应用服务启动入口（业务服务层）
 * 
 * @author chenjianping
 * @data 2016年12月15日
 */
@NettyBootstrap(springApplicationContext = "classpath:/mobanker-syd-application.xml", springServletContext = "classpath:/mobanker-syd-servlet.xml")
public class Server {

	/**
	 * @author chenjianping
	 * @data 2016年12月15日
	 * @Version V1.0
	 * @param args
	 */
	public static void main(String[] args) {
		MobankerApplication.run(Server.class, args);
	}

}
