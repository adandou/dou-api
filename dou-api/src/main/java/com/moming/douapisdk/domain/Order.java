package com.moming.douapisdk.domain;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.List;

/**
 * @author tianzong
 * @date 2020/7/23
 */
@Data
public class Order {
    @JSONField(name = "buyer_words")
    private String buyerWords;

    @JSONField(name = "c_type")
    private String cType;

    @JSONField(name = "cancel_reason")
    private String cancelReason;

    @JSONField(name = "cos_ratio")
    private String cosRatio;

    @JSONField(name = "coupon_amount")
    private int couponAmount;

    @JSONField(name = "create_time")
    private String createTime;

    @JSONField(name = "exp_ship_time")
    private int expShipTime;

    @JSONField(name = "is_comment")
    private String isComment;

    @JSONField(name = "logistics_code")
    private String logisticsCode;

    @JSONField(name = "logistics_id")
    private int logisticsId;

    @JSONField(name = "logistics_time")
    private String logisticsTime;

    @JSONField(name = "order_id")
    private String orderId;

    @JSONField(name = "order_status")
    private int orderStatus;

    @JSONField(name = "final_status")
    private int finalStatus;

    @JSONField(name = "order_total_amount")
    private int orderTotalAmount;

    @JSONField(name = "pay_type")
    private int payType;

    @JSONField(name = "pay_time")
    private String payTime;

    @JSONField(name = "post_addr")
    private PostAddr postAddr;

    @JSONField(name = "post_amount")
    private int postAmount;

    @JSONField(name = "post_code")
    private String postCode;

    @JSONField(name = "post_receiver")
    private String postReceiver;

    @JSONField(name = "post_tel")
    private String postTel;

    @JSONField(name = "receipt_time")
    private String receiptTime;

    @JSONField(name = "seller_words")
    private String sellerWords;

    @JSONField(name = "shop_coupon_amount")
    private int shopCouponAmount;

    @JSONField(name = "shop_id")
    private Long shopId;

    @JSONField(name = "update_time")
    private int updateTime;

    @JSONField(name = "urge_cnt")
    private int urgeCnt;

    @JSONField(name = "user_name")
    private String userName;

    private List<OrderChild> child;

    @JSONField(name = "coupon_info")
    private List<CouponInfo> couponInfo;
}
