/**
 * Copyright (c) 2020 Copyright bp All Rights Reserved.
 * Author: lipengxiang
 * Date: 2020-2020/6/11 14:52
 * Desc:
 */
package cn.brainpoint.febs.identify.starter.event;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.bus.event.RefreshRemoteApplicationEvent;
import org.springframework.cloud.context.refresh.ContextRefresher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import cn.brainpoint.febs.identify.starter.IdentifyProperties;
import cn.brainpoint.febs.identify.starter.IdentifyService;
import lombok.extern.slf4j.Slf4j;

/**
 *
 *
 * @author pengxiang.li
 * @date 2020/6/11 2:52 下午
 */
@Component
@Slf4j
public class RefreshRemoteListener implements ApplicationListener<RefreshRemoteApplicationEvent> {

    @Autowired
    private Environment env;

    @Autowired
    private ContextRefresher contextRefresher;

    @Override
    public void onApplicationEvent(RefreshRemoteApplicationEvent event) {
        Set<String> keys = this.contextRefresher.refresh();

        if (keys.isEmpty()) {
            return;
        }

        handleRefreshIdentify(keys, null);
    }

    /***
     * handle identify config refresh.
     * @param keys
     */
    public void handleRefreshIdentify(final Set<String> keys, ApplicationContext context) {
        if (!containerKey(keys,
                "febs.identify.createMachineIdSelf",
                "febs.identify.db.type",
                "febs.identify.db.url",
                "febs.identify.db.username",
                "febs.identify.db.password",
                "febs.identify.db.tablename",
                "febs.identify.db.retryCount",
                "febs.identify.db.connectTimeout")) { return; }

        boolean createMachineIdSelf = this.env.getProperty("febs.identify.createMachineIdSelf", Boolean.class, false);
        String type = this.env.getProperty("febs.identify.db.type", String.class);
        String url = this.env.getProperty("febs.identify.db.url", String.class);
        String username = this.env.getProperty("febs.identify.db.username", String.class);
        String password = this.env.getProperty("febs.identify.db.password", String.class);
        String tablename = this.env.getProperty("febs.identify.db.tablename", String.class);
        Integer retryCount = this.env.getProperty("febs.identify.db.retryCount", Integer.class);
        Integer connectTimeout = this.env.getProperty("febs.identify.db.connectTimeout", Integer.class);

        new IdentifyService(createMachineIdSelf, IdentifyProperties.DB.Make(
                type,
                url,
                username,
                password,
                tablename,
                retryCount==null?0:retryCount.intValue(),
                connectTimeout==null?0:connectTimeout.intValue()
        ));
    }

    /**
     * 判断给定的key是否有一个存在于keys中
     * @param keys 如果为null则直接返回true.
     * @param key
     * @return
     */
    private static boolean containerKey(final Set<String> keys, final String... key) {
        if (null == keys) {
            return true;
        }

        for (String k : key) {
            if (keys.contains(k)) {
                return true;
            }
        }
        return false;
    }
}
