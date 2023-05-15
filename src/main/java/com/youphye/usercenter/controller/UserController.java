package com.youphye.usercenter.controller;

import com.youphye.usercenter.common.Response;
import com.youphye.usercenter.pojo.User;
import com.youphye.usercenter.service.UserService;
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
@RequestMapping("/user")
public class UserController {
	@Resource
	UserService userService;

	@PostMapping("/register")
	public Response<User> register(@RequestBody String userName, String userPassword, String repeatPassword) {
		User user = userService.register(userName, userPassword, repeatPassword);
		return Response.success(user);
	}
}
