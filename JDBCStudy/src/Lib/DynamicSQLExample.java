package Lib;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import oracle.jdbc.driver.OracleDriver;

public class DynamicSQLExample {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";// IP:포트번호:xe버전
	String username = "hr";
	String password = "hr";

	// sql에 따라 실행가능한 메서드
	public void executeSQL(String sql) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver).newInstance();
			con = DriverManager.getConnection(url, username, password);
			pstmt = con.prepareStatement(sql);
			boolean existRS = pstmt.execute(); //리턴타입이 다르다. (boolean)

			if (existRS) {
				rs = pstmt.getResultSet(); //내용값
				ResultSetMetaData rsm = rs.getMetaData(); //형태만 
				int ColumnCount = rsm.getColumnCount();

				// 컬럼명 출력 - 컬럼을 빼온다. 
				for (int i = 1; i <= ColumnCount; i++) {
					String columnName = rsm.getColumnLabel(i);
					System.out.println(columnName);
				}
				// 컬럼값 출력
				while (rs.next()) {
					int columCount = rsm.getColumnCount();
					for (int i = 1; i <= columCount; i++) {
						String columnName = rsm.getColumnLabel(i);
						String columnValue = rs.getString(columnName);
						System.out.println(columnValue);

					}
					System.out.println();
				}

			} else {
				int count = pstmt.getUpdateCount();
				System.out.println(count + " 행이 변경되었습니다..");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		DynamicSQLExample exam = new DynamicSQLExample();
		exam.executeSQL("SELECT * FROM employees");

//		String sql = "create table my_table(somecolumn varchar2(20) not null)";
//		exam.executeSQL(sql);

	}
}
