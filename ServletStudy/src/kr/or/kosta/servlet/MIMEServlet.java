package kr.or.kosta.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 마임 타입 이해를 위한 서블릿
 */
public class MIMEServlet extends HttpServlet {
	// 알아두어야 할 것 : init / Service 메서드가 상속되어 있다. 
	
	/*
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.service(req, resp);
	}
	*/
	
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/plain; charset=utf-8");
		// "text/html의 뜻" => 주고받는 데이터 구조 : mytype/ subtype
		// Content-Type : text/plain; charset=utf-8 [필수사항이다!!!]
		PrintWriter out = response.getWriter();
		out.println("일반적인 텍스트 입니다...");

	}
}
