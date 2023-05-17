package com.youphye.usercenter.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
	public static final String SUCCESS = "执行成功";
	public static final String FAILED = "执行失败";
	public static final String ERROR = "系统错误";

}
