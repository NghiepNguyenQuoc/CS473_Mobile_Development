package com.data.DTO;

public class Category {
	private int id;
	private String namecategory;
	private String img;
	private int lock;
	private String description;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNamecategory() {
		return namecategory;
	}

	public void setNamecategory(String namecategory) {
		this.namecategory = namecategory;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public int getLock() {
		return lock;
	}

	public void setLock(int lock) {
		this.lock = lock;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Category()
	{
		
	}
	
	public Category(int id, String namecategory, String img, int lock,
			String description) {
		super();
		this.id = id;
		this.namecategory = namecategory;
		this.img = img;
		this.lock = lock;
		this.description = description;
	}

}
