package Pattern2;

import java.sql.Connection;
import java.sql.DriverManager;

import org.apache.commons.dbcp2.BasicDataSource;

public class OracleJdbcUserDao extends JdbcUserDao{
	
	private static final String driver = "oracle.jdbc.driver.OracleDriver";
	private static final String url = "jdbc:oracle:thin:@localhost:1521:xe";// IP:포트번호:xe버전
	private static final String username = "hr";
	private static final String password = "hr";
	
	@Override
	public Connection getConnection() throws Exception {
		//커넥션 풀 적용하여 Connection 생성
//		Class.forName(driver).newInstance();
//		return DriverManager.getConnection(url, username, password);
//		return UserConnectionPool.getInstance().getConnection(); //코드를 한줄로 가능
	
		BasicDataSource dataSource = new BasicDataSource(); //생성 
		dataSource.setDriverClassName(driver);
		dataSource.setUrl(url);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		dataSource.setInitialSize(5); //초기 몇개 만들었는지
		dataSource.setMaxTotal(10); // 최대값 (최대 만들어진 갯수)
		dataSource.setMaxIdle(7);
		return dataSource.getConnection();
	}
}
