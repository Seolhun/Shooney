package com.shun.blog.model.board;

public enum EntityName {
	//향후 더 추가예정 /PathVariable에 쓸 수 있는 객체들을 사용한다.
	NOTICE("notice"),
	QNA("qna"),
	PROJECT("project"),
	FREEBOARD("freeboard");
	
	private String boardName;
	
	private EntityName(String boardName){
		this.boardName=boardName;
	}
	
	public String getBoardName(){
		return boardName;
	}
	
	@Override
	public String toString(){
		return this.boardName;
	}

	public String getName(){
		return this.name();
	}
}
