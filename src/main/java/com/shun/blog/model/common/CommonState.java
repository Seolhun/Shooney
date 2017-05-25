package com.shun.blog.model.common;

public enum CommonState {

	ACTIVE("active"),
	INACTIVE("inactive"),
	DELETED("deleted"),
	LOCKED("locked");
	//name//Type
	private String type;
	
	private CommonState(final String Type){
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
