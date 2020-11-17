package com.moming.douapisdk;


import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;
import java.util.Map;

/**
 * @author tianzong
 * @date 2020/7/23
 */
@Data
public abstract class BaseDouYinResponse implements Serializable {

    private static final long serialVersionUID = -8976982130056201475L;

    /**
     * 错误码
     */
    @JSONField(name = "err_no")
    private Integer errNo ;

    /**
     * 错误信息
     */
    private String message;


    private String body;

    /**
     * 请求url
     */
    private String requestUrl;

    /**
     * api请求参数列表
     */
    private Map<String, String> params;

    /**
     *
     * 不用自己调用判断，会自动判断并记录异常日志
     * @return 是否有错误
     */
    public boolean hasError() {
        return this.errNo != null && this.errNo > 0;
    }

}
