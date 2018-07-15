package com.spring.fcg.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.activerecord.Model;

public class Demo extends Model<Demo> { 
	private static final long serialVersionUID = 7126687906392571498L;
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Demo(String name) {
		super();
		this.name = name;
	}

	public Demo() {
		super();
	}

	@Override
	public String toString() {
		return "DemoEntity [name=" + name + "]";
	}

	@Override
	protected Serializable pkVal() {
		// TODO Auto-generated method stub
		return this.name;
	}
	
}
