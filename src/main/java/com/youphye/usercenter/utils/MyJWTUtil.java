package com.youphye.usercenter.utils;

import cn.hutool.core.convert.NumberWithFormat;
import cn.hutool.json.JSONUtil;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import cn.hutool.jwt.JWTValidator;
import cn.hutool.jwt.signers.JWTSigner;
import cn.hutool.jwt.signers.JWTSignerUtil;
import com.youphye.usercenter.common.*;
import com.youphye.usercenter.exception.BusinessException;
import com.youphye.usercenter.pojo.User;

import java.util.Date;

/**
 * @ClassName: MyJWTUtil
 * @Package: com.youphye.usercenter.utils
 * @Description: 封装了hutool中的JWTUtil
 * @Author Tina Serria
 * @Create 2023/5/21 11:08
 * @Version 1.0
 */
public class MyJWTUtil {
	private static final JWTSigner jwtSigner = JWTSignerUtil.hs256(MyConstant.SALT.getBytes());

	/**
	 * @param responseUser 用户对象
	 * @return String JWT令牌字符串
	 * @Description 根据账号和用户类型生成JWT令牌
	 */
	public static String create(ResponseUser responseUser) {
		JWTData jwtData = new JWTData(responseUser);
		return JWT.create()
				.setExpiresAt(new Date(System.currentTimeMillis() + MyConstant.TIMEOUT))
				.setPayload(MyConstant.JWT_DATA,JSONUtil.toJsonStr(jwtData))
				.sign(jwtSigner);
	}

	/**
	 * @param token JWT令牌字符串
	 * @return RoleCode 用户类型枚举
	 * @Description 验证解析JWT令牌，返回用户枚举类型。如果没有找到用户类型，或者JWT验证失败则会抛出异常
	 */
	public static JWTData verify(String token) {
		try {
			JWTUtil.verify(token, jwtSigner);
			// 验证JWT令牌过期
			// 如果验证失败会抛出异常
			JWTValidator.of(token).validateDate(new Date());
		} catch (Exception e) {
			throw new BusinessException(ResponseCode.IDENTIFY_EXPIRED);
		}
		// 解析令牌
		final JWT jwt = JWTUtil.parseToken(token);
		String json = (String)jwt.getPayload(MyConstant.JWT_DATA);
		JWTData jwtData = JSONUtil.toBean( json, JWTData.class);
		for (RoleCode roleCode : RoleCode.values()) {
			if (roleCode.getCode().equals(jwtData.getUserRole())) {
				return jwtData;
			}
		}
		throw new BusinessException(ResponseCode.IDENTIFY_EXPIRED);
	}
}
