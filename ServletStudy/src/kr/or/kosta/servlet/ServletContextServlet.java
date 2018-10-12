package kr.or.kosta.servlet;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class ServletContextServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
		String message = "서블릿간의 데이터 공유 입니다.";
		ServletContext context = getServletContext();
		
		
		System.out.println(context.getServerInfo());
		System.out.println(context.getContextPath());
		
		context.setAttribute("message", message);
		response.sendRedirect("/servlet/hello.do"); //브라우저 url을 기준으로 보는 것이다. : 상대경로로 인식한다. 
		
		String location = context.getInitParameter("Location"); //WEB에 작성해둔 InitParameter가 인출된다. 
		System.out.println(location);
	}
}
