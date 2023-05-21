package com.youphye.usercenter.controller;

import com.youphye.usercenter.common.JWTData;
import com.youphye.usercenter.common.JWTResponse;
import com.youphye.usercenter.common.Response;
import com.youphye.usercenter.common.MyConstant;
import com.youphye.usercenter.common.ResponseCode;
import com.youphye.usercenter.pojo.User;
import com.youphye.usercenter.service.UserService;
import com.youphye.usercenter.utils.MyJWTUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName: UserController
 * @Package: com.youphye.usercenter.controller
 * @Description:
 * @Author Tina Serria
 * @Create 2023/5/15 9:22
 * @Version 1.0
 */
@RestController
@RequestMapping("/users")
public class UserController {
	@Resource
	UserService userService;

	/**
	 * @param userName       用户名
	 * @param userPassword   密码
	 * @param repeatPassword 确认密码
	 * @return JWTResponse 携带JWT令牌和User对象的返回对象
	 * @Description 注册成功返回JWT令牌以及User对象
	 */
	@PostMapping("/register")
	public JWTResponse register(String userName, String userPassword, String repeatPassword) {
		User user = userService.register(userName, userPassword, repeatPassword);
		String jwt = MyJWTUtil.create(user);
		return JWTResponse.success(ResponseCode.REGISTER_SUCCESS, user, jwt);
	}

	/**
	 * @param userAccount  账号
	 * @param userPassword 密码
	 * @return JWTResponse 携带JWT令牌和User对象的返回对象
	 * @Description 登录成功返回JWT令牌以及User对象
	 */
	@PostMapping("/login")
	public JWTResponse login(Long userAccount, String userPassword) {
		User user = userService.login(userAccount, userPassword);
		String jwt = MyJWTUtil.create(user);
		return JWTResponse.success(ResponseCode.LOGIN_SUCCESS, user, jwt);
	}

	@GetMapping("/{userAccount}")
	public Response<User> selectOne(@PathVariable Long userAccount) {
		User user = userService.selectOne(userAccount);
		return Response.success(ResponseCode.SELECT_SUCCESS, user);
	}

	/**
	 * @return Response<List < User>> 包含用户对象列表的返回对象
	 * @Description 返回所有用户，需要管理员权限。
	 */
	@GetMapping
	public Response<List<User>> selectAll() {
		List<User> userList = userService.selectAll();
		return Response.success(ResponseCode.SELECT_SUCCESS, userList);
	}

	/**
	 * @param user 需要修改的用户对象
	 * @return JWTResponse 携带JWT令牌和User对象的返回对象
	 * @Description 修改需要重新生成JWT令牌并返回JWT令牌以及User对象
	 */
	@PutMapping
	public JWTResponse modify(@RequestBody User user) {
		User modifiedUser = userService.modify(user);
		String jwt = MyJWTUtil.create(modifiedUser);
		return JWTResponse.success(ResponseCode.MODIFY_SUCCESS, user, jwt);
	}

	/**
	 * @return Response 返回对象
	 * @Description 用户自己可以删除自己的账号。从JWT令牌中解析数据。
	 */
	@DeleteMapping
	public Response delete(@RequestHeader(MyConstant.TOKEN) String token) {
		JWTData jwtData = MyJWTUtil.verify(token);
		userService.delete(jwtData.getUserAccount());
		return Response.success(ResponseCode.DELETE_SUCCESS, true);
	}
}
