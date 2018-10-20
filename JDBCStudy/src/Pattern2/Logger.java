package Pattern2;

import java.util.Calendar;
/**
 * 싱글톤 패턴 적용 클래스 : 여러 객체가 한 객체를 참조할때 사용한다. 
 * @author 이혜림
 *
 */
public class Logger {
	private static Logger logger = new Logger();
	//생성자
	private Logger() {
		
	}
	
	public static Logger getInstance() {
		return logger;
	}
	
	public void log(String message) {
		Calendar today = Calendar.getInstance();
		String time = String.format("%1$tF %1$tT", today);
		System.out.println("[" +time + "]" + message);
	}
	
}
