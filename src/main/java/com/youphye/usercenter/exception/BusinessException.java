package com.youphye.usercenter.exception;

import com.youphye.usercenter.common.StatusCode;
import jdk.jshell.spi.ExecutionControl;

/**
 * @ClassName: BusinessException
 * @Package: com.youphye.usercenter.exception
 * @Description: 业务异常，业务执行失败的时候抛出。
 * @Author Tina Serria
 * @Create 2023/5/15 22:03
 * @Version 1.0
 */
public class BusinessException extends RuntimeException {
	private final Integer code;
	private final String description;

	public BusinessException(Integer code, String message, String description) {
		super(message);
		this.code = code;
		this.description = description;
	}

	public BusinessException(StatusCode statusCode) {
		this(statusCode.getCode(), statusCode.getMessage(), statusCode.getDescription());
	}

	public Integer getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}
}
