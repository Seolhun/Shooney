package com.shun.blog.model.user;

import java.io.Serializable;

public enum UserProfileType implements Serializable{
	GUEST("GUEST"),
	PLAYER("PLAYER"),
	CAPTAIN("CAPTAIN"),
	DIRECTOR("DIRECTOR"),
	SUPERADMIN("SUPERADMIN");
	
	String type;
	
	private UserProfileType(String type){
		this.type = type;
	}
	
	public String getUserProfileType(){
		return type;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString(){
		return this.type;
	}

	public String getName(){
		return this.name();
	}
}
