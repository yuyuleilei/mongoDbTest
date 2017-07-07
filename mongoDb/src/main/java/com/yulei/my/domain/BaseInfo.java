package com.yulei.my.domain;

import lombok.Data;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.*;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Yulei
 * @version 1.0
 * @date 2017/7/3
 * @description
 */
@Data
@Indexes({
        @Index(fields = {@Field("no")}, options = @IndexOptions(unique = true))
})
@Entity(value = "my_test_baseinfo" , noClassnameStored = true)
public class BaseInfo implements Serializable {
    private static final long serialVersionUID = -6150809575957445674L;
    @Id
    private ObjectId _id;
    private String no;

    private String name;
    private String idNo;
    private String mobile;
    private String age;
    private String sex;

    private Date createTime;
}
