package com.bitcamp.mm.member.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.bitcamp.mm.member.domain.MemberInfo;
import com.bitcamp.mm.member.domain.SearchParam;

/* 빈의 이름 지정하지 않으면 자동으로 memberDao 이렇게 지정될 것 */
/* 멤버dao 복사해와서 그대로 이거 그냥 dao라고 하니까 서버를 시작할 수 없고 500에러가 났음. 그래서 이름을 바꿔줬다 */
@Repository("templateDao")
public class MemberJdbcTemplateDao {
	@Autowired
	JdbcTemplate template;
	
	// 더 새로운 selectMemberById - 선생님 참고하기 선생님은 selectMemberById2라는 이름. 
	// 선생님은 개인적으로 아래 코드(삼항연산자 한 거)를 더 선호함
	public MemberInfo selectMemberByIdNew(String userId) {
		
		String sql = "select * from member where uid=?";
		MemberInfo memberInfo = null;
		try {
			memberInfo=template.queryForObject(sql, new Object[]{userId},new MemberInfoRowMapper());
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return memberInfo;
	}
	
	// 새로운 selectMemberById
	public MemberInfo selectMemberById(String userId) {
		String sql = "select * from member where uid=?";
		List<MemberInfo> list = template.query(sql, new Object[]{userId},new MemberInfoRowMapper());// sql, 오브젝트 타입의 배열(어떤 타입이 와도 상관x단거)(파라미터들의 배열.. 순서대로),이번엔 이거 한 번 써볼게여 
		//template.query("select * from member where uid=?", rse, userId);// 어젠 이걸사용했죠? 같은거임. 근데 파라미터 나열 하다보면 실수가 잦을 것 같아서 쌤은 위에 걸 많이 사용함  
		
		return list.isEmpty()?null:list.get(0);
	}
	
	// 예전 selectMemberById
//	public MemberInfo selectMemberById(Connection conn, String userId) {
//
//		MemberInfo memberInfo = null;
//
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		System.out.println("dao : memberId -> " + userId);
//
//		String sql = "select * from member where uid=?";
//
//		try {
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, userId);
//			rs = pstmt.executeQuery();
//			// 널인 경우가 있고 나왔는데 행이 없는 경우도 있으니까. 그리고 아이디는 유니크니까 와일이 아닌 이프를 썼음. 있다없다만 판단하면 되니까
//			if (rs != null && rs.next()) {
//				System.out.println("check ::::::::::::::::::::::::");
//				memberInfo = new MemberInfo(
//						rs.getInt("idx"), 
//						rs.getString("uid"), 
//						rs.getString("upw"),
//						rs.getString("uname"), 
//						rs.getString("uphoto"), 
//						new Date(rs.getTimestamp("regdate").getTime()));
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} finally {
//			JdbcUtil.close(rs);
//			JdbcUtil.close(pstmt);
//		}
//
//		return memberInfo;
//	}

	// 새 insertMember
	public int insertMember(MemberInfo memberInfo) {
		String sql = "insert into member (uid, uname, upw, uphoto) values (?,?,?,?) ";
		return template.update(sql,memberInfo.getuId(),memberInfo.getuName(),memberInfo.getuPW(),memberInfo.getuPhoto());
	}
	
	// 이전 insertMember
//	public int insertMember(Connection conn, MemberInfo memberInfo) {
//
//		PreparedStatement pstmt = null;
//
//		int rCnt = 0;
//
//		// idx도 오토인크리먼트, regDate는 디폴트 해놔서 안 썼음. 웹서버 서버시간으로 하는 경우도 있는데 그렇게 할거면 디폴트가 아니라
//		// 따로 ~해줘야겠죠?
//		// uPhoto는 실제 저장한 파일 이름을~
//		String sql = "insert into member (uid, uname, upw, uphoto) values (?,?,?,?) ";
//
//		try {
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, memberInfo.getuId());
//			pstmt.setString(2, memberInfo.getuName());
//			pstmt.setString(3, memberInfo.getuPW());
//			pstmt.setString(4, memberInfo.getuPhoto());
//			rCnt = pstmt.executeUpdate();
//
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		return rCnt;
//
//	}
	
	// 새 selectTotalCount
	public int selectTotalCount(SearchParam searchParam) {
		
		String sql = "select count(*) from member";
		
		// 문자열 연산이 많이 이루어지니까 좋은 방식은 아님. 메모리 낭비
		if (searchParam != null) {
			sql = "select count(*) from member where ";
			if (searchParam.getStype().equals("both")) {
				sql += " uid like '%" + searchParam.getKeyword() + "%' or uname like '%" + searchParam.getKeyword()
						+ "%'";
			}
			if (searchParam.getStype().equals("id")) {
				sql += " uid like '%" + searchParam.getKeyword() + "%'";
			}
			if (searchParam.getStype().equals("uname")) {
				sql += " uname like '%" + searchParam.getKeyword() + "%'";
			}
		}
		
		return template.queryForObject(sql, Integer.class);
	}
	
	// 예전 selectTotalCount
