package com.moming.douapisdk.request;


import com.moming.douapisdk.BaseDouYinRequest;
import com.moming.douapisdk.internal.util.DouYinHashMap;
import com.moming.douapisdk.response.OrderListResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * @author tianzong
 * @date 2020/7/21
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class OrderListRequest extends BaseDouYinRequest<OrderListResponse> {

    /**
     * 按创建时间排序
     */
    public final static String ORDER_BY_FIELD_CREATE = "create_time";

    /**
     * 按更新时间排序
     */
    public final static String ORDER_BY_FIELD_UPDATE = "update_time";

    /**
     * 子订单状态
     */
    private String orderStatus;

    /**
     * 开始时间
     */
    private LocalDateTime startTime;

    /**
     * 开始时间
     */
    private LocalDateTime endTime;

    /**
     * 搜索时间条件：按订单创建时间create_time；按订单更新时间进行搜索update_time
     * 默认创建时间
     */
    private String orderBy;
    /**
     * 订单排序方式：设置了此字段即为desc(最近的在前)
     * 默认为asc（最近的在后）
     */
    private String isDesc;

    /**
     * 页数（默认为0，第一页从0开始）
     */
    private String page;

    /**
     * 每页订单数（默认为10，最大100）
     */
    private String size;

    /**
     * 获取api的url地址
     *
     * @return url地址
     */
    @Override
    public String getApiUrl() {
        return "/order/list";
    }

    @Override
    public String getApiMethodName() {
        return "order.list";
    }

    /**
     * 获取所有的key-value形式的文本请求参数集合，其中：
     * <ul>
     *     <li>Key: 请求参数名</li>
     *     <li>Value: 请求参数值</li>
     * </ul>
     *
     * @return 文本请求参数集合
     */
    @Override
    public Map<String, String> getTextParams() {
        DouYinHashMap textParams = new DouYinHashMap();
        textParams.put("order_status", this.orderStatus);
        textParams.put("start_time", this.startTime);
        textParams.put("end_time", this.endTime);
        textParams.put("order_by", this.orderBy);
        textParams.put("is_desc", this.isDesc);
        textParams.put("page", this.page);
        textParams.put("size", this.size);

        return textParams;
    }

    /**
     * 获取具体响应实现类的定义。
     */
    @Override
    public Class<OrderListResponse> getResponseClass() {
        return OrderListResponse.class;
    }
}
