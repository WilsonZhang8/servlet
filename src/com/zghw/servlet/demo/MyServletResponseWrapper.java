package com.zghw.servlet.demo;

import javax.servlet.ServletResponse;
import javax.servlet.ServletResponseWrapper;
/**
 * ServletResponseWrapper装饰器模式 装饰ServletResponse对象，可以对一个servlet中的request对象
 * 进行包装改变，比如request请求参数设置、请求参数编码、国际化、内容体修改等。
 * 通过包装后的request对象来满足业务需求。
 * @author zghw
 *
 */
public class MyServletResponseWrapper extends ServletResponseWrapper {

	public MyServletResponseWrapper(ServletResponse response) {
		super(response);
	}

}
