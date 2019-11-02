package com.springcloud.filter;


import com.springcloud.util.WebUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author ZhangLong on 2019/11/1  6:42 下午
 * @version V1.0
 */
@WebFilter(filterName = "webServerFilter",urlPatterns = "/*")
public class WebServerFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        //用来绑定请求 响应，在请求的整个过程都可以使用
        WebUtils.bindContext(httpServletRequest, httpServletResponse);

        chain.doFilter(request,response);
    }
}
