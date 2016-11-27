/*
 * Copyright (C), 2015-2016, 蜂巢互联网科技有限公司
 * FileName: LoginFilter.java
 * Author:   huhu
 * Date:     2016年11月26日 下午10:45:13
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.bee.base.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 登陆状态检查
 *
 * @author wanglihui
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class LoginFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		System.out.println("-------------------------Demo1过滤前");
		String userNo = request.getParameter("userNo");
		HttpServletRequest req = (HttpServletRequest)request; 
		String uri = req.getRequestURI();
		boolean check = uri.indexOf("login")>=0?true:false;
		HttpSession session = req.getSession();
		if(!check){
			if(req.getSession(true) != null && session.getAttribute("bee") != null){
				System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>用户已经登录");
			}else{
				HttpServletResponse  rsp = (HttpServletResponse )response;
				rsp.sendRedirect("/login.html");
				System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<用户未经登录");
			}
		}
		chain.doFilter(request, response);
		System.out.println("-------------------------Demo1过滤后");
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("检查登陆状态的过滤器-------------初始化了");
	}

	public void destroy() {
		System.out.println("检查登陆状态的过滤器-------------销毁了");
	}
}
