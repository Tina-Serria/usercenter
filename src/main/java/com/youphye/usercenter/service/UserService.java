package com.youphye.usercenter.service;

import com.youphye.usercenter.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

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
	 * @Description 用户注册, 注册成功，返回JWT令牌。注册失败会抛出异常。
	 */
	public User register(String userName, String userPassword, String repeatPassword);

	/**
	 * @param userAccount  账号
	 * @param userPassword 密码
	 * @return User
	 * @Description 用户登录，登录成功返回JWT令牌。登录失败会抛出异常。
	 */
	public User login(Long userAccount, String userPassword);

	/**
	 * @param userAccount 账号
	 * @return User 用户对象
	 * @Description 查询单个用户，需要管理员权限，失败抛出异常。
	 */
	public User selectOne(Long userAccount);

	/**
	 * @return List<User>
	 * @Description 批量查询用户数据。需要管理员权限。失败会抛出异常。
	 */
	public List<User> selectAll();

	/**
	 * @param user 需要修改的用户
	 * @Description 修改用户信息，修改成功返回JWT。修改失败会抛出异常。
	 */
	public User modify(User user);

	/**
	 * @param userAccount 用户账号
	 * @Description 删除用户，用户自己可以注销自己。删除失败会抛出异常。
	 */
	public void delete(Long userAccount);

	/**
	 * @param userAccountList 用户账号列表
	 * @Description 批量删除用户数据，需要管理员权限 。失败会抛出异常
	 */
	public void deleteAll(List<Long> userAccountList);

	/**
	 * @param userAccountList 用户账号列表
	 * @Description 批量封禁用户，需要管理员权限，失败会抛出异常。
	 */
	public void banAll(List<Long> userAccountList);

}
