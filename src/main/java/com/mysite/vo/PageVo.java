package com.mysite.vo;

public class PageVo {
	private int firstNo;
	private int endNo;
	private int currNo;
	private int totalCount;
	private int pageCnt;
	private int pageNo;
	private int pageEnd;
	
	
	public PageVo() {
		super();
	}


	public PageVo(int firstNo, int endNo, int currNo, int totalCount, int pageCnt, int pageNo, int pageEnd) {
		super();
		this.firstNo = firstNo;
		this.endNo = endNo;
		this.currNo = currNo;
		this.totalCount = totalCount;
		this.pageCnt = pageCnt;
		this.pageNo = pageNo;
		this.pageEnd = pageEnd;
	}

	
	public void setting(int currNo,int totalCount) {
		this.currNo=currNo;
		this.totalCount=totalCount;
		pageNo=4;
		pageCnt=3;
		pageEnd=(int) Math.ceil( (double)totalCount/pageNo);
		int unit=(int) Math.ceil((double)currNo/pageCnt);
		
		endNo=pageCnt*unit;
		firstNo=1+pageCnt*(unit-1);
		
		if(pageEnd<=endNo) {
			endNo=pageEnd;
		}
		
	}
	
	public int getFirstNo() {
		return firstNo;
	}


	public void setFirstNo(int firstNo) {
		this.firstNo = firstNo;
	}


	public int getEndNo() {
		return endNo;
	}


	public void setEndNo(int endNo) {
		this.endNo = endNo;
	}


	public int getCurrNo() {
		return currNo;
	}


	public void setCurrNo(int currNo) {
		this.currNo = currNo;
	}


	public int getTotalCount() {
		return totalCount;
	}


	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}


	public int getPageCnt() {
		return pageCnt;
	}


	public void setPageCnt(int pageCnt) {
		this.pageCnt = pageCnt;
	}


	public int getPageNo() {
		return pageNo;
	}


	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}


	public int getPageEnd() {
		return pageEnd;
	}


	public void setPageEnd(int pageEnd) {
		this.pageEnd = pageEnd;
	}


	@Override
	public String toString() {
		return "PageVo [firstNo=" + firstNo + ", endNo=" + endNo + ", currNo=" + currNo + ", totalCount=" + totalCount
				+ ", pageCnt=" + pageCnt + ", pageNo=" + pageNo + ", pageEnd=" + pageEnd + "]";
	}
	
	
	

}
