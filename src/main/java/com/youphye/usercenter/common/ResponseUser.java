package com.youphye.usercenter.common;

import com.youphye.usercenter.pojo.User;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: ResponseUser
 * @Package: com.youphye.usercenter.common
 * @Description: 用于返回的用户对象，去掉了一些逻辑字段，避免暴露表结构
 * @Author Tina Serria
 * @Create 2023/5/23 10:01
 * @Version 1.0
 */
@Data
@NoArgsConstructor
public class ResponseUser {
	/**
	 * 账号
	 */
	private Long userAccount;

	/**
	 * 用户名
	 */
	private String userName;

	/**
	 * 性别
	 */
	private Integer userGender;

	/**
	 * 邮箱
	 */
	private String userEmail;

	/**
	 * 手机号
	 */
	private String userPhone;

	/**
	 * 账号状态
	 */
	private Integer userStatus;
	private Integer userRole;
	public ResponseUser(User user)
	{
		this.userAccount = user.getUserAccount();
		this.userName = user.getUserName();
		this.userGender = user.getUserGender();
		this.userEmail = user.getUserEmail();
		this.userPhone = user.getUserPhone();
		this.userStatus = user.getUserStatus();
		this.userRole = user.getUserRole();
	}
	public static ResponseUser create(User user){
		return new ResponseUser(user);
	}
}
