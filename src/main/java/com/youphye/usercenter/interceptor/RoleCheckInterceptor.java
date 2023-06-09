package com.youphye.usercenter.interceptor;

import cn.hutool.json.JSONConfig;
import cn.hutool.json.JSONUtil;
import com.youphye.usercenter.common.*;
import com.youphye.usercenter.utils.MyJWTUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName: LoginCheckInterceptor
 * @Package: com.youphye.usercenter.interceptor
 * @Description: 拦截器，对需要鉴权的请求进行拦截
 * @Author Tina Serria
 * @Create 2023/5/21 14:48
 * @Version 1.0
 */
@Slf4j
@Component
public class RoleCheckInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		// 获取JWT令牌字符串
		String jwt = request.getHeader(MyConstant.TOKEN);
		// 解析JWT令牌,获得用户身份。如果解析失败,或者没有找到用户身份则会直接抛出异常
		JWTData jwtData = MyJWTUtil.verify(jwt);
		// 解析url
		String url = request.getRequestURL().toString();
		// 如果路径包括 ALL 或者 SELECT_URL，并且用户身份不是管理员则拦截请求
		if (url.contains(MyConstant.ALL) || url.contains(MyConstant.SELECT)) {
			if(!RoleCode.ADMINISTRATOR.getCode().equals(jwtData.getUserRole())){
				response.setCharacterEncoding(MyConstant.CHARSET);
				response.setContentType(MyConstant.CONTENT_TYPE);
				JSONConfig jsonConfig = new JSONConfig();
				jsonConfig.setIgnoreNullValue(false);
				response.getWriter().write(JSONUtil.toJsonStr(Response.success(ResponseCode.PERMISSION_DENIED),jsonConfig));
				return false;
			}
		}
		// 如果是用户请求则放行请求
		return true;
	}
}
