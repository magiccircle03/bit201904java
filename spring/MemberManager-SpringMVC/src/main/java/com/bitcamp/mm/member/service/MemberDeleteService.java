package com.bitcamp.mm.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bitcamp.mm.member.dao.MemberJdbcTemplateDao;

@Service("deleteService")
public class MemberDeleteService implements MemberService{
	@Autowired
	private MemberJdbcTemplateDao dao;
	
	public int memberDelete(int id) { //사실 idx지만 그냥 idx라고 표현해주고
		return dao.memberDelete(id);
	}
	
	// 이전 코드
//	@Autowired
//	private MemberDao dao;
//	
//	public int memberDelete(int id) { //사실 idx지만 그냥 idx라고 표현해주고
//		int rCnt = 0;
//		Connection conn=null;
//		try {
//			conn = ConnectionProvider.getConnection();
//			rCnt = dao.memberDelete(conn,id);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		return rCnt;
//	}
}
