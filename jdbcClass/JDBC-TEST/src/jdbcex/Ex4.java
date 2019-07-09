// 4. EMP 테이블에서 “SCOTT” 이름으로 검색한 결과를 출력하는 프로그램을 작성해보자.
package jdbcex;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Ex4 {
	
	public static void main(String[] args) {
		
		Connection conn = null;
		
		String dbUrl = "jdbc:oracle:thin:@localhost:1521:orcl";
		String dbId = "scott";
		String dbPw = "tiger";
		
		String ename = "SCOTT";
		
		try {
			// 1. 드라이버 불러오기
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// 2. DB 연결하기
			conn = DriverManager.getConnection(dbUrl,dbId,dbPw);
			
			// 3. Statement 생성 / sql 실행

			String sql = "select * from emp0709 where ename=?";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, ename);
			
			ResultSet rs = pstmt.executeQuery();
			
			// 4. 결과 출력
			System.out.println("* "+ename+"씨의 정보는 다음과 같습니다! *");
			System.out.println("-------------------------------------------");
			while (rs.next()) {
				System.out.println("사원번호 : "+rs.getInt(1));//empno
				System.out.println("사원이름 : "+rs.getString(2));//ename
				System.out.println("담당업무 : "+rs.getString(3));//job
				System.out.println("매 니 저 : "+rs.getInt(4));//mgr
				System.out.println("입 사 일 : "+rs.getDate(5));//hiredate
				System.out.println("월    급 : "+rs.getInt(6));//sal
				System.out.println("커 미 션 : "+rs.getInt(7));//comm
				System.out.println("부서번호 : "+rs.getInt(8));//deptno
				System.out.println("-------------------------------------------");
	
			}
			
			// 5. 닫기
			pstmt.close();
			conn.close();
			

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
