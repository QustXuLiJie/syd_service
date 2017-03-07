package com.mobanker.shanyidai.esb.business.common;

import com.mobanker.shanyidai.esb.common.constants.ErrorConstant;
import com.mobanker.shanyidai.esb.common.constants.ZkConfigConstant;
import com.mobanker.shanyidai.esb.common.exception.EsbException;
import com.mobanker.zkc.cache.CacheManager;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author hantongyang
 * @version 1.0
 * @description 公共业务，包括查询缓存等方法
 * @date 创建时间：2016/12/23 16:21
 */
@Service
public class EsbCommonBusinessImpl implements EsbCommonBusiness{

    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * @description 从zk读取配置信息
     * @author hantongyang
     * @time 2016/12/22 15:35
     * @method getCacheSysConfig
     * @param key
     * @return java.lang.String
     */
    @Override
    public String getCacheSysConfig(String key) {
        String value = CacheManager.getActiveMapValue(key);
        if (org.apache.commons.lang3.StringUtils.isBlank(value)) {
            logger.error("警告：配置系统缺少对key[{}]的配置，采用默认配置参数", key);
            value = ZkConfigConstant.getByKey(key).getDefaultValue();
        }
        return value;
    }

    /**
     * @description 从zk读取配置的URL信息，为空时返回自定义异常
     * @author hantongyang
     * @time 2017/1/17 10:26
     * @method getCacheSysConfig
     * @param key
     * @param errorCode
     * @param errorMsg
     * @return java.lang.String
     */
    @Override
    public String getCacheSysConfig(String key, String errorCode, String errorMsg) {
        String value = null;
        try {
            value = CacheManager.getActiveMapValue(key);
        } catch (Exception e) {
            logger.warn("配置系统获取配置失败" + e);
        }
        if (org.apache.commons.lang3.StringUtils.isBlank(value)) {
            if(StringUtils.isBlank(errorCode) && StringUtils.isBlank(errorMsg)){
                throw new EsbException(ErrorConstant.CONFIG_DATA_NULL);
            }else{
                throw new EsbException(errorCode, errorMsg);
            }
        }
        return value;
    }

    /**
     * @description 获取版本号
     * @author hantongyang
     * @time 2016/12/22 20:18
     * @method getVersion
     * @param version
     * @return java.lang.String
     */
    @Override
    public String getVersion(String version) {
        if (org.apache.commons.lang3.StringUtils.isBlank(version)) {
            String value = getCacheSysConfig(ZkConfigConstant.SYD_APP_VERSION.getZkValue());
            return (org.apache.commons.lang3.StringUtils.isBlank(value) ? ZkConfigConstant.SYD_APP_VERSION.getDefaultValue() : value);
        }
        return version;
    }
}
