package com.youphye.usercenter.common;


/**
 * @ClassName: Constant
 * @Package: com.youphye.usercenter.common
 * @Description:
 * @Author Tina Serria
 * @Create 2023/5/15 16:35
 * @Version 1.0
 */
public class MyConstant {
	/*
		密码加密所用的盐
	 */
	public static final String SALT = "youphye";
	/*
		账号从这里开始自增，其他的账号保留。
	 */
	public static final Long USER_ACCOUNT_START = 100000L;
	/*
		超时时间为10分钟。单位是毫秒。
	 */
	public static final Long TIMEOUT = 600000L;
	public static final String JWT_DATA = "jwtData";

	public static final String TOKEN = "token";
	public static final String ALL = "all";
	public static final String SELECT = "select";
	public static final String CHARSET = "UTF-8";
	public static final String CONTENT_TYPE = "application/json;charset=UTF-8";

}
