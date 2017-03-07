package com.mobanker.shanyidai.esb.call.dubbo;

import com.mobanker.cust.contactinfo.contract.params.ContactInfoParams;

import java.util.List;
import java.util.Map;

/**
 * @description 联系人相关服务接口
 * @author hantongyang
 * @time 2016-12-28 11:06
 */
public interface ContactInfoDubbo {

    /**
     * @description 根据类型查询用户对应的联系人信息
     * @author hantongyang
     * @time 2016/12/28 11:43
     * @method getContactInfoByUserId
     * @param param
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.String>>
     */
    List<Map<String, String>> getContactInfoByUserId(ContactInfoParams param);

    /**
     * @description 新增联系人信息
     * @author hantongyang
     * @time 2016/12/28 14:23
     * @method addContactInfo
     * @param param
     * @return java.util.Map<java.lang.String,java.lang.String>
     */
    Map<String, String> addContactInfo(ContactInfoParams param);

    /**
     * @description 修改联系人信息
     * @author hantongyang
     * @time 2016/12/28 14:24
     * @method updateContactInfo
     * @param param
     * @return java.util.Map<java.lang.String,java.lang.String>
     */
    Map<String, String> updateContactInfo(ContactInfoParams param);
}
