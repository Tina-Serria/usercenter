package com.youphye.usercenter.controller;

import com.youphye.usercenter.common.*;
import com.youphye.usercenter.pojo.User;
import com.youphye.usercenter.service.UserService;
import com.youphye.usercenter.utils.MyJWTUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
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
	 * @Description 用户注册。注册成功返回JWT令牌以及ResponseUser对象
	 */
	@PostMapping("/register")
	public JWTResponse register(String userName, String userPassword, String repeatPassword) {
		User user = userService.register(userName, userPassword, repeatPassword);
		ResponseUser responseUser = ResponseUser.create(user);
		String jwt = MyJWTUtil.create(responseUser);
		return JWTResponse.success(ResponseCode.REGISTER_SUCCESS, responseUser, jwt);
	}

	/**
	 * @param userAccount  账号
	 * @param userPassword 密码
	 * @return JWTResponse 带有JWT和ResponseUser的返回对象
	 * @Description 用户登录。登录成功，生成并JWT和ResponseUser对象
	 */
	@PostMapping("/login")
	public JWTResponse login(Long userAccount, String userPassword) {
		User user = userService.login(userAccount, userPassword);
		ResponseUser responseUser = ResponseUser.create(user);
		String jwt = MyJWTUtil.create(responseUser);
		return JWTResponse.success(ResponseCode.LOGIN_SUCCESS, responseUser, jwt);
	}

	/**
	 * @param userAccount 账号
	 * @return Response<ResponseUser> 返回对象
	 * @Description 用户查询。根据账号查询单个用户，需要管理员权限。
	 */
	@GetMapping("/select/{userAccount}")
	public Response<ResponseUser> selectOne(@PathVariable Long userAccount) {
		User user = userService.selectOne(userAccount);
		ResponseUser responseUser = ResponseUser.create(user);
		return Response.success(ResponseCode.SELECT_SUCCESS, responseUser);
	}

	/**
	 * @return Response<List < ResponseUser>> 包含用户对象列表的返回对象
	 * @Description 查询全部用户。返回所有用户，需要管理员权限。
	 */
	@GetMapping("/selectAll")
	public Response<List<ResponseUser>> selectAll() {
		List<User> userList = userService.selectAll();
		List<ResponseUser> responseUserList = new ArrayList<>();
		for(User user:userList){
			responseUserList.add(ResponseUser.create(user));
		}
		return Response.success(ResponseCode.SELECT_SUCCESS, responseUserList);
	}

	/**
	 * @param responseUser 需要修改的用户对象
	 * @return JWTResponse 携带JWT令牌和User对象的返回对象
	 * @Description 修改用户。修改需要重新生成JWT令牌并返回JWT令牌以及User对象
	 */
	@PutMapping("/modify")
	public JWTResponse modify(@RequestBody ResponseUser responseUser) {
		User modifiedUser = userService.modify(User.create(responseUser));
		ResponseUser modifiedResponseUser = ResponseUser.create(modifiedUser);
		String jwt = MyJWTUtil.create(modifiedResponseUser);
		return JWTResponse.success(ResponseCode.MODIFY_SUCCESS, modifiedResponseUser, jwt);
	}

	/**
	 * @return Response 返回对象
	 * @Description 删除用户。用户自己可以删除自己的账号。从JWT令牌中解析数据。
	 */
	@DeleteMapping("/delete")
	public Response delete(@RequestHeader(MyConstant.TOKEN) String token) {
		JWTData jwtData = MyJWTUtil.verify(token);
		userService.delete(jwtData.getUserAccount());
		return Response.success(ResponseCode.DELETE_SUCCESS);
	}

	/**
	 * @param userAccountList 账号列表
	 * @return Response 返回对象
	 * @Description 删除所有列表中用户。需要管理员权限。
	 */
	@DeleteMapping("/deleteAll")
	public Response deleteAll(@RequestParam List<Long> userAccountList) {
		userService.deleteAll(userAccountList);
		return Response.success(ResponseCode.DELETE_SUCCESS);
	}
	@PutMapping("/banAll")
	public Response banAll(@RequestParam List<Long> userAccountList){
		userService.banAll(userAccountList);
		return Response.success(ResponseCode.BAN_SUCCESS);
	}
}
