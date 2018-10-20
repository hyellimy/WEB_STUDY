package Pattern;

import java.awt.Toolkit;
import java.io.IOException;

public class SingletoneExample {
	
public static void main(String[] args) throws IOException {
//	Logger logger = new Logger();
	Logger logger = Logger.getInstance();
	logger.log("테스트 입니다. ");
	
	Toolkit toolkit = Toolkit.getDefaultToolkit();
	Runtime runtime = Runtime.getRuntime();
	runtime.exec("C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe http://www.naver.com");  //크롬 실행을 위한 절대주소 작성 필요하다. 
}
}
