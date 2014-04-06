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
import com.jxust.infolab.dao.StudentsShowDao;
import com.jxust.infolab.utils.SessionUtil;

/**
 * Servlet implementation class CheckUpload
 */
@WebServlet(name = "CheckUpload.svl", urlPatterns = { "/CheckUpload.svl" })
public class CheckUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final Logger log = Logger.getLogger(getClass());

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CheckUpload() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String info = (String) SessionUtil.get("upload",request);
		PrintWriter out = response.getWriter();
		out.write(info);
		out.close();
		log.info(info);
	}

}
