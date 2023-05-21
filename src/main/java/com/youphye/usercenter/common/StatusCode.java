package com.youphye.usercenter.common;

/**
 * @ClassName: StatusCode
 * @Package: com.youphye.usercenter.common
 * @Description: 账号状态码，封禁（正常）;
 * @Author Tina Serria
 * @Create 2023/5/17 11:26
 * @Version 1.0
 */
public enum StatusCode implements MyEnum {
	// 正常
	NORMAL(0),
	// 封禁
	BANNED(1);
	private final Integer code;

	StatusCode(Integer code) {
		this.code = code;
	}

	public Integer getCode() {
		return code;
	}

}
