package com.youphye.usercenter.utils;

import com.youphye.usercenter.common.RoleCode;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * @ClassName: UserDataUtilTest
 * @Package: com.youphye.usercenter.utils
 * @Description:
 * @Author Tina Serria
 * @Create 2023/5/15 18:04
 * @Version 1.0
 */
public class UserDataUtilTest {
	@Test
	public void test() {
		String userName = "serria";
		String userPassword = "1234abc";
		String email = "tina@gmail.com";
		String phone = "15555555555";

		userName = " ";
		assertFalse(UserDataUtil.checkUserName(userName));
		userName = "se ";
		assertFalse(UserDataUtil.checkUserName(userName));
		userName = "ser*asdf123";
		assertFalse(UserDataUtil.checkUserName(userName));
		userName = "aser:dsaf  asdf asdfasdfasdfasdfasdfasdfasdfasdfasdffffffff";
		assertFalse(UserDataUtil.checkUserName(userName));
		userName = "123";
		assertFalse(UserDataUtil.checkUserName(userName));


		userPassword = "1";
		assertFalse(UserDataUtil.checkUserPassword(userPassword));
		userPassword = "1234sadgfadfsgasdfasdfasdfasdfasdfasdf";
		assertFalse(UserDataUtil.checkUserPassword(userPassword));
		userPassword = "1234345345345";
		assertFalse(UserDataUtil.checkUserPassword(userPassword));
		userPassword = "   123124124";
		assertFalse(UserDataUtil.checkUserPassword(userPassword));
		userPassword = "1234basdf!";
		assertTrue(UserDataUtil.checkUserPassword(userPassword));

		email = "@gmail.com";
		assertFalse(UserDataUtil.checkEmail(email));
		email = "asfasdfas";
		assertFalse(UserDataUtil.checkEmail(email));
		email = "f asd f";
		assertFalse(UserDataUtil.checkEmail(email));
		email = " ";
		assertFalse(UserDataUtil.checkEmail(email));
		email = "tinaserria@gmail..com";
		assertFalse(UserDataUtil.checkEmail(email));
		email = "tinaserria@gmail.com";
		assertTrue(UserDataUtil.checkEmail(email));

	}

	@Test
	public void test2() {
		Class enumClass = RoleCode.class;
//		enumClass.getDeclaredConstructor().newInstance();
		System.out.println(Arrays.toString(enumClass.getEnumConstants()));
//		System.out.println(Arrays.toString(enumClass.getDeclaredFields()));
		System.out.println( );
		assertTrue(true);

	}

	@Test
	public void codeInMyEnum() {
		UserDataUtil.codeInMyEnum(1,RoleCode.values());
	}
}