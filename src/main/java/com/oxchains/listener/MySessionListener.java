package com.oxchains.listener;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 监听session创建和销毁
 * 
 * @author liuruichao
 * Created on 2016-01-06 14:57
 */
public class MySessionListener implements HttpSessionListener {
    private static final Map<String, HttpSession> sessionMap = new ConcurrentHashMap<>();

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        sessionMap.put(se.getSession().getId(), se.getSession());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        sessionMap.remove(se.getSession().getId());
    }

    public static HttpSession getSession(String sessionId) {
        return sessionMap.get(sessionId);
    }
}
