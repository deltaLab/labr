package com.jxust.infolab.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.jxust.infolab.beans.TestBean;

/**
 * Servlet implementation class Rules
 */
@WebServlet(name = "Rules.svl", urlPatterns = { "/Rules.svl" })
public class Rules extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Logger log = Logger.getLogger(getClass());
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Rules() {
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
		response.setContentType("text/plain");
		PrintWriter out = response.getWriter();
		
		TestBean testBean = new TestBean();
		testBean.setLeve(5);
		testBean.setName("tome");
		Gson gson = new Gson();
		String result = gson.toJson(testBean);
		out.write(result);
		out.close();
		log.info("end");
	}

}
