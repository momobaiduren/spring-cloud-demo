package com.springcloud.security;


import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class TokenAuthenticationFilter implements Filter {
    private static final String[] HEADERS = new String[]{"login-token", "x-token"};
    private static final String SUPPLIER_HEADER = ",supplier-code";
    @Autowired
    private IUserTokenService userTokenService;

    public void init(FilterConfig filterConfig) throws ServletException {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        this.setupResponseHeader((HttpServletRequest) request, (HttpServletResponse) response);

//        try {
        String token = this.fetchToken((HttpServletRequest) request);
        UserContext.saveUserOrElseCache(token, userTokenService);
        chain.doFilter(request, response);
//        } catch (BizException var6) {
//            wrapper = WrapMapper.wrap(ErrorCodeEnum.UC40001001.code(), ErrorCodeEnum.UC40001001.msg());
//            this.handleException(response, wrapper);
//        } catch (Exception var7) {
//            wrapper = WrapMapper.wrap(ErrorCodeEnum.GL99990500.code(), ErrorCodeEnum.GL99990500.msg());
//            this.handleException(response, wrapper);
//        }

    }

    private boolean isOptionsRequest(HttpServletRequest request) {
        return HttpMethod.OPTIONS.name().equals(request.getMethod());
    }

    private void setupResponseHeader(HttpServletRequest request, HttpServletResponse response) {
        String origin = request.getHeader("Origin");
        if (origin != null) {
            response.setHeader("Access-Control-Allow-Origin", origin);
        } else {
            response.setHeader("Access-Control-Allow-Origin", "*");
        }
        String allowHeaders = StringUtils.join(HEADERS, ", ");
        response.setHeader("Access-Control-Allow-Headers", "origin, content-type, accept, authorization, " + allowHeaders + ",supplier-code");
        response.setHeader("Access-Control-Expose-Headers", "Content-Disposition, " + allowHeaders + ",supplier-code");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Max-Age", "86400");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD, PATCH");
        if (HttpMethod.OPTIONS.name().equals(request.getMethod())) {
            response.setStatus(204);
        }
    }

    /**
     * create by ZhangLong on 2019/10/26
     * description 响应回错误信息  wrapper 本身为对象
     */
    private void handleException(ServletResponse response, String wrapper) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        try (PrintWriter writer = response.getWriter()) {
            writer.write(wrapper);
            writer.flush();
        }
    }

    public void destroy() {
    }

    private String fetchToken(HttpServletRequest request) {
        String[] var2 = HEADERS;
        int var3 = var2.length;

        for (int var4 = 0; var4 < var3; ++var4) {
            String header = var2[var4];
            String token = request.getHeader(header);
            if (StringUtils.isNotBlank(token)) {
                return token;
            }
        }

        return null;
    }
}

