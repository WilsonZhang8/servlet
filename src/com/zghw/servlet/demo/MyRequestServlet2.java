package com.zghw.servlet.demo;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.security.Principal;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Map;

import javax.servlet.AsyncContext;
import javax.servlet.DispatcherType;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 * Servlet implementation class MyRequestServlet
 */
@WebServlet("/myRequestServlet2/*")
public class MyRequestServlet2 extends HttpServlet {
	static void f(Object obj) {
		System.out.println(obj);
	}

	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// http://localhost:8080/servlet-demo/myRequestServlet/test?id=123&abc=222&abc=333
		// URL信息
		Request_URL(request);
		// request请求行
		RequestLine(request);
		// request请求header
		RequestHeader(request);
		// 客户端和服务器端信息地址
		RequestLocalRemoteAddr(request);
		// 会话cookie和session
		RequestCookieSession(request);
		// request请求参数及属性
		RequestParameter(request);
		// 请求转发
		RequestDispatcher(request, response);
		
		request.isAsyncStarted();
		request.isAsyncSupported();
		request.startAsync();
		request.startAsync(request, response);
		AsyncContext asyncContext = request.getAsyncContext();
		
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

	private void RequestDispatcher(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		f("============ RequestDispatcher ==================");
		// 转发的类型FORWARD,INCLUDE,REQUEST,ASYNC,ERROR
		// REQUEST代表来自客户端的请求
		// INCLUDE代表了当前(servlet、jsp、html)是被包含在其他(servlet、jsp、html)里的
		// FORWARD代表了由其他Servlet、jsp或html转发过来的
		// ASYNC
		// ERROR
		DispatcherType dispathcerType = request.getDispatcherType();
		f(dispathcerType);
		//可以使用？id=111可以使用像get方法一样传送字符串查询参数
		RequestDispatcher dispatcher = request.getRequestDispatcher("/ms1?id=111");
		
		//在include()或forward()时包括请求参数的作法，仅适用于传递字符串给另一个Servlet，在调派请求的过程中，如果由必须共享的“对象”
		//可以设置给请求对象成为属性，称为请求范围属性。
		//在使用了include或forward后，包含或转发Servlet则可以从request.getAttribute中取出这个值
		//而response.sendRedirect不能取出值，因为它是二次访问
		request.setAttribute("nowDate", new Date());
		
		// include意为包含，即包含url中的内容，进一步理解为，将url中的内容包含进当前的servlet当中来，并用当前servlet的request和
		// respose来执行url中的内容处理业务.所以不会发生页面的跳转，地址栏地址不会发生改变。
		//使用include()时，被包含的Servlet中任何对请求标头的设置都会被忽略，，被包含的Servlet中可以使用getSession()方法取得HttpSession对象
		// dispatcher.include(request, response);

		// forward是指转发，将当前request和response对象保存，交给指定的url处理。并没有表示页面的跳转，所以地址栏的地址不会发生改变。
		 dispatcher.forward(request, response);

		// response.sendRedirect是指重定向,包含两次浏览器请求，浏览器根据url请求一个新的页面，所有的业务处理都转到下一个页面，
		// 地址栏的地址会变发生改变。
		// response.sendRedirect(request.getContextPath()+"/ms");

		// 如果在处理请求的过程中发现一些错误，而你想要传送服务器默认的状态与错误信息，可以使用sendError()方法。
		// 由于利用到HTTP状态码，要求浏览器重定向网页，因此，sendError()方法同样必须在未确认输出前执行，否则会抛出IllegalStateException
		// 可以使用HttpServletResponse来查询状态码
		//response.sendError(HttpServletResponse.SC_NOT_FOUND);
		// 当然可以自定义原因短语
		// response.sendError(HttpServletResponse.SC_NOT_FOUND, "找不到页面");
		f("分发后还执行我");
		f("============  RequestDispatcher end ==================");
	}

