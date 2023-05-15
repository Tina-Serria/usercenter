package com.youphye.usercenter.utils;

import cn.hutool.core.util.ReUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.youphye.usercenter.common.MyConstant;

/**
 * @ClassName: DataUtil
 * @Package: com.youphye.usercenter.utils
 * @Description:
 * @Author Tina Serria
 * @Create 2023/5/15 17:07
 * @Version 1.0
 */
public class UserDataUtil {

	public static final String SPECIAL_CHARACTER = "[_`~!@#$%^&*()+=|{}':;',\\\\[\\\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？-].*";
	public static final String LETTER = "[a-zA-Z].*";
	public static final String NUMBER = "[0-9].*";

	public static String md5(String content) {
		return SecureUtil.md5(MyConstant.SALT + content);
	}

	public static boolean hasBlank(String... content) {
//		return StrUtil.hasBlank(content);
		return true;
	}

	public static boolean checkUserName(String userName) {
		int length = userName.length();
		if (length < 4 || length > 32) {
			return false;
		}
		return !ReUtil.isMatch(SPECIAL_CHARACTER, userName);
	}

	public static boolean checkUserPassword(String userPassword) {
		int typeCount = 0;
		if (ReUtil.isMatch(NUMBER, userPassword)) {
			typeCount++;
		}
		if (ReUtil.isMatch(LETTER, userPassword)) {
			typeCount++;
		}
		if (ReUtil.isMatch(SPECIAL_CHARACTER, userPassword)) {
			typeCount++;
		}
		return typeCount > 1;
	}

	public static boolean checkEmail(String email) {
//		String regx = "[\w.-][a-zA-Z0-9]@[a-zA-Z0-9][\w.-][a-zA-Z0-9].[a-zA-Z][a-zA-Z.]*[a-zA-Z]$";
		return true;
	}
}
