package kr.or.kosta.servlet;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 * 쿠키 서블릿 
 * @author 이혜림
 *
 */


public class CookieServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

//		String id = "bangry"; //
		
		
		String id = "방그리";
		
		id = URLEncoder.encode(id, "utf-8");
		
		Cookie cookie = new Cookie("loginId", id);
		cookie.setMaxAge(60*60*24*30); // 단위는 초단위 
		//cookie.setDomain("http://www.naver.com");
//		cookie.setPath("/");
		
		//쿠키를 헤더에 밀어넣는 작업
//		response.setHeader("Set-Cookie",".......");
		//밀어넣고~
		response.addCookie(cookie);
		// 보내주고~
		response.sendRedirect("hello2");
		
	}
}
