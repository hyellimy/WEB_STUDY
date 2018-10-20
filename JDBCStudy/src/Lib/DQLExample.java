package Lib;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import oracle.jdbc.driver.OracleDriver;

public class DQLExample {

	public static void main(String[] args) {
		// #1.JDBC드라이버 로딩(객체생성)
//		Driver driver = new OracleDriver();
//		System.out.println("JDBC 드라이버 생성 완료.");

		// Class 클래스를 이용한 동적 객체 생성
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";// IP:포트번호:xe버전
		String username = "hr";
		String password = "hr";

		String sql = "SELECT employee_id, last_name, salary\r\n" + "FROM employees"; // String / String / int

		try {
			Class.forName(driver);
			System.out.println("JDBC드라이버 생성완료 ");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		// #2. DriverManager를 이용한 RDBMS네트워크 연결 (SOCKET연결) (SOCKET관리자 : 드라이버매니저)
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			// Connection 역할 : 커넥션과 관련한 기능 (con)
//			Connection con = DriverManager.getConnection(url, username, password);
			System.out.println("DBMS 연결 완료" + con); // T4C

			// #3. SQL 서버 전송 및 결과집합(result set) 수신
//			Statement stmt = con.createStatement();
			System.out.println("stmt서버전송내용: " + stmt); // stmt를 통해 서버 전송
//			ResultSet rs = stmt.executeQuery(sql);  //varchar2를 string으로 형변환 해준다.
			System.out.println("rs결과집합: " + rs);

			// #4. ResultSet에서 데이터 인출
			while (rs.next()) {// 가져올 내용이 있으면,
//				rs.getString(1); 더 정확하게 인출하기 위해 이름 사용
				String employeeId = rs.getString("employee_id");
				String lastName = rs.getString("last_name");
				int salary = rs.getInt("salary");
				System.out.println(employeeId + "," + lastName + ", " + salary);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try { //마지막에는 순서 거꾸로 다 소켓 닫아주어야 한다. 
				if (rs != null) {
					rs.close();
					stmt.close();
					con.close();
				}
			} catch (Exception e) {
			}
		}
	}
}
