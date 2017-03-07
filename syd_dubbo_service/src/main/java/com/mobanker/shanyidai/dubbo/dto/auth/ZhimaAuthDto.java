package com.mobanker.shanyidai.dubbo.dto.auth;

import com.mobanker.shanyidai.esb.common.packet.ResponseEntity;
import com.mobanker.shanyidai.esb.common.utils.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
/**
 * @author xulijie
 * @version 1.0
 * @description 芝麻认证返回实体DTO
 * @date 创建时间：2017/1/22 14:39
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
public class ZhimaAuthDto extends Entity{
//    private String channel;// "channel": "qianlong"
    private String authUrl;//返回的地址
    private String authStatus;//授权状态，-2:未处理 -1:已处理 0:授权成功 1:身份证校验成功 2:身份证校验失败 3:手机号不匹配
    private String expired;//1 过期 ，0未过期
    private String openId;//授权码
    private String certNo;//身份证号
    private String name ;//姓名
    private String sences;//场景
    private String authTime;//授权时间
    private String expireTime;// 授权过期时间
    private String isOld;//新用户 还是老用户


}