//	public int selectTotalCount(Connection conn, SearchParam searchParam) {
//		int totalCnt = 0;
//
//		Statement stmt = null;
//		ResultSet rs = null;
//
//		String sql = "select count(*) from member";
//		// 마이바티스가면 xml로 동적쿼리를 더 편하게
//		if (searchParam != null) {
//			sql = "select count(*) from member where ";
//			if (searchParam.getStype().equals("both")) {
//				sql += " uid like '%" + searchParam.getKeyword() + "%' or uname like '%" + searchParam.getKeyword()
//						+ "%'";
//			}
//			if (searchParam.getStype().equals("id")) {
//				sql += " uid like '%" + searchParam.getKeyword() + "%'";
//			}
//			if (searchParam.getStype().equals("uname")) {
//				sql += " uname like '%" + searchParam.getKeyword() + "%'";
//			}
//		}
//
//		try {
//			stmt = conn.createStatement();
//			rs = stmt.executeQuery(sql);
//			// 있기는 물론 있을것임 0이 나오지 null이 나오지 않으니 rs는 무조건 개수가 나옴. 그럼에도 써보는 이유는~~???
//			if (rs.next()) {
//				totalCnt = rs.getInt(1);// 첫번째 거
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		return totalCnt;
//	}
	
	
	// 새 selectList
	public List<MemberInfo> selectList(int index, int count) {

		String sql = "SELECT * FROM member order by idx desc limit ?,?";

		return template.query(sql, new MemberInfoRowMapper(), index, count);
	}


	//예전 selectList
//	public List<MemberInfo> selectList(Connection conn, int index, int count) {
//
//		List<MemberInfo> memberList = new ArrayList<MemberInfo>();
//
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		// String sql = "SELECT * FROM member order by uname";//여기선 객체이름 적지 않아도 상관없져
//		String sql = "SELECT * FROM member limit ?,?";
//		try {
//			pstmt = conn.prepareStatement(sql); // 물음표도 없는데 왜? 뒤쪽에 검색하려구
//			pstmt.setInt(1, index);
//			pstmt.setInt(2, count);
//			rs = pstmt.executeQuery();
//			// rs.next()는 커서가 있는지 확인(커서란 표현을 사용)
//			while (rs.next()) {
//				memberList.add(new MemberInfo(rs.getInt("idx"), rs.getString("uid"), rs.getString("upw"),
//						rs.getString("uname"), rs.getString("uphoto"), new Date(rs.getDate("regDate").getTime())));
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		return memberList;
//	}

	
	// 새 selectListById
	public List<MemberInfo> selectListById(int index, int count, SearchParam searchParam) {

		String sql = "SELECT * FROM member where uid like ? limit ?, ?";
		
		return template.query(sql, new MemberInfoRowMapper(),"%"+searchParam.getKeyword()+"%",index,count);
		
	}

	
	// 예전 selectListById
//	public List<MemberInfo> selectListById(Connection conn, int index, int count, SearchParam searchParam) {
//
//		List<MemberInfo> memberList = new ArrayList<MemberInfo>();
//
//		PreparedStatement pstmt = null;
//		ResultSet rs = null; // 파이프 연산 %% || 문자열 합해주는 것이 필요함???
//
//		String sql = "SELECT * FROM member where uid like ? limit ?, ?";
//		// %?% 이렇게 안 하는 이유는 mysql은 그렇게 지원 안 해서
//		try {
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, "%" + searchParam.getKeyword() + "%");
//			pstmt.setInt(2, index);
//			pstmt.setInt(3, count);
//
//			rs = pstmt.executeQuery();
//			while (rs.next()) {
//				memberList.add(new MemberInfo(rs.getInt("idx"), rs.getString("uid"), rs.getString("upw"),
//						rs.getString("uname"), rs.getString("uphoto"), new Date(rs.getDate("regdate").getTime())));
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		return memberList;
//	}

	// 새 selectListByName
	public List<MemberInfo> selectListByName(int index, int count, SearchParam searchParam) {
		String sql = "SELECT * FROM member where uname like ?  limit ?, ?";

		return template.query(sql, new MemberInfoRowMapper(),"%"+searchParam.getKeyword()+"%",index,count);
	}
	
	// 예전 selectListByName
