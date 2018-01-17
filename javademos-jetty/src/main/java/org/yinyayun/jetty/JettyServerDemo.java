/**
 * Copyright (c) 2016, yayunyin@126.com All Rights Reserved
 */
package org.yinyayun.jetty;

import java.io.File;
import java.io.FileInputStream;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.xml.XmlConfiguration;
import org.slf4j.Logger;
import org.yinyayun.demos.common.JCommanderExecutorAbstract;
import org.yinyayun.demos.common.PropertiesLoader;

/**
 * JettyServerDemo.java
 *
 * @author yinyayun
 */
public class JettyServerDemo extends JCommanderExecutorAbstract<JettyServerDemo> {
    public static void main(String[] args) {
        new JettyServerDemo().executeMain(JettyServerDemo.class, args);
    }

    @Override
    protected void execute(PropertiesLoader<JettyServerDemo> propertiesLoader, Logger logger, String[] args)
            throws Exception {
        String jettyHome = propertiesLoader.getString("jetty.home");
        System.setProperty("instance.home", jettyHome);
        XmlConfiguration configuration = new XmlConfiguration(new FileInputStream(new File(jettyHome + "/jetty.xml")));
        configuration.getProperties().put("instance.home", jettyHome);
        Server server = (Server) configuration.configure();
        server.start();
        server.join();
    }
}
