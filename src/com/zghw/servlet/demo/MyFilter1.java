package com.zghw.servlet.demo;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class MyFilter1 implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("执行MyFilter1 init");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		System.out.println("执行MyFilter1 doFilter ");
		System.out.println("执行MyFilter1 doFilter before");
		chain.doFilter(request, response);
		System.out.println("执行MyFilter1 doFilter after");
	}

	@Override
	public void destroy() {
		System.out.println("执行MyFilter1 destroy");

	}

}
