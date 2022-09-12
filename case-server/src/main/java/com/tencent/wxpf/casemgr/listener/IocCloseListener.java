package com.tencent.wxpf.casemgr.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * 容器监听器，用来看关闭容器时有哪些session存在
 *
 * @author jeeffzheng
 * @date 2020/12/14
 */
@WebListener
@Component
public class IocCloseListener implements ServletContextListener {

    private static Logger logger = LoggerFactory.getLogger(IocCloseListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        logger.info("[监听器注册成功..]" + IocCloseListener.class.getName());
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        logger.info("[监听到容器关闭..]" + IocCloseListener.class.getName());
//        for (Map.Entry<String, WebSocket> ws : WebSocket.webSocket.entrySet()) {
//            LOGGER.info("[Session Info]{}", SocketInfo.build(ws.getValue()).toString());
//        }
    }

//    @Data
//    static class SocketInfo {
//        private String serial;
//        private String user;
//
//        public static SocketInfo build(WebSocket webSocket) {
//            SocketInfo info = new SocketInfo();
//            info.setSerial(webSocket.currentSession());
//            info.setUser(webSocket.getUser());
//            return info;
//        }
//    }
}
