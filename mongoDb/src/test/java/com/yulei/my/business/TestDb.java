package com.yulei.my.business;

import com.alibaba.fastjson.JSON;
import com.yulei.my.app.ApplicationContextConfig;
import com.yulei.my.domain.BaseInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.inject.Inject;
import java.util.Date;

/**
 * @author Yulei
 * @version 1.0
 * @date 2016/6/27
 * @description
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ApplicationContextConfig.class})
@WebAppConfiguration
public class TestDb {

    @Inject
    private MongoDBBusiness mongoDBBusiness;

    @Test
    public void submitTest(){
        BaseInfo baseInfo = new BaseInfo();
        baseInfo.setNo("123");
        baseInfo.setName("yulei");
        baseInfo.setMobile("17091918167");
        baseInfo.setIdNo("411524198905111410");
        baseInfo.setAge("20");
        baseInfo.setSex("男");
        baseInfo.setCreateTime(new Date());
        mongoDBBusiness.sumbit(baseInfo);
    }

    @Test
    public void findByOneTest(){
        System.out.println(JSON.toJSONString(mongoDBBusiness.findByOne("123")));
    }

    @Test
    public void deleteOneTest(){
        mongoDBBusiness.deleteOne("123");
    }

    @Test
    public void updateTest(){
        BaseInfo baseInfo = new BaseInfo();
        baseInfo.setNo("123");
        baseInfo.setName("余磊");
        mongoDBBusiness.update(baseInfo);
    }

}
