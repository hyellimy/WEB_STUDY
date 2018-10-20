package pattern4;

import java.lang.reflect.Method;

import javax.sql.DataSource;

public class JdbcDaoFactory extends DaoFactory {

	@Override
	public UserDao getUserDao() {
		UserDao dao = new JdbcUserDao();
		Class cls = dao.getClass();
		
		// 동적 메소드호출
		Method method;
		try {
			method = cls.getMethod("setDataSource", DataSource.class);
			method.invoke(dao, createDataSource());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dao;
	}
	
//	public BarDao getBarDao() {...};
//	public FooDao getFooDao() {...};

}
