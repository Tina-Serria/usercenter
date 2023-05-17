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
	 * @param userName       用户名
	 * @param userPassword   密码
	 * @param repeatPassword 重复密码
	 * @return User 用户对象
	 * @Description 用户注册
	 */
	public User register(String userName, String userPassword, String repeatPassword);

	/**
	 * @param userAccount  账号
	 * @param userPassword 密码
	 * @return User
	 * @Description 用户登录
	 */
	public User login(Long userAccount, String userPassword);

	/**
	 * @param userAccount 账号
	 * @return Boolean
	 * @Description 注销
	 */
	public Boolean logout(Long userAccount);

	/**
	 * @param userAccount 账号
	 * @return User
	 * @Description 根据账号查询用户
	 */
	public User select(Long userAccount);

	/**
	 * @Description 修改用户信息
	 * @param user 需要修改的用户
	 * @return Boolean
	 */
	public Boolean modify(User user);

	/**
	 * @param userAccount 用户账号
	 * @return Boolean 删除成功返回 true
	 * @Description 删除用户
	 */
	public Boolean delete(Long userAccount);

}
