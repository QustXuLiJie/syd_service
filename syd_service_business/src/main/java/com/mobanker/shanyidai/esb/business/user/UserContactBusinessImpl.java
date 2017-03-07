package com.mobanker.shanyidai.esb.business.user;

import com.mobanker.cust.contactinfo.contract.params.ContactInfoParams;
import com.mobanker.shanyidai.esb.call.dubbo.ContactInfoDubbo;
import com.mobanker.shanyidai.esb.common.constants.ErrorConstant;
import com.mobanker.shanyidai.esb.common.exception.EsbException;
import com.mobanker.shanyidai.esb.common.utils.BeanUtil;
import com.mobanker.shanyidai.esb.model.dto.user.UserContactBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author hantongyang
 * @description 用户联系人相关业务处理类
 * @time 2016/12/28 13:50
 */
@Service
public class UserContactBusinessImpl implements UserContactBusiness {

    @Resource
    private ContactInfoDubbo contactInfoDubbo;

    /**
     * @param bean
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @description 根据用户ID查询联系人信息
     * @author hantongyang
     * @time 2016/12/28 14:04
     * @method getContactByUserId
     */
    @Override
    public List<Map<String, String>> getContactByUserId(UserContactBean bean) {
        if (bean == null) {
            throw new EsbException(ErrorConstant.PARAMS_ILLEGE);
        }
        ContactInfoParams param = initContactInfoParams(bean, null);
        List<Map<String, String>> list = contactInfoDubbo.getContactInfoByUserId(param);
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list;
    }

    /**
     * @description 新增联系人
     * @author hantongyang
     * @time 2016/12/28 15:15
     * @method addContact
     * @param bean
     * @return java.util.Map<java.lang.String,java.lang.String>
     */
    @Override
    public Map<String, String> addContact(UserContactBean bean) {
        ContactInfoParams param = initContactInfoParams(bean, "add");
        Map<String, String> result = contactInfoDubbo.addContactInfo(param);
        if(result == null || result.isEmpty()){
            return null;
        }
        return result;
    }

    /**
     * @description 修改联系人信息
     * @author hantongyang
     * @time 2016/12/28 15:15
     * @method updateContact
     * @param bean
     * @return java.util.Map<java.lang.String,java.lang.String>
     */
    @Override
    public Map<String, String> updateContact(UserContactBean bean) {
        ContactInfoParams param = initContactInfoParams(bean, "update");
        Map<String, String> result = contactInfoDubbo.updateContactInfo(param);
        if(result == null || result.isEmpty()){
            return null;
        }
        return result;
    }

    /**
     * @description 初始化联系人信息
     * @author hantongyang
     * @time 2016/12/28 15:27
     * @method initContactInfoParams
     * @param bean
     * @param type
     * @return com.mobanker.cust.contactinfo.contract.params.ContactInfoParams
     */
    private ContactInfoParams initContactInfoParams(UserContactBean bean, String type){
        if (bean == null) {
            throw new EsbException(ErrorConstant.PARAMS_ILLEGE);
        }
        ContactInfoParams param = BeanUtil.cloneBean(bean, ContactInfoParams.class);
        return param;
    }
}
