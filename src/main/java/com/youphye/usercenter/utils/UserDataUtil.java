package com.youphye.usercenter.utils;

import cn.hutool.core.util.ReUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.youphye.usercenter.common.MyConstant;
import com.youphye.usercenter.common.MyEnum;
import com.youphye.usercenter.common.RoleCode;
import com.youphye.usercenter.pojo.User;

import java.lang.reflect.Method;
import java.util.Arrays;

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
	public static final String USER_NAME = "^[a-zA-Z0-9\\u4E00-\\u9FA5]+$";
	public static final String LETTER = "[a-zA-Z].*";
	public static final String NUMBER = "[0-9].*";
	public static final String EMAIL = "[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[a-zA-Z0-9]+";
	public static final String PHONE = "^[1][3,4,5,7,8][0-9]{9}$";

	/**
	 * @param content 字符串
	 * @return String 加密后的字符串
	 * @Description 盐加字符串md5加密
	 */
	public static String md5(String content) {
		return SecureUtil.md5(MyConstant.SALT + content);
	}

	/**
	 * @param content 字符串数组
	 * @return boolean 有返回true 否则返回false
	 * @Description 判断是否有字符串为空串、null 、不可字符
	 */
	public static boolean hasBlank(String... content) {
		return StrUtil.hasBlank(content);
	}

	/**
	 * @param userName 用户名
	 * @return boolean 合法返回true 否则返回false
	 * @Description 检查用户名是否合法，长度4-32 不包含特殊字符
	 */
	public static boolean checkUserName(String userName) {
		int length = userName.length();
		if (length < 4 || length > 32) {
			return false;
		}
		return ReUtil.isMatch(USER_NAME, userName);
	}

	/**
	 * @param userPassword 密码
	 * @return boolean 合法返回true 否则返回false
	 * @Description 检查密码是否合法 长度 8-32 包含数字、字母、符号中的两种。
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
	 * @param email 邮箱
	 * @return boolean 合法返回true 否则返回false
	 * @Description 检验邮箱
	 */
	public static boolean checkEmail(String email) {
		return ReUtil.isMatch(EMAIL, email);
	}

	/**
	 * @param phone 手机号
	 * @return boolean 合法返回true 否则返回false
	 * @Description 检测手机号
	 */
	public static boolean checkPhone(String phone) {
		return ReUtil.isMatch(PHONE, phone);
	}

	public static boolean checkStatus(){return true;}
	/**
	 * @Description 校验此用户对象中需要校验的非空字段是否合法
	 * @param user 用户对象
	 * @return boolean
	 */
	public static boolean checkUser(User user) {
		if (user.getUserAccount() != null && user.getUserAccount() > MyConstant.USER_ACCOUNT_START) {
			return false;
		}
		if (user.getUserName() != null && !checkUserName(user.getUserName())) {
			return false;
		}
		if (user.getUserPassword() != null && !checkUserPassword(user.getUserPassword())) {
			return false;
		}
		if (user.getUserEmail() != null && !checkEmail(user.getUserEmail())) {
			return false;
		}
		if (user.getUserPhone() != null && !checkPhone(user.getUserPhone())) {
			return false;
		}
//		if(user.getUserStatus() != null && )
		return true;
	}
	public static<T extends MyEnum> boolean codeInMyEnum(Integer code, T myEnum){
		for (RoleCode roleCode : RoleCode.values()) {
			if(roleCode.getCode().equals(code)){
				return true;
			}
		}
	}

}
