/**
 * @Company
 * @Title: RestBaseFilter.java
 * @Package com.mobanker.shanyidai.esb.common.utils
 * @author hanty
 * @date 2017-2-4 上午11:49:24
 * @version V1.0
 */
package com.mobanker.shanyidai.esb.common.utils;

import com.alibaba.dubbo.common.URL;

/** 
 * @ClassName: DubboUtil 
 * @Description: dubbo中的一些工具类
 *  
 */
public class DubboUtil {

	private static final String REST = "rest";

	/** 
	* @Description: 根据dubbo的执行URL判断是否是Rest接口的URL
	* restURL形如： rest://172.28.174.1:26002/../.......
	* @author hanty
	* @date 2017-2-4 上午11:49:24
	* @param invokeUrl
	* @return  
	*/ 
	public static boolean isRest(URL invokeUrl){
		if(invokeUrl == null){
			return false;
		}
		return REST.equals(invokeUrl.getProtocol());
	}
}
