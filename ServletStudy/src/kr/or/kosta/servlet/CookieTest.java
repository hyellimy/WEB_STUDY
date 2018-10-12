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
public class CookieTest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("text/html; charset=utf-8");

        Cookie[] cookies = request.getCookies();

        if ( cookies != null ) {
            for ( Cookie cookie : cookies ) {
                String name = cookie.getName();
                String value = cookie.getValue();
                out.println("Cookie name : "+name+"<br>");
                out.println("Cookie value : "+value+"<hr>");

                if ( cookie.getName().equals("count") ) {
                    //value에 1을 더하고 새로운 value를 세팅
                    value = Integer.toString(Integer.parseInt(cookie.getValue()) + 1);
                    //새값이 설정된 쿠키를 브라우저로 전송
                    Cookie countCookie = new Cookie("count",value);
                    response.addCookie(countCookie);

                    //cookie.setValue(value);
                    //res.addCookie(cookie);  이녀석도 된다. ㅡㅅ-)>
                }
            }
            out.println("<a href=CookieTest>다시방문하기</a>");
        } else {
            out.println("No cookies..");
        }
    }

	/*
    public void setCookies(HttpServletResponse response){
        Cookie logCookie = new Cookie("login", "honeymon");
        logCookie.setMaxAge(-1);

        Cookie passCookie = new Cookie("pass", "wow");
        passCookie.setMaxAge(-1);

        Cookie countCookie = new Cookie("count","0");
        countCookie.setMaxAge(-1);

        response.addCookie(logCookie);
        response.addCookie(passCookie);
        response.addCookie(countCookie);

        out.println("<hr>cookie가 setting 되었습니다.<hr><br>");
        out.println("<a href=CookieTest>돌아가기</a>");
    }
*/

}

