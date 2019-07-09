// 3. EMP 테이블에 서 “SCOTT” 사원의 급여(sal) 정보를 1000으로 바꾸는 프로그램을 작성해보자.
package jdbcex;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Ex3 {
	
	public static void main(String[] args) {
		
		Connection conn = null;
		
		String dbUrl = "jdbc:oracle:thin:@localhost:1521:orcl";
		String dbId = "scott";
		String dbPw = "tiger";
		
		String ename = "SCOTT";
		int sal = 1000;
		
		try {
			// 1. 드라이버 불러오기
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// 2. DB 연결하기
			conn = DriverManager.getConnection(dbUrl,dbId,dbPw);
			
			System.out.println("db에 연결되었습니다!");
			
			
			// 3. Statement 생성 / sql 실행
			System.out.println(ename+"씨의 월급을 $"+sal+" 로 변경합니다!");
			String sql = "update emp0709 set sal=? where ename=?";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, sal);
			pstmt.setString(2, ename);
			
			int resultSql = pstmt.executeUpdate();
			System.out.println(resultSql+"개의 행 수정완료!");
			
			
			// 4. 닫기
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
