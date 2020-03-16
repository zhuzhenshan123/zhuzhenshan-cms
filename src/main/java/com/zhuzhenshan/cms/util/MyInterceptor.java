package com.zhuzhenshan.cms.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
/**
 * 
 * @ClassName: MyInterceptor 
 * @Description: 个人中心拦截器
 * @author: 朱振山
 * @date: 2020年3月13日 上午8:40:22
 */
public class MyInterceptor extends HandlerInterceptorAdapter {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		//如果注册用户已登录则不拦截
		HttpSession session = request.getSession();
		
		Object obj = session.getAttribute("user");
		if(null!=obj) {
			return true;
		}
		//response.sendRedirect("/passport/login");
		request.setAttribute("msg", "请登录后重试");
		request.getRequestDispatcher("/WEB-INF/view/passport/login.jsp").forward(request, response);
		return false;
	}
	
}
