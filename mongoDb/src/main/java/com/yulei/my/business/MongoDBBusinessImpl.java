package com.yulei.my.business;


import com.yulei.my.annotation.Business;
import com.yulei.my.domain.BaseInfo;
import com.zhixindu.commons.api.ServiceCode;
import com.zhixindu.commons.api.ServiceException;
import org.apache.commons.lang.StringUtils;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.UpdateOperations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * @author Yulei
 * @version 1.0
 * @date 2017/7/3
 * @description
 */
@Business("mongoDBBbusiness")
public class MongoDBBusinessImpl implements MongoDBBusiness {

    @Autowired
    private Datastore datastore;

    @Override
    public void sumbit(BaseInfo baseInfo) {
        datastore.save(baseInfo);
    }

    @Override
    public void update(BaseInfo baseInfo) {
        BaseInfo baseInfo1 = this.findByOne(baseInfo.getNo());
        UpdateOperations<BaseInfo> updateOperations = datastore.createUpdateOperations(BaseInfo.class).set("name", baseInfo.getName());
        datastore.update(baseInfo1, updateOperations);
    }

    @Override
    public BaseInfo findByOne(String no) {
        if (StringUtils.isBlank(no) || StringUtils.isBlank(no)) {
            throw new ServiceException(ServiceCode.ILLEGAL_PARAM, "no or note is blank.");
        }
        BaseInfo baseInfo = datastore.find(BaseInfo.class).field("no").equal(no).get();
        return baseInfo;
    }

    @Override
    public void deleteOne(String no) {
        BaseInfo baseInfo = datastore.find(BaseInfo.class).field("no").equal(no).get();
        System.out.println("delete:"+datastore.delete(baseInfo));
    }
}
