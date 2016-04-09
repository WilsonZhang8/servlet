package com.zghw.servlet.demo;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

/**
 * 当HttpSession属性值为HttpSessionBindingListener实例且此属性发生改变的监听器，监听器触发事件
 * 不需要在web.xml中配置Listener
 * @author zghw
 *
 */
public class MyHttpSessionBindingListener implements HttpSessionBindingListener {

	/**
	 * 当HttpSession添加属性为HttpSessionBindingListener实例，则会触发这个事件
	 */
	@Override
	public void valueBound(HttpSessionBindingEvent event) {
		System.out.println("HttpSessionBindingListener valueBound  name="+event.getName()+" value="+event.getValue());
	}

	/**
	 * 当HttpSession移除属性为HttpSessionBindingListener实例，则会触发这个事件
	 */
	@Override
	public void valueUnbound(HttpSessionBindingEvent event) {
		System.out.println("HttpSessionBindingListener valueBound  name="+event.getName()+" value="+event.getValue());
	}

}
