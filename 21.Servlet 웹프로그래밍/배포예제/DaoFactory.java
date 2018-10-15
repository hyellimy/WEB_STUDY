import java.lang.reflect.Method;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;

import kr.or.kosta.ems.dao.JdbcUserDao;
import kr.or.kosta.ems.dao.UserDao;
import kr.or.kosta.ems.exception.EMSException;

/**
 * DaoFactory
 * #1. Dao 인스턴스 생성
 * #2. DataSource 인스턴스 생성
 * #3. Dao에 DataSource 설정
 * #4. DataSource가 설정된 Dao 반환
 * #5. DaoFactory에 싱글톤 패턴 적용
 * 
 * @author 김기정
 */
public class DaoFactory {
	
	private static DaoFactory instance = new DaoFactory();
	
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521";
	private static final String USER = "hr";
	private static final String PASSWORD = "hr";
	private static final int INIT_SIZE = 5;
	private static final int MAX_SIZE = 10;
	
	private BasicDataSource dataSource;
	
	private DaoFactory() {
		dataSource = new BasicDataSource();
		dataSource.setDriverClassName(DRIVER);
		dataSource.setUrl(URL);
		dataSource.setUsername(USER);
		dataSource.setPassword(PASSWORD);
		dataSource.setInitialSize(INIT_SIZE);
		dataSource.setMaxTotal(MAX_SIZE);
	}
	
	public BasicDataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(BasicDataSource dataSource) {
		this.dataSource = dataSource;
	}

	public static DaoFactory getInstance() {
		return instance;
	}
	
	
	/*
	public UserDao getUserDao() {
		UserDao dao = new JdbcUserDao(dataSource);
		return dao;
	}
	
	public ArticleDao getArticleDao() {
		ArticleDao dao = new JdbcArticleDao(dataSource);
		return dao;
	}
	.
	.
	.
	*/
	
	/**
	 * 단일화된 Dao객체 요청 창구
	 */
	public Object getDao(String className) {
		Object dao = null;
		try {
			Class cls = Class.forName(className);
			dao = cls.newInstance();
			
			//JdbcXXXDao dao = (JdbcXXXDao)dao;
			//dao.setDataSource(dataSource);
			
			// 동적 메소드호출
			Method method =  cls.getMethod("setDataSource", DataSource.class);
			method.invoke(dao, dataSource);
			
		} catch (Exception e) {
			throw new EMSException("DaoFactory.getDao(String className) 실행 중 예외발생", e);
		}
		return dao;
	}
	
	public Object getDao(Class cls) {
		return getDao(cls.getName());
	}
}
