package com.mobanker.shanyidai.esb.call.dubbo.impl;

import com.mobanker.cust.contactinfo.contract.manager.DubboContactInfoManager;
import com.mobanker.cust.contactinfo.contract.params.ContactInfoParams;
import com.mobanker.framework.dto.ResponseEntity;
import com.mobanker.shanyidai.esb.call.dubbo.ContactInfoDubbo;
import com.mobanker.shanyidai.esb.common.constants.ErrorConstant;
import com.mobanker.shanyidai.esb.common.exception.EsbException;
import com.mobanker.shanyidai.esb.common.logger.LogManager;
import com.mobanker.shanyidai.esb.common.logger.Logger;
import com.mobanker.shanyidai.esb.common.packet.CallResultUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @description 联系人相关服务业务实现
 * @author hantongyang
 * @time 2016-12-28 11:06
 */
@Service
public class ContactInfoDubboImpl implements ContactInfoDubbo {
    public static final Logger logger = LogManager.getSlf4jLogger(ContactInfoDubboImpl.class);

    @Resource
    private DubboContactInfoManager dubboContactInfoManager;

    /**
     * @description 根据类型查询用户对应的联系人信息
     * @author hantongyang
     * @time 2016/12/28 11:43
     * @method getContactInfoByUserId
     * @param param
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.String>>
     */
    @Override
    public List<Map<String, String>> getContactInfoByUserId(ContactInfoParams param) {
        checkBasic(param, null);
        ResponseEntity<List<Map<String, String>>> result = dubboContactInfoManager.getContactInfoByUserId(param);
        //判断是否有异常，有异常则抛出异常
        if(!CallResultUtil.isSuccess(result)){
            logger.warn("调用用户联系人基础服务-根据userId查询用户联系人信息", result);
            throw new EsbException(ErrorConstant.SERVICE_FAIL.getCode(), result.getMsg());
        }
        return result.getData();
    }

    /**
     * @description 新增联系人信息
     * @author hantongyang
     * @time 2016/12/28 14:23
     * @method addContactInfo
     * @param param
     * @return java.util.Map<java.lang.String,java.lang.String>
     */
    @Override
    public Map<String, String> addContactInfo(ContactInfoParams param) {
        checkBasic(param, "add");
        ResponseEntity<Map<String, String>> result = dubboContactInfoManager.addMoreContactInfoByUserId(param);
        if(!CallResultUtil.isSuccess(result)){
            logger.warn("调用用户联系人基础服务-新增联系人信息", result);
            throw new EsbException(ErrorConstant.SERVICE_FAIL.getCode(), result.getMsg());
        }
        return result.getData();
    }

    /**
     * @description 修改联系人信息
     * @author hantongyang
     * @time 2016/12/28 14:24
     * @method updateContactInfo
     * @param param
     * @return java.util.Map<java.lang.String,java.lang.String>
     */
    @Override
    public Map<String, String> updateContactInfo(ContactInfoParams param) {
        checkBasic(param, "update");
        ResponseEntity<Map<String, String>> result = dubboContactInfoManager.updateMoreContactInfoByLinkmanId(param);
        if(!CallResultUtil.isSuccess(result)){
            logger.warn("调用用户联系人基础服务-修改联系人信息", result);
            throw new EsbException(ErrorConstant.SERVICE_FAIL.getCode(), result.getMsg());
        }
        return result.getData();
    }

    /**
     * @description 验证参数
     * @author hantongyang
     * @time 2016/12/28 14:47
     * @method checkBasic
     * @param param
     * @param type
     * @return void
     */
    private static void checkBasic(ContactInfoParams param, String type){
        if(param == null || param.getUserId() == null){
            throw new EsbException(ErrorConstant.PARAMS_ILLEGE);
        }
        if(StringUtils.isNotBlank(type)){
            //保存、更新时需要判断保存参数、公共参数是否为空
            if(param.getSaveFields() == null || param.getSaveFields().isEmpty()){
                throw new EsbException(ErrorConstant.PARAMS_ILLEGE);
            }
            if(param.getCommonFields() == null || param.getCommonFields().isEmpty()){
                throw new EsbException(ErrorConstant.PARAMS_ILLEGE);
            }
        }else{
            //查询时需要判断类型
            if(StringUtils.isBlank(param.getType())){
                throw new EsbException(ErrorConstant.PARAMS_ILLEGE);
            }
        }
    }
}
