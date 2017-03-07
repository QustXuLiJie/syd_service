package com.mobanker.shanyidai.esb.call.dubbo.impl;

import com.alibaba.fastjson.JSONArray;
import com.mobanker.framework.dto.ResponseEntity;
import com.mobanker.message.contract.dto.JqPage;
import com.mobanker.message.contract.dto.JqPageQueryCondition;
import com.mobanker.message.contract.manager.DubboQueryMessageInfoManager;
import com.mobanker.message.contract.manager.DubboReplaceMessageInfoManager;
import com.mobanker.message.contract.params.SysMessageParams;
import com.mobanker.shanyidai.dubbo.dto.message.SysMessageDto;
import com.mobanker.shanyidai.dubbo.dto.message.SysMessageParamsDto;
import com.mobanker.shanyidai.esb.call.dubbo.MessageDubbo;
import com.mobanker.shanyidai.esb.common.constants.ErrorConstant;
import com.mobanker.shanyidai.esb.common.exception.EsbException;
import com.mobanker.shanyidai.esb.common.logger.LogManager;
import com.mobanker.shanyidai.esb.common.logger.Logger;
import com.mobanker.shanyidai.esb.common.packet.CallResultUtil;
import com.mobanker.shanyidai.esb.common.utils.BeanUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Richard Core
 * @description
 * @date 2016/12/28 9:23
 */
@Service
public class MessageDubboImpl implements MessageDubbo {
    public static final Logger LOGGER = LogManager.getSlf4jLogger(MessageDubboImpl.class);
    @Resource
    private DubboQueryMessageInfoManager dubboQueryMessageInfoManager;
    @Resource
    private DubboReplaceMessageInfoManager dubboReplaceMessageInfoManager;

    /**
     * @param paramDto
     * @return java.util.List<com.mobanker.shanyidai.dubbo.dto.message.SysMessageDto>
     * @description 查询系统消息
     * 参数 userId 用户id
     * readStatus 是否已读  1 已读 0 未读
     * @author Richard Core
     * @time 2017/2/14 16:09
     * @method getSysMessage
     */
    @Override
    public List<SysMessageDto> getSysMessage(SysMessageParamsDto paramDto) {
        if (paramDto == null || paramDto.getUserId() == null) {
            throw new EsbException(ErrorConstant.LOGIN_TIME_OUT);
        }
        JqPageQueryCondition condition = new JqPageQueryCondition();
        SysMessageParams params = BeanUtil.cloneBean(paramDto, SysMessageParams.class);
//        params.setProduct(ProductType.shoujidai);// TODO: 2017/2/14 私服上暂时没有这个枚举
        ResponseEntity result = dubboQueryMessageInfoManager.getSysMessage(condition, params);
        if (!CallResultUtil.isSuccess(result)) {
            LOGGER.warn("获取系统消息失败" + result.getError() + ":" + result.getMsg());
            throw new EsbException(ErrorConstant.SYS_MESSAGE_ERROR.getCode(), result.getMsg());
        }
        Object data = result.getData();
        if (data == null) {
            return null;
        }
        JqPage jqPage = (JqPage) result.getData();//暂时不分页
        List<SysMessageDto> sysMessageDtos = JSONArray.parseArray(JSONArray.toJSONString(jqPage.getData()), SysMessageDto.class);
        return sysMessageDtos;
    }

    /**
     * @param ids
     * @return void
     * @description 更新消息读取状态
     * @author Richard Core
     * @time 2017/2/14 17:18
     * @method updateReadStatus
     */
    @Override
    public void updateReadStatus(List<String> ids) {
        if (ids == null || ids.isEmpty()) {
            throw new EsbException(ErrorConstant.PARAM_REQUIRED.getCode(), "未读消息id为空");
        }
        SysMessageParams params = new SysMessageParams();
        params.setIds(ids);
        ResponseEntity responseEntity = dubboReplaceMessageInfoManager.updateSysMessageById(params);
        if (!CallResultUtil.isSuccess(responseEntity)) {
            LOGGER.warn("获取系统消息失败" + responseEntity.getError() + ":" + responseEntity.getMsg());
            throw new EsbException(ErrorConstant.SYS_MESSAGE_ERROR.getCode(), responseEntity.getMsg());
        }
    }

    /**
     * @param paramDto
     * @return int
     * @description 查询未读系统消息数
     * @author Richard Core
     * @time 2017/2/14 17:36
     * @method getUnreadSysMessageAmount
     */
    @Override
    public long getUnreadSysMessageAmount(SysMessageParamsDto paramDto) {
        if (paramDto == null || paramDto.getUserId() == null) {
            throw new EsbException(ErrorConstant.LOGIN_TIME_OUT);
        }
        SysMessageParams params = BeanUtil.cloneBean(paramDto, SysMessageParams.class);
//        params.setProduct(ProductType.shoujidai);// TODO: 2017/2/14 私服上暂时没有这个枚举
        ResponseEntity responseEntity = dubboQueryMessageInfoManager.getUnreadSysMessageCount(params);
        if (!CallResultUtil.isSuccess(responseEntity)) {
            LOGGER.warn("获取未读系统消息数失败" + responseEntity.getError() + ":" + responseEntity.getMsg());
            throw new EsbException(ErrorConstant.SYS_MESSAGE_ERROR.getCode(), responseEntity.getMsg());
        }
        Object data = responseEntity.getData();
        if (data == null) {
            return 0l;
        }
        Map<String, Long> resultMap = (HashMap<String, Long>) data;
        return Long.valueOf(resultMap.get("unreadSysMessage"));
    }
}
