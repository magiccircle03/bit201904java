package com.bitcamp.mm.member.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bitcamp.mm.member.dao.MemberJdbcTemplateDao;
import com.bitcamp.mm.member.domain.MemberInfo;

@Service("loginService")
public class MemberLoginService implements MemberService { // MemberService는 그저 마킹효과 나중에 관리~

	@Autowired
	private MemberJdbcTemplateDao dao;

	public boolean login(String id, String pw, HttpServletRequest request) {
		boolean loginChk = false;

		MemberInfo memberInfo = null;

		memberInfo = dao.selectMemberById(id);
		if (memberInfo != null && memberInfo.pwChk(pw)) {
			// 세션에 저장
			// loginChk 상태값 true로 변경
			request.getSession(true).setAttribute("loginInfo", memberInfo.toLoginInfo());
			;// 없으면 오류인데, 트루로 하면 될듯. 트루하면 세션 새로 만들어주니까
			loginChk = true;

		}

		return loginChk;
	}

	//이전코드
//	@Autowired
//	private MemberDao dao;
//
//	public boolean login(String id, String pw, HttpServletRequest request) {
//		boolean loginChk = false;
//
//		MemberInfo memberInfo = null;
//
//		Connection conn = null;
//		try {
//			conn = ConnectionProvider.getConnection();
//			memberInfo = dao.selectMemberById(conn, id);
//			if (memberInfo!=null && memberInfo.pwChk(pw)) {
//				// 세션에 저장
//				// loginChk 상태값 true로 변경
//				request.getSession(true).setAttribute("loginInfo", memberInfo.toLoginInfo());;// 없으면 오류인데, 트루로 하면 될듯. 트루하면 세션 새로 만들어주니까
//				loginChk=true;
//				
//			}
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//
//		return loginChk;
//	}

}
