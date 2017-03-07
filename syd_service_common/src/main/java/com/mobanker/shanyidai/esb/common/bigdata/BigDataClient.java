package com.mobanker.shanyidai.esb.common.bigdata;

import com.alibaba.fastjson.JSON;
import com.dianping.cat.message.Event;
import com.mobanker.bigdata.sensor.constants.contract.BigDataConstants;
import com.mobanker.bigdata.sensor.constants.contract.BigDataTopicEnum;
import com.mobanker.bigdata.sensor.microsite.contract.BigDataUserDeviceDto;
import com.mobanker.bigdata.sensor.microsite.contract.MicroConstantDto;
import com.mobanker.framework.tracking.EE;
import com.mobanker.framework.tracking.model.BigData;
import com.mobanker.shanyidai.esb.common.logger.LogManager;
import com.mobanker.shanyidai.esb.common.logger.Logger;
import org.apache.commons.lang.StringUtils;

import java.util.UUID;


/**
 * @desc:
 * @author: Richard Core
 * @create time: 2017/2/8 15:41
 */
public class BigDataClient {
    public static final Logger logger = LogManager.getSlf4jLogger(BigDataClient.class);

    /**
     * @param obj
     * @return void
     * @description 鹰眼埋点
     * @author Richard Core
     * @time 2017/2/8 15:51
     * @method saveBigdata
     */
    public static void saveBigdata(Object obj) {
        if (obj == null) {
            return;
        }
        //消息队列topic名称，统一前缀“bigdata_rca_”，加上domain名称。例如bigdata_rca_customer,再加上dto名称
//        String domain = SydConstant.BIG_DATA_DOMAIN;//domain名称  各产品取固定值
//        String topicNamePrefix = MicroConstantDto.TOPICNAME;// 统一前缀“bigdata_rca_”
//        String simpleName = obj.getClass().getSimpleName();
//        simpleName = simpleName.toLowerCase().replaceAll("(bigdata|dto)", "");
//        String topicName = topicNamePrefix + domain + "_" + simpleName;

        String domain = BigDataConstants.MobankerDomain.SYD;//domain名称  各产品取固定值
        String simpleName = obj.getClass().getSimpleName();
        String topicName = BigDataTopicEnum.getLookup().get(simpleName).getTopicName();
        if (StringUtils.isBlank(topicName)) {
            logger.warn("获取" + simpleName + "大数据topic名称失败");
            return;
        }
        try {
            BigData data = new BigData();
            data.setData(JSON.toJSONString(obj));//业务数据，DTO对象实例的json字符串（第三方单独梳理，和大数据对接）。消费端接收到msg后，反序列化成dto实例，再进行业务处理；
            data.setDomain(domain);//domain名称
            data.setMessageId(UUID.randomUUID().toString().replaceAll("-", ""));
            data.setSellerId(MicroConstantDto.SELLERID);//Mobanker，前隆金融（第三方单独梳理，和大数据对接）；
            data.setTypeId(MicroConstantDto.TYPEID);//InternetFinance，互联网金融（第三方单独梳理，和大数据对接）
            data.setTimestamp(System.currentTimeMillis());
            data.setName(obj.getClass().getSimpleName());
            data.setEvent(MicroConstantDto.Event.INSERT);
            EE.sendData(topicName, data);
        } catch (Exception e) {
            EE.logEvent("Exception_BigData_Service", "大数据埋点异常", Event.SUCCESS, "user_id:" + JSON.toJSONString(obj) + ",error:" + e.getMessage());
        }
    }

    public static void main(String[] args) {
        BigDataUserDeviceDto dto = new BigDataUserDeviceDto();
        String simpleName = dto.getClass().getSimpleName();
        simpleName = simpleName.toLowerCase().replaceAll("(bigdata|dto)", "");
        System.out.println(simpleName);
    }
}
