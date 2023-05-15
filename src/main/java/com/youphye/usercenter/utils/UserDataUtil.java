package com.youphye.usercenter.utils;

import cn.hutool.core.util.ReUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.youphye.usercenter.common.MyConstant;

/**
 * @ClassName: UserDataUtil
 * @Package: com.youphye.usercenter.utils
 * @Description: 用户数据检查工具类
 * @Author Tina Serria
 * @Create 2023/5/15 17:07
 * @Version 1.0
 */
public class UserDataUtil {

	public static final String SPECIAL_CHARACTER = "[_`~!@#$%^&*()+=|{}':;',\\\\[\\\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？-].*";
	public static final String LETTER = "[a-zA-Z].*";
	public static final String NUMBER = "[0-9].*";
	public static final String EMAIL = "[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[a-zA-Z0-9]+";
	public static final String PHONE = "^[1][3,4,5,7,8][0-9]{9}$";

	/**
	 * @Description 盐加字符串md5加密
	 * @param content 字符串
	 * @return String 加密后的字符串
	 */
	public static String md5(String content) {
		return SecureUtil.md5(MyConstant.SALT + content);
	}

	/**
	 * @Description 判断是否有字符串为null、""或者含有不可见字符
	 * @param content defaultDescription
	 * @return boolean 有返回true 否则返回false
	 */
	public static boolean hasBlank(String... content) {
		return StrUtil.hasBlank(content);
	}

	/**
	 * @Description 检查用户名是否合法，长度4-32 不包含特殊字符
	 * @param userName 用户名
	 * @return boolean 合法返回true 否则返回false
	 */
	public static boolean checkUserName(String userName) {
		int length = userName.length();
		if (length < 4 || length > 32) {
			return false;
		}
		return !ReUtil.contains(SPECIAL_CHARACTER, userName);
	}

	/**
	 * @Description 检查密码是否合法 长度 8-32 包含数字、字母、符号中的两种。
	 * @param userPassword 密码
	 * @return boolean 合法返回true 否则返回false
	 */
	public static boolean checkUserPassword(String userPassword) {
		int length = userPassword.length();
		if (length < 8 || length > 32) {
			return false;
		}
		int typeCount = 0;
		if (ReUtil.contains(NUMBER, userPassword)) {
			typeCount++;
		}
		if (ReUtil.contains(LETTER, userPassword)) {
			typeCount++;
		}
		if (ReUtil.contains(SPECIAL_CHARACTER, userPassword)) {
			typeCount++;
		}
		return typeCount > 1;
	}
	/**
	 * @Description 检验邮箱
	 * @param email 邮箱
	 * @return boolean 合法返回true 否则返回false
	 */
	public static boolean checkEmail(String email) {
		return ReUtil.isMatch(EMAIL, email);
	}

	/**
	 * @Description 检测手机号
	 * @param phone 手机号
	 * @return boolean 合法返回true 否则返回false
	 */
	public static boolean checkPhone(String phone) {
		return ReUtil.isMatch(PHONE, phone);
	}

}
