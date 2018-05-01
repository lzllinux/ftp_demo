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
	        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
	        if (file.exists() && file.isFile()) {
	            if (file.delete()) {
	               
	                return true;
	            } else {
	                System.out.println("删除单个文件" + fileName + "失败！");
	                return false;
	            }
	        } else {
	            System.out.println("删除单个文件失败：" + fileName + "不存在！");
	            return false;
	        }
		 
	 }
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 得到要下载的文件名
		String fileName = req.getParameter("filename");
		System.out.println(fileName);
		// fileName = new String(fileName.getBytes("iso8859-1"), "UTF-8");
		// 上传的文件都是保存在/WEB-INF/upload目录下的子目录当中
		String fileSaveRootPath = this.getServletContext().getRealPath("/WEB-INF/upload");
		// 得到要下载的文件
		System.out.println(fileSaveRootPath);
		File file = new File(fileSaveRootPath + "/" + fileName);
		System.out.println(file.toString());
		if(deleteFile(fileSaveRootPath + "/" + fileName)) {
			req.setAttribute("message", "您要下载的资源已被删除！！");
			req.getRequestDispatcher("/message.jsp").forward(req, resp);
		}
		else {
			req.setAttribute("message", "删除失败！！");
			req.getRequestDispatcher("/message.jsp").forward(req, resp);
		}
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}

}
