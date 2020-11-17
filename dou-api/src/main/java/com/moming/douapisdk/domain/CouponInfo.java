package com.moming.douapisdk.domain;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @author tianzong
 * @date 2020/7/23
 */
@Data
public class CouponInfo {

    private long id;
    private String name;
    private String description;
    private int credit;
    private int type;
    private int discount;
    @JSONField(name = "pay_type")
    private int payType;

}
