package com.mobanker.shanyidai.dubbo.service.user;

import com.mobanker.shanyidai.dubbo.dto.user.UserContactDto;
import com.mobanker.shanyidai.esb.common.packet.ResponseEntity;

import java.util.List;
import java.util.Map;

/**
 * @author hantongyang
 * @description
 * @time 2016/12/28 15:39
 */
public interface UserContactDubboService {

    /**
     * @param dto
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @description 根据用户ID查询联系人信息
     * @author hantongyang
     * @time 2016/12/28 14:04
     * @method getContactByUserId
     */
    ResponseEntity getContactByUserId(UserContactDto dto);

    /**
     * @param dto
     * @return java.util.Map<java.lang.String,java.lang.String>
     * @description 新增联系人
     * @author hantongyang
     * @time 2016/12/28 15:15
     * @method addContact
     */
    ResponseEntity addContact(UserContactDto dto);

    /**
     * @param dto
     * @return java.util.Map<java.lang.String,java.lang.String>
     * @description 修改联系人信息
     * @author hantongyang
     * @time 2016/12/28 15:15
     * @method updateContact
     */
    ResponseEntity updateContact(UserContactDto dto);
}
