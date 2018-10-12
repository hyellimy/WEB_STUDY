package kr.or.kosta.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HelloServlet2
 */
public class HelloServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int count = 0; // 갯수를 샐 수 있는 변수 선언 

		Cookie[] cookies = request.getCookies(); // 브라우저에서 전달된 모든 쿠키 전달
		if (cookies != null) { // 브라우저에서 전달된 쿠키정보가 있다면
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("count")) { //쿠키의 getName = count일 경우 
					count = Integer.parseInt(cookie.getValue()); //value 값의 숫자 가져오기
					break;
				}
			}
		}
		count++;

		Cookie cookie = new Cookie("count", String.valueOf(count));
		cookie.setMaxAge(60 * 60 * 24 * 30);
		response.addCookie(cookie);

		response.setContentType("text/html; charset=utf-8");
		request.setCharacterEncoding("utf-8");

		PrintWriter out = response.getWriter();

		out.println("<html>");
		out.println("<head>");
		out.println("<title>Servlet Programming</title>");
		out.println("<meta charset=\"utf-8\">");
		out.println("</head>");
		out.println("<body style='font-size:20pt;'>");
		out.println("<h2>당신은 " + cookie.getValue() + "번째 방문하셨습니다. </h2>");
		out.println("</body>");
		out.println("</html>");

	}
}
