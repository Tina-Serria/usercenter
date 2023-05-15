package com.youphye.usercenter.service.impl;

import com.youphye.usercenter.pojo.User;
import com.youphye.usercenter.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * @ClassName: UserServiceImplTest
 * @Package: com.youphye.usercenter.service.impl
 * @Description:
 * @Author Tina Serria
 * @Create 2023/5/15 16:58
 * @Version 1.0
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceImplTest {
	@Resource
	private UserService userService;

	@Test
	public void register() {
		String userName = "serria";
		String userPassword = "1234abcd";
		String repeatPassword = "1234abcd";
		User user = userService.register(userName, userPassword, repeatPassword);
		assertNotNull(user);
		System.out.println(user);
	}
}