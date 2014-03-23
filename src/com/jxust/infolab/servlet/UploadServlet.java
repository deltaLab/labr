package com.jxust.infolab.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.jxust.infolab.dao.UploadFileDao;
import com.jxust.infolab.entities.UploadedFile;
import com.jxust.infolab.utils.Constants;

/**
 * Servlet implementation class UploadServlet
 * 
 * @author lumence<br>
 *         上传文件类<br>
 *         图片缩小的方法{@link #resizePicture(List)}还没实现
 */
/* 这个是配置这个servlet用于处理二进制数据，与前台的 enctype="multipart/form-data" 对应 */
/* 单个文件最大10M，总文件大小最大300M */
@MultipartConfig(maxFileSize = 1024 * 1024 * 10, maxRequestSize = 1024 * 1024 * 300)
@WebServlet("/upload.svl")
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 2304820820384L;
	private final Logger log = Logger.getLogger(getClass());
	private final String picType = "BMP,DXF,EMF,EPS,FLI,FLC,GIF,JPEG,JPG,LIC,PCX,PNG,PSD,SWF,SVG,TGA,TIFF,WMF";
	private String[] picTypes=null;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		Collection<Part> parts = null;
		try {
			parts = request.getParts();
		} catch (IllegalStateException ise) {
			// 可能某个文件大于指定文件容量maxFileSize，或者提交数据大于maxRequestSize
			log.info("maybe the request body is larger than maxRequestSize, or any Part in the request is larger than maxFileSize");
		} catch (IOException ioe) {
			// 在获取某个文件时遇到拉IO异常错误
			log.error("an I/O error occurred during the retrieval of the Part components of this request");
		} catch (Exception e) {
			log.error("the request body is larger than maxRequestSize, or any Part in the request is larger than maxFileSize");
			e.printStackTrace();
		}

		if (isEmpty(parts, response)) {
			return;
		}
		foldersCheck();
		// 传入的文件名称集合
		List<String> fileNames = new ArrayList<String>();
		// 文件保存路径
		List<String> filePaths = new ArrayList<String>();
		List<UploadedFile> uploadedFileList = new ArrayList<UploadedFile>();
		// 写文件的输出流
		OutputStream out = null;
		InputStream filecontent = null;
		// 前端具有几个file组件，这里会对应几个Part对象
		for (Part part : parts) {
			if (part == null) {
				continue;
			}
			// 这里提取源文件名
			String fileName = getFileName(part);
			// 文件类型
			String fileType = fileName.substring(fileName.lastIndexOf('.') + 1);
			if (fileType.length() == fileName.length()) {
				fileType = null;
			}
			String saveName = radomName(fileType);
			// 文件的保存地址
			String filePath = Constants.uploadPath + File.separator + saveName;
			out = new FileOutputStream(new File(filePath));
			filecontent = part.getInputStream();

			int read = 0;
			final byte[] bytes = new byte[1024];

			while ((read = filecontent.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
			// 把文件名加进去
			fileNames.add(fileName);
			if (isPicture(fileType)) {
				filePaths.add(filePath);
			}
			// 上传信息需要存入数据库
			UploadedFile uploadedFile = new UploadedFile();
			uploadedFile.setCreateTime(new Date());
			uploadedFile.setFileName(fileName);
			uploadedFile.setSaveName(saveName);
			uploadedFile.setFileType(fileType);
			uploadedFile.setValid(true);
			uploadedFileList.add(uploadedFile);
		}

		UploadFileDao uploadFileDao = new UploadFileDao();
		uploadFileDao.saveUploadedFiles(uploadedFileList);

		resizePicture(filePaths);

		Gson gson = new Gson();
		String files = gson.toJson(fileNames, fileNames.getClass());
		PrintWriter writer = response.getWriter();
		writer.print("{'status':'succes',files':'" + files + "}");
		writer.flush();
		writer.close();
	}

	/**
	 * 判断文件是不是图片文件
	 * 
	 * @param fileType
	 * @return
	 */
	private boolean isPicture(String fileType) {
		boolean isPic = false;
		if(fileType!=null){
			fileType = fileType.toUpperCase();
			if(picTypes==null){
				picTypes = picType.split(",");
			}
			for(String type:picTypes){
				if(type.equals(fileType)){
					isPic = true;
					break;
				}
			}
		}
		return isPic;
	}

	/**
	 * 根据传入的图片文件路径，生成小图片
	 * 
	 * @param filePaths
	 */
	private void resizePicture(List<String> filePaths) {
		// TODO Auto-generated method stub
		for (String path : filePaths) {

		}
	}

	/**
	 * 客户端是否一个文件都没有上传过来
	 * 
	 * @param parts
	 * @param response
	 * @return
	 */
	private boolean isEmpty(Collection<Part> parts, HttpServletResponse response) {
		boolean empty = true;
		if (parts == null || parts.isEmpty()) {
			log.error("上传文件为空");
			PrintWriter out;
			try {
				out = response.getWriter();
				out.print("{'status':'failure','files':'0'}");
				out.flush();
				out.close();
				empty = false;
			} catch (IOException e) {
				log.error(e.getMessage());
			}
		}
		return empty;
	}

	/**
	 * 确保上传目录存在
	 */
	private void foldersCheck() {
		// 若上传文件目录没有，则创建一个
		File folder = new File(Constants.uploadPath);
		if (!folder.isDirectory()) {
			folder.mkdirs();
		}
	}

	private String getFileName(final Part part) {
		final String partHeader = part.getHeader("content-disposition");
		for (String content : partHeader.split(";")) {
			if (content.trim().startsWith("filename")) {
				return content.substring(content.indexOf('=') + 1).trim()
						.replace("\"", "");
			}
		}
		return "";
	}

	private String radomName(String fileType) {
		long t = new Date().getTime();
		if (fileType != null) {
			fileType = "." + fileType;
		}
		return t + "" + (int) (Math.random() * 900) + fileType;
	}
}