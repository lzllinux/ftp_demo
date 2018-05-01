package cn.hngyjx.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase.FileSizeLimitExceededException;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
@WebServlet("/upload")
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// �����ļ��ϴ�����չ��
	private String Ext_Name = "gif,jpg,txt,exe,jpeg,png,bmp,swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb,doc,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,gz,bz2";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// �õ��ϴ��ļ��ı���Ŀ¼�����ϴ��ļ������WEB-INFĿ¼�£����������ֱ�ӷ��ʣ���֤�ϴ��ļ��İ�ȫ
		String savePath = this.getServletContext().getRealPath("/WEB-INF/upload");
		System.out.println(savePath);
		File saveFileDir = new File(savePath);
		if (!saveFileDir.exists()) {
			// ������ʱĿ¼
			saveFileDir.mkdirs();
		}
		// �ϴ�ʱ������ʱĿ¼
		String tpPath = this.getServletContext().getRealPath("/WEB-INF/tem");
		File tmpFile = new File("tmpPath");
		if (!tmpFile.exists()) {
			tmpFile.mkdirs();
		}
		// ��Ϣ��ʾ
		String message = "";
		try {
			// ʹ��Apache�ļ��ϴ���������ļ��ϴ����裺
			// 1.����һ��DiskFileItemFactory����
			DiskFileItemFactory factory = new DiskFileItemFactory();
			// ���ù����Ļ������Ĵ�С�����ϴ����ļ���С�����������Ĵ�Сʱ���ͻ�����һ����ʱ�ļ���ŵ�ָ������ʱĿ¼����
			factory.setSizeThreshold(1024 * 10);// ���û������Ĵ�СΪ100KB�������ָ������ôĬ�ϻ������Ĵ�С��10KB
			// �����ϴ�ʱ���ɵ���ʱ�ļ��ı���Ŀ¼
			factory.setRepository(tmpFile);
			// 2.����һ���ļ��ϴ�������
			ServletFileUpload upload = new ServletFileUpload(factory);
			// �����ļ��ϴ�����
			upload.setProgressListener(new ProgressListener() {

				@Override
				public void update(long readedBytes, long totalBytes, int currentItem) {
					// TODO Auto-generated method stub
					System.out.println("��ǰ�Ѵ���" + readedBytes + "-----------�ļ���СΪ��" + totalBytes + "--" + currentItem);
				}
			});
			// ����ϴ��ļ�����������������
			upload.setHeaderEncoding("UTF-8");
			// 3.�ж��ύ�����������Ƿ����ϴ���������
			if (!ServletFileUpload.isMultipartContent(req)) {
				// ���մ�ͳ��ʽ��ȡ����
				return;
			}

			// �����ϴ������ļ������ֵ
			upload.setFileSizeMax(1024 * 1024 * 1000);// 1M
			// �����ϴ��ļ����������ֵ�����Ǳ����ϴ��������ļ����ܺ͵����ֵ
			upload.setSizeMax(1024 * 1024 * 10000);// 10M

			List items = upload.parseRequest(req);
			Iterator itr = items.iterator();
			while (itr.hasNext()) {
				FileItem item = (FileItem) itr.next();
				// ���fileitem�з�װ������ͨ������������
				if (item.isFormField()) {
					String name = item.getFieldName();
					String value = item.getString("UTF-8");
					// value = new String(value.getBytes("iso8859-1"),"UTF-8");
					System.out.println(name + "=" + value);
				} else// ���fileitem�з�װ�����ϴ��ļ�
				{
					// �õ��ϴ��ļ����ļ���
					String fileName = item.getName();
					System.out.println("�ļ�����" + fileName);
					if (fileName == null && fileName.trim().length() == 0) {
						continue;
					}
					// ע�⣺��ͬ��������ύ���ļ����ǲ�һ���ģ���Щ������ύ�������ļ����Ǵ���·����
					// ��: C:\Users\H__D\Desktop\1.txt ����Щ���� �� 1.txt
					// �����ȡ�����ϴ��ļ����ļ�����·�����֣�ֻ�����ļ�������
					fileName = fileName.substring(fileName.lastIndexOf("\\") + 1);

					// �õ��ϴ��ļ�����չ��
					String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
					// �����չ��
					// �����Ҫ�����ϴ����ļ����ͣ���ô����ͨ���ļ�����չ�����ж��ϴ����ļ������Ƿ�Ϸ�
					System.out.println("�ϴ����ļ�����չ���ǣ�" + fileExt);
					if (!Ext_Name.contains(fileExt)) {
						System.out.println("�ϴ��ļ���չ���ǲ��������չ����" + fileExt);
						message = message + "�ļ���" + fileName + "���ϴ��ļ���չ���ǲ��������չ����" + fileExt + "<br/>";
						break;
					}

					// ����ļ���С
					if (item.getSize() == 0)
						continue;
					if (item.getSize() > 1024 * 1024 * 1000) {
						System.out.println("�ϴ��ļ���С��" + item.getSize());
						message = message + "�ļ���" + fileName + "���ϴ��ļ���С�������ƴ�С��" + upload.getFileSizeMax() + "<br/>";
						break;
					}

					// �õ����ļ����ļ���
					//String saveFileName = makeFileName(fileName);

					// �����ļ�����һ// ��ȡitem�е��ϴ��ļ���������
					InputStream is = item.getInputStream();
					// ����һ���ļ������
			FileOutputStream out = new FileOutputStream(savePath + "/" + fileName);
					// ����һ��������
					byte buffer[] = new byte[1024];
					// �ж��������е������Ƿ��Ѿ�����ı���
					int len = 0;
					while ((len = is.read(buffer)) > 0) {
						out.write(buffer, 0, len);
					}
					// �ر������
					out.close();
					// �ر�������
					is.close();
					// ɾ����ʱ�ļ�
					item.delete();

					message = message + "�ļ���" + fileName + "���ϴ��ɹ�<br/>";

					// �����ļ�������
					// File uploadedFile = new File(savePath, saveFileName);
					// item.write(uploadedFile);

				}

			}

		} catch (FileSizeLimitExceededException e) {
			message = message + "�ϴ��ļ���С��������<br/>";
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			req.setAttribute("message", message);
			req.getRequestDispatcher("/message.jsp").forward(req, resp);
		}
	}

	

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 doGet(req, resp);
	}

}
