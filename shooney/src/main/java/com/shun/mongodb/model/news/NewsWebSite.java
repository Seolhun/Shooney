package com.shun.mongodb.model.news;
public enum NewsWebSite {
	CIO("http://www.cio.com/article/");
	
	private String webAddress;
	
	private NewsWebSite(final String webAddress){
		this.webAddress=webAddress;
	}

	public String getWebAddress() {
		return webAddress;
	}

	public void setWebAddress(String webAddress) {
		this.webAddress = webAddress;
	}

	@Override
	public String toString(){
		return this.webAddress;
	}
}
