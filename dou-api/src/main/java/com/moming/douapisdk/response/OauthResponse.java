package com.moming.douapisdk.response;

import com.alibaba.fastjson.annotation.JSONField;
import com.moming.douapisdk.BaseDouYinResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author tianzong
 * @date 2020/7/28
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class OauthResponse extends BaseDouYinResponse {
    private static final long serialVersionUID = -6299470895146061875L;


    private ShopAccess data;

    @Data
    public static class ShopAccess{

        /**
         * access Token
         *
         */
        @JSONField(name = "access_token")
        private String accessToken;

        /**
         * 过期时间：秒
         */
        @JSONField(name = "expires_in")
        private Integer expiresIn;

        /**
         * 刷新令牌
         */
        @JSONField(name = "refresh_token")
        private String refreshToken;

        /**
         * 授权作用域
         */
        private String scope;

        /**
         * 店铺id
         */
        @JSONField(name = "shop_id")
        private String shopId;

        /**
         * 店铺名称
         */
        @JSONField(name = "shop_name")
        private String shopName;
    }
}
