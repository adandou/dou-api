package com.moming.douapisdk.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;


/**
 * @author tianzong
 * @date 2020/7/23
 */
@Data
@ConfigurationProperties(prefix = "spring.dou-yin")
@Validated
public class DouYinProperties {

    @NotNull
    private String appHost;

    @NotNull
    private String appKey;

    @NotNull
    private String appSecret;

    private String version = "2";
}


