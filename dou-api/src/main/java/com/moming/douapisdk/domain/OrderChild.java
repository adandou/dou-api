package com.moming.douapisdk.domain;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @author tianzong
 * @date 2020/7/23
 */
@Data
public class OrderChild {

    @JSONField(name = "buyer_words")
    private String buyerWords;

    @JSONField(name = "c_type")
    private String cType;

    @JSONField(name = "b_type")
    private String bType;

    @JSONField(name = "campaign_id")
    private String campaignId;

    @JSONField(name = "cancel_reason")
    private String cancelReason;

    private String code;

    @JSONField(name = "combo_amount")
    private int comboAmount;

    @JSONField(name = "combo_id")
    private int comboId;

    @JSONField(name = "combo_num")
    private int comboNum;

    @JSONField(name = "cos_ratio")
    private String cosRatio;

    @JSONField(name = "coupon_amount")
    private int couponAmount;

    @JSONField(name = "coupon_meta_id")
    private String couponMetaId;

    @JSONField(name = "create_time")
    private String createTime;

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

    @JSONField(name = "order_type")
    private int orderType;

    @JSONField(name = "out_product_id")
    private int outProductId;

    @JSONField(name = "out_sku_id")
    private int outSkuId;

    @JSONField(name = "pay_type")
    private int payType;

    @JSONField(name = "pay_time")
    private String payTime;

    private String pid;

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

    @JSONField(name = "product_id")
    private String productId;

    @JSONField(name = "product_name")
    private String productName;

    @JSONField(name = "product_pic")
    private String productPic;

    @JSONField(name = "receipt_time")
    private String receiptTime;

    @JSONField(name = "seller_words")
    private String sellerWords;

    @JSONField(name = "shop_coupon_amount")
    private int shopCouponAmount;

    @JSONField(name = "shop_id")
    private int shopId;

    @JSONField(name = "total_amount")
    private int totalAmount;

    @JSONField(name = "update_time")
    private int updateTime;

    @JSONField(name = "urge_cnt")
    private int urgeCnt;

    @JSONField(name = "user_name")
    private String userName;

    @JSONField(name = "exp_ship_time")
    private int expShipTime;

    @JSONField(name = "campaign_info")
    private List<CampaignInfo> campaignInfo;

    @JSONField(name = "coupon_info")
    private List<CouponInfo> couponInfo;

    @JSONField(name = "spec_desc")
    private List<SpecDesc> specDesc;


    @Setter
    @Getter
    @ToString
    private static class CampaignInfo {
        /**
         * campaign_id : 123123
         * title : 【好货】123===
         */

        @JSONField(name = "campaign_id")
        private int campaignId;
        private String title;


    }

    @Data
    @ToString
    private static class SpecDesc {
        /**
         * name : 颜色分类
         * value : 正方形（送土豪金勺+杯盖）
         */

        private String name;
        private String value;

    }
}
