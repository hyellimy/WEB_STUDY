package Pattern2;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;

/**
 * 아파치그룹에서 제공하는 DataSource 구현클래스 라이브러리(DBCP) 사용
 * 커넥션풀링 지원  
 * @author 김기정
 *
 */
public class ApacheDataSourceExample {
	
	private static final String DRIVER_NAME = "oracle.jdbc.driver.OracleDriver";
	private static final String DB_URL = "jdbc:oracle:thin:@localhost:1521";
	private static final String USER_ID = "hr";
	private static final String USER_PW = "hr";
	private static final int MAX_COUNT = 5;
	private static final int IDLE_COUNT = 2;
	
	public static void main(String[] args) throws SQLException {
		// Connection Pooling 지원 DataSource
//		DataSource ds = new BasicDataSource();
		BasicDataSource dataSource = new BasicDataSource(); //생성 
		dataSource.setDriverClassName(DRIVER_NAME);
		dataSource.setUrl(DB_URL);
		dataSource.setUsername(USER_ID);
		dataSource.setPassword(USER_PW);
		dataSource.setInitialSize(5); //초기 몇개 만들었는지
		dataSource.setMaxTotal(10); // 최대값 (최대 만들어진 갯수)
		
		// DataSource로부터 Connection 취득
		Connection con = dataSource.getConnection(); //추상 메소드 
		System.out.println("취득한 커넥션 : " + con);
		
		// Connection 사용
		
		// 사용한 Connection DataSource에 반환
		con.close(); // 닫는 것이 아니라, overiding 을 통해 반환하는 기능으로 구현
		// 반납하는 것 
	}

}
