package com.zghw.servlet.demo;

import java.io.IOException;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/myAsyncDispatcherServlet")
public class MyAsyncDispatcherServlet extends HttpServlet{
	static void f(Object obj) {
		System.out.println(obj);
	}
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		AsyncContext asyncContext=request.getAsyncContext();
		//请求异步dipatcher转发过来的无法获得AsyncContext中的request和response
		//ServletRequest req = asyncContext.getRequest();
		//ServletResponse res = asyncContext.getResponse();
	}
}
