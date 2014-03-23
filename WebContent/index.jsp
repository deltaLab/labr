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
	BufferedWriter bWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path+"/"+root+"index.html"),"UTF-8"));
	bWriter.append("<html>");
	bWriter.append("<head>");
	bWriter.append("<meta http-equiv='content-type' content='text/html; charset=utf-8' />");
	bWriter.append("<title>信息学院实验室</title>");
	bWriter.append("<meta name='keywords' content='' />");
	bWriter.append("<meta name='description' content='' />");
	bWriter.append("<link href='style.css' rel='stylesheet' type='text/css' media='screen' />");
	bWriter.append("</head>");
	bWriter.append("<body>");
	bWriter.append("<div id='header'>");
	bWriter.append("<div id='menu'>");
	bWriter.append("<ul>");
	bWriter.append("<li class='hmenu active' ><a node-request='Test.svl'>主页</a></li>");
	bWriter.append("<li class='hmenu'><a node-request=''>实验室概况</a></li>");
	bWriter.append("<li class='hmenu'><a node-request=''>规章制度</a></li>");
	bWriter.append("<li class='hmenu'><a node-request=''>学生风采</a></li>");
	bWriter.append("</ul>");
	bWriter.append("</div>");
	bWriter.append("<!-- end #menu -->");
	bWriter.append("<div id='logo'>");
	bWriter.append("<h1>");
	bWriter.append("<a href='#'>Info Lab Logo </a>");
	bWriter.append("</h1>");
	bWriter.append("</div>");
	bWriter.append("</div>");

	bWriter.append("<div id='page'>");
	bWriter.append("<div id='page-bgtop'>");
	bWriter.append("<div id='page-bgbtm'>");
	bWriter.append("<div id='content'>");
	bWriter.append("<div class='post'>");
	bWriter.append("<p class='meta'>");
	bWriter.append("<span class='date'>2014-3-18 23:11:25</span> 作者<a href='#'>管理员</a>");
	bWriter.append("</p>");
	bWriter.append("<h2 class='title'>");
	bWriter.append("<a href='#'>Welcome to Black Coffee </a>");
	bWriter.append("</h2>");
	bWriter.append("<div class='entry'>");
	bWriter.append("<p>");
	bWriter.append("这个是 <strong>示例文字</strong>, 你可以加入任何东西，比如 <a");
	bWriter.append(" href='http://www.baidu.com' rel='nofollow'>百度的链接</a>,");
	bWriter.append("如果你在新浪微博的关注人数超过了150个账号，那么请你现在刷新你的首页时间线，点击原创按钮筛选微博，你会发现一个事实，出现在你眼前的几乎只有媒体新闻账号、营销段子手和明星大V，你之前在微博上大部分好友都已经好久不见身影了，你也越来越不喜欢在微博上更新东西了。");
	bWriter.append("");
	bWriter.append("新浪微博在IPO文件中提到，“作为中国社会的微观形态，微博已吸引了多种用户，成为了一种文化现象。”而一直以来用户所能感受到的却是，新浪微博正加速远离普通用户的生活，又或者，她也从未真正走入普通用户的生活。微博完成了对用户社交网络（媒体）的启蒙，但却留不住他们。");
	bWriter.append("</p>");
	bWriter.append("</div>");
	bWriter.append("<div>");
	bWriter.append("<a node-request='#' class='links'>查看详情</a>");
	bWriter.append("</div>");
	bWriter.append("</div>");
	bWriter.append("<div class='post'>");
	bWriter.append("<p class='meta'>");
	bWriter.append("<span class='date'>Sunday, August 26, 2011</span> 7:27 AM Posted");
	bWriter.append("by <a href='#'>Someone</a>");
	bWriter.append("</p>");
	bWriter.append("<h2 class='title'>");
	bWriter.append("<a href='#'>Lorem ipsum sed aliquam</a>");
	bWriter.append("</h2>");
	bWriter.append("<div class='entry'>");
	bWriter.append("<p>");
	bWriter.append("Sed lacus. Donec lectus. Nullam pretium nibh ut turpis. Nam");
	bWriter.append("bibendum. In nulla tortor, elementum vel, tempor at, varius non,");
	bWriter.append("purus. Mauris vitae nisl nec consectetuer. Donec ipsum. Proin");
	bWriter.append("imperdiet est. Phasellus <a href='#'>dapibus semper urna</a>.");
	bWriter.append("Pellentesque ornare, orci in consectetuer hendrerit, urna elit");
	bWriter.append("eleifend nunc, ut consectetuer nisl felis ac diam. Etiam non");
	bWriter.append("felis. Donec ut ante. In id eros.");
	bWriter.append("</p>");
	bWriter.append("</div>");
	bWriter.append("<div>");
	bWriter.append("<a href='#' class='links'>查看详情</a>");
	bWriter.append("</div>");
	bWriter.append("</div>");
	bWriter.append("</div>");
	bWriter.append("<!-- end #content -->");
	bWriter.append("<div id='sidebar'>");
	bWriter.append("<ul>");
	bWriter.append("<li>");
	bWriter.append("<h2>一级目录</h2>");
	bWriter.append("<div id='bg1'>");
	bWriter.append("<div id='bg2'>");
	bWriter.append("<ul>");
	bWriter.append("<li class='active'><a node-request='#'>目录1</a></li>");
	bWriter.append("<li><a node-request='#'>目录11</a></li>");
	bWriter.append("<li><a node-request='#'>目录111</a></li>");
	bWriter.append("<li><a node-request='#'>目录1111</a></li>");
	bWriter.append("</ul>");
	bWriter.append("</div>");
	bWriter.append("</div>");
	bWriter.append("</li>");
	bWriter.append("<li>");
	bWriter.append("<h2>一级目录</h2>");
	bWriter.append("<div id='bg1'>");
	bWriter.append("<div id='bg2'>");
	bWriter.append("<ul>");
	bWriter.append("<li><a node-request='#'>Nec metus sed donec</a></li>");
	bWriter.append("<li><a node-request='#'>Magna lacus bibendum mauris</a></li>");
	bWriter.append("<li><a node-request='#'>Velit semper nisi molestie</a></li>");
	bWriter.append("<li><a node-request='#'>Eget tempor eget nonummy</a></li>");
	bWriter.append("<li><a node-request='#'>Nec metus sed donec</a></li>");
	bWriter.append("</ul>");
	bWriter.append("</div>");
	bWriter.append("</div>");
	bWriter.append("</li>");
	bWriter.append("</ul>");
	bWriter.append("</div>");
	bWriter.append("<!-- end #sidebar -->");
	bWriter.append("<div style='clear: both;'>&nbsp;</div>");
	bWriter.append("</div>");
	bWriter.append("<!-- end #page -->");
	bWriter.append("</div>");
	bWriter.append("</div>");
	bWriter.append("<div id='footer-bgcontent'>");
	bWriter.append("<div id='footer'>");
	bWriter.append("<p>Copyright &copy; 2014 Sitename.com. All rights reserved.</p>");
	bWriter.append("</div>");
	bWriter.append("<!-- end #footer -->");
	bWriter.append("</div>");
	bWriter.append("<script type='text/javascript' src='script/jquery-1.11.0.min.js'></script>");
	bWriter.append("<script type='text/javascript' src='script/main.js'></script>");
	bWriter.append("</body>");
	bWriter.append("</html>");
	bWriter.flush();
	bWriter.close();
	response.sendRedirect(""+root+"index.html");
%>
</body>
</html>