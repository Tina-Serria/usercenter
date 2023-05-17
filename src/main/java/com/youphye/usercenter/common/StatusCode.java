package com.youphye.usercenter.common;

/**
 * @ClassName: StatusCode
 * @Package: com.youphye.usercenter.common
 * @Description: 账好状态码
 * @Author Tina Serria
 * @Create 2023/5/17 11:26
 * @Version 1.0
 */
public enum StatusCode implements  MyEnum {
	// 用户
	NORMAL(0),
	// 管理员
	DISABLED(1);
	private final Integer code;

	StatusCode(Integer code) {
		this.code = code;
	}

	public Integer getCode() {
		return code;
	}

}
