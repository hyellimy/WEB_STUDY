package kr.or.kosta.servlets;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Apache 파일 업로드 API를 이용한 파일 업로드 처리 서블릿
 */
public class FileUploadServlet extends HttpServlet {
	
	private String fileRepository = "i:/..../";

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		/*
		String writer = request.getParameter("writer");
		System.out.println("작성자: " + writer);
		String file = request.getParameter("upfile");
		System.out.println(file);
		
		// 서블릿 API를 이용한 업로드 파일 데이터 직접 수신
		InputStream in = request.getInputStream();
		byte[] buffer = new byte[1024];
		int count = 0;
		while((count=in.read(buffer)) != -1){
			String data = new String(buffer, 0, count);
			System.out.println(data);
		}
		in.close();
		*/
		
		// 아파치 파일 업로드 API를 이용한 파일 수신 및 서버 디렉토리에 저장
		DiskFileItemFactory itemFactory = new DiskFileItemFactory();
		ServletFileUpload fileUpload = new ServletFileUpload(itemFactory);
		fileUpload.setSizeMax(5 * 1024 * 1024); // 업로드 파일 용량 제한
		
		List<FileItem> fileList = null;
		
		try {
			fileList = fileUpload.parseRequest(request);
			for (FileItem item : fileList) {
				if (item.isFormField()) {
					String writer = item.getString("utf-8");
					System.out.println("작성자: " + writer);
				}else {// 업로드 파일인 경우
					String fileName = item.getName();
					System.out.println("업로드 파일명: " + fileName);
					// fileName = c:\xxx\yyy\업로드파일명
					String[] tokens = fileName.split("\\\\");
					fileName = tokens[tokens.length-1];//파일명만 추출
					long fileSize = item.getSize();
					System.out.println("파일사이즈: " + fileSize);
	
					// 업로드된 파일을 서버의 특정 디렉토리에 저장
					File saveFile = new File(fileRepository + fileName);
					item.write(saveFile);
				}
			}
			// 업로드 결과 Response
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<html>");
			out.println("<body>");
			out.println("<h2>파일 업로드 완료!</h2>");
			out.println("</body>");
			out.println("</html>");
			
			//response.sendRedirect("/파일목록처리 서블릿");
		} catch (Exception e) {
			new ServletException(e.getMessage());
		}
	}
}
