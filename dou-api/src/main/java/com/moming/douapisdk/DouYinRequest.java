package com.moming.douapisdk;

import java.util.Map;

/**
 * Api请求接口
 */
public interface DouYinRequest <T extends BaseDouYinResponse> {

    /**
     * 获取api的url地址
     * @return url地址
     */
    public String getApiUrl();

    /**
     * 获取API的名称
     * @return API名称
     */
    public String getApiMethodName();

    /**
     * 获取所有的key-value形式的文本请求参数集合，其中：
     * <ul>
     *     <li>Key: 请求参数名</li>
     *     <li>Value: 请求参数值</li>
     * </ul>
     * @return 文本请求参数集合
     */
    public Map<String, String> getTextParams();

    /**
     * 获取具体响应实现类的定义。
     * @return 获取具体响应实现类的定义。
     */
    public Class<T> getResponseClass();
}
