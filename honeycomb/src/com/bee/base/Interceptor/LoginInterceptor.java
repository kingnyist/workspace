/*
 * Copyright (C), 2015-2016, 蜂巢互联网科技有限公司
 * FileName: LoginInterceptor.java
 * Author:   huhu
 * Date:     2016年11月27日 下午7:47:42
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.bee.base.Interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 校验登陆状态
 *
 * @author huhu
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class LoginInterceptor implements HandlerInterceptor {

	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object arg2) throws Exception {
		String uri = request.getRequestURI();
		boolean check = uri.indexOf("login")>=0?true:false;
		HttpSession session = request.getSession();
		if(!check){
			if(request.getSession(true) != null && session.getAttribute("bee") != null){
				System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>用户已经登录");
			}else{
				System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<用户未经登录");
				return false;
			}
		}
		return true;
	}

}
