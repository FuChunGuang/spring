package com.spring.fcg.util;

import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class BaseController {
	@Autowired
	protected SqlSession session;
	/**
	 * 把浏览器参数转化放到Map集合中
	 * 
	 * @param request
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	protected Map<String, Object> getParam() {
		//获取请求
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		//存放请求参数
		Map<String, Object> paramMap = new HashMap<String, Object>();
		//获取请求参数
		Enumeration<?> keys = request.getParameterNames();
		//获取参数的key value
		while (keys.hasMoreElements()) {
			Object key = keys.nextElement();
			if (key != null) {
				if (key instanceof String) {
					String value = request.getParameter(key.toString());
					//写入请求参数
					paramMap.put(key.toString(), value);
				}
			}
		}
		return paramMap;
	}
}
