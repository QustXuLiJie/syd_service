package com.mobanker.shanyidai.esb.business.common;

/**
 * @author hantongyang
 * @version 1.0
 * @description
 * @date 创建时间：2016/12/23 16:21
 */
public interface EsbCommonBusiness {

    /**
     * @description 从zk读取配置信息</br>@机制：先从数据库加载到缓存中，定时刷新缓存
     * @author hantongyang
     * @time 2016/12/22 14:31
     * @return String
     */
    String getCacheSysConfig(String key);

    String getCacheSysConfig(String key, String errorCode, String errorMsg);

    /**
     * @description 获取闪宜贷版本号。若是前端不传，则默认用微站默认配置的版本号
     * @author hantongyang
     * @time 2016/12/22 14:31
     * @method
     * @param version
     * @return
     */
    String getVersion(String version);
}
