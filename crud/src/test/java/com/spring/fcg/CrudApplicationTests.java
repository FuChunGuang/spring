package com.spring.fcg;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.spring.fcg.dao.Demo1Dao;
import com.spring.fcg.dao.DemoDao;
import com.spring.fcg.entity.Demo;
import com.spring.fcg.entity.Demo1;
import com.spring.fcg.mapper.Demo1Mapper;
import com.spring.fcg.mapper.DemoMapper;
import com.spring.fcg.service.Demo1Service;

@SuppressWarnings("all")
@RunWith(SpringRunner.class)
@SpringBootTest
public class CrudApplicationTests {
	@Autowired
	JdbcTemplate jdbcTemplate;
	@Autowired
	DemoDao demoDao;
	@Autowired
	Demo1Dao demo1Dao;
	@Autowired
	DemoMapper demoMapper;
	@Autowired
	Demo1Mapper demo1Mapper;
	@Autowired
	Demo1Service demo1Service;
	@Autowired
	SqlSession session;
	private Logger logger=LoggerFactory.getLogger(CrudApplicationTests.class);
	
//	@Test
	public void contextLoads() {
	}
	
//	@Test
	public void log() {
		logger.debug("这是debug");
	}
	
//	@Test
	public void druid() {
		Map<String, Object> m1=jdbcTemplate.queryForMap("select * from DEMO1 where rownum=1");
		logger.debug(m1.toString());
	}
//	@Test
	public void dao() {
//		demoDao.selectList(new QueryWrapper<Demo>().eq("name", "付春光"));
//		List<Demo> list = demoDao.selectList(new EntityWrapper<Demo>().eq("name", "付春光"));
//		List<Demo1> list2 = demo1Dao.selectList(new EntityWrapper<Demo1>().eq("id", "1"));
//		logger.debug("查询结果1"+list.get(0));
//		logger.debug("查询结果2"+list2.get(0));
	}
//	@Test
	public void service() {
//		Map<String,Object> map =demo1Service.selectMap(new EntityWrapper<Demo1>().eq("id", "2"));
//		logger.debug("service查询结果"+map);
	}
//	@Test
	public void mapper() {
		logger.debug("mapper"+demo1Mapper.getDemo1ById("2"));
	}
	@Test
	public void sqlSession() {
		logger.debug("sqlSession"+session.selectOne("com.spring.fcg.mapper.Demo1Mapper.getDemo1ById","2"));
	}
}
