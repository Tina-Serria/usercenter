package com.youphye.usercenter.common;

/**
 * @ClassName: RoleCode
 * @Package: com.youphye.usercenter.common
 * @Description: 角色码
 * @Author Tina Serria
 * @Create 2023/5/17 11:26
 * @Version 1.0
 */
public enum RoleCode implements MyEnum {
	// 用户
	USER(0),
	// 管理员
	ADMINISTRATOR(1);
	private final Integer code;

	RoleCode(Integer code) {
		this.code = code;
	}

	public Integer getCode() {
		return code;
	}
}
