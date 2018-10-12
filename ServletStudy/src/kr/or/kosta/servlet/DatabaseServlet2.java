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
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.dbcp2.BasicDataSource;

import jdk.nashorn.internal.ir.RuntimeNode.Request;
import kr.or.kosta.servlet.dao.JdbcUserDao;
import kr.or.kosta.servlet.dao.User;

/**
 * 데이터 베이스 연결
 * @author 이혜림
 *
 */
public class DatabaseServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	JdbcUserDao dao;
	private static final String driver = "oracle.jdbc.driver.OracleDriver";
	private static final String url = "jdbc:oracle:thin:@localhost:1521:xe";// IP:포트번호:xe버전
	private static final String username = "hr";
	private static final String password = "hr";
	
	Connection con;
	
	@Override
	public void init() throws ServletException {
		JdbcUserDao dao = new JdbcUserDao();
		BasicDataSource dataSource = new BasicDataSource(); //생성 
		
		dataSource.setDriverClassName(driver);
		dataSource.setUrl(url);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		dataSource.setInitialSize(5); 
		dataSource.setMaxTotal(10); 
		dataSource.setMaxIdle(7);
		dao.setDataSource(dataSource);
	}
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		List<User> list = null;


		try {
			list = dao.listAll();
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
			for (User user : list) {
				System.out.println(user);
			}
			out.println("</table>");
			out.println("</ul>");
			out.println("<h2></h2>");
			out.println("</body>");
			out.println("</html>"); 
		} catch (Exception e) {
			e.printStackTrace();
		}

		
		
	
	}
	
}
