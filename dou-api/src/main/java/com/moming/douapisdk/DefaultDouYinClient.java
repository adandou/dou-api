package com.moming.douapisdk;

import com.alibaba.fastjson.JSONObject;

import com.moming.douapisdk.internal.dto.RequestDTO;
import com.moming.douapisdk.internal.util.DouYinHashMap;
import com.moming.douapisdk.internal.util.DouYinUtils;
import com.moming.douapisdk.internal.util.HttpClientHelper;
import com.moming.douapisdk.internal.util.RequestParametersHolder;
import com.moming.douapisdk.response.OauthResponse;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.yaml.snakeyaml.scanner.Constant;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author tianzong
 * @date 2020/7/21
 */
@Data
@Log4j2
public class DefaultDouYinClient implements DouYinClient {

    protected String serverUrl;
    protected String appKey;
    protected String appSecret;

    private String version = "2";

    public DefaultDouYinClient() {
    }

    public DefaultDouYinClient(String appKey, String appSecret) {
        this.appKey = appKey;
        this.appSecret = appSecret;
    }


    public DefaultDouYinClient(String serverUrl, String appKey, String appSecret) {
        this.serverUrl = serverUrl;
        this.appKey = appKey;
        this.appSecret = appSecret;
    }

    public DefaultDouYinClient(String serverUrl, String appKey, String appSecret, String version) {
        this.serverUrl = serverUrl;
        this.appKey = appKey;
        this.appSecret = appSecret;
        this.version = version;
    }

    /**
     * 执行公开的API请求
     *
     * @param request 体的API请求类
     * @return 具体的API响应
     * @throws ApiException API调用异常
     */
    @Override
    public <T extends BaseDouYinResponse> T execute(DouYinRequest<T> request) throws ApiException {
        return execute(request, null);
    }

    /**
     * 执行隐私API请求。
     *
     * @param request     具体的API响应类
     * @param accessToken 用户授权码
     * @return 具体的API响应
     * @throws ApiException API调用异常
     */
    @Override
    public <T extends BaseDouYinResponse> T execute(DouYinRequest<T> request, String accessToken) throws ApiException {
        return _execute(request, accessToken);
    }

    /**
     * oauth2.0授权
     * @param code code授权码
     * @return
     */
    public OauthResponse code2Access(String code) throws ApiException {
        DouYinHashMap appParams = new DouYinHashMap();
        appParams.put("app_id", this.appKey);
        appParams.put("app_secret", this.appSecret);
        appParams.put("code", code);
        appParams.put("grant_type", Constants.AUTHORIZATION_CODE);
        OauthResponse tsr;
        String url = null;
        try {
            url = resolveApiUrl(Constants.AUTHORIZATION_PATH, appParams);
            log.info(" 换取token的url ： {} ", url);
            String body = HttpClientHelper.httpGet(url);
            tsr = JSONObject.parseObject(body,  OauthResponse.class);
            if (tsr == null) {
                tsr = new OauthResponse();
            }
            tsr.setRequestUrl(url);
        } catch (Exception e) {
            log.error("code2Access出现异常，url--{}", url == null ? "" : url, e);
            throw new ApiException(e);
        }
        return tsr;
    }


    /**
     *  刷新AccessToken
     *
     * @param refreshToken
     * @return
     * @throws ApiException
     */
    public OauthResponse refreshAccessToken(String refreshToken) throws ApiException {
        Objects.requireNonNull(refreshToken);
        final DouYinHashMap appParams = new DouYinHashMap();
        appParams.put("app_id", this.appKey);
        appParams.put("app_secret", this.appSecret);
        appParams.put("grant_type", Constants.REFRESH_GRANT_TYPE);
        appParams.put("refresh_token", refreshToken);
        OauthResponse tsr;
        String url = null;
        try {
            url = resolveApiUrl(Constants.REFRESH_TOKEN_PATH, appParams);
            log.info(" 刷新AccessToken的url ： {} ", url);
            String body = HttpClientHelper.httpGet(url);
            tsr = JSONObject.parseObject(body,  OauthResponse.class);
            if (tsr == null) {
                tsr = new OauthResponse();
            }
            tsr.setRequestUrl(url);
        } catch (Exception e) {
            log.error("code2Access出现异常，url--{}", url == null ? "" : url, e);
            throw new ApiException(e);
        }
        return tsr;
    }

