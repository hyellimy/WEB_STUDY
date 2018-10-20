package Pattern2;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserDaoTest {
	public static void main(String[] args) {
		//UserDao 인스턴스 생성
		UserDao dao = new OracleJdbcUserDao();
		//User 인스턴스 생성 
		User user = new User();
		
		
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
		
		
		
		
		//::::::::::::LISTALL::::::::::::
//		UserDao dao = new OracleJdbcUserDao();
//		try {
//			List<User> list =	dao.listAll();
//			for(User user : list) {
//				System.out.println(user);
//			}
			
	
			
			//리스트 맵 
//			List<Map<String, String>> list = null;
//			list = new ArrayList<Map<String, String>>();
//			
//			try {
//				List<User> list =	dao.listAll();
//				for(User user : list) {
//					System.out.println(user);
//				}
		

		}
	}
