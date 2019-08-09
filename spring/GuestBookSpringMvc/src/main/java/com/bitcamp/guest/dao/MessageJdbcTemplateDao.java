package com.bitcamp.guest.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.bitcamp.guest.domain.Message;

@Repository("jdbcTemplateDao")
public class MessageJdbcTemplateDao { // messageDao
	
	@Autowired //서블릿~에 빈으로 해놨죠
	JdbcTemplate template; // 주입받았음
	
	// 인서트 . jdbc를 사용한 새로운 인서트!
	public int insert(Message message) {
		String sql="INSERT INTO GUESTBOOK_MESSAGE " + " (MESSAGE_ID, GUEST_NAME, PASSWORD, MESSAGE) "
				+ " values (GM_MID_SEQ.nextval,?,?,?)";

		return template.update( sql, message.getGuestName(), message.getPassword(), message.getMessage() );
	}
	
	// 인서트 . 이전에 사용했던거 그냥 두고 오버라이드형식으로 해보겠음 (난 그냥 편하게 주석함)
//	public int insert(Connection conn, Message message) {
//		int rCnt = 0;
//		PreparedStatement pstmt = null;
//
//		String sql = "INSERT INTO GUESTBOOK_MESSAGE " + " (MESSAGE_ID, GUEST_NAME, PASSWORD, MESSAGE) "
//				+ " values (GM_MID_SEQ.nextval,?,?,?)";
//
//		try {
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, message.getGuestName());
//			pstmt.setString(2, message.getPassword());
//			pstmt.setString(3, message.getMessage());
//
//			rCnt = pstmt.executeUpdate();
//
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} finally {
//
//			try {
//				pstmt.close();
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//
//		}
//
//		return rCnt;
//	}

	

	// 새 카운트. 정말 간단하다...!
	public int selectCount() {
		// 저걸 인티저 형식으로 ~ 겠다. 이게 되는 이유는? 래퍼클래스 오토박싱 언박싱으로 되는것...!
		return template.queryForObject("select count(*) from guestbook_message", Integer.class);
	}
	
//	이전에 사용하던 카운트. 이전에 사용하던 
//	public int selectCount(Connection conn) {
//
//		Statement stmt = null;
//		ResultSet rs = null;
//
//		int totalCnt = 0;
//
//		String sql = "select count(*) from guestbook_message";
//
//		try {
//			stmt = conn.createStatement();
//
//			rs = stmt.executeQuery(sql);
//
//			if (rs.next()) {
//				totalCnt = rs.getInt(1);
//			}
//
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		return totalCnt;
//	}


	//새로운  셀렉트리스트
	public List<Message> selectList(int firstRow, int endRow) {


		String sql = "select message_id, guest_name, password, message from ( "
				+ " select rownum rnum, message_id, guest_name, password, message from ( "
				+ " select * from guestbook_message m order by m.message_id desc " + " ) where rownum <= ? "
				+ " ) where rnum >= ?";

		return template.query(sql, new MessageRowMapper(), endRow, firstRow);
	}

	//예전 셀렉트리스트
//	public List<Message> selectList(Connection conn, int firstRow, int endRow) {
//
//		List<Message> list = new ArrayList<Message>();
//
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		String sql = "select message_id, guest_name, password, message from ( "
//				+ " select rownum rnum, message_id, guest_name, password, message from ( "
//				+ " select * from guestbook_message m order by m.message_id desc " + " ) where rownum <= ? "
//				+ " ) where rnum >= ?";
//
//		try {
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setInt(1, endRow);
//			pstmt.setInt(2, firstRow);
//
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//
//				Message msg = new Message();
//				msg.setId(rs.getInt(1));
//				msg.setGuestName(rs.getString(2));
//				msg.setPassword(rs.getString(3));
//				msg.setMessage(rs.getString(4));
//
//				list.add(msg);
//
//			}
//
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		return list;
//	}

	//새 셀렉트. 
	public Message select(int messageId) {
		return template.queryForObject("select * from guestbook_message where message_id=?", new MessageRowMapper(), messageId);
	}
	
	
	//이전의 셀렉트
//	public Message select(Connection conn, int messageId) {
//
//		Message message = null;
//
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		String sql = "select * from guestbook_message where message_id=?";
//
//		try {
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setInt(1, messageId);
//
//			rs = pstmt.executeQuery();
//
//			if (rs.next()) {
//				message = new Message();
//				message.setId(rs.getInt(1));
//				message.setGuestName(rs.getString(2));
//				message.setPassword(rs.getString(3));
//				message.setMessage(rs.getString(4));
//
//			}
//
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		return message;
//
//	}
	
	
	// 새 방식의 딜리트. 내가 해본거
	public int deleteMessage(int messageId) throws SQLException {
		return template.update("delete from guestbook_message where message_id=?", messageId);
	}
	
	// 예전방식의 딜리트.
//	public int deleteMessage(Connection conn, int messageId) throws SQLException {
//
//		int resultCnt = 0;
//
//		PreparedStatement pstmt = null;
//
//		String sql = "delete from guestbook_message where message_id=?";
//
//		try {
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setInt(1, messageId);
//
//			resultCnt = pstmt.executeUpdate();
//
//		} finally {
//			JdbcUtil.close(pstmt);
//		}
//
//		return resultCnt;
//
//	}

}
