package com.moming.douapisdk.response;

import com.moming.douapisdk.BaseDouYinResponse;
import com.moming.douapisdk.domain.OrderPage;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author tianzong
 * @date 2020/7/21
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class OrderListResponse extends BaseDouYinResponse {

    private static final long serialVersionUID = -7698845462133858817L;

    private OrderPage data;

}
