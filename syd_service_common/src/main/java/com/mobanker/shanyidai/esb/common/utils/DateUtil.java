package com.mobanker.shanyidai.esb.common.utils;

import com.mobanker.shanyidai.esb.common.constants.DatePattern;
import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Richard Core
 * @description
 * @date 2016/12/22 18:34
 */
public class DateUtil {

    /**
     * @param
     * @return long
     * @description 获取当前时间秒数
     * @author Richard Core
     * @time 2016/12/22 18:35
     * @method getNowSeconds
     */
    public static long getNowSeconds() {
        return System.currentTimeMillis() / 1000;
    }

    /**
     * 获取当前时间戳
     *
     * @author: hantongyang
     * @date 创建时间：2016年8月23日
     * @version 1.0
     * @parameter
     * @return
     */
    public static Long getNowTime(Date date) {
        if(date == null){
            date = new Date();
        }
        return date.getTime() / 1000;
    }

    /**
     * @param dateStr
     * @param pattern
     * @return java.util.Date
     * @description 转化时间字符串
     * @author Richard Core
     * @time 2016/12/24 18:53
     * @method parse2Date
     */
    public static Date parse2Date(String dateStr, String pattern) {
        if (StringUtils.isBlank(dateStr)) {
            return null;
        }
        if (StringUtils.isBlank(pattern)) {
            for (DatePattern datePattern : DatePattern.values()) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(datePattern.getPattern());
                try {
                    Date date = simpleDateFormat.parse(dateStr);
                    return date;
                } catch (ParseException e) {
                    continue;
                }
            }
            return null;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        try {
            Date date = simpleDateFormat.parse(dateStr);
            return date;
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * @param date
     * @param pattern
     * @return java.lang.String
     * @description 获取时间格式字符串
     * @author Richard Core
     * @time 2017/2/27 19:34
     * @method format2String
     */
    public static String format2String(Date date, String pattern) {
        if (date == null) {
            return null;
        }
        if (StringUtils.isBlank(pattern)) {
            pattern = DatePattern.DATE_TIME.getPattern();
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String format = simpleDateFormat.format(date);
        return format;
    }
}
