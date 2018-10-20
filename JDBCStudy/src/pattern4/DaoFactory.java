/**
 * 
 */
package pattern4;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;

/**
 * 추상 팩토리 패턴 적용을 위한 DaoFactory
 * Database를 연동하는 방법이 기술별(jdbc, mybatis, hibernate, jpa 등)로 상이하기 때문에
 * 기술별로 다양한 Dao 생성을 위한 수직적 규약 선언
 * #1. Dao 생성
 * #2. DataSource 생성
 * #3. Dao에 DataSource 설정
 * #4. DataSource가 설정된 Dao 반환
 * 
 * @author 김기정
 */
public abstract class DaoFactory {
	
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521";
	private static final String USERNAME = "hr";
	private static final String PASSWORD = "hr";
	private static final int INIT_SIZE = 2;
	private static final int MAX_TOTAL = 10;
	private static final int MAX_IDLE = 5;
	
	public DataSource createDataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(DRIVER);
		dataSource.setUrl(URL);
		dataSource.setUsername(USERNAME);
		dataSource.setPassword(PASSWORD);
		dataSource.setInitialSize(INIT_SIZE);
		dataSource.setMaxTotal(MAX_TOTAL);
		dataSource.setMaxIdle(MAX_IDLE);
		return dataSource;
	}
	
	public abstract UserDao getUserDao();
//	public BarDao getBarDao();
//	public FooDao getFooDao();
	
	
}
