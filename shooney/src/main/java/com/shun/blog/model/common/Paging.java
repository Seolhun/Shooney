package com.shun.blog.model.common;

public class Paging {
	// 현재 페이지
	private int cPage;
	// 페이지 제한 수
	private int limit;
	// 검색 종류 설정
	private int sType;
	// 검색어
	private String sText;
	// 게시판 날짜 검색용
	private String sDate;
	// 총 게시물 갯수
	private int totalCount;
	// 총 페이지 갯수
	private int totalPage;
	// 페이지 블록 제한 수
	private int blockLimit;
	// 총 페이지 블록 수
	private int totalPageBlock;
	// 현재 블록 위치
	private int currentBlock;
	// 게시물 시작 블록번호
	private int blockStartNo;
	// 게시물 끝 블록번호
	private int blockEndNo;
	// 게시판 종류
	private String entityName;
	// 게시판 별 해당 게시판 제목
	private String pfName;
	
	private int id;

	public Paging() {

	}

	public Paging(int currentPage, int sType, String sText, String sDate, int limit) {
		this.cPage = currentPage;
		this.sType = sType;
		this.sText = sText;
		this.limit = limit;
		this.sDate = sDate;
	}

	public Paging(int currentPage, int sType, String sText, String sDate, int limit, String entityName, String pfName) {
		this.cPage = currentPage;
		this.sType = sType;
		this.sText = sText;
		this.sDate = sDate;
		this.limit = limit;
		this.entityName = entityName;
		this.pfName = pfName;
	}

	public int getcPage() {
		return cPage;
	}

	public void setcPage(int cPage) {
		this.cPage = cPage;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public int getsType() {
		return sType;
	}

	public void setsType(int sType) {
		this.sType = sType;
	}

	public String getsText() {
		return sText;
	}

	public void setsText(String sText) {
		this.sText = sText;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getBlockStartNo() {
		return blockStartNo;
	}

	public void setBlockStartNo(int blockStartNo) {
		this.blockStartNo = blockStartNo;
	}

	public int getBlockEndNo() {
		return blockEndNo;
	}

	public void setBlockEndNo(int blockEndNo) {
		this.blockEndNo = blockEndNo;
	}

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public String getPfName() {
		return pfName;
	}

	public void setPfName(String pfName) {
		this.pfName = pfName;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getBlockLimit() {
		return blockLimit;
	}

	public void setBlockLimit(int blockLimit) {
		this.blockLimit = blockLimit;
	}

	public int getTotalPageBlock() {
		return totalPageBlock;
	}

	public void setTotalPageBlock(int totalPageBlock) {
		this.totalPageBlock = totalPageBlock;
	}

	public int getCurrentBlock() {
		return currentBlock;
	}

	public void setCurrentBlock(int currentBlock) {
		this.currentBlock = currentBlock;
	}

	public String getsDate() {
		return sDate;
	}

	public void setsDate(String sDate) {
		this.sDate = sDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	};
}