package com.zghw.servlet.demo;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * 关于请求体body的处理 一般是通过post方式传递过来的，比如表单数据 或者是上传的资源 body体中的数据时二进制格式 上传文件需要设置@MultipartConfig
 * 或者在servlet设置可以使用Part
 * 
 */
// 设置上传目标文件夹地址
@MultipartConfig(location = "/home/zghw/testdoc/")
@WebServlet("/myRequestServlet3")
public class MyRequestServlet3 extends HttpServlet {
	static void f(Object obj) {
		System.out.println(obj);
	}

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 设置编码格式
		request.setCharacterEncoding("UTF-8");
		// 得到body体的内容长度单位字节
		int contentLength = request.getContentLength();
		f(contentLength);

		// 3.1版本支持，现在用的3.0
		// long contentLengthLong = request.getContentLengthLong();

		// 内容体body的类型，比如表单的内容体类型为：application/x-www-form-urlencoded
		String contentType = request.getContentType();
		// 当类型是multipart/form-data （form表单中属性值enctype="multipart/form-data"）时，
		// body体的内容就和默认的application/x-www-form-urlencoded 不一样了
		f(contentType);
		// getInputStream()和getReader()不能同时调用
		// 就像一个数据管道一样，当作为字节流打开后就不能同时使用字符流打开了
		/*
		 * ServletInputStream sis = request.getInputStream(); //字节流输出body中的内容
		 * byte[] b=new byte[1024]; int off=0; int len=b.length;
		 * while(sis.readLine(b, off, len)>-1){ //进行解码输出
		 * System.out.println(URLDecoder.decode(new String(b),"UTF-8")); }
		 */
		// 直接使用字符流读取信息
		/*
		 * BufferedReader reader = request.getReader(); String input=null;
		 * while((input=reader.readLine())!=null){ System.out.println(input); }
		 */
		// 文件上传的处理，也包含了文本框所以要和文本框分开的话就不再一个form中写
		// 取得上传的文件集合
		Collection<Part> partColl = request.getParts();
		if (partColl != null && !partColl.isEmpty()) {
			for (Part part : partColl) {
				// 得到上传的文件流
				// InputStream is=part.getInputStream();
				// 得到文件名
				f("name=" + part.getName());
				// 得到提交过来的文件名 servlet版本3.1才能使用
				// f("submittedFileName=" + part.getSubmittedFileName());
				// 文件大小
				f("size=" + part.getSize());
				// 文件类型
				f("content-type=" + part.getContentType());
				for (String head : part.getHeaderNames()) {
					f(head + "=" + part.getHeader(head));
				}
				part.write(part.getName());
			}
		}
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
