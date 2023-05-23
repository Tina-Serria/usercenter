package com.youphye.usercenter.exception;

import com.youphye.usercenter.common.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @ClassName: GlobalExceptionHandler
 * @Package: com.youphye.usercenter.exception
 * @Description: 全局异常处理器，分为业务异常和系统异常。
 * @Author Tina Serria
 * @Create 2023/5/15 22:40
 * @Version 1.0
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
	/**
	 * @param businessException 业务异常
	 * @return Response
	 * @Description 业务失败，返回对应的错误对象
	 */
	@ExceptionHandler(BusinessException.class)
	public Response businessExceptionHandler(BusinessException businessException) {
		return Response.failed(businessException);
	}

	/**
	 * @param runtimeException 系统异常
	 * @return Response
	 * @Description 系统错误，返回系统错误，并且记录日志
	 */
	@ExceptionHandler(RuntimeException.class)
	public Response runtimeExceptionHandler(RuntimeException runtimeException) {
		return Response.error();
	}
}
