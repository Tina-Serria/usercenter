package com.youphye.usercenter.service;

import com.youphye.usercenter.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author Tina Serria
* @description 针对表【user】的数据库操作Service
* @createDate 2023-05-15 08:52:45
*/
public interface UserService extends IService<User> {
	/**
	 * @Description 用户注册
	 * @param userName 用户名
	 * @param userPassword 密码
	 * @param repeatPassword  重复密码
	 * @return User 用户对象
	 */
	public User register(String userName, String userPassword,String repeatPassword);
}
