package Lib;

import java.sql.Connection;
import java.sql.Date;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import oracle.jdbc.driver.OracleDriver;

/**
 * JDBC ARI를 이용한 DBMS 연동
 * 
 * @author 이혜림
 *
 */
public class JDBCExample {

	public static void main(String[] args) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
		// #1.JDBC드라이버 로딩(객체생성)
//		Driver driver = new OracleDriver();
//		System.out.println("JDBC 드라이버 생성 완료.");

		// Class 클래스를 이용한 동적 객체 생성
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";// IP:포트번호:xe버전
		String username = "hr";
		String password = "hr";
		String sql = "SELECT e.employee_id     id, \r\n" + 
					 "       e.last_name       ename, \r\n" + 
					 "       e.salary          salary, \r\n" + 
					 "    TO_CHAR(e.hire_date,'YYYY-MM-DD HH24:MI:SS')   hiredate, \r\n" + 
					 "       d.department_name dname \r\n" + 
					 "FROM   employees e \r\n" + 
					 "       join departments d \r\n" + 
					 "         ON e.department_id = d.department_id";

		Class.forName(driver).newInstance(); // 정확한 FULLName
		//2. DBMS연결
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		con = DriverManager.getConnection(url, username, password);
		stmt = con.createStatement();
		rs = stmt.executeQuery(sql);

		while (rs.next()) {// 가져올 내용이 있으면,
			String employeeId = rs.getString("id");
			String lastName = rs.getString("ename");
			int salary = rs.getInt("salary");
			String hiredate = rs.getString("hiredate");
			String departmentName = rs.getString("dname");
			
			System.out.println(employeeId + "," + lastName + ", " + salary + ", " + hiredate + ", " + departmentName);
		}
		rs.close();
		stmt.close();
		con.close();
	}
}
