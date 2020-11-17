package com.moming.douapisdk.internal.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Map;

/**
 * @author tianzong
 * @date 2020/7/21
 */
@Data
public class RequestDTO implements Serializable {
    private static final long serialVersionUID = 188633764220380665L;

    private String apiUrl;

    private String apiMethodName;

    private String appKey;

    private Map<String, String> textParams;

    private Long timestamp;

    private String apiVersion;
}
