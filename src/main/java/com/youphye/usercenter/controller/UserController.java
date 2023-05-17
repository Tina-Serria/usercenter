package com.youphye.usercenter.controller;

import com.youphye.usercenter.common.Response;
import com.youphye.usercenter.common.ResponseCode;
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
		return Response.success(ResponseCode.REGISTER_SUCCESS, user);
	}

	@PostMapping("/login")
	public Response userLogin(Long userAccount, String userPassword) {
		User user = userService.login(userAccount, userPassword);
		return Response.success(ResponseCode.LOGIN_SUCCESS, user);
	}

	@GetMapping("/{userAccount}")
	public Response<User> userSelect(@PathVariable Long userAccount) {
		User user = userService.select(userAccount);
		return Response.success(ResponseCode.SELECT_SUCCESS, user);
	}

	@PutMapping
	public Response<User> userModify(@RequestBody User user) {

		return Response.success(null,null);
	}

	@DeleteMapping("/{userAccount}")
	public Response userDelete(@PathVariable Long userAccount) {
		return Response.success(ResponseCode.DELETE_SUCCESS, true);
	}
}
