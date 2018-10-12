```java
package kr.or.kosta.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.dbcp2.BasicDataSource;

import kr.or.kosta.dao.JDBCUserDao;
import kr.or.kosta.dao.User;

/**
 * Servlet implementation class HelloServlet2
 */
public class DatabaseServlet2 extends HttpServlet {
   private static final long serialVersionUID = 1L;

   JDBCUserDao dao;
   private static final String driver = "oracle.jdbc.OracleDriver";
   private static final String url = "jdbc:oracle:thin:@localhost:1521:xe";
   private static final String username = "hr";
   private static final String password = "hr";

   @Override
   public void init() throws ServletException {
      dao = new JDBCUserDao();
      BasicDataSource dataSource = new BasicDataSource();

      dataSource.setDriverClassName(driver);
      dataSource.setUrl(url);
      dataSource.setUsername(username);
      dataSource.setPassword(password);
      dataSource.setInitialSize(5);
      dataSource.setMaxTotal(10); // 최대 만들어지는 갯수
      dataSource.setMaxIdle(7);
      dao.setDataSource(dataSource);

   }

   protected void doGet(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {

      List<User> list;
      try {
         list = dao.listAll();

         response.setContentType("text/html; charset=utf-8");
         PrintWriter out = response.getWriter();

         out.println("<html>");
         out.println("<head>");
         out.println("<title>Servlet Programming</title>");
         out.println("<meta charset=\"utf-8\">");
         out.println("</head>");
         out.println("<body style='font-size: 20pt;'>");

         out.println("<table border='1' width='50%'>");
         for (User user : list) {
            System.out.println(user);
         }
         out.println("</table>");

         out.println("</body>");
         out.println("</html>");

      } catch (Exception e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }

}
```

# 데이터DB연결 2





# 데이터 DB연결1



```java
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

/**
 * Servlet implementation class HelloServlet2
 */
public class DatabaseServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;

   String driver = "oracle.jdbc.OracleDriver"; // 여기에 있는 이름만 바꿔주면 됨
   String url = "jdbc:oracle:thin:@localhost:1521:xe";
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

   protected void doGet(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {

      PreparedStatement pstmt = null;
      ResultSet rs = null;
      try {
         pstmt = con.prepareStatement(sql);
         rs = pstmt.executeQuery();
         /*
          * while (rs.next()) { // rs.getString(1); String employeeId =
          * rs.getString("employee_id"); // 컬럼 이름으로 적어주는 게 찾아오는 데 있어서 조금 더 명확 String
          * lastName = rs.getString("last_name"); int salary = rs.getInt("salary");
          * System.out.println(employeeId + ", " + lastName + ", " + salary); }
          */
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      response.setContentType("text/html; charset=utf-8");
      PrintWriter out = response.getWriter();
      out.println("<html>");
      out.println("<head>");
      out.println("<title>Servlet Programming</title>");
      out.println("<meta charset=\"utf-8\">");
      out.println("</head>");
      out.println("<body style='font-size: 20pt;'>");

      out.println("<table border='1' width='50%'>");
      try {

         while (rs.next()) {

            int id = rs.getInt("employee_id");
            String lastName = rs.getString("last_name");
            int salary = rs.getInt("salary");
            out.println("<tr>");
            out.println("<td>" + id + "</td><td>" + lastName + "</td><td>" + salary + "</td>");
            out.println("</tr>");

         }
      } catch (Exception e) {
         e.printStackTrace();
      }
      out.println("</table>");
      out.println("</body>");
      out.println("</html>");

   }
   
   @Override
   public void destroy() {
      if(con != null) {
         try {
            con.close();
         } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
         }
      }
   }

}
```





