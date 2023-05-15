package com.youphye.usercenter;


import java.util.List;
import cn.hutool.core.util.ReUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.youphye.usercenter.common.MyConstant;
import com.youphye.usercenter.pojo.User;
import com.youphye.usercenter.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

public
@SpringBootTest
@RunWith(SpringRunner.class)
class UserCenterApplicationTests {
	@Resource
	private UserService userService;
	@Test
	public void testMd5() {
		String md5 = SecureUtil.md5(MyConstant.SALT + "123456");
		System.out.println(md5);
		Assert.assertTrue(true);
	}

	@Test
	public void testRegx() {
		String content = "aa1*2aa";
		String specialCharacter = "[_`~!@#$%^&*()+=|{}':;',\\\\[\\\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？-]+";
		String word = "[a-zA-Z]+";
		String number = "[0-9]+";
		boolean contains = ReUtil.contains(specialCharacter, content);
		Assert.assertTrue(contains);
	}

	@Test
	public void testStrUtil() {
		Assert.assertTrue(StrUtil.hasBlank(" "));
	}
	@Test
	public void testUserService(){
		List<User> list = userService.list();
		System.out.println(list);
		assertTrue(true);
	}
}
