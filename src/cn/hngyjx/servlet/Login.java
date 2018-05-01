package cn.hngyjx.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.hngyjx.uservice.UserService;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	UserService ud = new UserService();

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = req.getSession();
		// �޸�response����
		resp.setCharacterEncoding("utf-8");
		// �޸����������
		resp.setContentType("text/html;charset=utf-8");
		// 1����ȡ�����ݹ����Ĳ���
		String username = req.getParameter("user");
		String password = req.getParameter("pass");
		// У��
		boolean flag;
		try {
			flag = ud.isLogin(username, password, req);
			System.out.println(flag);
			if (flag) {
				session.setAttribute("admin", username);
				// req.setAttribute("name", username);
				// req.getRequestDispatcher("/succese.jsp").forward(req, resp);
				req.getRequestDispatcher("/upload.jsp").forward(req, resp);
			} else {
				req.getRequestDispatcher("/login.jsp").forward(req, resp);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
