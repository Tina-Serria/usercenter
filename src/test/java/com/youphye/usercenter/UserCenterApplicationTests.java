package com.youphye.usercenter;

import cn.hutool.Hutool;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import cn.hutool.core.util.ReUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.youphye.usercenter.common.MyConstant;
import org.junit.Assert;
import org.junit.Test;


public
class UserCenterApplicationTests {

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
}
