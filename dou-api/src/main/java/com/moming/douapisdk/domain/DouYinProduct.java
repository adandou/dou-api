package com.moming.douapisdk.domain;


import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import org.springframework.boot.jackson.JsonComponent;

/**
 * @author lujingpo
 */
@Data
public class DouYinProduct {

    /**
     * 商品id
     */
    @JSONField(name = "product_id")
    private long productId;
    /**
     *
     */
    @JSONField(name = "open_user_id")
    private long openUserId;
    private String name;
    private String description;
    private String img;
    /**
     * 市场价格
     */
    @JSONField(name = "market_price")
    private int marketPrice;
    @JSONField(name = "discount_price")
    private int discountPrice;
    @JSONField(name = "settlement_price")
    private int settlementPrice;
    private int status;
    @JSONField(name = "spec_id")
    private int specId;
    @JSONField(name = "check_status")
    private int checkStatus;
    private String mobile;
    @JSONField(name = "first_cid")
    private int firstCid;
    @JSONField(name = "second_cid")
    private int secondCid;
    @JSONField(name = "third_cid")
    private int thirdCid;
    @JSONField(name = "pay_type")
    private int payType;
    @JSONField(name = "recommend_remark")
    private String recommendRemark;
    @JSONField(name = "is_create")
    private int isCreate;
    @JSONField(name = "create_time")
    private String createTime;
    @JSONField(name = "update_time")
    private String updateTime;

}
