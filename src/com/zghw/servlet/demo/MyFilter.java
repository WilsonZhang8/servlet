package com.zghw.servlet.demo;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class MyFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("执行MyFilter init");
		//用来测试ServletContextAttributeListenter实现类作用，
		ServletContext servletContext=filterConfig.getServletContext();
		//当添加属性值时，会通知监听器调用attributeAdded
		servletContext.setAttribute("test1", "test ServletContextAttributeListenter add in MyFilter ");
		//当改变属性值时，会通知监听器调用attributeReplaced
		servletContext.setAttribute("test1", "test ServletContextAttributeListenter replace in MyFilter ");
		//当改变属性值时，会通知监听器调用attributeRemoved
		servletContext.removeAttribute("test1");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		System.out.println("执行MyFilter doFilter");
		System.out.println("执行MyFilter doFilter before");
		chain.doFilter(request, response);
		System.out.println("执行MyFilter doFilter after");
	}

	@Override
	public void destroy() {
		System.out.println("执行MyFilter destroy");
	}

}
