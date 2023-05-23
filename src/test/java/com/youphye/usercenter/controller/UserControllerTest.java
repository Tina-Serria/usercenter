package com.youphye.usercenter.controller;

import com.youphye.usercenter.common.JWTResponse;
import com.youphye.usercenter.common.Response;
import com.youphye.usercenter.common.ResponseUser;
import com.youphye.usercenter.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @ClassName: UserControllerTest
 * @Package: com.youphye.usercenter.controller
 * @Description: UserController 测试类
 * @Author Tina Serria
 * @Create 2023/5/22 10:26
 * @Version 1.0
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserControllerTest {

	@Resource
	UserController userController;

	@Test
	public void register() {
		String userName = "tina";
		String userPassword = "1234aaaa";
		String repeatPassword = "1234aaaa";
		JWTResponse jwtResponse = userController.register(userName, userPassword, repeatPassword);
		System.out.println(jwtResponse.getJwt());
		assertTrue(true);
	}

	@Test
	public void login() {
		Long userAccount = 100004L;
		String userPassword = "1234aaaa";
		JWTResponse jwtResponse = userController.login(userAccount, userPassword);
		System.out.println(jwtResponse.getJwt());
		assertTrue(true);
	}

	@Test
	public void selectOne() {
		Response<ResponseUser> userResponse = userController.selectOne(100001L);
		System.out.println(userResponse.getData());
		assertTrue(true);
	}

	@Test
	public void selectAll() {
		Response<List<ResponseUser>> userListResponse = userController.selectAll();
		System.out.println(userListResponse.getData());
		assertTrue(true);
	}

	@Test
	public void modify() {
		User user = new User();
		user.setUserName("Christina");
		user.setUserAccount(100004L);
		JWTResponse modify = userController.modify(ResponseUser.create(user));
		System.out.println(modify);
		assertTrue(true);
	}

	@Test
	public void delete() {
		String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2ODQ3MjU2NzQsImp3dERhdGEiOiJ7XCJ1c2VyTmFtZVwiOlwiQ2hyaXN0aW5hXCIsXCJ1c2VyQWNjb3VudFwiOjEwMDAwNCxcInVzZXJSb2xlXCI6MH0ifQ.-LVAVwHPxW6tfYFtfMYgOkrekn4MBXj0r9d7dTKcAW0";
		Response deleted = userController.delete(token);
		System.out.println(deleted);
		assertTrue(true);
	}

	@Test
	public void deleteAll() {
		Response response = userController.deleteAll(List.of(100001L, 100002L));
		System.out.println(response);
		assertTrue(true);
	}

	@Test
	public void banAll() {
		Response response = userController.banAll(List.of(100000L, 100003L));
		System.out.println(response);
		assertTrue(true);
	}
}