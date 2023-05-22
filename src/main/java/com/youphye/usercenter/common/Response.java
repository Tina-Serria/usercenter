package com.youphye.usercenter.common;

import com.youphye.usercenter.exception.BusinessException;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: Response
 * @Package: com.youphye.usercenter.common
 * @Description: 通用返回对象
 * @Author Tina Serria
 * @Create 2023/5/15 9:01
 * @Version 1.0
 */
@Data
@NoArgsConstructor
public class Response<T> {
	private Integer code;
	private T data;
	private String message;
	private String description;

	public Response(ResponseCode responseCode, T data) {
		this.code = responseCode.getCode();
		this.message = responseCode.getMessage();
		this.description = responseCode.getDescription();
		this.data = data;
	}

	public Response(Integer code, String message, String description, T data) {
		this.code = code;
		this.message = message;
		this.description = description;
		this.data = data;
	}

	/**
	 * @param responseCode 返回业务成功相关信息
	 * @param data         返回的数据
	 * @return Response<T>
	 * @Description 因为执行成功
	 */
	public static <T> Response<T> success(ResponseCode responseCode, T data) {
		return new Response<>(responseCode, data);
	}
	public static  Response success(ResponseCode responseCode) {
		return new Response<>(responseCode, null);
	}


	/**
	 * @param businessException 抛出的业务失败异常
	 * @return Response
	 * @Description 业务失败的返回
	 */
	public static Response failed(BusinessException businessException) {
		return new Response<>(businessException.getCode(), businessException.getMessage(), businessException.getDescription(), null);
	}

	/**
	 * @return Response 错误对象
	 * @Description 发送RuntimeException即系统错误时的返回方法
	 */
	public static Response error() {
		return new Response<>(ResponseCode.SYSTEM_ERROR, null);
	}
}
