package com.shun.blog.model.menu;

public enum MenuType {

	NORMAL("normal"),
	ADMIN("admin");
	//name//Type
	private String type;
	
	private MenuType(final String Type){
		this.type = Type;
	}
	
	public String getType(){
		return this.type;
	}

	public String getName(){
		return this.name();
	}

	@Override
	public String toString(){
		return this.type;
	}
}
