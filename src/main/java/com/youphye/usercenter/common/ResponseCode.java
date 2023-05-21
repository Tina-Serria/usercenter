package com.youphye.usercenter.common;

/**
 * @ClassName: Code
 * @Package: com.youphye.usercenter.common
 * @Description: 业务返回码枚举，包括业务状态码和系统状态码
 * @Author Tina Serria
 * @Create 2023/5/15 9:03
 * @Version 1.0
 */
public enum ResponseCode  implements MyEnum{
	REGISTER_SUCCESS(40001, "注册成功", "注册成功"),
	LOGIN_SUCCESS(40002, "登录成功", "登录成功"),
	SELECT_SUCCESS(40003, "查询成功", "查询成功"),
	DELETE_SUCCESS(40004,"注销成功","删除成功"),
	LOGOUT_SUCCESS(40005,"退出成功","退出成功"),
	MODIFY_SUCCESS(40006, "修改成功","修改成功"),

	PARAM_NULL(40000, "操作失败", "输入不能为空"),

	/*
		注册失败
	 */
	USERNAME_ILLEGAL(40101, "注册失败", "用户名长度应为4-32位，不能包含特殊字符"),
	PASSWORD_DIFFERENT(40102, "注册失败", "两次输入的密码不同"),
	PASSWORD_ILLEGAL(40103, "注册失败", "密码长度应为8-32位，且至少包含数字，字母，特殊字符中的两种"),

	/*
		登录失败
	 */
	LOGIN_FAILED(40201, "登录失败", "账号或密码错误"),
	IDENTIFY_EXPIRED(40202, "登录过期","登录过期，请重新登录"),

	/*
		注销失败
	 */
	DELETE_FAILED(40401,"注销失败","注销失败，请稍后重试"),
	/*
		退出失败
	 */

	/*
		修改失败
	 */
	MODIFY_FAILED(40601,"修改失败","修改失败，请检查是否有不合法字段"),

	SYSTEM_ERROR(50100, "系统异常", "网络环境异常");
	private final Integer code;
	private final String message;
	private final String description;

	ResponseCode(Integer code, String message, String description) {
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
