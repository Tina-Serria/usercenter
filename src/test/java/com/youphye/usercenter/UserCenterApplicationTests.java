package com.youphye.usercenter;

import cn.hutool.core.util.ReUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.youphye.usercenter.common.MyConstant;
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
	@Test
	public void testMd5() {
		String md5 = SecureUtil.md5(MyConstant.SALT + "123456");
		System.out.println(md5);
		Assert.assertTrue(true);
	}

	@Test
	public void testRegx() {
		String content = "aa1我2aa";
		String specialCharacter = "[_`~!@#$%^&*()+=|{}':;',\\\\[\\\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？-]+";
		String word = "[a-zA-Z]+";
		String number = "[0-9]+";
		String USER_NAME = "^[a-zA-Z0-9\\u4E00-\\u9FA5]+$";
		boolean isMatch = ReUtil.isMatch(USER_NAME, content);
		Assert.assertTrue(isMatch);
	}

	@Test
	public void testStrUtil() {
		String[] args = new String[]{"tin a","","asdf"};
		Assert.assertTrue(StrUtil.hasBlank(args));
	}
}
