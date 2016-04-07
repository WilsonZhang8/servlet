package com.zghw.servlet.demo;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
/**
 * 当Web应用程序中的ServletContext的属性事件发生改变时，通知监听器做些事情。
 * @author zghw
 *
 */
public class MyServletContextAttributeListener implements
		ServletContextAttributeListener {

	/**
	 * 当向ServletContext中调用setAttribute添加一个不存在的属性时，
	 * 该监听器得到事件ServletContextAttributeEvent，做一些处理
	 * ServletContextAttributeEvent继承了ServletContextEvent可以获得ServletContext
	 * 主要获得添加的值对象，进行处理。event.getName() event.getValue()
	 * key 和 value的方式出现。
	 */
	@Override
	public void attributeAdded(ServletContextAttributeEvent event) {
		ServletContext servletContext=event.getServletContext();
		System.out.println(" MyServletContextAttributeListener attributeAdded 添加后 "
				+ "name="+event.getName() +"， 值="+event.getValue());
	}

	/**
	 * 当属性移除时，
	 */
	@Override
	public void attributeRemoved(ServletContextAttributeEvent event) {
		System.out.println(" MyServletContextAttributeListener attributeRemoved 移除后 name="+event.getName() +"， 值="+event.getValue());
	}

	/**
	 * 当属性被替换时。
	 */
	@Override
	public void attributeReplaced(ServletContextAttributeEvent event) {
		System.out.println(" MyServletContextAttributeListener attributeReplaced 替换后 name="+event.getName() +"， 值="+event.getValue());
	}

}
