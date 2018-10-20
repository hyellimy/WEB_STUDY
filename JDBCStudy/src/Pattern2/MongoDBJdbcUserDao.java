package Pattern2;
/**
 * 몽고DB JDBC CONNECTION
 */
import java.sql.Connection;
import java.sql.DriverManager;

public class MongoDBJdbcUserDao extends JdbcUserDao{

	@Override
	public Connection getConnection() throws Exception {
		//MongoDB 에 맞게 Connection 코드 생성
		return null;
	}

}
