package com.spring.fcg.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;


/**
 * <p>
 * 
 * </p>
 *
 * @author 付春光
 * @since 2018-07-14
 */
public class Demo1 extends Model<Demo1> {

    private static final long serialVersionUID = 1L;

    @TableId("ID")
    private String id;
    @TableField("NAME")
    private String name;


    public Demo1() {
		super();
	}

	public Demo1(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Demo1{" +
        ", id=" + id +
        ", name=" + name +
        "}";
    }
}
