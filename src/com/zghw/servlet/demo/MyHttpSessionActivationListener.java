package com.zghw.servlet.demo;

import javax.servlet.http.HttpSessionActivationListener;
import javax.servlet.http.HttpSessionEvent;

/**
 * 对象迁移监听器，很多情况不会使用，在分布式环境时会使用，应用程序的对象可能分散在多个JVM中，
 * 当HttpSession要从一个JVM迁移至另一个JVM时，必须现在原来的JVM上序列化所有的属性对象。
 * 在这之前若属性对象有实现HttpSessionActivationListener，就会调用sessionWillPassivate方法
 * 而迁移至另一个JVM后，就会对所有属性对象做反序列化，此时会调用sessionDidActivate()方法。
 * @author zghw
 *
 */
public class MyHttpSessionActivationListener implements
		HttpSessionActivationListener {

	@Override
	public void sessionWillPassivate(HttpSessionEvent se) {
		System.out.println("HttpSessionActivationListener sessionWillPassivate");
	}

	@Override
	public void sessionDidActivate(HttpSessionEvent se) {
		System.out.println("HttpSessionActivationListener sessionDidActivate");
	}

}
