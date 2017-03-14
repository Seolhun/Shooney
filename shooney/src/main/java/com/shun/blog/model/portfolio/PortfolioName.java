package com.shun.blog.model.portfolio;

public enum PortfolioName {
	//향후 더 추가예정 /PathVariable에 쓸 수 있는 객체들을 사용한다.
	JAVA("java"),
	SPRING("spring"),
	JAVASCRIPT("javascript"),
	ANGULAR("angular"),
	LINUX("linux"),
	DB("db"),
	MAC("mac"),
	MUSIC("music");
	
	private String type;
	
	private PortfolioName(String type){
		this.type=type;
	}
	
	public String getPortfolioName(){
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
