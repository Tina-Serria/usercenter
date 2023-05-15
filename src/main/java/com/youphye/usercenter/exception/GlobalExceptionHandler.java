package com.youphye.usercenter.exception;

import com.youphye.usercenter.common.Response;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @ClassName: GlobalExceptionHandler
 * @Package: com.youphye.usercenter.exception
 * @Description:
 * @Author Tina Serria
 * @Create 2023/5/15 22:40
 * @Version 1.0
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
	/**
	 * @Description 业务失败，返回对应的错误对象
	 * @param businessException 业务失败抛出的异常
	 * @return Response
	 */
	@ExceptionHandler(BusinessException.class)
	public Response businessExceptionHandler(BusinessException businessException) {
		return Response.failed(businessException);
	}

	/**
	 * @Description 系统错误，返回系统错误
	 * @param runtimeException 系统异常
	 * @return Response
	 */
	@ExceptionHandler(RuntimeException.class)
	public Response runtimeExceptionHandler(RuntimeException runtimeException) {
		return Response.error(runtimeException.getMessage());
	}
}
