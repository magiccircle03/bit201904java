//2.EMP 테이블의 모든 데이터를 출력하는 프로그램을 작성해보자.
package jdbcex;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Ex2 {

	public static void main(String[] args) {
		Connection conn = null;
		try {
			// 1. 드라이버 로드
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// 2. DB 연결
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","scott","tiger");
			
			// 3. 처리 (statement 생성, excute)
			// statement 생성
			Statement stmt = conn.createStatement();
			// sql 실행
			String sql = "select * from emp0709";
			ResultSet rs = stmt.executeQuery(sql);
			
			System.out.println("\n=====  emp0709 테이블의 모든 정보를 출력합니다.  =====\n");
			
			// 4. 결과 출력
			// resultset으로 컬럼명도 불러오도록 해보기
			while(rs.next()) {
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
			rs.close();
			stmt.close();
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
