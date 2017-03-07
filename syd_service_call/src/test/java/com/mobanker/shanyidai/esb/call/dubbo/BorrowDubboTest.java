package com.mobanker.shanyidai.esb.call.dubbo;

import com.mobanker.shanyidai.dubbo.service.borrow.BorrowDubboService;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * @author hantongyang
 * @description
 * @time 2017/2/10 11:16
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/mobanker-syd-application.xml, classpath:/mobanker-syd-servlet.xml"})
public class BorrowDubboTest{

    @Resource
    private BorrowDubboService borrowDubboService;

    @Test
    public void test(){
        String callBack = "{\"addChannel\":\"app\",\"addProduct\":\"shanyidai\",\"amount\":\"500\",\"appversion\":\"1.0\",\"borrowTime\":\"1486705909644\",\"borrowType\":\"7\",\"ip\":\"127.0.0.1\",\"orderNo\":\"18\",\"period\":\"1\",\"periodDays\":\"15\",\"productModelId\":\"4472bc01cb7911e6901e0242ac110000\",\"queueName\":\"productOrderNo\",\"status\":\"0\",\"systemName\":\"shanyidai\",\"type\":\"1\",\"userId\":\"5\",\"orderNid\":\"shanyidai18\"}";
    }
}
