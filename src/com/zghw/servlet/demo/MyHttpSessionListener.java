package com.zghw.servlet.demo;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * HttpSession生命周期监听器，HttpSession的创建及销毁
 * @author zghw
 *
 */
public class MyHttpSessionListener implements HttpSessionListener {

	/**
	 * 创建完HttpSession对象时触发监听器。
	 */
	@Override
	public void sessionCreated(HttpSessionEvent se) {
		System.out.println("MyHttpSessionListener sessionCreated");
	}

	/**
	 * 即将销毁HttpSession前时触发监听器。
	 * 可以从HttpSessionEvent中取得HttpSession对象，可以处理一些HttpSession的信息。
	 * 比如：当浏览器关闭时，session会被web容器回收，回收的时间时session的过期时间。。
	 */
	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		System.out.println("MyHttpSessionListener sessionDestroyed");
		String userName=(String)se.getSession().getAttribute("login");
		System.out.println("销毁前userName="+userName);
	}

}
