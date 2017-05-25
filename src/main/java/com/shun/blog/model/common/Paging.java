package com.shun.blog.model.common;

import lombok.Data;

import java.io.Serializable;

@Data
public class Paging implements Serializable {
	/** For Paging Field */
	// 현재 페이지
	private Integer currentPage;
	// 페이지 제한 수
	private Integer limit;
	//currentPage*limit
	private Integer maxCount;
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
	
	// 페이징 시작 번호 
	private int startNum;
	// 페이징 끝나는 번호
	private int lastNum;
	private int previousPage;
	private int nextPage;
	
	/** For searching Field */
	// 참조할 때의 엔티티 키 값
	private Long id;
	// To Find Entity Name
	private String entityName;
	// 게시판 종류
	private String boardType;

	private String question;
	// 검색 종류 설정
	private int searchType;
	// 검색어
	private String searchText;
	// 게시판 날짜 검색용
	private int searchDate;

	public Paging() {

	}

	public Paging(int currentPage, int searchType, String searchText, int searchDate, int limit, String boardType){
		this.currentPage = currentPage;
		this.searchType = searchType;
		this.searchText = searchText;
		this.searchDate=searchDate;
		this.limit = limit;
		this.boardType=boardType;
	}

	public Paging(int currentPage, int searchType, String searchText, int searchDatesDate, int limit) {
		this.currentPage = currentPage;
		this.searchType = searchType;
		this.searchText = searchText;
		this.limit = limit;
		this.searchDate = searchDatesDate;
	}
}