package com.youphye.usercenter.common;

import com.youphye.usercenter.exception.BusinessException;
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

	public Response(Integer code, String message, String description, T data) {
		this.code = code;
		this.message = message;
		this.description = description;
		this.data = data;
	}

	/**
	 * @Description 因为执行成功
	 * @param statusCode 返回业务成功相关信息
	 * @param data 返回的数据
	 * @return Response<T>
	 */
	public static <T> Response<T> success(StatusCode statusCode, T data) {
		return new Response<>(statusCode, data);
	}


	/**
	 * @Description 业务失败的返回
	 * @param businessException 抛出的业务失败异常
	 * @return Response
	 */
	public static Response failed(BusinessException businessException) {
		return new Response<>(businessException.getCode(), businessException.getMessage(), businessException.getDescription(), null);
	}

	/**
	 * @Description 发送RuntimeException即系统错误时的返回方法
	 * @return Response 错误对象
	 */
	public static Response error() {
		return new Response<>(StatusCode.SYSTEM_ERROR, null);
	}
}
