package kr.or.kosta.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jdk.nashorn.internal.ir.RuntimeNode.Request;

/**
 * 데이터 베이스 연결
 * @author 이혜림
 *
 */
public class DatabaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";// IP:포트번호:xe버전
	String username = "hr";
	String password = "hr";
	
	
	
	
	String sql = "select employee_id, last_name, salary\r\n" + "from employees";
	Connection con;
		
	@Override
	public void init() throws ServletException {
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, username, password);
			System.out.println(con);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	/** 
	 * 웹 어플리케이션은 WEB-INF 안에 web.xml이 있고 lib폴더가 존재해 있다. 
	 * 폴더 내에 파일을 넣어주면 자동으로  DB관리 가능하다. 
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		try {
			psmt = con.prepareStatement(sql);
			rs = psmt.executeQuery();
			
			/*
			while (rs.next()) {// 가져올 내용이 있으면,
				String employeeId = rs.getString("id");
				String lastName = rs.getString("ename");
				int salary = rs.getInt("salary");
				String hiredate = rs.getString("hiredate");
				String departmentName = rs.getString("dname");
				System.out.println(employeeId + "," + lastName + ", " + salary + ", " + hiredate + ", " + departmentName);
			} */
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		response.setContentType("text/html; charset=utf-8");
		request.setCharacterEncoding("utf-8");
		
		PrintWriter out = response.getWriter();
		
		
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Servlet Programming</title>");
		out.println("<meta charset=\"utf-8\">");
		out.println("</head>");
		out.println("<body style='font-size:20pt;'>");
		out.println("<table border='1' width='50%'>");
		try {
			while(rs.next()) {
				int id = rs.getInt("employee_id");
				String lastName = rs.getString("last_name");
				int salary = rs.getInt("salary");
				out.println("<tr>");
				out.println("<td>"+id+"</td><td>"+lastName+"</td><td>"+salary+"</td>");
				out.println("</tr>");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	
		out.println("</table>");

		out.println("</ul>");
		out.println("<h2></h2>");
		out.println("</body>");
		out.println("</html>"); 
	}
	@Override
	public void destroy() {
		if(con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
