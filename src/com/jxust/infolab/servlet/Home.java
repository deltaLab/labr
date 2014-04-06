package com.jxust.infolab.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.jxust.infolab.beans.TestBean;
import com.jxust.infolab.dao.UploadFileDao;
import com.jxust.infolab.entities.UploadedFile;

/**
 * Servlet implementation class Test
 */
@WebServlet("/Home.svl")
public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Logger log = Logger.getLogger(getClass());
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Home() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String subpath = request.getParameter("subpath");
		if(subpath.equals("uploadedFils")){
			getUploadedFiles(request, response);
		}else if(subpath.equals("fileUpload")){
			response.getWriter().write("{}");
		}
		log.info("end");
	}
	
	private void getUploadedFiles(HttpServletRequest request, HttpServletResponse response){
		try {
			PrintWriter out = response.getWriter();
			String offset = request.getParameter("offset");
			String pagesize = request.getParameter("pagesize");
			UploadFileDao fileDao = new UploadFileDao();
			Map<String,String> params = new HashMap<String,String>();
			if(offset!=null){
				params.put("offset", offset);
			}
			if(pagesize!=null){
				params.put("pagesize", pagesize);
			}
			List<UploadedFile> list = fileDao.getFiles(params);

			Gson gson = new Gson();
			String result = gson.toJson(list);
			out.write(result);
			out.close();
			log.info("获取已上传文件>开始位置："+offset+" 最多个数："+pagesize+" 实际个数:"+list.size());
		} catch (IOException e) {
			log.info("获取已上传文件时出现错误："+e);
		}
	}

}
