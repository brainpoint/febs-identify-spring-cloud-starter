/**
 * Copyright (c) 2020 Copyright bp All Rights Reserved.
 * Author: lipengxiang
 * Date: 2020-2020/6/24 16:40
 * Desc:
 */
package cn.brainpoint.febs.identify.starter;

import cn.brainpoint.febs.identify.starter.event.ContextRefreshedListener;
import cn.brainpoint.febs.identify.starter.event.RefreshRemoteListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(IdentifyProperties.class)
@ConditionalOnProperty(prefix = "febs.identify.db", name = {
        "type",
        "url",
        "username",
        "password"
}, matchIfMissing = false)
@Slf4j
public class IdentifyAutoConfigure {

    @Bean
    @ConditionalOnMissingBean
    IdentifyService identifyService (){
        return new IdentifyService();
    }

    @Bean
    @ConditionalOnMissingBean
    ContextRefreshedListener contextRefreshedListener (){
        return new ContextRefreshedListener();
    }

    @Bean
    @ConditionalOnMissingBean
    RefreshRemoteListener refreshRemoteListener (){
        return new RefreshRemoteListener();
    }
}
