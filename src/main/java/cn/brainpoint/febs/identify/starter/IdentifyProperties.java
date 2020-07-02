/**
 * Copyright (c) 2020 Copyright bp All Rights Reserved.
 * Author: lipengxiang
 * Date: 2020-2020/6/24 15:17
 * Desc:
 */
package cn.brainpoint.febs.identify.starter;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 *
 *
 * @author pengxiang.li
 * @date 2020/6/24 3:17 下午
 */
@Data
@ConfigurationProperties(prefix = "febs.identify")
public class IdentifyProperties {

    /** create a machine id in this instance **/
    protected boolean createMachineIdSelf;

    @Data
    @ConfigurationProperties(prefix = "febs.identify.db")
    public static class DB {
        public static DB Make(String type, String url, String username, String password, String tablename, int retryCount, int connectTimeout) {
            DB prop = new DB();
            prop.type = type;
            prop.url = url;
            prop.username = username;
            prop.password = password;
            prop.tablename = tablename;
            prop.retryCount = retryCount;
            prop.connectTimeout = connectTimeout;
            return prop;
        }

        /** db type; e.g. mysql **/
        protected String type;
        /** db url; e.g. localhost:3306/xx **/
        protected String url;
        /** the username of database. **/
        protected String username;
        protected String password;
        /** the db table name. **/
        protected String tablename;
        /** retry to connect to database. **/
        protected int retryCount;
        /** connect timeout in milliseconds. **/
        protected int connectTimeout;
    }
}

