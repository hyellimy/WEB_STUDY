package Pattern3;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbcp2.BasicDataSource;

public class UserDaoTest {
	private static final String driver = "oracle.jdbc.driver.OracleDriver";
	private static final String url = "jdbc:oracle:thin:@localhost:1521:xe";// IP:포트번호:xe버전
	private static final String username = "hr";
	private static final String password = "hr";
	
	public static void main(String[] args) {
		
		JdbcUserDao dao = new JdbcUserDao();

		BasicDataSource dataSource = new BasicDataSource(); //생성 
		dataSource.setDriverClassName(driver);
		dataSource.setUrl(url);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		dataSource.setInitialSize(5); 
		dataSource.setMaxTotal(10); 
		dataSource.setMaxIdle(7);
		dao.setDataSource(dataSource);

		//::::::::::::LISTALL::::::::::::
		try {
			List<User> list =	dao.listAll();
			for(User user : list) {
				System.out.println(user);
			}
		}catch (Exception e) {
		}
	}
}
		//create 
		
//		user.setId("bangry");
//		user.setName("이혜림");
//		user.setPasswd("1111");
//		user.setEmail("haeriming3355@gmail.com");
//		
//
//		
//		try {
//			dao.create(user);
//			System.out.println("회원 가입 완료");
//		} catch (Exception e) {
//			e.printStackTrace();
//			System.out.println(e.getMessage());
//			SQLException ex = (SQLException)e; //오류를 다운캐스팅 해준다. 
//			ex.getErrorCode();
//		}
		
		//::::::: READ :::::::::
//		User usr = null;
//		try {
//			usr = dao.read("bangry");
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		if(usr == null) {
//			System.out.println("존재하지 않는 아이디 ");
//		}else {
//			System.out.println("조회된 사용자 정보 입니다. ");
//			System.out.println(usr.toString());
//		}
		
		//:::::::: UPDATE :::::::::
		
//		user.setId("hyerim");
//		user.setName("혜림");
//		user.setPasswd("2222");
//		user.setEmail("nina3355@naver.com");
//		user.setRegdate("2018-09-28");
//		System.out.println("업데이트 완료");
//		
//		try {
//			dao.update(user);
//		} catch (Exception e) {
//			e.printStackTrace();
//			System.out.println(e.getMessage());
//		}
		
		//DELETE
		
		
//		try {
//			dao.delete("hyerim");
//			System.out.println("삭제 완료");
//		} catch (Exception e) {
//			e.printStackTrace();
//			System.out.println(e.getMessage());
//		}
		
		
		
		


	
			
			//리스트 맵 
//			List<Map<String, String>> list = null;
//			list = new ArrayList<Map<String, String>>();
//			
//			try {
//				List<User> list =	dao.listAll();
//				for(User user : list) {
//					System.out.println(user);
//				}
		
