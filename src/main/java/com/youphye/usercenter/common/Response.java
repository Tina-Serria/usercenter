package com.youphye.usercenter.common;

import lombok.Data;

/**
 * @ClassName: Response
 * @Package: com.youphye.usercenter.common
 * @Description: 通用返回对象
 * @Author Tina Serria
 * @Create 2023/5/15 9:01
 * @Version 1.0
 */
@Data
public class Response<T> {
	private Integer code;
	private T data;
	private String message;
	private String description;

	public Response(StatusCode statusCode, T data) {
		this.code = statusCode.getCode();
		this.message = statusCode.getMessage();
		this.description = statusCode.getDescription();
		this.data = data;
	}

	public static <T> Response<T> success(T data) {
		return new Response<>(StatusCode.BUSINESS_SUCCESS, data);
	}

	public static <T> Response<T> systemError() {
		return new Response<>(StatusCode.SYSTEM_ERROR, null);
	}

	public static <T> Response<T> businessError(StatusCode statusCode) {
		return new Response<>(statusCode, null);
	}
}