	/**
	 * 获取请求参数
	 * 
	 * @param request
	 * @throws UnsupportedEncodingException
	 */
	private void RequestParameter(HttpServletRequest request)
			throws UnsupportedEncodingException {
		f("============request parameter==================");
		//得到编码方式，这个body内容的编码方式，及POST方式提交的编码方式。
		String characterEncoding = request.getCharacterEncoding();
		f(characterEncoding);
		// 得到所有参数值
		Enumeration<String> paramterNames = request.getParameterNames();
		while (paramterNames.hasMoreElements()) {
			String name = paramterNames.nextElement();
			// 根据参数名称得到参数值
			f(name + "=" + request.getParameter(name));
		}
		// 得到所有参数值，以map的形式存储，
		Map<String, String[]> parameterMap = (Map<String, String[]>) request
				.getParameterMap();
		for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
			System.out.print(entry.getKey() + "=");
			if (entry.getValue() != null && entry.getValue().length > 0) {
				for (String value : entry.getValue()) {
					System.out.print(value + ",");
				}
			}
		}
		f("");
		// 得到参数的多个值，比如多选框
		String[] parameterValues = request.getParameterValues("abc");
		if (parameterValues != null) {
			for (String pv : parameterValues) {
				// 没有用
				// String p = URLDecoder.decode(pv, "UTF-8");
				// 直接设置编码方式
				// String p = new String(pv.getBytes("ISO-8859-1"),"UTF-8");
				System.out.print(pv + ",");
			}
		}
		// 根据参数名称得到参数值
		String parameter = request.getParameter("id");
		f("");
		f(parameter);
		f("============request parameter end==================");
	}

	/**
	 * 会话功能 cookie 和session
	 * 
	 * @param request
	 */
	private void RequestCookieSession(HttpServletRequest request) {
		f("============cookies session ==================");
		// 取得所有Cookie
		Cookie[] cookies = request.getCookies();
		// 取得session
		HttpSession hs = request.getSession();
		// 如果当前session为空则创建一个，true为自动创建session
		HttpSession httpSession = request.getSession(true);
		// 当前的请求的sessionID，没有指定则返回null
		String sessionId = request.getRequestedSessionId();
		f("改变前的sessionId="+sessionId);
		//request.changeSessionId();
		//sessionId = request.getRequestedSessionId();
		f("改变后的sessionId="+sessionId);
		boolean sessionIdFromCookie=request.isRequestedSessionIdFromCookie();
		f("sessionID是从Cookie中来的？"+sessionIdFromCookie);
		boolean sessionIdFromURL=request.isRequestedSessionIdFromURL();
		f("sessionId是从URL来的？"+sessionIdFromURL);
		boolean sessionIdValid=request.isRequestedSessionIdValid();
		f("sessionId是有效的？"+sessionIdValid);
		f("============cookies session end==================");
	}

	/**
	 * 客户端地址信息和服务器端地址信息
	 * 
	 * @param request
	 */
	private void RequestLocalRemoteAddr(HttpServletRequest request) {
		f("============IP Local port==================");
		// 服务器的IP地址
		String localAddr = request.getLocalAddr();
		f(localAddr);
		// 服务器IP地址对应的域名
		String localName = request.getLocalName();
		f(localName);
		// 本地使用的端口号
		int port = request.getLocalPort();
		f(port);
		// 如果客户端提供了Accept-Language 值，则使用客户端提供的语言，否则默认就是用服务器设置的国际化语言
		Locale locale = request.getLocale();
		f(locale);
		// 和上面的不同之处在于这个是多个服务器语言，默认是使用服务器设置的本地语言
		Enumeration<Locale> enumLocale = request.getLocales();
		while (enumLocale.hasMoreElements()) {
			Locale loc = enumLocale.nextElement();
			f(loc);
		}
		// 客户端IP地址
		String remoteAddr = request.getRemoteAddr();
		f(remoteAddr);
		// 获得客户端的主机名,如果没有就获取IP地址
		String remoteHost = request.getRemoteHost();
		f(remoteHost);
		// 客户端端口号
		int remotePort = request.getRemotePort();
		f(remotePort);
		// 客户端登录用户信息
		String user = request.getRemoteUser();
		f(remotePort);
		f("============IP Local port end ==================");
	}

	/**
	 * 
	 * @param request
	 */
	private void RequestHeader(HttpServletRequest request) {
		// 得到请求中所有header的名称集合
		Enumeration<String> en = request.getHeaderNames();
		f("============Header==================");
		while (en.hasMoreElements()) {
			// 得到header名称
			String name = en.nextElement();
			// 取得header对应的值
			f(name + "=" + request.getHeader(name));
		}
		// 查询header中的时间戳，比如If-Modified-Since,，如果不存在返回-1
		long modified = request.getDateHeader("If-Modified-Since");
		f(modified);
		// 查询head中对应的App值
		Enumeration<String> getHeaders = request.getHeaders("App");
		while (getHeaders.hasMoreElements()) {
			// 得到header名称
			String name = getHeaders.nextElement();
			// 取得header对应的值
			f(name + "=" + request.getHeader(name));
		}
		// 查询Header头返回一个数值，如果不存在返回-1
		int intValue = request.getIntHeader("abc");
		f(intValue);
		f("============Header end==================");
	}

	/**
	 * reqeust请求行
	 * 请求报文请求服务器对资源进行一些操作。请求报文的起始行，或称为请求行，包含了一个方法和一个请求URL，这个方法描述了服务器应该执行的操作，
	 * 请求URL描述了要对那个资源执行这个方法。请求行中还包含HTTP的版本，用来告知服务器，客户端使用的是哪种HTTP。
	 */
	private void RequestLine(HttpServletRequest request) {
		f("============request line==================");
		// 请求使用的协议及版本
		String protocol = request.getProtocol();
		f(protocol);
		// 请求使用的方法比如GET POST
		String method = request.getMethod();
		f(method);
		f("============request line end==================");
	}

	/**
	 * 请求取得URL的所有信息
	 * 
	 * @param request
	 * @throws UnsupportedEncodingException
	 */
	private void Request_URL(HttpServletRequest request)
			throws UnsupportedEncodingException {
		f("============request URL ==================");
		// 实例http://localhost:8080/servlet-demo/myRequestServlet/test?id=123&abc=222
		// 方案实际上是规定如何访问制定资源的主要标识符，它会告诉负责解析URL的应用程序应该使用什么协议。
		// 方案组件必须以一个字母符号开始，有第一个“：”符号将其与URL的其余部分分隔开来。方案名是大小写无关的。
		String scheme = request.getScheme();
		// 输出：http
		f(scheme);
		// 主机组件表示了因特网上能够访问资源的宿主机器
		String serverName = request.getServerName();
		// 输出：localhost
		f(serverName);
		// 端口组件标识了服务器正在监听的网络端口。
		int serverPort = request.getServerPort();
		// 输出：8080
		f(serverPort);
		// 项目在web容器中的根路径，环境路径。如果应用程序环境路径和Web服务器环境根路径相同，则应用程序环境路径为空字符串，
		// 如果不是，则应用程序环境路径以“/”开头，不包括“/”结尾。
		String contextPath = request.getContextPath();
		// 输出：/servlet-demo
		f(contextPath);
		// 资源位于服务器的什么地方。路径通常很像一个分级的文件系统路径。
		// Servlet中资源路径
		String servletPath = request.getServletPath();
		// 输出：/myRequestServlet/test
		f(servletPath);
		// 通过提问题或者查询来缩小所请求资源类型的范围。
		// 查询字符串以一系列“名/值”对的形式出现，名值对之间用字符“&”分隔
		String queryString = request.getQueryString();
		// 输出：id=123&abc=222
		f(queryString);
		f(URLDecoder.decode(queryString, "UTF-8"));
		// 路径信息不包括请求参数，值的是不包括环境路径与Servlet路径部分的额外路径信息。
		// 如果没有额外路径信息，则为null(扩展映射、预设Servlet、完全匹配的情况下，getPathInfo()就会取得null)
		// 如果有额外路径信息，则是一个以“/”开头的字符串。
		String pathInfo = request.getPathInfo();
		f(pathInfo);
		// 请求的URI不包含方案主机名端口查询参数。仅包含地址
		String requestURI = request.getRequestURI();
		// 输出：/servlet-demo/myRequestServlet/test
		f(requestURI);
		// 请求的URL不包含查询参数
		String requestURL = request.getRequestURL().toString();
		// 输出：http://localhost:8080/servlet-demo/myRequestServlet/test
		f(requestURL);
		// 服务器端真实绝对路径资源位置
		String pathTranslated = request.getPathTranslated();
		f(pathTranslated);
		f("============request URL end==================");
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
