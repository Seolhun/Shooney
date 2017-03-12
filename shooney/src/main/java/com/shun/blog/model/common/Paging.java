package com.shun.blog.model.common;

import lombok.Data;

@Data
public class Paging {
	// 참조할 때의 엔티티 키 값
	private Long id;
	// 게시판 종류
	private String tableName;
	private String kind;
	private String pfName;
	private String entityName;

	// 현재 페이지
	private Integer cPage;
	// 페이지 제한 수
	private Integer limit;
	// 검색 종류 설정
	private Integer sType;
	// 검색어
	private String sText;
	// 게시판 날짜 검색용
	private Integer sDate;
	// 총 게시물 갯수
	private Integer totalCount;
	// 총 페이지 갯수
	private Integer totalPage;
	// 페이지 블록 제한 수
	private Integer blockLimit;
	// 총 페이지 블록 수
	private Integer totalBlock;
	// 현재 블록 위치
	private Integer currentBlock;
	// 게시물 시작 블록번호
	private Integer blockStartNo;
	// 게시물 끝 블록번호
	private Integer blockEndNo;

	private String question;
	private Integer prev_cPage;
	private Integer next_cPage;

	public Paging() {

	}
	
	public Paging(Integer cPage, Integer sType, String sText, Integer sDate, Integer limit, String kind, String pfName){
		this.cPage = cPage;
		this.sType = sType;
		this.sText = question;
		this.sDate=sDate;
		this.kind=kind;
		this.limit = limit;
	}

	public Paging(Integer cPage, Integer sType, String sText, Integer sDate, Integer limit) {
		this.cPage = cPage;
		this.sType = sType;
		this.sText = sText;
		this.limit = limit;
		this.sDate = sDate;
	}

	public Paging(Integer cPage, Integer sType, String sText, Integer sDate, Integer limit, String tableName) {
		this.cPage = cPage;
		this.sType = sType;
		this.sText = sText;
		this.sDate = sDate;
		this.limit = limit;
		this.tableName = tableName;
	}
}