package kr.or.kosta.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jdk.nashorn.internal.ir.RuntimeNode.Request;

/**
 * 출력 전에 , 요청 내용 
 * @author 이혜림
 *
 */
public class HttpServletResponseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		request.setCharacterEncoding("utf-8");
//		response.setStatus(HttpServletResponse.SC_BAD_REQUEST); // 에러 상태 바꾸기 
		
		String name = request.getParameter("name");
		// 입력값이 존재할 경우, 블랙리스트 설정해주기 
		if(name != null && name.length() != 0 ) {
			if(name.equals("bangry")) {
				response.sendError(HttpServletResponse.SC_FORBIDDEN);	// 방그리 접속시, 에러 메세지 접근 [404 허용되지 않은 접속 에러 메시지]
				return;
			}
		}
		
		
		
		PrintWriter out = response.getWriter();
		
		/* 
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Servlet Programming</title>");
		out.println("<meta charset=\"utf-8\">");
		out.println("</head>");
		out.println("<body style='font-size:20pt;'>");
		out.println("<ul>");

		
		out.println("<li>????</li>");

		out.println("</ul>");
		out.println("<h2></h2>");
		out.println("</body>");
		out.println("</html>"); */
		
		//Dispatch 기술 
		System.out.println(HttpServletResponse.SC_MOVED_PERMANENTLY);
		//response.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
		//response.setHeader("Location", "/servlet/hello.do");
		response.sendRedirect("/servlet/hello.do");	
	}

}
