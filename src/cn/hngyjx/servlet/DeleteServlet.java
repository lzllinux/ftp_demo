package cn.hngyjx.servlet;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DeleteServlet")
public class DeleteServlet extends HttpServlet {
	 public static boolean deleteFile(String fileName) {
		 File file = new File(fileName);
	        // ����ļ�·������Ӧ���ļ����ڣ�������һ���ļ�����ֱ��ɾ��
	        if (file.exists() && file.isFile()) {
	            if (file.delete()) {
	               
	                return true;
	            } else {
	                System.out.println("ɾ�������ļ�" + fileName + "ʧ�ܣ�");
	                return false;
	            }
	        } else {
	            System.out.println("ɾ�������ļ�ʧ�ܣ�" + fileName + "�����ڣ�");
	            return false;
	        }
		 
	 }
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// �õ�Ҫ���ص��ļ���
		String fileName = req.getParameter("filename");
		System.out.println(fileName);
		// fileName = new String(fileName.getBytes("iso8859-1"), "UTF-8");
		// �ϴ����ļ����Ǳ�����/WEB-INF/uploadĿ¼�µ���Ŀ¼����
		String fileSaveRootPath = this.getServletContext().getRealPath("/WEB-INF/upload");
		// �õ�Ҫ���ص��ļ�
		System.out.println(fileSaveRootPath);
		File file = new File(fileSaveRootPath + "/" + fileName);
		System.out.println(file.toString());
		if(deleteFile(fileSaveRootPath + "/" + fileName)) {
			req.setAttribute("message", "��Ҫ���ص���Դ�ѱ�ɾ������");
			req.getRequestDispatcher("/message.jsp").forward(req, resp);
		}
		else {
			req.setAttribute("message", "ɾ��ʧ�ܣ���");
			req.getRequestDispatcher("/message.jsp").forward(req, resp);
		}
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}

}
