package com.zghw.servlet.demo;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyServlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void init() throws ServletException {
		System.out.println("执行Servlet1 init()");
	}
	public void destroy() {
		System.out.println("执行Servlet1 destroy()");
    }
	public void doGet(HttpServletRequest request, HttpServletResponse respose)
			throws ServletException, IOException {
		System.out.println("执行Servlet1 service");
	}
}
