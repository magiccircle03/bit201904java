package dateShare.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dateShare.Model.Food;
import jdbc.jdbcUtil;

/*
	맛집 게시판 Dao
*/
public class FoodDao {
	
	// 싱글톤 처리
	private static FoodDao dao = new FoodDao();

	public static FoodDao getInstance() {
		return dao;
	}

	private FoodDao() {
	}

	// 게시글 insert
	public int insert(Connection conn, Food food) {
		int rCnt = 0;
		PreparedStatement pstmt = null;

		try {
			String sql = "insert into food values (FD_FNUM_SEQ.nextVal,?,?,?,sysdate,0,?,0,?)";

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, food.getU_num()); // 회원번호
			pstmt.setString(2, food.getF_title()); // 제목
			pstmt.setString(3, food.getF_content()); // 내용
			pstmt.setString(4, food.getF_path()); // 파일 경로
			pstmt.setInt(5, food.getF_star()); // 평점

			rCnt = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return rCnt;
	}
	
	// 게시글 1개 select - 상세보기와 delete에 쓰려고한다!
		public Food select(Connection conn, int f_num) {
			Food food = null;

			PreparedStatement pstmt = null;
			ResultSet rs = null;

			String sql = "select * from food where f_num=?";

			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, f_num);

				rs = pstmt.executeQuery();

				if (rs.next()) {
					//일단 다 가져와봤다
					food = new Food();
					food.setF_num(rs.getInt("f_num"));
					food.setU_num(rs.getInt("u_num"));
					food.setF_title(rs.getString("f_title"));
					food.setF_content(rs.getString("f_content"));
					food.setF_writedate(rs.getDate("f_writedate"));
					food.setF_like(rs.getInt("f_like"));
					food.setF_path(rs.getString("f_path"));
					food.setF_hits(rs.getInt("f_hits"));
					food.setF_star(rs.getInt("f_star"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return food;
		}
		
		// select count (전체 개수)  
		public int selectCount(Connection conn) {
			Statement stmt = null;
			ResultSet rs = null;

			int totalCnt = 0;

			String sql = "select count(*) from food";

			try {
				stmt = conn.createStatement();

				rs = stmt.executeQuery(sql);
				if (rs.next()) {
					totalCnt = rs.getInt(1);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

			return totalCnt;
		}

		// 출력에 필요한 만큼 리스트에 담아서 반환시키는 메서드 (대략보기)
		public List<Food> selectList(Connection conn, int firstRow, int endRow) {

			List<Food> list = new ArrayList<Food>();

			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			String sql = "select f_num, u_num, f_title, f_content, f_writedate, f_like, f_path, f_hits, f_star from (" 
					+ "select rownum rnum, f_num, u_num, f_title, f_content, f_writedate, f_like, f_path, f_hits, f_star from (" 
						+ "select * from food f order by f.f_num desc ) where rownum <= ? ) where rnum >= ?";
		
			//필요한 것만 한 경우
			/*
			 * select f_num, u_num, f_writedate, f_path, f_title, f_hits, f_star from (
			 * select rownum rnum, f_num, u_num, f_writedate, f_path, f_title, f_hits,
			 * f_star from ( select * from food f order by f.f_num desc ) where rownum <= ?
			 * ) where rnum >= ?;
			 */
			
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, endRow);
				pstmt.setInt(2, firstRow);

				rs = pstmt.executeQuery();

				while (rs.next()) {
					Food food = new Food();
					food.setF_num(rs.getInt(1));
					food.setU_num(rs.getInt(2));
					food.setF_title(rs.getString(3));
					food.setF_content(rs.getString(4));
					food.setF_writedate(rs.getDate(5));
					food.setF_like(rs.getInt(6));
					food.setF_path(rs.getString(7));
					food.setF_hits(rs.getInt(8));
					food.setF_star(rs.getInt(9));
					list.add(food);
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return list;
		}
		
		// 글번호로 글을 찾아 삭제하는 메서드
		public int deleteFood(Connection conn, int f_num) throws SQLException {
			int resultCnt = 0;
			PreparedStatement pstmt = null;
			String sql = "delete from food where f_num=?";
			
			try {
				pstmt = conn.prepareStatement(sql); 
				pstmt.setInt(1, f_num);
				resultCnt = pstmt.executeUpdate();
			} finally {
				jdbcUtil.close(pstmt);
			}
			return resultCnt;
		}
		
}
