package com.zghw.servlet.demo;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.*;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public void init() throws ServletException {
		System.out.println("执行Servlet init()");
	}
	public void destroy() {
		System.out.println("执行Servlet destroy()");
    }
	public void doGet(HttpServletRequest request, HttpServletResponse respose)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		System.out.println("取得session时，使用了HttpSessionListener监听器会触发调用sessionCreated");
		String username = request.getParameter("userName");
		String age = request.getParameter("age");
		//使用ServletRequestAttributeListener监听器监听属性改变
		request.setAttribute("age", 100);
		request.setAttribute("age", 20);
		request.removeAttribute("age");
		if(username!=null){
			String info=(String)session.getAttribute(username);
			if(info==null){
				session.setAttribute("login",username);
				//当session绑定了SessionBindingListener监听器会触发SessionBindingListener的valueBound方法
				MyHttpSessionBindingListener mhsbl=new MyHttpSessionBindingListener();
				session.setAttribute("mhsbl", mhsbl);
				//
				MyHttpSessionActivationListener mhsal=new MyHttpSessionActivationListener();
				session.setAttribute("mhsal", mhsal);
				//设置session活动时间，当session清空后就会触发HttpSessionListener sessionDestroyed
				session.setMaxInactiveInterval(70);
			}
		}
		
		System.out.println("执行Servlet service");
		respose.setContentType("text/html;charset=UTF-8");
		PrintWriter out = respose.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>");
		out.println("this is a servlet out");
		out.println("</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1>Hello Servlet ： " + getServletInfo() + "</h1>");
		out.println("<p>");

		out.println("<h3>ServletConfig</h3>");
		out.println("Servlet名字:" + this.getServletName());
		out.println("<br/>");
		Enumeration<String> initParamNames = this.getInitParameterNames();
		while (initParamNames.hasMoreElements()) {
			String name = initParamNames.nextElement();
			out.print("初始化参数： name = " + name + " ， value = "
					+ this.getInitParameter(name));
			out.println("<br/>");
		}
		ServletContext servletContext = this.getServletContext();

		out.println("<h3>servletContext</h3>");

		out.println("</p>");
		out.println("</body>");
		out.println("</html>");
		//销毁session
		//System.out.println("即将销毁session");
		//session.invalidate();
	}
}
