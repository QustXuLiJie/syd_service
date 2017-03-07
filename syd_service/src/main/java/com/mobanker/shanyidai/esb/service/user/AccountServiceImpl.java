package com.mobanker.shanyidai.esb.service.user;

import com.mobanker.cust.baseinfo.contract.params.BaseInfoParams;
import com.mobanker.shanyidai.dubbo.dto.user.UserLoginDto;
import com.mobanker.shanyidai.dubbo.dto.user.UserRegisterDto;
import com.mobanker.shanyidai.dubbo.service.user.AccountDubboService;
import com.mobanker.shanyidai.esb.business.user.UserBusiness;
import com.mobanker.shanyidai.esb.common.constants.ErrorConstant;
import com.mobanker.shanyidai.esb.common.packet.ResponseBuilder;
import com.mobanker.shanyidai.esb.common.packet.ResponseEntity;
import com.mobanker.shanyidai.esb.common.utils.BeanUtil;
import com.mobanker.shanyidai.esb.model.dto.user.UserLoginBean;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * @author Richard Core
 * @description 账户相关
 * @date 2016/12/22 10:55
 */
//@Component
//@Service("accountDubboService")
public class AccountServiceImpl implements AccountDubboService {

    @Autowired
    private UserBusiness userBusiness;

    //    @Autowired
//    private UserSessionBusiness userSessionBusiness;

    /**
     * @param dto
     * @return com.mobanker.shanyidai.esb.common.packet.ResponseEntity
     * @description 注册用户
     * @author Richard Core
     * @time 2016/12/22 18:07
     * @method userRegister
     */
    @Override
    public ResponseEntity userRegister(UserRegisterDto dto) {
//        return ResponseBuilder.normalResponse("hello world");
        try {
             /* 1：检测手机、验证码、请求IP */
            UserEsbConvert.checkRegistParam(dto);
		    // 2：检测用户电话 是否已注册过
		    // TODO: 2016/12/28 什么样的手机号可以注销 删除后再次注册
            userBusiness.checkUserInfoByPhone(dto.getPhone());
            /*4：创建登录账号*/
            BaseInfoParams userCreateparam = UserEsbConvert.getUserCreateparam(dto);
            Map<String, String> user = userBusiness.createUser(userCreateparam);
            return ResponseBuilder.normalResponse(user);
        } catch (Exception e) {
            return ResponseBuilder.errorResponse(ErrorConstant.REGISTER_ERROR, e, this.getClass().getSimpleName(), "userRegister");
        }
    }

    /**
     * @param dto
     * @return com.mobanker.shanyidai.esb.common.packet.ResponseEntity
     * @description 用户登录认证服务
     * @author Richard Core
     * @time 2016/12/22 18:07
     * @method userLogin
     */
    @Override
    public ResponseEntity userLogin(UserLoginDto dto) {
        try {
            //验证登录参数
            UserEsbConvert.checkLoginParam(dto);
            //参数封装
            UserLoginBean userLoginBean = BeanUtil.cloneBean(dto, UserLoginBean.class);
            //验证登录
            Map<String, String> result = userBusiness.userLogin(userLoginBean);

            //验证返回结果
            return ResponseBuilder.normalResponse(result);
        } catch (Exception e) {
            return ResponseBuilder.errorResponse(ErrorConstant.LOGIN_ERROR, e, this.getClass().getSimpleName(), "userLogin");
        }

    }




}
