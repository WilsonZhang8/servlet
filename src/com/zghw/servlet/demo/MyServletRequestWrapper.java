package com.zghw.servlet.demo;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * 包装request的请求参数编码的转换
 * 
 * @author zghw
 *
 */
public class MyServletRequestWrapper extends HttpServletRequestWrapper {
	private static final String CHARSET = "UTF-8";

	public MyServletRequestWrapper(HttpServletRequest request) {
		super(request);
	}

	/**
	 * 重写get方法编码参数
	 */
	@Override
	public String getParameter(String name) {
		String paramter = super.getParameter(name);
		paramter = ("").equals(paramter) ? null : convert(paramter);
		return paramter;
	}

	@Override
	public Map<String, String[]> getParameterMap() {
		Map<String, String[]> map = super.getParameterMap();
		Map<String,String[]> wapper=new HashMap<String,String[]>();
		if (map != null) {
			for (Map.Entry<String, String[]> m : map.entrySet()) {
				String[] orgin = m.getValue();
				String[] values = convertArray(orgin);
				wapper.put(m.getKey(), values);
			}
		}
		return wapper;
	}

	@Override
	public String[] getParameterValues(String name) {
		String[] strs = super.getParameterValues(name);
		if (strs != null) {
			for (int i = 0; i < strs.length; i++) {
				strs[i] = convert(strs[i]);
			}
		}
		return strs;
	}

	private String[] convertArray(String[] orgin) {
		String[] value = null;
		if (orgin != null) {
			value = new String[orgin.length];
			int i = 0;
			for (String val : orgin) {
				value[i] = convert(val);
				i++;
			}
		}
		return value;
	}

	private String convert(String paramter) {
		if (paramter != null) {
			try {
				//设置请求参数的默认编码方式ISO-8859-1变为UTF-8
				return new String(paramter.getBytes("ISO-8859-1"), CHARSET);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

}
