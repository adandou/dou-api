package com.moming.douapisdk.request;

import com.moming.douapisdk.BaseDouYinRequest;
import com.moming.douapisdk.internal.util.DouYinHashMap;
import com.moming.douapisdk.response.ProductListResponse;
import lombok.Data;

import java.util.Map;

/**
 * 获取商品列表
 * @author lujingpo
 */
@Data
public class ProductListRequest extends BaseDouYinRequest<ProductListResponse> {

    /**
     * 第几页（第一页为0）
     */
    private String page;

    /**
     * 每页返回条数
     */
    private String size;

    /**
     * 指定状态返回商品列表：0上架 1下架
     */
    private String status;

    /**
     * 指定审核状态返回商品列表：1未提审 2审核中 3审核通过 4审核驳回 5封禁
     */
    private String checkStatus;

    @Override
    public String getApiUrl() {
        return "/product/list";
    }

    @Override
    public String getApiMethodName() {
        return "product.list";
    }

    @Override
    public Map<String, String> getTextParams() {
        DouYinHashMap textParams = new DouYinHashMap();
        textParams.put("page", this.page);
        textParams.put("size", this.size);
        textParams.put("status", this.status);
        textParams.put("check_status", this.checkStatus);
        return textParams;
    }

    @Override
    public Class<ProductListResponse> getResponseClass() {
        return ProductListResponse.class;
    }
}
