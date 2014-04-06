<%@page import="com.jxust.infolab.utils.Constants"%>
<%@page import="java.io.BufferedWriter"%>
<%@page import="java.io.FileWriter"%>
<%@page import="java.io.File"%>
<%@page import="java.io.FileOutputStream"%>
<%@page import="java.io.OutputStreamWriter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	9999
	<%

	String path = request.getServletContext().getRealPath("/");
	Object rut = session.getAttribute("root");
	String root = "";
	if (rut != null) {
		root = (String) rut+"/";
	}
	File folder = new File(path+"/"+root);
	if(!folder.isDirectory()){
		folder.mkdir();
	}
	File file = new File(path+"/"+root+"index.html");
	String uploadPath = getServletContext().getContextPath()+Constants.uploadPath;
	while(uploadPath.contains(".")){
		uploadPath = uploadPath.substring(1);
	}
	BufferedWriter bWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path+"/"+root+"index.html"),"UTF-8"));
	bWriter.append("<html>");
	bWriter.append("<head>");
	bWriter.append("<meta http-equiv=\"content-type\" content=\"text/html; charset=utf-8\" />");
	bWriter.append("<meta name=\"fileSavePath\" content=\""+uploadPath+"\" />");
	bWriter.append("<title>信息学院实验室</title>");
	bWriter.append("<link href=\"style.css\" rel=\"stylesheet\" type=\"text/css\"/>");
	bWriter.append("<link href=\"jquery.confirm.css\" rel=\"stylesheet\" type=\"text/css\"/>");
	bWriter.append("<link href=\"jquery.loadmask.css\" rel=\"stylesheet\" type=\"text/css\" />");
	bWriter.append("<link href=\"jquery.wysiwyg.css\" rel=\"stylesheet\" type=\"text/css\" />");
	bWriter.append("<link href=\"jquery.wysiwyg.modal.css\" rel=\"stylesheet\" type=\"text/css\" />");
	bWriter.append("</head>");
	bWriter.append("<body>");
	bWriter.append("<div id=\"header\">");
	bWriter.append("<div id=\"menu\">");
	bWriter.append("<ul>");
	bWriter.append("<li ><font></font></li>");
	bWriter.append("</ul>");
	bWriter.append("</div>");
	bWriter.append("<!-- end #menu -->");
	bWriter.append("<div id=\"logo\">");
	bWriter.append("<h1>");
	bWriter.append("<a href=\"index.html\">Info Lab Logo </a>");
	bWriter.append("<span id=\"logio\" node-request=\"Logout.svl\">登录</span>");
	bWriter.append("</h1>");
	bWriter.append("</div>");
	bWriter.append("</div>");
	bWriter.append("");
	bWriter.append("<div id=\"page\">");
	bWriter.append("<div id=\"page-bgtop\">");
	bWriter.append("<div id=\"page-bgbtm\">");
	bWriter.append("<div id=\"content\">");
	bWriter.append("</div>");
	bWriter.append("<!-- end #content -->");
	bWriter.append("<div id=\"sidebar\">");
	bWriter.append("<ul>");
	bWriter.append("<li>");
	bWriter.append("<h2 class=\"Students.svl\">默认目录</h2>");
	bWriter.append("<div id=\"bg1\">");
	bWriter.append("<div id=\"bg2\">");
	bWriter.append("<ul>");
	bWriter.append("<li class=\"active\"><a node-request=\"fileUpload\">加载失败</a></li>");
	bWriter.append("</ul>");
	bWriter.append("</div>");
	bWriter.append("</div>");
	bWriter.append("</li>");
	bWriter.append("</ul>");
	bWriter.append("</div>");
	bWriter.append("<!-- end #sidebar -->");
	bWriter.append("<div style=\"clear: both;\">&nbsp;</div>");
	bWriter.append("</div>");
	bWriter.append("<!-- end #page -->");
	bWriter.append("</div>");
	bWriter.append("</div>");
	bWriter.append("<div id=\"footer-bgcontent\">");
	bWriter.append("<div id=\"footer\">");
	bWriter.append("<p>Copyright &copy; 2014 Sitename.com. All rights reserved.</p>");
	bWriter.append("</div>");
	bWriter.append("<!-- end #footer -->");
	bWriter.append("</div>");
	bWriter.append("<script type=\"text/javascript\" src=\"script/jquery-1.11.0.min.js\"></script>");
	bWriter.append("<script type=\"text/javascript\" src=\"script/jquery.xhashchange.min.js\"></script>");
	bWriter.append("<script type=\"text/javascript\" src=\"script/jquery.confirm.js\"></script>");
	bWriter.append("<script type=\"text/javascript\" src=\"script/jquery.loadmask.min.js\"></script>");
	bWriter.append("<script type=\"text/javascript\" src=\"script/jquery.wysiwyg.js\"></script>");
	bWriter.append("<script type=\"text/javascript\" src=\"script/main.js\"></script>");
	bWriter.append("</body>");
	bWriter.append("</html>");
	bWriter.flush();
	bWriter.close();
	response.sendRedirect(""+root+"index.html");
%>
</body>
</html>