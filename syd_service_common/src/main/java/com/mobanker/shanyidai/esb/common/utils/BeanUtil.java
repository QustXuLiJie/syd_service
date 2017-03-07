package com.mobanker.shanyidai.esb.common.utils;

import com.alibaba.fastjson.JSONObject;
import com.mobanker.framework.pojo.Page;
import com.mobanker.shanyidai.esb.common.constants.ErrorConstant;
import com.mobanker.shanyidai.esb.common.exception.EsbException;
import com.mobanker.shanyidai.esb.common.packet.ResponseBuilder;
import com.mobanker.shanyidai.esb.common.packet.ResponseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

/**
 * @author Richard Core
 * @description
 * @date 2016/12/23 13:59
 */
public class BeanUtil {
    public static final Logger logger = LoggerFactory.getLogger(BeanUtil.class);

    /**
     * @param content
     * @param clazz
     * @return T
     * @description 将content转化成指定类型的JavaBean
     * @author Richard Core
     * @time 2016/12/20 15:29
     * @method parseJson
     */
    public static <T> T parseJson(String content, Class<T> clazz) {
        try {
            logger.info("将json参数转化为JavaBean,class:[{}]，content:[{}]", clazz.getSimpleName(), content);
            T t = JSONObject.parseObject(content, clazz);
            return t;
        } catch (Exception e) {
            logger.error("将json参数转化为JavaBean异常", e);
            throw new EsbException(ErrorConstant.PARAM_VALID.getCode(), ErrorConstant.PARAM_VALID.getDesc(), e);
        }
    }

    /**
     * @param source
     * @param clazz
     * @return T
     * @description 属性拷贝
     * @author Richard Core
     * @time 2016/12/23 14:04
     * @method cloneBean
     */
    public static <T> T cloneBean(Object source, Class<T> clazz) {
        if (source == null) {
            return null;
        }
        try {
            logger.info("拷贝属性到指定的类型,class:[{}]，source:[{}]", clazz.getSimpleName(), source);
            T t = clazz.newInstance();
            BeanUtils.copyProperties(source, t);
            return t;
        } catch (Exception e) {
            logger.error("拷贝属性异常", e);
            throw new EsbException(ErrorConstant.PARAM_VALID.getCode(), ErrorConstant.PARAM_VALID.getDesc(), e);
        }
    }

    /**
     * @description 将订单中心返回值封装成ESB返回值
     * @author hantongyang
     * @time 2017/1/11 17:44
     * @method cloneResponseEntity
     * @param entity
     * @return com.mobanker.shanyidai.esb.common.packet.ResponseEntity
     */
    public static ResponseEntity cloneResponseEntity(com.mobanker.core.pojo.ResponseEntity entity){
        if(entity == null){
            return null;
        }
        try {
            logger.info("拷贝属性到指定的类型,class:[{}]，source:[{}]", ResponseEntity.class.getSimpleName(), entity);
            ResponseEntity bean = cloneBean(entity, ResponseEntity.class);
            //封装分页参数
            if(bean != null && bean.getPage() == null && entity.getPage() != null){
                bean.setPage(cloneBean(entity.getPage(), Page.class));
            }
            if(ResponseBuilder.isFinished(bean)){
                //由于订单中心返回值中没有error返回值，所以添加公共返回值
                bean.setError(ErrorConstant.SUCCESS.getCode());
            }
            return bean;
        } catch (Exception e) {
            logger.error("拷贝属性异常", e);
            throw new EsbException(ErrorConstant.PARAM_VALID.getCode(), ErrorConstant.PARAM_VALID.getDesc(), e);
        }
    }

}
