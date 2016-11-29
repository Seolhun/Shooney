package com.shun.blog.model.board;

public enum PortfolioName {
	//향후 더 추가예정 /PathVariable에 쓸 수 있는 객체들을 사용한다.
	MUSIC("music"),
	SPORTS("sports"),
	IT("it"),
	TOON("toon"),
	GAME("game");
	
	private String pfName;
	
	private PortfolioName(String pfName){
		this.pfName=pfName;
	}
	
	public String getPortfolioName(){
		return pfName;
	}
	
	@Override
	public String toString(){
		return this.pfName;
	}

	public String getName(){
		return this.name();
	}
}
