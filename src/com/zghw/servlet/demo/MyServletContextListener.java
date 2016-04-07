package com.zghw.servlet.demo;

import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
/**
 * ServletContextListener时生命周期监听器，如果想要知道何时Web应用程序已经初始化或即将结束销毁，
 * 可以实现ServletContextListener：
 * @author zghw
 *
 */
public class MyServletContextListener implements ServletContextListener {

	/**
	 * Web应用程序初始化后，监听ServletContext事件做些处理。
	 * ServletContextEvent封装了ServletContext对象，可以通过getServletContext方法取得
	 * ServletContext，可以使用ServletContext做些处理。
	 * 在整个Web应用程序生命周期，Servlet需要共享的资料可以设置ServletContext属性。由于ServletContext
	 * 在Web应用程序存活期间都会一直存在，所以设置为ServletContext属性的数据，除非主动移除，否则也是一直存活于
	 * Web应用程序中。
	 */
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext servletContext=sce.getServletContext();
		Enumeration<String> params=servletContext.getInitParameterNames();
		while(params.hasMoreElements()){
			String name=params.nextElement();
			System.out.println("初始化参数："+name+" 值： "+servletContext.getInitParameter(name));
		}
		System.out.println("执行MyServletContextListener contextInitialized");

	}
	/**
	 * Web应用程序即将销毁前，监听ServletContext事件做些处理。
	 */
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("执行MyServletContextListener contextDestroyed");
	}

}
