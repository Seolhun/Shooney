package com.shun.mongodb.model.it;

public enum ItWebsite {
	itworld("ITWorld"),
	cio("CIO");
	
	private String webName;
	
	private ItWebsite(final String webName){
		this.webName=webName;
	}

	public String getWebName() {
		return webName;
	}

	public void setWebName(String webName) {
		this.webName = webName;
	}
	
	@Override
	public String toString(){
		return this.webName;
	}
}
