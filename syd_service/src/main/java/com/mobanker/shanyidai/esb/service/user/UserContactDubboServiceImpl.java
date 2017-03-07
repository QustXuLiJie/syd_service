package com.mobanker.shanyidai.esb.service.user;

import com.mobanker.shanyidai.dubbo.dto.user.UserContactDto;
import com.mobanker.shanyidai.dubbo.service.user.UserContactDubboService;
import com.mobanker.shanyidai.esb.business.user.UserContactBusiness;
import com.mobanker.shanyidai.esb.common.constants.ErrorConstant;
import com.mobanker.shanyidai.esb.common.exception.EsbException;
import com.mobanker.shanyidai.esb.common.packet.ResponseBuilder;
import com.mobanker.shanyidai.esb.common.packet.ResponseEntity;
import com.mobanker.shanyidai.esb.common.utils.BeanUtil;
import com.mobanker.shanyidai.esb.model.dto.user.UserContactBean;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author hantongyang
 * @description
 * @time 2016/12/28 15:41
 */
public class UserContactDubboServiceImpl implements UserContactDubboService {

    @Resource
    private UserContactBusiness userContactBusiness;

    /**
     * @param dto
     * @return com.mobanker.shanyidai.esb.common.packet.ResponseEntity
     * @description 根据用户ID查询联系人信息
     * @author hantongyang
     * @time 2016/12/28 15:41
     * @method getContactByUserId
     */
    @Override
    public ResponseEntity getContactByUserId(UserContactDto dto) {
        if (dto == null || dto.getUserId() == null || StringUtils.isBlank(dto.getType())) {
            return ResponseBuilder.errorResponse(ErrorConstant.PARAM_REQUIRED, this.getClass().getSimpleName(), "getContactByUserId");
        }
        UserContactBean bean = BeanUtil.cloneBean(dto, UserContactBean.class);
        try {
            List<Map<String, String>> list = userContactBusiness.getContactByUserId(bean);
            return ResponseBuilder.normalResponse(list);
        } catch (Exception e) {
            return ResponseBuilder.errorResponse(ErrorConstant.SERVICE_FAIL, e, this.getClass().getSimpleName(), "getContactByUserId");
        }
    }

    /**
     * @param dto
     * @return com.mobanker.shanyidai.esb.common.packet.ResponseEntity
     * @description 新增联系人
     * @author hantongyang
     * @time 2016/12/28 15:42
     * @method addContact
     */
    @Override
    public ResponseEntity addContact(UserContactDto dto) {
        if (dto == null || dto.getUserId() == null) {
            return ResponseBuilder.errorResponse(ErrorConstant.PARAM_REQUIRED, this.getClass().getSimpleName(), "addContact");
        }
        if (dto.getSaveFields() == null || dto.getSaveFields().isEmpty()) {
            return ResponseBuilder.errorResponse(ErrorConstant.PARAM_REQUIRED, this.getClass().getSimpleName(), "addContact");
        }
        if (dto.getCommonFields() == null || dto.getCommonFields().isEmpty()) {
            return ResponseBuilder.errorResponse(ErrorConstant.PARAM_REQUIRED, this.getClass().getSimpleName(), "addContact");
        }
        UserContactBean bean = BeanUtil.cloneBean(dto, UserContactBean.class);
        try {
            Map<String, String> map = userContactBusiness.addContact(bean);
            return ResponseBuilder.normalResponse(map);
        } catch (Exception e) {
            return ResponseBuilder.errorResponse(ErrorConstant.SERVICE_FAIL, e, this.getClass().getSimpleName(), "addContact");
        }
    }

    /**
     * @param dto
     * @return com.mobanker.shanyidai.esb.common.packet.ResponseEntity
     * @description 修改联系人信息
     * @author hantongyang
     * @time 2016/12/28 15:43
     * @method updateContact
     */
    @Override
    public ResponseEntity updateContact(UserContactDto dto) {
        if (dto == null || dto.getUserId() == null || StringUtils.isBlank(dto.getType())) {
            return ResponseBuilder.errorResponse(ErrorConstant.PARAM_REQUIRED, this.getClass().getSimpleName(), "updateContact");
        }
        if (dto.getSaveFields() == null || dto.getSaveFields().isEmpty()) {
            return ResponseBuilder.errorResponse(ErrorConstant.PARAM_REQUIRED, this.getClass().getSimpleName(), "updateContact");
        }
        if (dto.getCommonFields() == null || dto.getCommonFields().isEmpty()) {
            return ResponseBuilder.errorResponse(ErrorConstant.PARAM_REQUIRED, this.getClass().getSimpleName(), "updateContact");
        }
        UserContactBean bean = BeanUtil.cloneBean(dto, UserContactBean.class);
        try {
            Map<String, String> map = userContactBusiness.updateContact(bean);
            return ResponseBuilder.normalResponse(map);
        } catch (Exception e) {
            return ResponseBuilder.errorResponse(ErrorConstant.SERVICE_FAIL, e, this.getClass().getSimpleName(), "updateContact");
        }
    }

}
