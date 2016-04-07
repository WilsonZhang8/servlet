package com.zghw.servlet.demo;

import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;

/**
 * ServletRequest的属性改变时，触发监听器执行事件
 * @author zghw
 *
 */
public class MyServletRequestAttributeListener implements
		ServletRequestAttributeListener {

	/**
	 * ServletRequest添加属性后触发
	 */
	@Override
	public void attributeAdded(ServletRequestAttributeEvent srae) {
		System.out.println("ServletRequestAttributeListener attributeAdded name = " + srae.getName() + " value= "+ srae.getValue());

	}
	/**
	 * ServletRequest移除属性前触发
	 */
	@Override
	public void attributeRemoved(ServletRequestAttributeEvent srae) {
		System.out.println("ServletRequestAttributeListener attributeRemoved name = " + srae.getName() + " value= "+ srae.getValue());
	}
	/**
	 * ServletRequest属性替换后触发
	 */
	@Override
	public void attributeReplaced(ServletRequestAttributeEvent srae) {
		System.out.println("ServletRequestAttributeListener attributeReplaced name = " + srae.getName() + " value= "+ srae.getValue());

	}

}
