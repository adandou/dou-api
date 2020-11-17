package com.moming.douapisdk.request;

import com.moming.douapisdk.BaseDouYinRequest;
import com.moming.douapisdk.internal.util.DouYinHashMap;
import com.moming.douapisdk.response.OrderDetailResponse;
import lombok.*;

import java.util.Map;

/**
 * @author yangyang<chillz @ dingtalk.com>
 * @Date 2020/8/29 16:44
 * @Desc
 */
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class OrderDetailRequest  extends BaseDouYinRequest<OrderDetailResponse> {

    /**
     * 订单id 查询订单详情的时候 订单id最后面要拼接上A
     */
    public final static String ORDER_ID_END_CHAR = "A";

    /**
     * 主订单id eg: 6496679971677798670A
     */
    private String mainOrderId;

    @Override
    public String getApiUrl() {
        return "/order/detail";
    }

    @Override
    public String getApiMethodName() {
        return "order.detail";
    }

    /**
     * param_json={"order_id":"order_id_here"}
     *
     * @return
     */
    @Override
    public Map<String, String> getTextParams() {
        final DouYinHashMap map = new DouYinHashMap();
        if (!mainOrderId.endsWith(ORDER_ID_END_CHAR)) {
            mainOrderId = mainOrderId + ORDER_ID_END_CHAR;
        }
        map.put("order_id", this.mainOrderId);
        return map;
    }

    @Override
    public Class<OrderDetailResponse> getResponseClass() {
        return OrderDetailResponse.class;
    }
}
