/**
 * Copyright (c) 2020 Copyright bp All Rights Reserved.
 * Author: lipengxiang
 * Date: 2020-2020/6/29 23:40
 * Desc:
 */
package cn.brainpoint.febs.identify.starter;

import cn.brainpoint.febs.identify.Identify;
import cn.brainpoint.febs.identify.IdentifyCfg;
import org.springframework.stereotype.Service;

/**
 * @author pengxiang.li
 * @date 2020/6/29 11:40 下午
 */
@Service
public class IdentifyService {
    public IdentifyService() {}

    public IdentifyService(boolean createMachineIdSelf, IdentifyProperties.DB properties) {
        if (properties != null && properties.getType() != null) {
            Identify.setupDatabase(new IdentifyCfg(
                    properties.getType(),
                    properties.getUrl(),
                    properties.getUsername(),
                    properties.getPassword(),
                    properties.getTablename(),
                    properties.getRetryCount(),
                    properties.getConnectTimeout()));

            if (createMachineIdSelf) {
                Identify.initializeByDatabase(null);
            }
        }
    }
}
