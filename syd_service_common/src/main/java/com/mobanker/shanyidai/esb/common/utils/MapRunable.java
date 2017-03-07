/**
* @Company
* @Title: MapRunable.java 
* @Package com.mobanker.shanyidai.esb.common.utils
* @author hantongyang
* @date 2017-2-7 11:34:03
* @version V1.0   
*/ 
package com.mobanker.shanyidai.esb.common.utils;

import java.util.Map;

/** 
 * @ClassName: MapRunable 
 * @Description: 可以传递map参数的执行方法
 *  
 */
public abstract class MapRunable implements Runnable {

	protected Map<String,Object> map;
	
	public MapRunable(Map<String,Object> map){
		this.map = map;
	}
	
}
