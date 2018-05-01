package cn.java.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;

import cn.java.entity.mets;
import cn.java.utils.jdbcUtil;

public class userDaoImpl implements UserDaoImp {
	private QueryRunner qr = new QueryRunner();

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.java.dao.impl.UserDaoImp#isLogin(java.lang.String, java.lang.String)
	 */
	String sql = "select count(*) um from user";

	@Override
	public Map<String, Object> isLogin(String username, String password) throws SQLException {
		return qr.query(jdbcUtil.getConn(),
				"SELECT COUNT(*) um FROM users WHERE name='" + username + "' and password='" + password + "';",
				new MapHandler());
	}
	@Override
	public List<mets> getAllMets() throws SQLException{
		return qr.query(jdbcUtil.getConn(), "select * from mets", new BeanListHandler<mets>(mets.class));
	}
	@Override
	public List<mets> queryMets(String metsName) throws Exception {
		return qr.query(jdbcUtil.getConn(),"select * from mets where metsName='"+metsName+"';", new BeanListHandler<mets>(mets.class));
	}
	 /**
     * 
     * Description: 添加菜品信息<br/>
     *
     * @author dingP
     * @param cp
     * @return
     * @throws SQLException
     */
    @Override
    public int addMets(mets cp) throws SQLException {
        return qr.update(jdbcUtil.getConn(),
                "INSERT INTO mets SET metsName='" + cp.getMetsName() + "',metsTaste='" + cp.getMetsTaste() + "',metsPic='"
                        + cp.getMetsPic() + "',metsPrice=" + Float.parseFloat(cp.getMetsPrice()) + ",metsDiscription='" + cp.getMetsDiscription() + "'");
    }
}

