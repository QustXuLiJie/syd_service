package com.mobanker.shanyidai.esb.service.auth.util;

import com.mobanker.shanyidai.esb.common.constants.DatePattern;
import com.mobanker.shanyidai.esb.common.utils.DateUtil;

import java.util.Date;
import java.util.Random;

/**
 * @desc:
 * @author: Richard Core
 * @create time: 2017/2/27 19:51
 */
public class AuthDubboConvert{

    /**
     * @param userId
     * @return java.lang.String
     * @description 生成一个自定义的单号
     * @author Richard Core
     * @time 2017/2/27 19:52
     * @method getRandomDataId
     */
    public static String getRandomDataId(Long userId) {
        String dateTime = DateUtil.format2String(new Date(), DatePattern.DATETIME_ZONE_NOSEP.getPattern());
        Random random = new Random();
        int i = random.nextInt(100);
        return "SYD" + userId + "" + dateTime + i;
    }

}
