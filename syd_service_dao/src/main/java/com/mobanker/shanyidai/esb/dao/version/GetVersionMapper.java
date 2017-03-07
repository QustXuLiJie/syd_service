package com.mobanker.shanyidai.esb.dao.version;

import com.mobanker.shanyidai.esb.model.entity.version.VersionEntity;
import org.apache.ibatis.annotations.Param;

/**
 * @description: 获取app版本更新
 * @author: xulijie
 * @create time: 11:17 2017/2/24
 */
public interface GetVersionMapper {


    /**
     * @param entity
     * @return com.mobanker.shanyidai.esb.model.entity.version.VersionEntity
     * @author xulijie
     * @method findByProductAndType
     * @description 根据产品、手机类型、状态查询版本更新信息
     * @time 14:26 2017/2/24
     */
    VersionEntity findByProductAndType(@Param("entity") VersionEntity entity);
}
