package com.moming.douapisdk.request;



import com.moming.douapisdk.DouYinRequest;
import com.moming.douapisdk.response.OrderLogisticsCompanyListResponse;

import java.util.Map;

/**
 * @author tianzong
 * @date 2020/7/23
 */
public class OrderLogisticsCompanyListRequest implements DouYinRequest<OrderLogisticsCompanyListResponse> {
    /**
     * 获取api的url地址
     *
     * @return url地址
     */
    @Override
    public String getApiUrl() {
        return "/order/logisticsCompanyList";
    }

    /**
     * 获取API的名称
     *
     * @return API名称
     */
    @Override
    public String getApiMethodName() {
        return "order.logisticsCompanyList";
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
        return null;
    }

    /**
     * 获取具体响应实现类的定义。
     *
     * @return 获取具体响应实现类的定义。
     */
    @Override
    public Class<OrderLogisticsCompanyListResponse> getResponseClass() {
        return OrderLogisticsCompanyListResponse.class;
    }
}
