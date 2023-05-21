package com.youphye.usercenter.common;

/**
 * @ClassName: GenderCode
 * @Package: com.youphye.usercenter.common
 * @Description:
 * @Author Tina Serria
 * @Create 2023/5/17 13:06
 * @Version 1.0
 */
public enum GenderCode implements MyEnum {
	// 女性
	WOMAN(0),
	// 男性
	MAN(1);
	private final Integer code;

	GenderCode(Integer code) {
		this.code = code;
	}

	public Integer getCode() {
		return code;
	}
}
