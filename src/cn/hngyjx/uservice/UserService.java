package cn.hngyjx.uservice;

import java.sql.SQLException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import cn.java.dao.impl.userDaoImpl;

public class UserService {

	userDaoImpl ud = new userDaoImpl();
	/* (non-Javadoc)
	 * @see cn.java.service.impl.UserService#isLogin(java.lang.String, java.lang.String, javax.servlet.http.HttpServletRequest)
	 */
	public boolean isLogin(String username, String password, HttpServletRequest request) throws SQLException {
		String pattern = "[a-zA-Z]{2,12}";
		Boolean flag = username.matches(pattern);
		request.setAttribute("errorMsg", "用户名或密码不正确");
		if(flag) {
			// 2、查询数据库，看username、password是否存在
			Map<String, Object> map = ud.isLogin(username, password);
			// 3、如果存在则跳转到success.jsp，否则跳转到fail.jsp
			Object obj = map.get("um");
			for (String key : map.keySet()) {
				System.out.println(key + "..." + map.get(key));
			}
			int num = Integer.parseInt(obj.toString());
			if(num <= 0) {
				// username格式完全正确，但是数据库不存在
                request.setAttribute("errorMsg", "*用户名或者密码不正确");
                flag = false;
			}
		}
		return flag;
	}
}