    private <T extends BaseDouYinResponse> T _execute(DouYinRequest<T> request, String accessToken) throws ApiException {
        long start = System.currentTimeMillis();

        // 构建响应解释器

        // 调用api
        RequestParametersHolder requestHolder = invokeApi(request, accessToken, start);

        T tsr = null;
        // 解析结果
        try {
            tsr = parseBody(requestHolder, request);

            if (tsr.hasError()) {
                throw new Exception("调用api结果异常");
            }

        } catch (Exception e) {
            log.error("调用api接口发生错误，错误ErrorNo---{}, 错误Message---{}, 请求的Url--{}",
                    tsr.getErrNo(),
                    tsr.getMessage(),
                    tsr.getRequestUrl());
            throw new ApiException(e);
        }


        return tsr;
    }

    private <T extends BaseDouYinResponse> T parseBody(RequestParametersHolder requestHolder, DouYinRequest<T> request) throws IllegalAccessException, InstantiationException {


        T tsr = JSONObject.parseObject(requestHolder.getResponseBody(), request.getResponseClass());
        if (tsr == null) {
            tsr = request.getResponseClass().newInstance();
        }
        tsr.setParams(request.getTextParams());
        tsr.setRequestUrl(requestHolder.getRequestUrl());
        tsr.setBody(requestHolder.getResponseBody());


        return tsr;
    }

    private <T extends BaseDouYinResponse> RequestParametersHolder invokeApi(DouYinRequest<T> request, String session, long start) throws ApiException {
        RequestDTO requestDTO = new RequestDTO();
        requestDTO.setTextParams(request.getTextParams());
        requestDTO.setApiMethodName(request.getApiMethodName());
        requestDTO.setApiUrl(request.getApiUrl());
        return invokeApi(requestDTO, session, start);
    }

    protected RequestParametersHolder invokeApi(RequestDTO request, String accessToken, long start) throws ApiException{

            RequestParametersHolder requestHolder = new RequestParametersHolder();
            if (request.getTextParams() == null) {
                request.setTextParams(new HashMap<>(0));
            }
            DouYinHashMap appParams = new DouYinHashMap(request.getTextParams());
            requestHolder.setParamJson(appParams);


            // 添加协议级请求参数
            DouYinHashMap protocolParams = new DouYinHashMap();
            protocolParams.put(Constants.METHOD, request.getApiMethodName());
            protocolParams.put(Constants.VERSION, request.getApiVersion() != null ? request.getApiVersion() : version);
            if (appKey != null) {
                protocolParams.put(Constants.APP_KEY, appKey);
            }
            Long timestamp = request.getTimestamp();
            if (timestamp == null) {
                timestamp = System.currentTimeMillis();
            }

            protocolParams.put(Constants.TIMESTAMP, new Date(timestamp));
            protocolParams.put(Constants.PARAM_JSON, requestHolder.getParamJson());
            requestHolder.setProtocolParams(protocolParams);

            // 添加签名
            protocolParams.put(Constants.SIGN, DouYinUtils.signRequest(requestHolder, this.appSecret));

            // 签名不算access_token
            protocolParams.put(Constants.ACCESS_TOKEN, accessToken);
        try {
            // 调用httpClient处理
            String url = resolveApiUrl(request.getApiUrl(), requestHolder.getProtocolParams());
            log.info(" 请求API接口; url : {} ", url);
            requestHolder.setRequestUrl(url);
            String body = HttpClientHelper.httpGet(url);

            requestHolder.setResponseBody(body);

        } catch (Exception e) {
            log.error("调用api接口发生异常\r\n Url---->{}\r\n", requestHolder.getRequestUrl(), e);
            throw new ApiException(e);
        }
        return requestHolder;
    }

    private String resolveApiUrl(String apiUrl, Map<String, String> params) throws IOException {
        return this.serverUrl
                + apiUrl
                + "?"
                + HttpClientHelper.buildQuery(params, Constants.CHARSET_UTF8);
    }

}
