// 1.EMP 테이블에 새로운 사원 정보를 입력하는 프로그램을 작성해보자.
package jdbcex;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Ex1 {
	
	public static void main(String[] args) {
		
		Connection conn = null;
		
		String dbUrl = "jdbc:oracle:thin:@localhost:1521:orcl";
		String dbId = "scott";
		String dbPw = "tiger";
		
		try {
			// 1. 드라이버 불러오기
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// 2. DB 연결하기
			conn = DriverManager.getConnection(dbUrl,dbId,dbPw);
			
			System.out.println("db에 연결되었습니다!");
			
			// ================= 입력받기 시작 =================
			System.out.println("추가할 사원의 정보를 입력해주세요! ");
			
			Scanner sc = new Scanner(System.in);
			
			System.out.print("사원번호 : ");
			int empno = Integer.parseInt(sc.nextLine().trim());
			
			System.out.print("사원이름 : ");
			String ename = sc.nextLine();
			
			System.out.print("담당업무 : ");
			String job = sc.nextLine();
			
			System.out.print("매니저 사원번호 : ");
			int mgr = Integer.parseInt(sc.nextLine().trim());
			
			//System.out.println("입사일 : ");
			//String hiredate = sc.nextLine();
			
			System.out.println("월급 : ");
			int sal = Integer.parseInt(sc.nextLine().trim());
			
			System.out.println("커미션 : ");
			int comm = Integer.parseInt(sc.nextLine().trim());
			
			System.out.println("부서 번호 : ");
			int deptno = Integer.parseInt(sc.nextLine().trim());
			
			//System.out.println("입력한 정보 : "+empno+"\t "+ename+"\t "+job+"\t "+mgr+"\t "+sal+"\t "+comm+"\t "+deptno);
			
			// ================= 입력받기 끝 =================
			
			// 3. Statement 생성 / sql 실행

			String sql = "insert into emp0709 values (?,?,?,?,sysdate,?,?,?)";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, empno);
			pstmt.setString(2, ename);
			pstmt.setString(3, job);
			pstmt.setInt(4, mgr);
			//pstmt.setDate(5, hiredate);
			pstmt.setInt(5, sal);
			pstmt.setInt(6, comm);
			pstmt.setInt(7, deptno);
			
			int resultSql = pstmt.executeUpdate();
			System.out.println(resultSql+" 입력 완료!");

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
