package com.youphye.usercenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import static com.youphye.usercenter.common.StatusCode.*;
import static com.youphye.usercenter.common.MyConstant.*;

import com.youphye.usercenter.common.MyConstant;
import com.youphye.usercenter.exception.BusinessException;
import com.youphye.usercenter.pojo.User;
import com.youphye.usercenter.service.UserService;
import com.youphye.usercenter.mapper.UserMapper;
import com.youphye.usercenter.utils.UserDataUtil;
import org.springframework.stereotype.Service;

/**
 * @author Tina Serria
 * @description 针对表【user】的数据库操作Service实现
 * @createDate 2023-05-15 08:52:45
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

	@Override
	public User register(String userName, String userPassword, String repeatPassword) {
		// 参数不能为空，或者包含空格
		if (UserDataUtil.hasBlank(userName, userPassword, repeatPassword)) {
			throw new BusinessException(PARAM_NULL);
		}
		// 用户名必须合法
		if (!UserDataUtil.checkUserName(userName)) {
			throw new BusinessException(USERNAME_ILLEGAL);
		}
		// 两次密码必须相同
		if (!userPassword.equals(repeatPassword)) {
			throw new BusinessException(PASSWORD_DIFFERENT);
		}
		// 密码必须合法
		if (!UserDataUtil.checkUserPassword(userPassword)) {
			throw new BusinessException(PASSWORD_ILLEGAL);
		}
		// 获取最新用户的账号
		LambdaQueryWrapper<User> lambdaQuery = new LambdaQueryWrapper<>();
		lambdaQuery.orderByDesc(User::getUserAccount).last("limit 1");
		User lastUser = this.getOne(lambdaQuery);

		// 为当前用户创建User对象，并赋值
		User user = new User();
		// 默认账号递增
		if (lastUser == null) {
			user.setUserAccount(USER_ACCOUNT_START);
		} else {
			user.setUserAccount(lastUser.getUserAccount() + 1);
		}
		user.setUserName(userName);
		user.setUserPassword(UserDataUtil.md5(userPassword));
		// 向数据库写入用户
		this.save(user);
		// 返回
		return user;
	}

	@Override
	public User select(Long userAccount) {
		// USER_ACCOUNT_START：100000之前的账号保留，因此不合法。
		if (userAccount < USER_ACCOUNT_START) {
			throw new BusinessException(USER_ACCOUNT_ILLEGAL);
		}
		LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
		lambdaQueryWrapper.eq(User::getUserAccount, userAccount);
		// 返回如果是null 表示没有用户
		return this.getOne(lambdaQueryWrapper);
	}
}




