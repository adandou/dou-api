package com.moming.douapisdk;

/**
 * 抖音客户端
 *
 * @author tianzong
 * @date 2020/7/23
 */
public interface DouYinClient {

    /**
     * 执行公开的API请求
     * @param request 体的API请求类
     * @param <T> 具体的API请求类
     * @return 具体的API响应
     * @throws ApiException API调用异常
     */
     <T extends BaseDouYinResponse> T execute(DouYinRequest<T> request) throws ApiException;

    /**
     * 执行隐私API请求。
     * @param request 具体的API响应类
     * @param accessToken 用户授权码
     * @param <T> 具体的API响应类
     * @return 具体的API响应
     * @throws ApiException API调用异常
     */
     <T extends BaseDouYinResponse> T execute(DouYinRequest<T> request, String accessToken) throws ApiException;

}
