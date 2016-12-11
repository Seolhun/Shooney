package com.shun.blog.model.portfolio.crawl;

public enum ITworldType {
	
	Insight("insight"),
	Howto("howto"),
	News("news");
	
	private String itWorldType;
	
	private ITworldType(String itWorldType){
		this.itWorldType=itWorldType;
	}

	public String getItWorldType() {
		return itWorldType;
	}

	public void setItWorldType(String itWorldType) {
		this.itWorldType = itWorldType;
	}
}
