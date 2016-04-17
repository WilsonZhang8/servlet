package com.zghw.servlet.demo;

import java.io.IOException;
import java.security.Principal;
import java.util.Collection;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * 请求异步处理
 *可以先释放容器分配给请求的线程与相关资源，减轻系统负担，原先释放了容器所分配线程的请求。
 *其响应将被延后，可以在处理完成(例如长时间运算完成、所需资源以获得)时在对客户端进行响应。
 */
public class MyRequestServlet3 extends HttpServlet {
	static void f(Object obj) {
		System.out.println(obj);
	}

	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		boolean isAsyncStarted = request.isAsyncStarted();
		f("当前请求是否开启异步？" + isAsyncStarted);
		boolean isAsyncSupported = request.isAsyncSupported();
		f("当前请求是否支持异步？" + isAsyncSupported);
		if (!isAsyncStarted && isAsyncSupported) {
			// 把这个请求放入到异步模式中，如多线程，使用原始的request和response即没有包装过的来初始化一个AsyncContext
			// AsyncContext asyncContextOriginal =request.startAsync();
			// 使用包装后的request和response初始化AsyncContext对象
			final AsyncContext asyncContextWapper = request.startAsync(request,
					response);
			// asyncContextOrigin = request.getAsyncContext();
			// 当请求参数时name=张三，使用request.startAsync();取得AsyncContext使用了没有包装的原始的ServeltReqeust和ServletResponse
			// 得到的参数"张三"是乱码，没有使用MyServletRequestWrapper，虽然filter使用了但并不会起作用
			// String
			// name=asyncContextOriginal.getRequest().getParameter("name");

			// 而使用request.startAsync(request,
			// response);取得AsyncContext使用了包装后的ServeltReqeust和ServletResponse
			// 解析中文参数"张三"时，使用了MyServletRequestWrapper来转换编码
			String name = asyncContextWapper.getRequest().getParameter("name");

			// 为请求异步处理添加一个事件监听器
			asyncContextWapper.addListener(new MyAsyncListener(), request,
					response);
			// 设置超时时间单位毫秒
			asyncContextWapper.setTimeout(5000);
			//设置一个线程任务到线程池中，并启动该线程异步处理任务
			asyncContextWapper.start(new Runnable() {
				@Override
				public void run() {
					try {
						System.out.println("处理任务开始");
						Thread.sleep(3000);
						System.out.println("任务完成");
						
						//代表request对应的request中请求url或分发的url进行转发
						//如请求/url/A则转向这个URL，了解详情看这个方法上的注释
						//asyncContextWapper.dispatch();
						//转发到对应的路径
						asyncContextWapper.dispatch("/myAsyncDispatcherServlet");
						//转发的目标路径在设置的ServletContext中
						//asyncContextWapper.dispatch(getServletContext(), "/myAsyncDispatcherServlet");
						// 设置异步请求完成，当进行了dispatch转发，这里就不能在使用complete()方法。
						//asyncContextWapper.complete();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

				}
			});
			// 创建AsyncContext是否使用原始的ServletRequest和ServletResponse对象
			boolean hasOriginal = asyncContextWapper
					.hasOriginalRequestAndResponse();
			f(hasOriginal);
			
			f("" + name);
		}

		request.isSecure();

		String authType = request.getAuthType();
		Principal principal = request.getUserPrincipal();

		Collection<Part> partColl = request.getParts();
		Part part = request.getPart("");

		ServletInputStream sis = request.getInputStream();
		int contentLength = request.getContentLength();
		// long contentLengthLong = request.getContentLengthLong();
		String contentType = request.getContentType();

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
