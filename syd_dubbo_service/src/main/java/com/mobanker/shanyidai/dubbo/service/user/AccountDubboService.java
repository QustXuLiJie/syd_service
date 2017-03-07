package com.mobanker.shanyidai.dubbo.service.user;

import com.mobanker.shanyidai.dubbo.dto.user.*;
import com.mobanker.shanyidai.esb.common.packet.ResponseEntity;

/**
 * @author Richard Core
 * @description
 * @date 2016/12/22 10:01
 */
public interface AccountDubboService {
    /**
     * @param dto
     * @return com.mobanker.shanyidai.esb.common.packet.ResponseEntity
     * @description 注册用户
     * @author Richard Core
     * @time 2016/12/22 18:07
     * @method userRegister
     */
    public ResponseEntity userRegister(UserRegisterDto dto);

    /**
     * @param dto
     * @return com.mobanker.shanyidai.esb.common.packet.ResponseEntity
     * @description 用户登录认证服务
     * @author Richard Core
     * @time 2016/12/22 18:07
     * @method userLogin
     */
    public ResponseEntity userLogin(UserLoginDto dto);


}
