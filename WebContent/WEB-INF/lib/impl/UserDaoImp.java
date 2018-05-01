package cn.java.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import cn.java.entity.mets;

public interface UserDaoImp {

	/**
	 * 查询数据库中是否存在该用户.
	 * 
	 * @see cn.java.dao.impl.UserDao#isLogin(java.lang.String, java.lang.String)
	 */
	Map<String, Object> isLogin(String username, String password) throws SQLException;
	/**
	 * 
	 * @return 所有菜品集合
	 * @throws SQLException
	 */
	List<mets> getAllMets() throws SQLException;
	
	List<mets> queryMets(String metsName) throws Exception;
	int addMets(mets cp) throws SQLException;
	

}