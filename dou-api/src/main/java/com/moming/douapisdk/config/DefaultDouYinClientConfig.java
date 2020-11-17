package com.moming.douapisdk.config;

import com.moming.douapisdk.DefaultDouYinClient;
import com.moming.douapisdk.properties.DouYinProperties;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author tianzong
 * @date 2020/7/23
 */
@Configuration
@EnableConfigurationProperties(value = DouYinProperties.class)
@Log4j2
public class DefaultDouYinClientConfig {

    @Bean
    @ConditionalOnMissingBean(DefaultDouYinClient.class)
    public DefaultDouYinClient defaultDouYinClient(DouYinProperties douYinProperties) {

        log.info("create DouYinClient success---{}", douYinProperties.toString());

        return new DefaultDouYinClient(
                douYinProperties.getAppHost(),
                douYinProperties.getAppKey(),
                douYinProperties.getAppSecret(),
                douYinProperties.getVersion()
        );
    }
}
