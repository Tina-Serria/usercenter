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
	BUSINESS_SUCCESS(40000, "执行成功", "执行成功"),
	PARAM_NULL(40100,"执行失败","用户名和密码不能为空"),
	USERNAME_ILLEGAL(40200,"执行失败","用户名长度应为4-32位，不能包含特殊字符"),
	PASSWORD_DIFFERENT(40300,"执行失败","两次输入的密码不同"),
	PASSWORD_ILLEGAL(40400,"执行失败","密码长度应为8-32位，且至少包含数字，字母，特殊字符中的两种"),
	USER_ACCOUNT_ILLEGAL(40500,"执行失败","账号不存在"),
	SYSTEM_ERROR(50100,"系统错误","");
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
