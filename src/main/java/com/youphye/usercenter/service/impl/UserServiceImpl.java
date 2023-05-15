package com.youphye.usercenter.service.impl;

import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.youphye.usercenter.pojo.User;
import com.youphye.usercenter.service.UserService;
import com.youphye.usercenter.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author Tina Serria
* @description 针对表【user】的数据库操作Service实现
* @createDate 2023-05-15 08:52:45
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService {

	@Override
	public User register(String userName, String userPassword, String repeatPassword) {
		SecureUtil.md5();
		return null;
	}
}




