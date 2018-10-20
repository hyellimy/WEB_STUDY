package Pattern;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class JdbcUserDao implements UserDao{
	
	private static final String driver = "oracle.jdbc.driver.OracleDriver";
	private static final String url = "jdbc:oracle:thin:@localhost:1521:xe";// IP:포트번호:xe버전
	private static final String username = "hr";
	private static final String password = "hr";
	
	@Override
	public void create(User user) throws Exception { //회원가입
		Connection con = null;
		PreparedStatement pstmt = null;
		
		String sql = "INSERT INTO users \r\n" + 
				"VALUES     (?, \r\n" +  //id
				"            ?, \r\n" +  //이름
				"            ?, \r\n" +  //비밀번호
				"            ?, \r\n" +  //이메일
				"            SYSDATE) ";
		
		try {	
		con = getConnection(); 	//db연결
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, user.getId());
		pstmt.setString(2, user.getName());
		pstmt.setString(3, user.getPasswd());
		pstmt.setString(4, user.getEmail());
		pstmt.executeUpdate();
	
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(con != null)	 con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public User read(String id) throws Exception {
		User user = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql ="SELECT id, \r\n" + 
				"       name, \r\n" + 
				"       passwd, \r\n" + 
				"       email, \r\n" + 
				"       TO_CHAR(regdate,'YYYY-MM-DD') regdate \r\n" + 
				"FROM   users \r\n" + 
				"WHERE  id = ? ";
		
		con = getConnection(); // 연결
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1,id);
		rs = pstmt.executeQuery(); //쿼리를 보내고 -> 값을 가져오는 전체과정
		
		if(rs.next()) {
			user= new User();
			user.setId(rs.getString("id"));
			user.setName(rs.getString("name"));
			user.setPasswd(rs.getString("passwd"));
			user.setEmail(rs.getString("email"));
			user.setRegdate(rs.getString("regdate"));
		}
		return user;
		
	}

	@Override
	public void update(User user) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		String sql = "UPDATE users \r\n" + 
				"SET    id = ?, \r\n" + 
				"       name = ?, \r\n" + 
				"       passwd = ?, \r\n" + 
				"       email = ?, \r\n" + 
				"       regdate = ?";
				
		try {	
		con = getConnection();
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, user.getId());
		pstmt.setString(2, user.getName());
		pstmt.setString(3, user.getPasswd());
		pstmt.setString(4, user.getEmail());
		pstmt.setString(5, user.getRegdate());
		
		pstmt.executeUpdate();
		
		System.out.println("User with id " + user.getId() + " was updated in DB with following details: " + user.toString());
		}finally{
		if(pstmt != null) pstmt.close();
		if(con != null)	 con.close();
		}
	}

	@Override
	public void delete(String id) throws Exception {

		Connection con = null;
		PreparedStatement pstmt = null;

		String sql = "DELETE FROM users WHERE id=?";

		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.executeUpdate();
		} finally {
			if (pstmt != null)
				pstmt.close();
			if (con != null)
				con.close();
		}
	}
	//전체 리스트 listAll()
	@Override
	public List<User> listAll() throws Exception {
		List<User> list = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT * FROM users ORDER BY id";
			List<User> users = new LinkedList<User>();
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			
			Statement statement = con.createStatement();
			ResultSet resultset = statement.executeQuery(sql);
			
			User user = null;
			while(resultset.next()) {
				user = new User();
				user.setId(resultset.getString("id"));
				user.setName(resultset.getString("name"));
				user.setPasswd(resultset.getString("passwd"));
				user.setEmail(resultset.getString("email"));
				user.setRegdate(resultset.getString("regdate"));
				users.add(user);
			}
		return users;
	}

	
	@Override
	public User certify(String id, String passwd) throws Exception {
	
		User user = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT id, \r\n" + 
				"       name, \r\n" + 
				"       passwd, \r\n" + 
				"       email, \r\n" + 
				"       TO_CHAR(regdate,'YYYY-MM-DD') regdate \r\n" + 
				"FROM   users \r\n" + 
				"WHERE  id = ? AND passwd = ?";
				
				con = getConnection(); // 연결
				pstmt = con.prepareStatement(sql);
				
				pstmt.setString(1,id);
				pstmt.setString(2,passwd);
				
				rs = pstmt.executeQuery(); 
				if(rs.next()) {
					user= new User();
					user.setId(rs.getString("id"));
					user.setPasswd(rs.getString("passwd"));
	}			
				return user;
	}
	
	
	@Override
	public  List<Map<String, String>> employeeList() throws Exception{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs= null;
		List<Map<String, String>> list = null;
		
		String sql ="SELECT e.employee_id     eid, \r\n" + 
				"       e.last_name       ename, \r\n" + 
				"       e.salary          salary, \r\n" + 
				"       d.department_name dname, \r\n" + 
				"       l.city            city, \r\n" + 
				"       e2.last_name      mname \r\n" + 
				"FROM   employees e \r\n" + 
				"       left outer join departments d \r\n" + 
				"                    ON e.department_id = d.department_id \r\n" + 
				"       left outer join locations l \r\n" + 
				"                    ON d.location_id = l.location_id \r\n" + 
				"       left outer join employees e2 \r\n" + 
				"                    ON e.manager_id = e2.employee_id \r\n" + 
				"ORDER  BY eid ASC ";

		con = getConnection();
		pstmt = con.prepareStatement(sql);
		rs = pstmt.executeQuery(); 
		
		list = new ArrayList<Map<String, String>>();
		
		ResultSetMetaData rsd = rs.getMetaData();
		int columCount = rsd.getColumnCount();
		
		
		while(rs.next()) {
		
			Map<String, String> row = new HashMap<String, String>();
			
			for (int i = 1; i <= columCount; i++) {
//				System.out.println("몇개 나올까요?" + columCount);
				String columName = rsd.getColumnLabel(i);
				String columValue = rs.getString(i);
				row.put(columName, columValue);
				list.add(row);
				
			}
		}
		System.out.println("뭘까");
		return list;
	}

	
	@Override
	public Connection getConnection() throws Exception {
		Class.forName(driver).newInstance();
		return DriverManager.getConnection(url, username, password);
	}


	
	

}
