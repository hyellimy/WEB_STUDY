package kr.or.kosta.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 최초 작성 서블릿
 * @author 이혜림
 *
 */


public class HelloServlet extends HttpServlet /* implements Servlet */{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//callback 메소드 : doGet의 가로 내용 (요청(request), 응답(response)) 
	@Override
	protected void doGet(HttpServletRequest requset, HttpServletResponse response) throws ServletException, IOException {
		// 웹 클라이언트로 동적 출력하고자 하는 데이터 생성
		Calendar now = Calendar.getInstance();
		String nowString = String.format("%1$tF %1$tT", now);
	
		// 응답 메시지의 헤더에 컨텐츠 유형 설정
		response.setContentType("text/html; charset=utf-8");
		
		
		PrintWriter out = response.getWriter();
		
//		String html = "<html>";
//			   html += "<head>";
			   
		
		
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Servlet Programming</title>");
		out.println("<meta charset=\"utf-8\">");
		out.println("</head>");
		out.println("<body>");
		out.println("<h2> 오늘은 "+nowString+" 입니다..</h2>");
		out.println("</body>");
		out.println("</html>");
		
	}
	
}
