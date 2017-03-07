package com.mobanker.shanyidai.esb.business.basedata;

/**
 * 获取职位类型列表
 * Created by zhouqianqian on 2017/2/13.
 */

public interface BaseDataBusiness {
    /**
     * 获取职位类型列表
     * Created by zhouqianqian on 2017/2/13.
     */
    String getJobTypeList();

    /**
     * 获取联系人关系
     * Created by zhouqianqian on 2017/2/13.
     */
    String getRelation(String product);

    /**
     * 获取联系人关系
     * Created by zhouqianqian on 2017/2/13.
     */
    String getEducation(String product);
}
