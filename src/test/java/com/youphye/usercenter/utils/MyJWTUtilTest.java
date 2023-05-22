package com.youphye.usercenter.utils;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @ClassName: MyJWTUtilTest
 * @Package: com.youphye.usercenter.utils
 * @Description:
 * @Author Tina Serria
 * @Create 2023/5/21 11:41
 * @Version 1.0
 */
public class MyJWTUtilTest {

	@Test
	public void create() {
		System.out.println();
	}

	@Test
	public void verify() {
		System.out.println(MyJWTUtil.verify("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2ODQ2NDMzMDksInVzZXJBY2NvdW50IjoiMTAwMDAxIiwidXNlclJvbGUiOjN9.QPtx-5ek6RVL3TeMQH8yfqNxvA_r5yB1ru9nFLoq69U"));
	}

}