package com.shun.blog.model.common;

import lombok.Data;

@Data
public class Paging {
	/** For searching Field */
	// 참조할 때의 엔티티 키 값
	private Long id;
	// To Find Entity Name
	private String entityName;
	// 게시판 종류
	private String boardType;
	//게시판에서 게시물의 타입.
	private String portfolioType;
	
	private String question;
	// 검색 종류 설정
	private int searchType;
	// 검색어
	private String searchText;
	// 게시판 날짜 검색용
	private int searchDate;
	
	/** For Paging Field */
	// 현재 페이지
	private int currentPage;
	// 페이지 제한 수
	private int limit;
	// 페이징 시작 번호 
	private int startNum;
	// 페이징 끝나는 번호
	private int lastNum;
	// 총 게시물 갯수
	private int totalCount;
	// 총 페이지 갯수
	private int totalPage;
	
	
	// 총 페이지 블록 수
	private int totalBlock;
	// 페이지 블록 제한 수
	private int blockLimit;
	// 현재 블록 위치
	private int currentBlock;
	// 게시물 시작 블록번호
	private int blockStartNum;
	// 게시물 끝 블록번호
	private int blockEndNum;
	
	private int nextPage;
	private int previousPage;
	
	public Paging() {

	}
	
	public Paging(int currentPage, int searchType, String searchText, int searchDate, int limit, String boardType, String portfolioType){
		this.currentPage = currentPage;
		this.searchType = searchType;
		this.searchText = searchText;
		this.searchDate=searchDate;
		this.limit = limit;
		this.boardType=boardType;
		this.portfolioType=portfolioType;
	}

	public Paging(int currentPage, int searchType, String searchText, int searchDatesDate, int limit) {
		this.currentPage = currentPage;
		this.searchType = searchType;
		this.searchText = searchText;
		this.limit = limit;
		this.searchDate = searchDatesDate;
	}

	public Paging(int currentPage, int searchType, String searchText, int searchDate, int limit, String boardType) {
		this.currentPage = currentPage;
		this.searchType = searchType;
		this.searchText = searchText;
		this.searchDate = searchDate;
		this.limit = limit;
		this.boardType = boardType;
	}
}