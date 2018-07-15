package com.spring.fcg.config;

import java.util.Date;

import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.mapper.MetaObjectHandler;


@Component
public class MyMetaObjectHandler extends MetaObjectHandler{
	  @Override
	    public void insertFill(MetaObject metaObject) {
	        Object fieldType = getFieldValByName("gmtCreate", metaObject);//mybatis-plus版本2.0.9+
	        if (fieldType == null) {
	            setFieldValByName("gmtCreate", new Date(System.currentTimeMillis()), metaObject);//mybatis-plus版本2.0.9+
	        }
	    }
	 
	    @Override
	    public void updateFill(MetaObject metaObject) {
	        Object fieldType = getFieldValByName("gmtModified", metaObject);//mybatis-plus版本2.0.9+
	        if (fieldType == null) {
	            setFieldValByName("gmtModified", new Date(System.currentTimeMillis()), metaObject);//mybatis-plus版本2.0.9+
	        }
	    }

}
