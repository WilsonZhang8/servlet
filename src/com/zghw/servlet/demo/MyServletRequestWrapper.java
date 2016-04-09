package com.zghw.servlet.demo;

import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestWrapper;

public class MyServletRequestWrapper extends ServletRequestWrapper {

	public MyServletRequestWrapper(ServletRequest request) {
		super(request);
	}

}
