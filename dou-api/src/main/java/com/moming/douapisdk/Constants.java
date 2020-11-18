package com.moming.douapisdk;

/**
 * @author tianzong
 * @date 2020/7/21
 */
public class Constants {
    /** 协议入参共享参数 **/
    public static final String APP_KEY = "app_key";
    public static final String METHOD = "method";
    public static final String TIMESTAMP = "timestamp";
    public static final String VERSION = "ver";
    public static final String SIGN = "sign";
    public static final String ACCESS_TOKEN = "accesstoken";
    public static final String PARAM_JSON = "param";

    public static final String AUTHORIZATION_CODE = "code";

    public static final String REFRESH_GRANT_TYPE = "refresh_token";
    /**
     * 授权访问地址
     */
    public static final  String AUTHORIZATION_PATH = "/oauth2/access_token";

    /**
     * 刷新token的地址
     */
    public static final String REFRESH_TOKEN_PATH = "/oauth2/refresh_token";


    /** 协议出参共享参数 */
    public static final String ERROR_CODE = "err_no";
    public static final String ERROR_MSG = "message";

    /** 默认时间格式 **/
    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /** Date默认时区 **/
    public static final String DATE_TIMEZONE = "GMT+8";

    /** UTF-8字符集 **/
    public static final String CHARSET_UTF8 = "UTF-8";

    /** HTTP请求相关 **/
    public static final String METHOD_POST = "POST";
    public static final String METHOD_GET = "GET";
    public static final String CTYPE_FORM_DATA = "application/x-www-form-urlencoded";
    public static final String CTYPE_FILE_UPLOAD = "multipart/form-data";
    public static final String CTYPE_TEXT_XML = "text/xml";
    public static final String CTYPE_APPLICATION_XML = "application/xml";
    public static final String CTYPE_TEXT_PLAIN = "text/plain";
    public static final String CTYPE_APP_JSON = "application/json";
    
    /** others **/
}
