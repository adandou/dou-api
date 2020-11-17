package com.moming.douapisdk.internal.util;

import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.config.SocketConfig;
import org.apache.http.conn.DnsResolver;
import org.apache.http.conn.HttpConnectionFactory;
import org.apache.http.conn.ManagedHttpClientConnection;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.DefaultConnectionReuseStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.DefaultHttpResponseParserFactory;
import org.apache.http.impl.conn.ManagedHttpClientConnectionFactory;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.impl.conn.SystemDefaultDnsResolver;
import org.apache.http.impl.io.DefaultHttpRequestWriterFactory;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author tianzong
 * @date 2020/7/21
 */
@Log4j2
public class HttpClientHelper {

    private static PoolingHttpClientConnectionManager manager = null;
    private static CloseableHttpClient httpClient = getHttpClient();


    public HttpClientHelper() {
    }

    private static CloseableHttpClient getHttpClient() {


        //注册访问协议相关的Socket工厂
        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder
                .<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.INSTANCE)
                .register("https", SSLConnectionSocketFactory.getSystemSocketFactory())
                .build();

        //HttpConnection 工厂:配置写请求/解析响应处理器
        HttpConnectionFactory<HttpRoute, ManagedHttpClientConnection> connectionFactory
                = new ManagedHttpClientConnectionFactory(
                DefaultHttpRequestWriterFactory.INSTANCE,
                DefaultHttpResponseParserFactory.INSTANCE);
        //DNS 解析器
        DnsResolver dnsResolver = SystemDefaultDnsResolver.INSTANCE;
        //创建池化连接管理器
        manager = new PoolingHttpClientConnectionManager(socketFactoryRegistry,
                connectionFactory, dnsResolver);
        //默认为Socket配置
        SocketConfig defaultSocketConfig = SocketConfig.custom().setTcpNoDelay(true).build();
        manager.setDefaultSocketConfig(defaultSocketConfig);
        //设置整个连接池的最大连接数
        manager.setMaxTotal(300);
        //每个路由的默认最大连接，每个路由实际最大连接数由DefaultMaxPerRoute控制，而MaxTotal是整个池子的最大数
        //设置过小无法支持大并发(ConnectionPoolTimeoutException) Timeout waiting for connection from pool
        //每个路由的最大连接数
        manager.setDefaultMaxPerRoute(200);
        //在从连接池获取连接时，连接不活跃多长时间后需要进行一次验证，默认为2s
        manager.setValidateAfterInactivity(5 * 1000);
        //默认请求配置
        RequestConfig defaultRequestConfig = RequestConfig.custom()
                //设置连接超时时间，2s
                .setConnectTimeout(2 * 1000)
                //设置等待数据超时时间，5s
                .setSocketTimeout(5 * 1000)
                //设置从连接池获取连接的等待超时时间
                .setConnectionRequestTimeout(2000)
                .build();
        //创建HttpClient
        httpClient = HttpClients.custom()
                .setConnectionManager(manager)
                //连接池不是共享模式
                .setConnectionManagerShared(false)
                //定期回收空闲连接
                .evictIdleConnections(60, TimeUnit.SECONDS)
                // 定期回收过期连接
                .evictExpiredConnections()
                //连接存活时间，如果不设置，则根据长连接信息决定
                .setConnectionTimeToLive(60, TimeUnit.SECONDS)
                //设置默认请求配置
                .setDefaultRequestConfig(defaultRequestConfig)
                //连接重用策略，即是否能keepAlive
                .setConnectionReuseStrategy(DefaultConnectionReuseStrategy.INSTANCE)
                //长连接配置，即获取长连接生产多长时间
                .setKeepAliveStrategy(DefaultConnectionKeepAliveStrategy.INSTANCE)
                //设置重试次数，默认是3次，当前是禁用掉（根据需要开启）
                .setRetryHandler(new DefaultHttpRequestRetryHandler(0, false))
                .build();

        //JVM 停止或重启时，关闭连接池释放掉连接(跟数据库连接池类似)
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                try {
                    if (httpClient != null) {
                        httpClient.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        return httpClient;
    }


    public static String httpGet(String url) throws IOException {

        String resultJson = null;

        // 创建Get请求
        HttpGet httpGet = new HttpGet(url);
        // 响应模型
        CloseableHttpResponse response = null;
        try {
            // 由客户端执行(发送)Get请求
            response = httpClient.execute(httpGet);

            // 从响应模型中获取响应实体
            HttpEntity responseEntity = response.getEntity();

            resultJson = EntityUtils.toString(responseEntity);

        } finally {
            try {
                // 释放资源
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return resultJson;
    }

    /**
     * 发送POST请求 Http body传json参数
     *
     * @param url       目标URL
     * @param jsonParam JSON字符串参数
     * @return 响应的字符串
     * @throws IOException IOException
     */
    public static String httpPost(String url, String jsonParam) throws IOException {

        String resultJson = null;

        HttpPost httpPost = new HttpPost(url);
        // 响应模型
        CloseableHttpResponse response = null;
        try {
            HttpContext httpContext = new HttpClientContext();
            httpContext.setAttribute("accept", "*/*");
            httpContext.setAttribute("connection", "Keep-Alive");
            httpContext.setAttribute("Content-Type", "application/x-www-form-urlencoded");
            httpContext.setAttribute("Content-Length", String.valueOf(jsonParam.getBytes().length));
            log.debug("发送请求 ： {} ", url);
            // 由客户端执行(发送)Get请求
            response = httpClient.execute(httpPost, httpContext);

            // 从响应模型中获取响应实体
            HttpEntity responseEntity = response.getEntity();

            resultJson = EntityUtils.toString(responseEntity);

        } finally {
            try {
                // 释放资源
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return resultJson;
    }


    public static String buildQuery(Map<String, String> params, String charset) throws IOException {
        if (params == null || params.isEmpty()) {
            return null;
        }

        StringBuilder query = new StringBuilder();
        Set<Map.Entry<String, String>> entries = params.entrySet();
        boolean hasParam = false;

        for (Map.Entry<String, String> entry : entries) {
            String name = entry.getKey();
            String value = entry.getValue();
            // 忽略参数名或参数值为空的参数
            if (StringUtils.areNotEmpty(name, value)) {
                if (hasParam) {
                    query.append("&");
                } else {
                    hasParam = true;
                }
                String encode = URLEncoder.encode(value, charset);
                String s = encode.replaceAll("\\+", "%20");
                query.append(name).append("=").append(s);
            }
        }

        return query.toString();
    }
}
