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


public class ReceiveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		process(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		process(request, response);
	}
	
	public void process(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html; charset=utf-8");
		request.setCharacterEncoding("utf-8"); // 한글 인코딩 처리를 가능하게 하는 처리 :-) 
		PrintWriter out = response.getWriter();

		//요청 파라메터 수신 
		String userId = request.getParameter("userid");
		String userPw = request.getParameter("passwd");
		String team = request.getParameter("teams");
		String message = request.getParameter("message");
		String gender = request.getParameter("gender");
		String[] hobbies = request.getParameterValues("hobby");
		
		Enumeration<String> paramNames = request.getParameterNames();
		while (paramNames.hasMoreElements()) {
			String name =  paramNames.nextElement();
			String value = request.getParameter(name);
			System.out.println(name + "=" + value);
		}
		//산 워킹 독서 : 세가지를 모두 받기 위한 속성
		
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Servlet Programming</title>");
		out.println("<meta charset=\"utf-8\">");
		out.println("</head>");
		out.println("<body style='font-size:20pt;'>");

		out.println("<h3>이름 : "+userId+"</h3>");
		out.println("<h3>비밀번호 : "+userPw+"</h3>");
		out.println("<h3>선택한 팀을 보여주세요!  : "+team+"</h3>");
		out.println("<h3>전송된 메시지를 보여주세요! : "+message+"</h3>");
		out.println("<h3>성별  : "+gender+"</h3>");
		// 가끔 받는 것 
		if(hobbies != null) {
			for (String hobby : hobbies) {
				out.println("<h3>취미 : "+hobby+"</h3>");
				
			}
		}
		out.println("</body>");
		out.println("</html>");
	}

}
