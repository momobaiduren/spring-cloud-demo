package com.springcloud.util;

import org.springframework.boot.web.server.WebServer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author ZhangLong on 2019/11/1  5:15 下午
 * @version V1.0
 */
public final class WebUtils {

    private static final ThreadLocal<WebUtils.WebContext> threadContext = new ThreadLocal();

    private WebUtils() {}

    public static WebUtils.WebContext getContext() {
        WebUtils.WebContext webContext = threadContext.get();
        return webContext;
    }

    public static HttpServletRequest getRequest() {
        WebUtils.WebContext webContext = getContext();
        return webContext == null ? null : webContext.request;
    }

    public static HttpServletResponse getResponse() {
        WebUtils.WebContext webContext = getContext();
        return webContext == null ? null : webContext.response;
    }

    public static void bindContext(HttpServletRequest request, HttpServletResponse response) {
        threadContext.set(new WebUtils.WebContext(request, response));
    }

    public static void clearContext() {
        threadContext.remove();
    }

    public static String getBaseUrl() {
        if (!ContextUtils.isWeb()) {
            return "";
        } else {
            WebServer webServer = ContextUtils.getConfigurableWebServerApplicationContext().getWebServer();
            return webServer == null ? "" : "http://" + NetworkUtils.getIpAddress() + ":" + webServer.getPort() + PropertyUtils.getPropertyCache("server.servlet.context-path", "");
        }
    }
    public static class WebContext {

        private HttpServletRequest request;
        private HttpServletResponse response;

        WebContext(HttpServletRequest request, HttpServletResponse response) {
            this.request = request;
            this.response = response;
        }
    }
}
