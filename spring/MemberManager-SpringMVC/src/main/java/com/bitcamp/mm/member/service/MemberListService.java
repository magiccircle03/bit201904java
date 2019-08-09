package com.bitcamp.mm.member.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bitcamp.mm.member.dao.MemberJdbcTemplateDao;
import com.bitcamp.mm.member.domain.ListViewData;
import com.bitcamp.mm.member.domain.MemberInfo;
import com.bitcamp.mm.member.domain.SearchParam;

@Service("listService")
public class MemberListService implements MemberService {

	@Autowired
	private MemberJdbcTemplateDao dao;

	final int MEMBER_CNT_LIST = 3; // 상수는 바뀌지 않으니까 굳이 프라이빗 할 필요는 없음

	public ListViewData getListData(int currentPageNumber, SearchParam searchParam) {

		ListViewData listData = new ListViewData();

		// 전체 게시물의 개수
		int totalCnt = dao.selectTotalCount(searchParam);

		int totalPageCnt = 0;
		// 전체 페이지 개수

		if (totalCnt > 0) {
			totalPageCnt = totalCnt / MEMBER_CNT_LIST;
			if (totalCnt % MEMBER_CNT_LIST > 0) {
				totalPageCnt++;
			}
		}
		listData.setPageTotalCount(totalPageCnt);

		// 구간 검색을 위한 index
		// 1->0 , 2->3, 3->6, 4->9 나오게
		int index = (currentPageNumber - 1) * MEMBER_CNT_LIST;

		// 회원 정보 리스트
		List<MemberInfo> memberList = null; // 이렇게 뺀다. 왜? 조건따라 다르게 하고싶어서

		// 흐름 알기 쉬우라고 메서드 따로 만들어서 처리했음(이래도 성능 문제되지 않음. 마이바티스가면 selectList하나로 다 하지만)

		// 1. 검색 조건이 없는 경우 selectList -> 전체 회원의 리스트
		// 2. id로 검색 : where uid like '%?%'
		// 3. name 으로 검색 : where uname like '%?%'
		// 4. id 또는 name 으로 검색 : where uid like '%?%' or uname like '%?%'

		if (searchParam == null) {
			memberList = dao.selectList(index, MEMBER_CNT_LIST);
		} else if (searchParam.getStype().equals("both")) {
			memberList = dao.selectListByBoth(index, MEMBER_CNT_LIST, searchParam);
		} else if (searchParam.getStype().equals("id")) {
			memberList = dao.selectListById(index, MEMBER_CNT_LIST, searchParam);
		} else if (searchParam.getStype().equals("name")) {
			memberList = dao.selectListByName(index, MEMBER_CNT_LIST, searchParam);
		}

		listData.setMemberList(memberList);

		// listData.setMemberList(dao.selectList(conn, index, MEMBER_CNT_LIST));

		// 1->9-0 = 9, 2->9-3=6
		int no = totalCnt - index; // 이러면 첫페이지 9 두번째 6 나오겠음
		listData.setNo(no);

		listData.setTotalCount(totalCnt);

		return listData;
	}

//	@Autowired
//	private MemberDao dao;
//	final int MEMBER_CNT_LIST = 3; // 상수는 바뀌지 않으니까 굳이 프라이빗 할 필요는 없음
//	
//	public ListViewData getListData(int currentPageNumber, SearchParam searchParam) {
//		
//		ListViewData listData = new ListViewData();
//		
//		Connection conn = null;
//		
//		try {
//			conn = ConnectionProvider.getConnection();
//			
//			// 현재 페이지 번호
//			listData.setCurrentPageNumber(currentPageNumber);
//			
//			// 전체 게시물의 개수
//			int totalCnt = dao.selectTotalCount(conn,searchParam);
//			
//			int totalPageCnt = 0;
//			// 전체 페이지 개수
//			
//			if(totalCnt>0) {
//				totalPageCnt = totalCnt/MEMBER_CNT_LIST;
//				if(totalCnt%MEMBER_CNT_LIST>0) {
//					totalPageCnt++;
//				}
//			}
//			listData.setPageTotalCount(totalPageCnt);
//			
//			
//			// 구간 검색을 위한 index
//			// 1->0 , 2->3, 3->6, 4->9 나오게 
//			int index = (currentPageNumber-1)*MEMBER_CNT_LIST;
//			
//			// 회원 정보 리스트
//			List<MemberInfo> memberList = null; // 이렇게 뺀다. 왜? 조건따라 다르게 하고싶어서
//			
//			// 흐름 알기 쉬우라고 메서드 따로 만들어서 처리했음(이래도 성능 문제되지 않음. 마이바티스가면 selectList하나로 다 하지만)
//			
//			// 1. 검색 조건이 없는 경우 selectList -> 전체 회원의 리스트
//			// 2. id로 검색 : where uid like '%?%'
//			// 3. name 으로 검색 : where uname like '%?%'
//			// 4. id 또는 name 으로 검색 : where uid like '%?%' or uname like '%?%'
//			
////			
////			if(searchParam != null) {
////				switch (searchParam.getStype()) {
////					case "both":
////						memberList = dao.selectListByBoth(conn, index, MEMBER_CNT_LIST, searchParam);
////					break;
////					case "id":
////						memberList = dao.selectListById(conn, index, MEMBER_CNT_LIST, searchParam);
////					break;
////					case "name":
////						memberList = dao.selectListByName(conn, index, MEMBER_CNT_LIST, searchParam);
////					break;
////				}
////			} else {
////				memberList = dao.selectList(conn, index, MEMBER_CNT_LIST);
////			}
////			
//			if(searchParam == null) {
//				memberList = dao.selectList(conn, index, MEMBER_CNT_LIST);
//			} else if(searchParam.getStype().equals("both")) {
//				memberList = dao.selectListByBoth(conn, index, MEMBER_CNT_LIST, searchParam);
//			} else if(searchParam.getStype().equals("id")) {
//				memberList = dao.selectListById(conn, index, MEMBER_CNT_LIST, searchParam);
//			} else if(searchParam.getStype().equals("name")) {
//				memberList = dao.selectListByName(conn, index, MEMBER_CNT_LIST, searchParam);
//			}
//			
//			
//			listData.setMemberList(memberList);
//			
//			//listData.setMemberList(dao.selectList(conn, index, MEMBER_CNT_LIST));
//		
//			// 1->9-0 = 9, 2->9-3=6 
//			int no = totalCnt - index; //이러면 첫페이지 9 두번째 6 나오겠음
//			listData.setNo(no);
//			
//			listData.setTotalCount(totalCnt);
//		
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		
//		return listData;
//	}
	
}
