package com.zghw.servlet.demo;

import java.io.IOException;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.DispatcherType;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyServlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static void f(Object obj){
		System.out.println(obj);
	}
	public void init() throws ServletException {
		System.out.println("执行Servlet1 init()");
	}
	public void destroy() {
		System.out.println("执行Servlet1 destroy()");
    }
	public void doGet(HttpServletRequest request, HttpServletResponse respose)
			throws ServletException, IOException {
		System.out.println("执行Servlet1 service");
		DispatcherType dispathcerType = request.getDispatcherType();
		System.out.println("dispathcerType:"+dispathcerType);
		//取出上一个转发的所有request的属性名
		Enumeration<String> names = request.getAttributeNames();
		while(names.hasMoreElements()){
			String name = names.nextElement();
			//取出属性值
			f(name+":"+request.getAttribute(name));
		}
		String id = request.getParameter("id");
		f("paramter:id="+id);
		Date now=(Date)request.getAttribute("nowDate");
		f("得到上一个转发送来的值：nowDate = "+now);
		//FORWARD上一个servlet或JSP html的信息参数属性，对于FORWARD前一个请求需要做的事情。
		if(dispathcerType.equals(DispatcherType.FORWARD)){
			String contextPath=(String)request.getAttribute(RequestDispatcher.FORWARD_CONTEXT_PATH);
			f("FORWARD:"+contextPath);
			String pathInfo= (String)request.getAttribute(RequestDispatcher.FORWARD_PATH_INFO);
			f("FORWARD:"+pathInfo);
			String queryString= (String)request.getAttribute(RequestDispatcher.FORWARD_QUERY_STRING);
			f("FORWARD:"+queryString);
			String requestURI= (String)request.getAttribute(RequestDispatcher.FORWARD_REQUEST_URI);
			f("FORWARD:"+requestURI);
			String servletPath= (String)request.getAttribute(RequestDispatcher.FORWARD_SERVLET_PATH);
			f("FORWARD:"+servletPath);
		}
		//INCLUDE包含类型时，可以从参数中取得的类型
		if(dispathcerType.equals(DispatcherType.INCLUDE)){
			String contextPath= (String)request.getAttribute(RequestDispatcher.INCLUDE_CONTEXT_PATH);
			f("INCLUDE:"+contextPath);
			String pathInfo= (String)request.getAttribute(RequestDispatcher.INCLUDE_PATH_INFO);
			f("INCLUDE:"+pathInfo);
			String queryString= (String)request.getAttribute(RequestDispatcher.INCLUDE_QUERY_STRING);
			f("INCLUDE:"+queryString);
			String requestURI= (String)request.getAttribute(RequestDispatcher.INCLUDE_REQUEST_URI);
			f("INCLUDE:"+requestURI);
			String servletPath= (String)request.getAttribute(RequestDispatcher.INCLUDE_SERVLET_PATH);
			f("INCLUDE:"+servletPath);
		}
		//ERROR类型时，可以从request.getAttribute();中取得需要的参数值
		if(dispathcerType.equals(DispatcherType.ERROR)){
			String errorException= (String)request.getAttribute(RequestDispatcher.ERROR_EXCEPTION);
			String exceptionType= (String)request.getAttribute(RequestDispatcher.ERROR_EXCEPTION_TYPE);
			String errorMessage= (String)request.getAttribute(RequestDispatcher.ERROR_MESSAGE);
			String requestURI= (String)request.getAttribute(RequestDispatcher.ERROR_REQUEST_URI);
			String servletName= (String)request.getAttribute(RequestDispatcher.ERROR_SERVLET_NAME);
			String statusCode= (String)request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
		}
	}
}
