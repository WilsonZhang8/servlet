package com.zghw.servlet.demo;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Session的使用示例 尝试运行HTTPServletRequest的getSession()时，Web容器会创建HttpSession对象，关键在于
 * 每个HttpSession对象都会有个特殊的ID，称为SessinoID，你可以执行HttpSession的getId()来取得SessionID。
 * 这个SessionID默认会使用Cookie存放在浏览器中。
 * 在Tomcat中，Cookie的名称是JSESSIONID，数值则是getId()所取得的SessionID。
 * 由于Web容器本身是执行于JVM中的一个JAVA程序
 * ，通过getSession()取得HttpSession对象，HttpSession中存放的属性，自然也就存放于服务器端的Web容器中。每一个
 * HttpSession各有一个特殊的SessionID，当浏览器请求应用程序时，会将Cookie中存放的SessionID一并发给应用程序，
 * Web容器会根据SessionID来找出对应的HttpSession 对象，这样就可以取得各浏览器对应的会话数据。
 * 所以使用HttpSession来进行会话管理时
 * ，设定为属性的对象是存储在服务器端，而SessionID默认使用Cookie存放于浏览器端。Web容器存储在SessionID的Cookie“默认”为
 * 关闭浏览器就失效，所以重启浏览器请求应用程序时，通过getSession()取得的是新的HttpSession对象。
 */
@WebServlet("/mySessionServlet")
public class MySessionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter pw = response.getWriter();
		// 通过request获取HttpSession对象
		HttpSession session = (HttpSession) request.getSession();
		// 得到存储在HttpSession对象中的值
		Integer in = (Integer) session.getAttribute("count");
		if (in == null) {
			// 每次重新打开一个浏览器会重新得到新的session对象，该对象对应新的sessionID，保存在浏览器端的Cookie中，
			// 只要浏览器不关闭，则这个SessionID一直存在直到过期时间结束对象。
			in = 0;
			pw.print("从session中第一次访问 ");
		} else {
			in++;
			pw.print("从session中第" + in + "次访问");
		}
		pw.print("sessionID=" + session.getId());
		// session对象的创建时间
		long createTime = session.getCreationTime();
		// session对象是否是第一次创建
		boolean isnew = session.isNew();
		pw.print("当前session对象是否是第一次：" + isnew);
		pw.print("当前session对象的第一次创建时间：" + createTime);
		// 设置HttpSession中的值
		session.setAttribute("count", in);
		// 设置session的过期时间单位为秒 0为永远不失效，除非调用invalidate,或关闭浏览器
		session.setMaxInactiveInterval(1800);
		// session对象销毁
		// session.invalidate();
		//用户禁用cookie时，每次都会创建一个新的session对象
		//如果不使用response.encodeURL来产生超链接的URL，在浏览器禁用Cookie的情况下，这个程序将会失败，也就是重符但即递增链接，
		//计数器也不会递增
		//如果执行encodeURL(),在浏览器第一次请求网站时，容器并不知道浏览器时否禁用Cookie，所以容器的作法时Cookie(发送set-cookie标头)与
		//URL重写的方式，因此若Servlet有以下语句，无论浏览器是否有禁用Cookie，第一次请求时，都会显示在SessionID的URL：
		//reqeust.getSession();response.encodeURL("mySessionServlet")
		//
		pw.print("<a href='"+response.encodeURL("mySessionServlet")+"'>递增</a>");

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
