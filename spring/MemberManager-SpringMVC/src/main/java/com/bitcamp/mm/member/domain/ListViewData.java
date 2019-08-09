package com.bitcamp.mm.member.domain;

import java.util.List;

public class ListViewData {
	private List<MemberInfo> memberList; // 실제론 얘 하나만 있어도 화면에 출력하는 데 문제가 없음. 넘버링 신경쓰지않고 출력만 한다면 얘만 있으면 ok
	// 이번엔 추가적으로 처리할 때 필요한 애들을 선언 
	private int totalCount; // 가져오는 거 dao에서 처리해야
	private int no; 
	private int currentPageNumber;
	private int pageTotalCount; //이걸 알면 넘버링 할 수 있음
	
	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public List<MemberInfo> getMemberList() {
		return memberList;
	}

	public void setMemberList(List<MemberInfo> memberList) {
		this.memberList = memberList;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public int getCurrentPageNumber() {
		return currentPageNumber;
	}

	public void setCurrentPageNumber(int currentPageNumber) {
		this.currentPageNumber = currentPageNumber;
	}

	public int getPageTotalCount() {
		return pageTotalCount;
	}

	public void setPageTotalCount(int pageTotalCount) {
		this.pageTotalCount = pageTotalCount;
	}

	
	
	
	
}