//	public List<MemberInfo> selectListByName(Connection conn, int index, int count, SearchParam searchParam) {
//
//		List<MemberInfo> memberList = new ArrayList<MemberInfo>();
//
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		String sql = "SELECT * FROM member where uname like ?  limit ?, ?";
//
//		try {
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, "%" + searchParam.getKeyword() + "%");
//			pstmt.setInt(2, index);
//			pstmt.setInt(3, count);
//
//			rs = pstmt.executeQuery();
//			while (rs.next()) {
//				memberList.add(new MemberInfo(rs.getInt("idx"), rs.getString("uid"), rs.getString("upw"),
//						rs.getString("uname"), rs.getString("uphoto"), new Date(rs.getDate("regdate").getTime())));
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		return memberList;
//	}
	
	// 새 selectListByBoth
	public List<MemberInfo> selectListByBoth(int index, int count, SearchParam searchParam) {

		String sql = "SELECT * FROM member where uid like ? or  uname like ?  limit ?, ?";


		return template.query(sql, new MemberInfoRowMapper(),"%"+searchParam.getKeyword()+"%","%"+searchParam.getKeyword()+"%",index,count);
	}
	
	// 옛selectListByBoth
//	public List<MemberInfo> selectListByBoth(Connection conn, int index, int count, SearchParam searchParam) {
//
//		List<MemberInfo> memberList = new ArrayList<MemberInfo>();
//
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		String sql = "SELECT * FROM member where uid like ? or  uname like ?  limit ?, ?";
//
//		try {
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, "%" + searchParam.getKeyword() + "%");
//			pstmt.setString(2, "%" + searchParam.getKeyword() + "%");
//			pstmt.setInt(3, index);
//			pstmt.setInt(4, count);
//
//			rs = pstmt.executeQuery();
//			while (rs.next()) {
//				memberList.add(new MemberInfo(rs.getInt("idx"), rs.getString("uid"), rs.getString("upw"),
//						rs.getString("uname"), rs.getString("uphoto"), new Date(rs.getDate("regdate").getTime())));
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		return memberList;
//	}
	
	// 새 selectMemberByIdx
	public MemberInfo selectMemberByIdx(int id) {
		String sql = "select * from member where idx=?";
		//null이 들어온경우 익셉션 처리해줘야. 그냥 주소창에 쳐서 들어오는 사람들도 있으니까
		MemberInfo memberInfo = null;
		try {
			memberInfo = template.queryForObject(sql, new MemberInfoRowMapper(), id);
		} catch (DataAccessException e) {
			//자바에서 해주는애도 있고.. 스프링에서 제공하는이걸 해봄
			e.printStackTrace();
		}
		return memberInfo;
	}
	
//	//옛 selectMemberByIdx
//	public MemberInfo selectMemberByIdx(Connection conn, int id) {
//
//		MemberInfo memberInfo = null;
//		
//		PreparedStatement pstmt=null;
//		ResultSet rs=null;
//		
//		System.out.println("dao : memberId -> " + id);
//		
//		String sql = "select * from member where idx=?";
//		
//		try {
//			pstmt=conn.prepareStatement(sql);
//			pstmt.setInt(1,id);
//			rs = pstmt.executeQuery();
//			if(rs!=null && rs.next()) {
//				System.out.println("check ::::::::::::::::::::::::");
//				memberInfo = new MemberInfo(
//					rs.getInt("idx"), 
//					rs.getString("uid"), 
//					rs.getString("upw"), 
//					rs.getString("uname"), 
//					rs.getString("uphoto"), 
//					new Date(rs.getTimestamp("regdate").getTime()));
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} finally {
//			JdbcUtil.close(rs);
//			JdbcUtil.close(pstmt);
//		}
//		
//		
//		return memberInfo;
//	}

	// 새 memberUpdate
	public int memberUpdate(MemberInfo memberInfo) {
	
		String sql = "update member set uname=?, upw=?, uphoto=? where idx=?";

		return template.update(sql, memberInfo.getuName(),memberInfo.getuPW(),memberInfo.getuPhoto(),memberInfo.getIdx());
	}

	// 옛 memberUpdate
//	public int memberUpdate(Connection conn, MemberInfo memberInfo) {
//		
//		System.out.println(">>>>>>>>>>>> "+memberInfo);
//		int rCnt = 0;
//		PreparedStatement pstmt = null;
//		String sql = "update member set uname=?, upw=?, uphoto=? where idx=?";
//		
//		try {
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, memberInfo.getuName());
//			pstmt.setString(2, memberInfo.getuPW());
//			pstmt.setString(3, memberInfo.getuPhoto());
//			pstmt.setInt(4, memberInfo.getIdx());
//			rCnt = pstmt.executeUpdate();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//				
//		return rCnt;
//	}

	// 새 memberDelete
	public int memberDelete(int id) {

		String sql = "delete from member where idx=?";
		
		return template.update(sql,id);
	}

	// 옛 memberDelete
//	public int memberDelete(Connection conn, int id) {
//		// TODO Auto-generated method stub
//		int rCnt=0;
//		PreparedStatement pstmt = null;
//		String sql = "delete from member where idx=?";
//		try {
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setInt(1, id);
//			rCnt = pstmt.executeUpdate();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		return rCnt;
//	}

}
