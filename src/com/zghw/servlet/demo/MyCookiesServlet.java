package com.zghw.servlet.demo;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Cookie的原理 由于HTTP是无状态的，访问一次后，无法记得上一次访问的客户端浏览器是否和这一次访问的是同一客户端浏览器。
 * 为了实现认识同一客户端浏览器做一些业务需要，比如浏览历史商品记录,可以使用Servlet提供的cookie技术来实现。
 * 
 * 当客户端浏览器发送一个请求过来以后，经过业务处理，可以响应给客户端浏览器一个cookie，这个值设置在响应首部的set-cookie
 * 中，客户端浏览器收到这个set-cookie会把它以文件的方式存储在计算机上。这个文件就成为Cookie。
 * 
 * 当客户端浏览器发送一个请求过来时，同时也会将存在于该网站的Cookie通过请求首部的set-cookie一并发送过来，服务器可以循环Cookie
 * 集合来查询需要的Cookie进行处理。
 * 
 * Cookie限制，一个浏览器最多放入300cookies，一个web站点，最多20cookie，而且一个cookie大小限制在4k
 * 
 */
@WebServlet("/MyCookiesServlet")
public class MyCookiesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String lastTime = "lasttime";

	/**
	 * 模拟最近一次访问时间
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		boolean isFirst = true;
		// 通过request请求取得客户端浏览器发送过来的cookie集合
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (lastTime.equals(cookie.getName())) {
					// 更新上次访问时间，当客户端已经存在该cookie，新的cookie值会覆盖该cookie值
					addCookieLastTime(response);
					isFirst = false;
					out.print("上次访问时间：" + cookie.getValue());
				}
			}
		}
		if (isFirst) {
			addCookieLastTime(response);
			out.print("你是第一次访问！");
		}

	}

	private void addCookieLastTime(HttpServletResponse response) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String now = sdf.format(new Date());
		// 创建Cookie对象
		Cookie cookie = new Cookie(lastTime, now);
		// 设置Cookie的存活时间，为0则永远存在
		cookie.setMaxAge(3600 * 24 * 7);
		// 设置到响应response对象，把Cookie发送给客户端浏览器，让其保存在客户端文件中
		response.addCookie(cookie);
	}

	/**
	 * 
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
