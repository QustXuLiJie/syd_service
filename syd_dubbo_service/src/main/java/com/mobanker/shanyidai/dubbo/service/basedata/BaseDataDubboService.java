package com.mobanker.shanyidai.dubbo.service.basedata;

import com.mobanker.shanyidai.esb.common.packet.ResponseEntity;

/**
 * 获取职位类型列表
 * Created by zhouqianqian on 2017/2/13.
 */
public interface BaseDataDubboService {
    /**
     * 获取职位类型列表
     * Created by zhouqianqian on 2017/2/13.
     */
    public ResponseEntity getJobTypeList();

    /**
     * 获取联系人关系
     * Created by zhouqianqian on 2017/2/13.
     */
    public ResponseEntity getRelation(String product);

    /**
     * 获取学历列表
     * Created by zhouqianqian on 2017/2/13.
     */
    public ResponseEntity getEducation(String product);
}
