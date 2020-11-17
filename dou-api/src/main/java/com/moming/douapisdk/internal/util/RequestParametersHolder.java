package com.moming.douapisdk.internal.util;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import java.util.TreeMap;

/**
 * @author tianzong
 * @date 2020/7/21
 */
@Data
public class RequestParametersHolder {

    private String requestUrl;
    private String responseBody;

    private DouYinHashMap protocolParams;
    private DouYinHashMap paramJson;


    public String getParamJson() {
        return JSONObject.toJSONString(new TreeMap<>(this.paramJson));
    }

}
