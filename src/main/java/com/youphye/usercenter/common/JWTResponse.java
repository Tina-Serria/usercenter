package com.youphye.usercenter.common;

import com.youphye.usercenter.pojo.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @ClassName: JWTResponse
 * @Package: com.youphye.usercenter.common
 * @Description: 需要携帶JWT令牌和User对象的返回对象。继承Response<User>
 * @Author Tina Serria
 * @Create 2023/5/21 15:30
 * @Version 1.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class JWTResponse extends Response<User> {
	private String jwt;

	public JWTResponse(ResponseCode responseCode, User data, String jwt) {
		super(responseCode, data);
		this.jwt = jwt;
	}

	public static JWTResponse success(ResponseCode responseCode, User data, String jwt) {
		return new JWTResponse(responseCode, data, jwt);
	}
}
