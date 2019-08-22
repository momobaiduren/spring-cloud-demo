package com.springcloud.exception;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

/**
 * Interface 异常处理控制器
 * 捕捉业务异常与系统异常，并以JSON 字符串方式返回给调用方
 *
 * Created by yangwunan on 16/10/25.
 */
@ControllerAdvice
public class SystemExceptionHandler {

	private final static Logger LOGGER = LoggerFactory.getLogger(SystemExceptionHandler.class);

	/**
	 * 未认证方法签名
	 *
	 * @return 如果当前用户已登录，但没操作权限，响应页面为WEB-INF/page/exception/unauthorized.html,
	 *         如果用户未认证（登录），响应页面为WEB-INF/page/login.html,
	 */
	@ExceptionHandler(UnautherizedException.class)
	public String unauthorized(UnautherizedException ue) {
		if (ue.getMessage() == null) {
			return "exception/unauthorized";
		} else {
			return "redirect:/login";
		}
	}

	/**
	 *
	 * 出现 service 异常拦截的方法签名
	 *
	 * @param se 业务层异常
	 * @return 响应页面 : WEB-INF/page/exception/service-exception.html
	 */
	@ExceptionHandler(ServiceException.class)
	public String serviceException(ServiceException se) {
		LOGGER.error("系统发生业务异常", se);
		RequestContextHolder.getRequestAttributes().setAttribute("exception", se, RequestAttributes.SCOPE_REQUEST);
		return "exception/service-exception";
	}

	/**
	 *
	 * 出现任何异常拦截方法签名(除了ServiceException),由于 map 验证使用了 aop 当验证不通过时， 会抛出 aop 异常而不是
	 * BindException 异常。
	 *
	 * 所以通过该方法判断异常类是否为 BindException 异常，如果是，将跳转到:
	 * WEB-INF/page/exception/bind-exception.html页面。
	 *
	 * @param throwable 异常类
	 *
	 * @return 如果 BindException 异常，响应页面 :
	 *         WEB-INF/page/exception/bind-exception.html, 否则响应页面 :
	 *         WEB-INF/page/exception/global-exception.html
	 */
	@ExceptionHandler
	public String globalException(Throwable throwable) {
		if (throwable.getCause() instanceof BindException) {
			BindException bindException = (BindException) throwable.getCause();
			List<ObjectError> errors = bindException.getAllErrors();
			RequestContextHolder.getRequestAttributes().setAttribute("errors", errors, RequestAttributes.SCOPE_REQUEST);
			return "exception/bind-exception";
		}
		LOGGER.error("系统发生业务异常", throwable);
		return "exception/global-exception";
	}

}