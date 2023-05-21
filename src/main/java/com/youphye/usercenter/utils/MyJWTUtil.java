package com.youphye.usercenter.utils;

import cn.hutool.core.convert.NumberWithFormat;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import cn.hutool.jwt.JWTValidator;
import cn.hutool.jwt.signers.JWTSigner;
import cn.hutool.jwt.signers.JWTSignerUtil;
import com.youphye.usercenter.common.MyConstant;
import com.youphye.usercenter.common.ResponseCode;
import com.youphye.usercenter.common.RoleCode;
import com.youphye.usercenter.exception.BusinessException;

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
	 * @param userAccount 账号
	 * @param userRole    用户类型
	 * @return String JWT令牌
	 * @Description 根据账号和用户类型设置生成JWT令牌
	 */
	public static String create(String userAccount, Integer userRole) {
		return JWT.create()
				.setExpiresAt(new Date(System.currentTimeMillis() + MyConstant.TIMEOUT))
				.setPayload(MyConstant.USER_ACCOUNT, userAccount)
				.setPayload(MyConstant.USER_ROLE, userRole)
				.sign(jwtSigner);
	}

	public static RoleCode verify(String token) {
		boolean verified = JWTUtil.verify(token, jwtSigner);
		if (verified) {
			// JWT令牌过期
			try {
				// 如果验证失败会抛出异常
				JWTValidator.of(token).validateDate(new Date());
			} catch (Exception e) {
				throw new BusinessException(ResponseCode.IDENTIFY_EXPIRED);
			}
			// 解析令牌
			final JWT jwt = JWTUtil.parseToken(token);
			for (RoleCode roleCode : RoleCode.values()) {
				// 将 Object 进行类型转换 获取code
				NumberWithFormat code = (NumberWithFormat) jwt.getPayload(MyConstant.USER_ROLE);
				if (roleCode.getCode().equals(code.intValue())) {
					return roleCode;
				}
			}
		} else {
			// JWT令牌被篡改
			throw new BusinessException(ResponseCode.IDENTIFY_EXPIRED);
		}
		throw new BusinessException(ResponseCode.IDENTIFY_EXPIRED);
	}
}
