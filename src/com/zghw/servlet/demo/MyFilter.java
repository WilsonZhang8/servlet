package com.zghw.servlet.demo;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyFilter implements Filter {

	// 取得过滤器配置参数供doFilter使用
	private FilterConfig filterConfig;

	/**
	 * FilterConfig包含了Filter配置的参数，可以得到ServletContext对象
	 */
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("执行MyFilter init");
		this.filterConfig = filterConfig;
		// 获取过滤器的配置参数
		Enumeration<String> paramNames = filterConfig.getInitParameterNames();
		while (paramNames.hasMoreElements()) {
			String name = paramNames.nextElement();
			System.out.println("过滤器配置的参数 name = " + name + " , value = "
					+ filterConfig.getInitParameter(name));
		}

		// 用来测试ServletContextAttributeListenter实现类作用，
		ServletContext servletContext = filterConfig.getServletContext();
		// 当添加属性值时，会通知监听器调用attributeAdded
		servletContext.setAttribute("test1",
				"test ServletContextAttributeListenter add in MyFilter ");
		// 当改变属性值时，会通知监听器调用attributeReplaced
		servletContext.setAttribute("test1",
				"test ServletContextAttributeListenter replace in MyFilter ");
		// 当改变属性值时，会通知监听器调用attributeRemoved
		servletContext.removeAttribute("test1");
	}

	/**
	 * 当请求来到容器，而容器发现调用Servlet的serivce方法前，可以应用某过滤器时，就会调用该过滤器的doFilter()方法，
	 * 可以在doFilter（）方法中进行service()方法的前置处理，而后决定是否调用FilterChain的doFilter()方法。
	 * 如果调用了FilterChain的doFilter
	 * ()方法，就会运行下一个过滤器，如果没有下一个过滤器了，就调用请求目标Servlet的service()方法，
	 * 如果因为某个情况(如用户没有通过验证
	 * )而没有调用FilterChain的doFilter()方法，则请求就不会继续交给接下来的过滤器或目标Servlet,
	 * 这时候就是所谓的拦截请求(从Servlet的观点来看，根本不知道浏览器有发出请求)。
	 * 在陆续调用完FIlter实例的doFilter()仍至Servlet的service
	 * ()之后，流程会以堆栈顺序返回，所以在FilterChain的doFilter()运行完毕后， 就可以针对service()方法做后续处理。
	 * 
	 * 只需要知道FilterChain运行后会以堆栈顺序返回即可。在实现Filter接口时，不用理会这个Filter前后是否有其他Filter，
	 * 应该将之作为一个独立的元件设计。 Servlet/JSP提供的过滤器机制，其实是Java EE设计模式中Interceptor
	 * Filter模式的实现。如果希望可以弹性地抽换某功能地前置与后置处理元件 (例如Servlet/JSP
	 * 中Servlet的service()方法的前置与后置处理)，就可以应用Interceptor Filter模式。
	 */
	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		// POST方法参数编码为UTF-8
		request.setCharacterEncoding("UTF-8");
		if (request.getMethod().equals("GET")) {
			// 设置GET方法参数编码为UTF-8,通过装饰类MyServletRequestWrapper来改变request对象的请求参数编码
			request = new MyServletRequestWrapper(request);
		}
		System.out.println("执行MyFilter doFilter");
		System.out.println("执行MyFilter doFilter before");
		chain.doFilter(request, response);
		response.setCharacterEncoding("UTF-8");
		System.out.println("执行MyFilter doFilter after");
	}

	@Override
	public void destroy() {
		System.out.println("执行MyFilter destroy");
	}

}
