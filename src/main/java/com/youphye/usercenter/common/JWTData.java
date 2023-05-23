package com.youphye.usercenter.common;

import com.youphye.usercenter.pojo.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: JWTData
 * @Package: com.youphye.usercenter.common
 * @Description: JWT令牌解析出来的内容，放在这个对象中
 * @Author Tina Serria
 * @Create 2023/5/21 16:46
 * @Version 1.0
 */
@Data
@NoArgsConstructor
public class JWTData {
	private String userName;
	private Long userAccount;
	private Integer userRole;
	public JWTData(ResponseUser responseUser){
		this.userName = responseUser.getUserName();
		this.userAccount = responseUser.getUserAccount();
		this.userRole = responseUser.getUserRole();
	}
}
