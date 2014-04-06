package com.jxust.infolab.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.jxust.infolab.beans.StudentShowBean;
import com.jxust.infolab.beans.TestBean;
import com.jxust.infolab.dao.StudentsShowDao;

/**
 * Servlet implementation class Students
 */
@WebServlet("/Students.svl")
public class Students extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Logger log = Logger.getLogger(getClass());
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Students() {
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
		String sOffset = request.getParameter("offset");
		String sPageSize = request.getParameter("pageSize");
		String subpath = request.getParameter("subpath");
		System.out.println(subpath);
		int offset = 0;
		int pageSize =10;
		if(sOffset!=null&&sOffset.matches("\\d+")){
			offset = Integer.parseInt(sOffset);
		}
		if(sPageSize!=null&&sPageSize.matches("\\d+")){
			pageSize = Integer.parseInt(sPageSize);
		}
		response.setContentType("text/plain");
		PrintWriter out = response.getWriter();
		StudentsShowDao ssDao = new StudentsShowDao();
		List<StudentShowBean>  list = ssDao.lodPic(offset, pageSize);
		Gson gson = new Gson();
		String result = gson.toJson(list,list.getClass());
		out.write(result);
		out.close();
		log.info("返回图片数据");
	}

}
