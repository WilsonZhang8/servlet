package com.zghw.servlet.demo;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MyListener1 implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("执行MyListener1 contextInitialized");
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("执行MyListener1 contextDestroyed");
	}

}
