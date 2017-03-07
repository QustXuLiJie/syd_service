package com.mobanker.shanyidai.esb.business.user;

import com.mobanker.shanyidai.esb.model.dto.user.UserContactBean;

import java.util.List;
import java.util.Map;

/**
 * @author hantongyang
 * @description
 * @time 2016/12/28 13:50
 */
public interface UserContactBusiness {

    /**
     * @param bean
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @description 根据用户ID查询联系人信息
     * @author hantongyang
     * @time 2016/12/28 14:04
     * @method getContactByUserId
     */
    List<Map<String, String>> getContactByUserId(UserContactBean bean);

    /**
     * @description 新增联系人
     * @author hantongyang
     * @time 2016/12/28 15:15
     * @method addContact
     * @param bean
     * @return java.util.Map<java.lang.String,java.lang.String>
     */
    Map<String, String> addContact(UserContactBean bean);

    /**
     * @description 修改联系人信息
     * @author hantongyang
     * @time 2016/12/28 15:15
     * @method updateContact
     * @param bean
     * @return java.util.Map<java.lang.String,java.lang.String>
     */
    Map<String, String> updateContact(UserContactBean bean);
}
