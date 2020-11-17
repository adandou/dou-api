package com.moming.douapisdk.response;

import com.moming.douapisdk.BaseDouYinResponse;
import com.moming.douapisdk.domain.DouYinProductPage;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * 商品列表返回值
 * @author lujingpo
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ProductListResponse extends BaseDouYinResponse {

    private static final long serialVersionUID = 352260591893748608L;
    /**
     * 商品单页数据
     */
    DouYinProductPage data;

}
