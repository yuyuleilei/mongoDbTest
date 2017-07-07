package com.yulei.my.business;

import com.yulei.my.domain.BaseInfo;

/**
 * @author Yulei
 * @version 1.0
 * @date 2017/7/3
 * @description
 */
public interface MongoDBBusiness {

    void sumbit(BaseInfo baseInfo);

    void update(BaseInfo baseInfo);

    BaseInfo findByOne(String no);

    void deleteOne(String no);
}
