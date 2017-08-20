package com.mysite.vo;

public class ReplyBoardVo {
	private int rn;
	private int no;
	private String title;
	private String content;
	private String name;
	private int hit;
	private String regDate;
	private int userNo;
	private int groupNo;
	private int orderNo;
	private int depth;
	private int space;
	private int sign;
	
	public ReplyBoardVo() {
		super();
	}

	public ReplyBoardVo(int rn, int no, String title, String content, String name, int hit, String regDate, int userNo,
			int groupNo, int orderNo, int depth, int space, int sign) {
		super();
		this.rn = rn;
		this.no = no;
		this.title = title;
		this.content = content;
		this.name = name;
		this.hit = hit;
		this.regDate = regDate;
		this.userNo = userNo;
		this.groupNo = groupNo;
		this.orderNo = orderNo;
		this.depth = depth;
		this.space = space;
		this.sign = sign;
	}

	public int getRn() {
		return rn;
	}

	public void setRn(int rn) {
		this.rn = rn;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public int getGroupNo() {
		return groupNo;
	}

	public void setGroupNo(int groupNo) {
		this.groupNo = groupNo;
	}

	public int getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	public int getSpace() {
		return space;
	}

	public void setSpace(int space) {
		this.space = space;
	}

	public int getSign() {
		return sign;
	}

	public void setSign(int sign) {
		this.sign = sign;
	}

	@Override
	public String toString() {
		return "ReplyBoardVo [rn=" + rn + ", no=" + no + ", title=" + title + ", content=" + content + ", name=" + name
				+ ", hit=" + hit + ", regDate=" + regDate + ", userNo=" + userNo + ", groupNo=" + groupNo + ", orderNo="
				+ orderNo + ", depth=" + depth + ", space=" + space + ", sign=" + sign + "]";
	}
	
	
	

	
}
