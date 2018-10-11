package kr.or.kosta.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet 생명주기 테스트를 위한 서블릿 
 */
public class LifecycleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private int count;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LifecycleServlet() {
    	System.out.println("LifecycleServlet() called.... ");
        // TODO Auto-generated constructor stub
    }

	/**
	 * [init 1] : 기존에 제공 된 init
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
    	System.out.println("init(ServletConfig config) called.... ");
    	super.init(config);
    	count = 0;
	}

	/**
	 * [init2] : 메서드 분리 
	 */
	@Override
	public void init() throws ServletException {
		System.out.println("init() called.... ");
	}
	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		System.out.println("destroy() called.... ");
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		count ++;
		System.out.println("service(HttpServletRequest request, HttpServletResponse response) called.... ");
		super.service(request, response);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet(HttpServletRequest request, HttpServletResponse response) called.... ");
		System.out.println("request 내용 : " + request);
		System.out.println("response 내용 : " + response);
		response.setContentType("text/html; charset=utf-8");

		response.getWriter().append("Served at: ").append(request.getContextPath());
		PrintWriter out = response.getWriter();
		
		out.println("<html>");
		out.println("<head>");
		out.println("<title>서블릿 카운터</title>");
		out.println("<meta charset=\"utf-8\">");
		out.println("</head>");
		out.println("<body>");
		out.println("<h2> 당신은 "+count+"번째 방문자 입니다.</h2>");
		out.println("</body>");
		out.println("</html>");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		
		System.out.println("doPost(HttpServletRequest request, HttpServletResponse response) called.... ");
	}

}
