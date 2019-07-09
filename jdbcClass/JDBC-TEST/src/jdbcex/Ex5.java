// 5.모든 사원정보를 출력하되 부서정보를 함께 출력하는 프로그램을 작성해보자.
package jdbcex;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Ex5 {

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
			String sql = "select * from emp0709 e join dept d on e.deptno = d.deptno";
			ResultSet rs = stmt.executeQuery(sql);
			
			System.out.println("\n===== * 사원 정보와 부서 정보를 출력합니다. * =====\n");
			
			// 4. 결과 출력
			// + 컬럼명도 쓰기 귀찮으니까 불러와서 출력하도록 해보면 좋겠다
			while(rs.next()) {
				System.out.println("사원번호 : "+rs.getInt("empno"));//empno
				System.out.println("사원이름 : "+rs.getString("ename"));//ename
				System.out.println("담당업무 : "+rs.getString("job"));//job
				System.out.println("매 니 저 : "+rs.getInt("mgr"));//mgr
				System.out.println("입 사 일 : "+rs.getDate("hiredate"));//hiredate
				System.out.println("월    급 : "+rs.getInt("sal"));//sal
				System.out.println("커 미 션 : "+rs.getInt("comm"));//comm
				System.out.println("부서번호 : "+rs.getInt("deptno"));//deptno
				System.out.println("부서이름 : "+rs.getString("dname"));//dname
				System.out.println("부서위치 : "+rs.getString("loc"));//loc
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
