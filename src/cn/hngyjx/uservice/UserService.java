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
		request.setAttribute("errorMsg", "�û��������벻��ȷ");
		if(flag) {
			// 2����ѯ���ݿ⣬��username��password�Ƿ����
			Map<String, Object> map = ud.isLogin(username, password);
			// 3�������������ת��success.jsp��������ת��fail.jsp
			Object obj = map.get("um");
			for (String key : map.keySet()) {
				System.out.println(key + "..." + map.get(key));
			}
			int num = Integer.parseInt(obj.toString());
			if(num <= 0) {
				// username��ʽ��ȫ��ȷ���������ݿⲻ����
                request.setAttribute("errorMsg", "*�û����������벻��ȷ");
                flag = false;
			}
		}
		return flag;
	}
}
