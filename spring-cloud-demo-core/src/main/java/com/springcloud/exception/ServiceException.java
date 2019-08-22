package com.springcloud.exception;

/**
 * 业务异常处理类
 * 
 * 继承自RuntimeException, 从由Spring管理事务的函数中抛出时会触发事务回滚.
 * 
 * @author Bruce.Yang
 */
public class ServiceException extends RuntimeException {

	private static final long serialVersionUID = -4122882870690804299L;

	/**
	 * 消息标示代码
	 */
	private String messageCode;

	/**
	 * 异常信息
	 */
	private String message;

	public String getMessageCode() {
		return messageCode;
	}

	public String getMessage() {
		return message;
	}

	/**
	 * 抛出异常 无信息
	 */
	public ServiceException() {
		super();
	}

	/**
	 * 封装异常信息
	 * 
	 * @param messageCode : 消息标示代码
	 * 
	 * @see 也可以将exception的message 直接传入该方法; 
	 */
	public ServiceException(String messageCode) {
		this.messageCode = messageCode;
	}

	/**
	 * 封装异常信息
	 * 
	 * @param messageCode : 消息标示代码
	 * @param messages : 异常信息
	 */
	public ServiceException(String messageCode, String message) {
		this.messageCode = messageCode;
		this.message = message;
	}

	/**
	 * 封装异常信息,将Exception直接输入到该方法中
	 * 
	 * @param cause
	 */
	public ServiceException(Throwable cause) {
		super(cause);
	}

	/**
	 * 封装异常信息, 将Exception直接输入到该方法中
	 * 
	 * @param message
	 * @param cause
	 */
	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}
}