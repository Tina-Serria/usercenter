package com.youphye.usercenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.youphye.usercenter.common.ResponseCode;
import com.youphye.usercenter.common.MyConstant;
import com.youphye.usercenter.common.StatusCode;
import com.youphye.usercenter.exception.BusinessException;
import com.youphye.usercenter.pojo.User;
import com.youphye.usercenter.service.UserService;
import com.youphye.usercenter.mapper.UserMapper;
import com.youphye.usercenter.utils.UserDataUtil;
import org.springframework.stereotype.Service;

import java.util.List;

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
			throw new BusinessException(ResponseCode.PARAM_NULL);
		}
		// 用户名必须合法
		if (!UserDataUtil.checkUserName(userName)) {
			throw new BusinessException(ResponseCode.USERNAME_ILLEGAL);
		}
		// 两次密码必须相同
		if (!userPassword.equals(repeatPassword)) {
			throw new BusinessException(ResponseCode.PASSWORD_DIFFERENT);
		}
		// 密码必须合法
		if (!UserDataUtil.checkUserPassword(userPassword)) {
			throw new BusinessException(ResponseCode.PASSWORD_ILLEGAL);
		}
		// 获取最新用户的账号
		LambdaQueryWrapper<User> lambdaQuery = new LambdaQueryWrapper<>();
		lambdaQuery.orderByDesc(User::getUserAccount).last("limit 1");
		User lastUser = this.getOne(lambdaQuery);

		// 为当前用户创建User对象，并赋值
		User user = new User();
		// 默认账号递增
		if (lastUser == null) {
			user.setUserAccount(MyConstant.USER_ACCOUNT_START);
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
	public User login(Long userAccount, String userPassword) {
		// 不能为空，或者null或者“”
		if (UserDataUtil.hasBlank(userPassword)) {
			throw new BusinessException(ResponseCode.PARAM_NULL);
		}
		// 密码需要符合规则
		if (UserDataUtil.checkUserPassword(userPassword)) {
			throw new BusinessException(ResponseCode.LOGIN_FAILED);
		}
		LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
		lambdaQueryWrapper
				.eq(User::getUserAccount, userAccount)
				.eq(User::getUserPassword, UserDataUtil.md5(userPassword));
		User user = this.getOne(lambdaQueryWrapper);
		if (user == null) {
			throw new BusinessException(ResponseCode.LOGIN_FAILED);
		}
		return user;
	}

	@Override
	public User selectOne(Long userAccount) {
		LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
		lambdaQueryWrapper.eq(User::getUserAccount, userAccount);
		return this.getOne(lambdaQueryWrapper);
	}


	@Override
	public List<User> selectAll() {
		return this.getBaseMapper().selectList(null);
	}

	@Override
	public User modify(User user) {
		boolean checked = UserDataUtil.checkUser(user);
		// 检查User对象中是否存在非法字段
		if (!checked) {
			throw new BusinessException(ResponseCode.MODIFY_FAILED);
		}
		LambdaUpdateWrapper<User> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
		lambdaUpdateWrapper.eq(User::getUserAccount, user.getUserAccount());
		// 更新数据库中的用户信息
		boolean updated = this.update(lambdaUpdateWrapper);
		if (!updated) {
			throw new BusinessException(ResponseCode.MODIFY_FAILED);
		}
		LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
		lambdaQueryWrapper.eq(User::getUserAccount, user.getUserAccount());
		// 返回更新后的User对象
		return this.getOne(lambdaQueryWrapper);
	}

	@Override
	public void delete(Long userAccount) {
		// USER_ACCOUNT_START：100000之前的账号保留，因此不合法。
		if (userAccount < MyConstant.USER_ACCOUNT_START) {
			throw new BusinessException(ResponseCode.DELETE_FAILED);
		}
		LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
		lambdaQueryWrapper.eq(User::getUserAccount, userAccount);
		// 获取删除结果
		boolean removed = this.remove(lambdaQueryWrapper);
		if (!removed) {
			throw new BusinessException(ResponseCode.DELETE_FAILED);
		}
	}

	@Override
	public void deleteAll(List<Long> userAccountList) {
		LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
		// 循环删除所有用户
		for (Long userAccount : userAccountList) {
			if (userAccount < MyConstant.USER_ACCOUNT_START) {
				throw new BusinessException(ResponseCode.DELETE_FAILED);
			}
			lambdaQueryWrapper.clear();
			lambdaQueryWrapper.eq(User::getUserAccount, userAccount);
			this.remove(lambdaQueryWrapper);
		}
	}

	@Override
	public void banAll(List<Long> userAccountList) {
		LambdaUpdateWrapper<User> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
		// 循环删除所有用户
		for (Long userAccount : userAccountList) {
			if (userAccount < MyConstant.USER_ACCOUNT_START) {
				throw new BusinessException(ResponseCode.DELETE_FAILED);
			}
			lambdaUpdateWrapper.clear();
			lambdaUpdateWrapper.eq(User::getUserAccount, userAccount)
					.set(User::getUserStatus, StatusCode.BANNED);
			this.update(lambdaUpdateWrapper);
		}
	}
}




