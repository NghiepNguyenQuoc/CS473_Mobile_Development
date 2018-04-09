package com.swa.todo.model;

import java.io.Serializable;
import java.util.Date;

import com.swa.todo.TagEnum;

public class Item implements Serializable {
	
	private String name;	
	private String descr;
	private Date date;
	private String note;
	private TagEnum tag;
	
	public String getDescr() {
		return descr;
	}
	public void setDescr(String descr) {
		this.descr = descr;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public TagEnum getTag() {
		return tag;
	}
	public void setTag(TagEnum tag) {
		this.tag = tag;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	

}
