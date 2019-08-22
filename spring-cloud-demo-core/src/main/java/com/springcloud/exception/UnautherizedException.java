package com.springcloud.exception;

/**
 * 未认证异常
 * 
 * @author Bruce.Yang
 */
public class UnautherizedException extends ServiceException {

	private static final long serialVersionUID = -6024574197533635147L;

	/**
	 * 抛出异常 无信息
	 */
	public UnautherizedException() {
		super();
	}
}
