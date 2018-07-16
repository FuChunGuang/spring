package com.spring.fcg.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.apache.velocity.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.spring.fcg.dao.Demo1Dao;
import com.spring.fcg.entity.Demo1;
import com.spring.fcg.service.Demo1Service;
import com.spring.fcg.util.BaseController;

@RestController
public class sqlController extends BaseController {
	@Autowired
	private  Demo1Dao demo1Dao;
	@Autowired
	Demo1Service demo1service;
	@GetMapping("/demo1")
	public List<Demo1> getDemo1(Page<Demo1> page2) {
//		IPage<Demo1> page =demo1Dao.selectPage(page2, new QueryWrapper<Demo1>().eq("name", "付镭"));
		List<Demo1> page=demo1Dao.selectPage(page2,new EntityWrapper<Demo1>().eq("name", "付镭"));
//		return page;
		return page;
	}
	@GetMapping("/demo2")
	public Object updateDemo1(HttpServletRequest request) {
		Object name=request.getParameter("name");
		return "111"+name;
	}
	@GetMapping("/demo3")
	public Object upddateDemo2() {
		Map<String, Object> map=getParam();
		return "222"+map.get("name");
	}
	@GetMapping("/demo4")
	public Object upddateDemo3() {
		Demo1 map =(Demo1)session.selectOne("com.spring.fcg.mapper.Demo1Mapper.getDemo1ById","2");
		return "222"+map.getName();
	}
}
