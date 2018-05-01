package cn.java.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;


public interface UserDaoImp {

	/**
	 * 鏌ヨ鏁版嵁搴撲腑鏄惁瀛樺湪璇ョ敤鎴�.
	 * 
	 * @see cn.java.dao.impl.UserDao#isLogin(java.lang.String, java.lang.String)
	 */
	Map<String, Object> isLogin(String username, String password) throws SQLException;
	
	
}