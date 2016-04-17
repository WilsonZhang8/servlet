package com.zghw.servlet.demo;

import java.io.IOException;

import javax.servlet.AsyncContext;
import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 请求异步处理监听器，监听异步处理启动、完成、超时或错误事件。
 * 
 * @author zghw
 *
 */
public class MyAsyncListener implements AsyncListener {
	/**
	 * 请求异步处理启动时触发
	 */
	@Override
	public void onStartAsync(AsyncEvent event) throws IOException {
		System.out.println("MyAsyncListener onStartAsync");
		ServletRequest req = event.getSuppliedRequest();
		ServletResponse res = event.getSuppliedResponse();
		AsyncContext asyncContext = event.getAsyncContext();
	}

	/**
	 * 请求异步完成后触发
	 */
	@Override
	public void onComplete(AsyncEvent event) throws IOException {
		System.out.println("MyAsyncListener onComplete");
		AsyncContext asyncContext = event.getAsyncContext();
		ServletRequest req = event.getSuppliedRequest();
		ServletResponse res = event.getSuppliedResponse();
		String contextPath = (String) req
				.getAttribute(AsyncContext.ASYNC_CONTEXT_PATH);
		f(contextPath);
		String pathInfo = (String) req
				.getAttribute(AsyncContext.ASYNC_PATH_INFO);
		f(pathInfo);
		String queryString = (String) req
				.getAttribute(AsyncContext.ASYNC_QUERY_STRING);
		f(queryString);
		String requestURI = (String) req
				.getAttribute(AsyncContext.ASYNC_REQUEST_URI);
		f(requestURI);
		String servletPath = (String) req
				.getAttribute(AsyncContext.ASYNC_SERVLET_PATH);
		f(servletPath);
	}

	/**
	 * 请求异步超时触发
	 */
	@Override
	public void onTimeout(AsyncEvent event) throws IOException {
		System.out.println("MyAsyncListener onTimeout");
	}

	/**
	 * 请求异步处理失败完成
	 */
	@Override
	public void onError(AsyncEvent event) throws IOException {
		System.out.println("MyAsyncListener onError");
	}

	static void f(Object obj) {
		System.out.println(obj);
	}
}
