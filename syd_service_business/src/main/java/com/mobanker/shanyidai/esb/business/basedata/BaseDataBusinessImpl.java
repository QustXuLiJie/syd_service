package com.mobanker.shanyidai.esb.business.basedata;

import com.mobanker.shanyidai.esb.business.common.EsbCommonBusiness;
import com.mobanker.shanyidai.esb.call.dubbo.BaseDataDubbo;
import com.mobanker.shanyidai.esb.call.dubbo.impl.BaseDataDubboImpl;
import com.mobanker.shanyidai.esb.common.constants.ErrorConstant;
import com.mobanker.shanyidai.esb.common.exception.EsbException;
import com.mobanker.shanyidai.esb.common.logger.LogManager;
import com.mobanker.shanyidai.esb.common.logger.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 获取职位类型列表、联系人关系列表
 * Created by zhouqianqian on 2017/2/13.
 */
@Service
public class BaseDataBusinessImpl implements BaseDataBusiness {
    @Resource
    private BaseDataDubbo baseDataDubbo;
    @Resource
    EsbCommonBusiness esbCommonBusiness;
    public static final Logger logger = LogManager.getSlf4jLogger(BaseDataDubboImpl.class);
    /**
     * @return String
     * @description 获取职位列表
     * @author zhouqianqian
     * @method getJobTypeList
     */
    @Override
    public String getJobTypeList() {
        String result = baseDataDubbo.getJobTypeList();
        return result;
    }

    /**
     * @return String
     * @description 获取联系人关系
     * @author zhouqianqian
     * @method getRelation
     */
    @Override
    public String getRelation(String product) {

        String value= null;
        try {
            value = esbCommonBusiness.getCacheSysConfig("basedata_relation_type");
        } catch (Exception e) {
            logger.error("查询联系人关系获取配置中心参数异常", e);
            throw new EsbException(ErrorConstant.ERROR_RELATION_GET_INFO.getCode(), e.getMessage(), e);
        }
        String result = null;
        try {
            result = baseDataDubbo.getRelation(product,value);
        } catch (Exception e) {
            logger.error("查询联系人关系异常", e);
            throw new EsbException(ErrorConstant.ERROR_RELATION_GET_INFO.getCode(), e.getMessage(), e);
        }
        return result;
    }

    /**
     * @return String
     * @description 获取学历列表
     * @author zhouqianqian
     * @method getEducation
     */
    @Override
    public String getEducation(String product) {

        String result = null;
        try {
            result = baseDataDubbo.getEducation(product);
        } catch (Exception e) {
            logger.error("查询学历列表异常", e);
            throw new EsbException(ErrorConstant.ERROR_EDUCATION_GET_INFO.getCode(), e.getMessage(), e);
        }
        return result;
    }

}
