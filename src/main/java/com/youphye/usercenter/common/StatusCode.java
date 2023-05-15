package com.youphye.usercenter.common;

/**
 * @ClassName: Code
 * @Package: com.youphye.usercenter.common
 * @Description: 状态码枚举，包括业务状态码和系统状态码
 * @Author Tina Serria
 * @Create 2023/5/15 9:03
 * @Version 1.0
 */
public enum StatusCode {
	BUSINESS_SUCCESS(40000, "OK", ""),
	BUSINESS_ERROR(40001, "参数错误", ""),
	SYSTEM_ERROR(50101,"系统错误","");
	private final Integer code;
	private final String message;
	private final String description;

	StatusCode(Integer code, String message, String description) {
		this.code = code;
		this.message = message;
		this.description = description;
	}

	public Integer getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	public String getDescription() {
		return description;
	}
}
