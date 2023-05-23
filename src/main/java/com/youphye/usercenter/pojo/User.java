package com.youphye.usercenter.pojo;


import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

import com.youphye.usercenter.common.ResponseUser;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @TableName user
 */
@TableName(value = "user")
@Data
@NoArgsConstructor
public class User implements Serializable {
	/**
	 * 主键
	 */
	@TableId(type = IdType.AUTO)
	private Long id;

	/**
	 * 账号
	 */
	private Long userAccount;

	/**
	 * 用户名
	 */
	private String userName;

	/**
	 * 密码
	 */
	@TableField(select = false)
	private String userPassword;

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

	/**
	 * 账号角色
	 */
	private Integer userRole;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 更新时间
	 */
	private Date updateTime;

	/**
	 * 逻辑删除
	 */
	@TableLogic
	@TableField(select = false)
	private Integer deleted;

	@TableField(exist = false)
	private static final long serialVersionUID = 1L;
	public User(ResponseUser responseUser){
		this.userAccount = responseUser.getUserAccount();
		this.userName = responseUser.getUserName();
		this.userGender = responseUser.getUserGender();
		this.userEmail = responseUser.getUserEmail();
		this.userPhone = responseUser.getUserPhone();
		this.userStatus = responseUser.getUserStatus();
		this.userRole = responseUser.getUserRole();
	}
	public static User create(ResponseUser responseUser){
		return new User(responseUser);
	}
}