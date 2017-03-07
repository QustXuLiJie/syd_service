package com.mobanker.shanyidai.dubbo.service.user;

import com.mobanker.shanyidai.esb.common.packet.ResponseEntity;

public interface UserSessionService {
	public ResponseEntity deleteUserSessionByCode(String code);
	
	public ResponseEntity deleteUserSessionByUserId(String userId);
	
	public ResponseEntity getUserSessionBy(String code);
	
	public ResponseEntity getUserSessionBy(String code, String channel);
	
	/**
	 * 更新用户Session时间为updateTime所表示的时间.<br>
	 * 
	 * @param primaryKey
	 *            主键标识.<br>
	 * @param updateTime
	 *            当前时间毫秒数.<br>
	 * @return
	 */
	public ResponseEntity updateUserSessionTime(Long primaryKey, Long updateTime);
}
