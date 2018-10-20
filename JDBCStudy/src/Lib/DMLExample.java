package Lib;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

public class DMLExample {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";// IP:포트번호:xe버전
	String username = "hr";
	String password = "hr";
	
	//코드의 메서드화
	public void create(String departmentName, int managerId, int locationId) {
		String mid = "NULL";
		String lid = "NULL";
		// int형에 0을 넣으면 안되므로, String 변환 후 null 넣기 
		if(managerId !=0) {
			mid = managerId+"";
		}if(locationId != 0) {
			lid = locationId+""; 
		}
		
		
		
		String sql = "INSERT INTO departments \r\n" + 
				"            (department_id, \r\n" + 
				"             department_name, \r\n" + 
				"             manager_id, \r\n" + 
				"             location_id) \r\n" + 
				"VALUES      (departments_seq.NEXTVAL, \r\n" + 
				"             '"+departmentName+"', \r\n" + 
				"             "+mid+", \r\n" + 
				"             "+lid+")"; 
	
		
		Connection con = null;
		Statement stmt = null;

		try {
			Class.forName(driver).newInstance();
			con = DriverManager.getConnection(url, username, password);
			con.setAutoCommit(false);
			stmt = con.createStatement();
			int count = stmt.executeUpdate(sql);
			con.commit(); // 메서드 호출 
			System.out.println(count + "행이 추가되었습니다..");
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	

		try {
			con = DriverManager.getConnection(url, username, password);
			con.setAutoCommit(false); //autocommit(자동저장) 해제하기
			stmt = con.createStatement();
			int count = stmt.executeUpdate(sql);
			con.commit(); // 메서드 호출 
			System.out.println(count + "행이 추가되었습니다..");
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} // 저장취소 메소드 호출
		}finally {
				try {
					if(stmt != null) stmt.close();
					if(con != null)	 con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		
		}

	}
	
	
	public void create2(String departmentName, int managerId, int locationId) {
		create2(new Department(0, departmentName, managerId, locationId));
	}
	public void create2(Department department) {	
		String sql = "INSERT INTO departments \r\n" + 
				"            (department_id, \r\n" + 
				"             department_name, \r\n" + 
				"             manager_id, \r\n" + 
				"             location_id) \r\n" + 
				"VALUES      (departments_seq.NEXTVAL, \r\n" + 
				"             ?, \r\n" + 
				"             ?, \r\n" + 
				"             ?)";  
	
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver).newInstance();
			con = DriverManager.getConnection(url, username, password);
			con.setAutoCommit(false);
			// SQL 전처리
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, department.getDepartmentName());
			
			if(department.getManagerId() != 0) {
				pstmt.setInt(2,department.getManagerId());
			}else {
				pstmt.setNull(2, Types.INTEGER);
			}

			if(department.getLocationId() != 0) {
				pstmt.setInt(3, department.getLocationId());
			}else {
				pstmt.setNull(3, Types.INTEGER);
			}
			
			int count = pstmt.executeUpdate();
			con.commit(); 
			System.out.println(count + "행이 추가되었습니다..");
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
				try {
					if(pstmt != null) pstmt.close();
					if(con != null)	 con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		
		}

	}
	
//	String driver = "oracle.jdbc.driver.OracleDriver";
//	String url = "jdbc:oracle:thin:@localhost:1521:xe";// IP:포트번호:xe버전
//	String username = "hr";
//	String password = "hr";
	public void delete(int departmentId) throws SQLException {
		String dpi = "NULL";
		if(departmentId !=0) {
			dpi = departmentId+"";
		}
		
		String sql = "DELETE FROM departments WHERE department_id = ?";
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver).newInstance();
			con = DriverManager.getConnection(url, username, password);
			con.setAutoCommit(false);
			// SQL 전처리
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, departmentId);
			int count = pstmt.executeUpdate();
			con.commit(); 
			System.out.println(count + "행이  삭제되었습니다..");
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(con != null)	 con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	
	}
	
	
	
	
	
	
	
	public static void main(String[] args) throws SQLException {
		DMLExample exam = new DMLExample();
//		exam.create("코스타", 0, 0);
//		exam.create2("코스타2", 100, 1700);
//		exam.create2("코스타3", 0, 0);
		exam.delete(360);
	}
}
