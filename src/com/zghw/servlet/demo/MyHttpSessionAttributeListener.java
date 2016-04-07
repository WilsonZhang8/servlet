package com.zghw.servlet.demo;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

/**
 * HttpSession属性监听器，用于监听当属性操作变化事件。
 * @author zghw
 *
 */
public class MyHttpSessionAttributeListener implements
		HttpSessionAttributeListener {

	/**
	 * 当向session添加属性值时，
	 */
	@Override
	public void attributeAdded(HttpSessionBindingEvent event) {
		HttpSession hs = event.getSession();
		System.out.println("过期时间："+hs.getMaxInactiveInterval());
		System.out.println("HttpSessionAttributeListener attributeAdded"
				+ " name="+event.getName()+" value="+event.getValue());
		
		//替换一个属性
		if(event.getValue().equals("zhangsan")){
			hs.setAttribute(event.getName(), "lisi");
		}
	}

	/**
	 * HttpSession属性被移除后触发监听
	 */
	@Override
	public void attributeRemoved(HttpSessionBindingEvent event) {
		System.out.println("HttpSessionAttributeListener attributeRemoved");
	}

	/**
	 * HttpSession属性被替换后触发监听
	 */
	@Override
	public void attributeReplaced(HttpSessionBindingEvent event) {
		System.out.println("HttpSessionAttributeListener attributeReplaced");
		System.out.println("HttpSessionAttributeListener attributeAdded"
				+ " name="+event.getName()+" value="+event.getValue());
	}
}
