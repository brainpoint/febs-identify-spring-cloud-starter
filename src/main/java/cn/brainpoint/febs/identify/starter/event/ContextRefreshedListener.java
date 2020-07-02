/**
 * Copyright (c) 2020 Copyright bp All Rights Reserved.
 * Author: lipengxiang
 * Date: 2020-2020/6/11 14:52
 * Desc:
 */
package cn.brainpoint.febs.identify.starter.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import cn.brainpoint.febs.identify.starter.IdentifyProperties;
import cn.brainpoint.febs.identify.starter.IdentifyService;
import lombok.extern.slf4j.Slf4j;


/**
 * @author pengxiang.li
 * @date 2020/6/11 2:52 下午
 */
@Component
@Slf4j
public class ContextRefreshedListener implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private Environment env;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

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
}
