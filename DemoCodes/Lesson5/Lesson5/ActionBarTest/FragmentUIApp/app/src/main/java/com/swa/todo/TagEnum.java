package com.swa.todo;

public enum TagEnum {
	
	BLACK(R.color.black,"Black"), RED(R.color.red, "Red"), 
	GREEN(R.color.green, "Green"), BLUE(R.color.blue, "Blue"),YELLOW(R.color.yellow,"Yellow");
	
	
	private int code;
	private String name;
	
	private TagEnum(int code, String name) {
		this.code = code;
		this.name = name;
	}
	
	
	public int getTagColor() {
		return this.code;
	}
	
	public String getName() {
		return this.name;
	}

}
