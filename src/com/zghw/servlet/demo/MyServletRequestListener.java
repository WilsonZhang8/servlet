package com.zghw.servlet.demo;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
/**
 * ServletRequest的生命周期监听器。
 * @author zghw
 *
 */
public class MyServletRequestListener implements ServletRequestListener {

	/**
	 * ServletRequest对象创建后
	 */
	@Override
	public void requestDestroyed(ServletRequestEvent sre) {
		ServletContext sc=sre.getServletContext();
		ServletRequest sr=sre.getServletRequest();
		System.out.println("ServletRequestListener requestDestroyed");
	}
	/**
	 * ServletRequest对象销毁前
	 */
	@Override
	public void requestInitialized(ServletRequestEvent sre) {
		System.out.println("ServletRequestListener requestInitialized");

	}

}
