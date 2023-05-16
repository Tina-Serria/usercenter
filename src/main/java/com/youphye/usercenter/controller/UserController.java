package com.youphye.usercenter.controller;

import com.youphye.usercenter.common.Response;
import com.youphye.usercenter.common.StatusCode;
import com.youphye.usercenter.pojo.User;
import com.youphye.usercenter.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @ClassName: UserController
 * @Package: com.youphye.usercenter.controller
 * @Description:
 * @Author Tina Serria
 * @Create 2023/5/15 9:22
 * @Version 1.0
 */
@RestController
@Slf4j
@RequestMapping("/users")
public class UserController {
	@Resource
	UserService userService;

	@PostMapping("/register")
	public Response<User> userRegister(String userName, String userPassword, String repeatPassword) {
		User user = userService.register(userName, userPassword, repeatPassword);
		return Response.success(StatusCode.REGISTER_SUCCESS,user);
	}
	@PostMapping("/login")
	public Response userLogin(String userAccount, String userPassword) {

		return Response.success(StatusCode.LOGIN_SUCCESS,true);
	}

	@GetMapping("/{userAccount}")
	public Response<User> userSelect(@PathVariable Long userAccount) {
		User user = userService.select(userAccount);
		return Response.success(user);
	}

	@PutMapping
	public Response<User> userModify(@RequestBody User user) {
		return Response.success(user);
	}

	@DeleteMapping("/{userAccount}")
	public Response userDelete(@PathVariable Long userAccount) {
		return Response.success(true);
	}
}
