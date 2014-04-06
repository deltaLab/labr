/**
 * 加载完后执行的主函数
 */
$(function() {
	buildMenu();
	attach();
	$(window).hashchange(function() {
		attach();
	});
});
/**
 * 构建顶上的主目录
 */
function buildMenu() {
	for (key in menuTree) {
		var value = menuTree[key];
		$("#menu ul").append(
				'<li class="hmenu"><a node-request="' + key + '">'
						+ value.value + '</a></li>');
	}
	listenHmenu();
}
/**
 * 横向导航栏监听器
 */
function listenHmenu() {

	$(".hmenu a").click(function() {
		if ($(this).parent().hasClass("active")) {
			return;
		}
		var href = $(this).attr("node-request");
		// hMenuRequest(href, callSwitcher[href]);
		buildSideMenu(href);
	});
}
/**
 * 当URL变化的时候调用
 */
function attach() {
	var href = getHash();
	buildSideMenu(href);
	// hMenuRequest(href, callSwitcher[href]);
}
/**
 * 由于点击目录，发送的请求，并处理相应的结果
 * 
 * @param href
 *            请求地址
 * @param deal
 *            处理结果的方法，若不是方法则不处理返回的数据
 * @param request
 *            放请求参数的对象
 * @param dtType
 *            返回数据的数据类型
 */
function menuRequest(href, deal, request, dtType) {
	// --> 始终保证有子目录
	if (typeof (request) != "object") {
		request = {
			"subpath" : menuTree[href]["default"]
		};
	} else {
		if (typeof (request.subpath) == "undefined") {
			request.subpath = menuTree[href]["default"];
		}
	}
	// <--始终保证有子目录
	// 看本次请求是否在前进后退时忽略掉，要忽略的，请到ignor对象里面去配置成true
	if (ignor[href] != "true") {
		location.hash = href + "&" + request.subpath;
	}
	// 返回的数据类型可以有json html xml script等等，参考MIME类型
	if (typeof (dtType) != "string") {
		dtType = "json";
	}
	$.ajax({
		url : "真正的请求地址，如这里href是test.svl",
		url : href,
		dataType : "这里默认返回json类型数据格式，方便今后处理",
		dataType : dtType,
		data : request
	}).done(function(data) {
		if (typeof (deal) == "function") {
			deal(data, request.subpath);
		}
		// 返回数据后unmask掉
		$("#content").unmask();
		hMenuTaggle();
		sideMenuTaggle(request.subpath);
	});
	// 提交请求时，中心内容去mask上，不让操作
	$("#content").mask("数据加载中");
}
/**
 * 获取当前URL的hash
 * 
 * @returns 在有hash的时候返回hash，没有“#”号；在没有hash时，默认返回Home.svl
 */
function getHash() {
	var hash = location.hash;
	if (hash != "") {
		hash = hash.substring(1);
		var params = hash.split("&");
		hash = params[0];
	} else {
		hash = "Home.svl";
	}
	return hash;
}
/**
 * 获取子路径
 * 
 * @returns 若url的hash中还有&，则返回&后面的字符，若没有返回""
 */
function getSubpath() {
	var hash = location.hash;
	if (hash != "") {
		hash = hash.substring(1);
		var params = hash.split("&");
		if (params.length > 1) {
			hash = params[1];
		}
	} else {
		hash = "";
	}
	return hash;
}
/**
 * 顶上目录根据当前的hash进行样式变化
 */
function hMenuTaggle() {
	var href = getHash();
	var currentHref = $(".hmenu.active a").attr("node-request");
	if (href == currentHref) {
		return;
	}
	$(".hmenu.active").removeClass("active");
	$("#menu a").each(function() {
		var _this = $(this);
		if (_this.attr("node-request") == href) {
			_this.parent().addClass("active");
		}
	});
}
/**
 * 构建侧边目录行
 * 
 * @param href
 */
function buildSideMenu(href) {
	var currentPath = $("#sidebar h2");
	if (!currentPath.hasClass(href)) {
		var sideMenuObj = menuTree[href];
		var sideMenuStr = "<h2 class='" + href + "'>" + sideMenuObj.value
				+ "</h2>";
		var lv2Menu_bg1 = $("<div id=\"bg1\"></div>");
		var lv2Menu_bg2 = $("<div id=\"bg2\"></div>");
		lv2Menu_bg1.append(lv2Menu_bg2);
		var lv2Menux = $("<ul></ul>");
		for (key in sideMenuObj) {
			if (key != "default" && key != "value") {
				lv2Menux.append("<li><div node-request=\"" + key + "\">"
						+ sideMenuObj[key] + "</div></li>");
			}
		}
		var sideMenuRoot = $("#sidebar>ul>li");
		sideMenuRoot.empty();
		lv2Menux = lv2Menux.appendTo(lv2Menu_bg2);
		sideMenuRoot.append(sideMenuStr);
		sideMenuRoot.append(lv2Menu_bg1);
		listenSideMenu(href);
		var subpath = getSubpath();
		if (menuTree[href][subpath] == undefined) {
			subpath = menuTree[href]["default"];
		}
		var request = {
			"subpath" : subpath
		};
		setRequest(subpath, request);
		menuRequest(href, callSwitcher[href], request);
	}
}
/**
 * 侧边目录点击监听
 * 
 * @param href
 */
function listenSideMenu(href) {
	$("#bg2 div").click(function() {
		var subpath = $(this).attr("node-request");
		var lastPath = getSubpath();
		if (subpath == lastPath) {// 若点击目录是在活动状态，则返回
			return;
		}
		var request = {
			"subpath" : subpath
		};
		setRequest(subpath, request);
		menuRequest(href, callSwitcher[href], request);
	});
}

function setRequest(subpath, request) {
	if (subpath == "uploadedFils") {// 去获取上传文件前，需啊传递开始位置offset，和一次取几个pagesize
		request.offset = 0;
		request.pagesize = 20;
	}
}

/**
 * 侧边目录根据当前的子路径进行样式变化
 * 
 * @param subpath
 */
function sideMenuTaggle(subpath) {
	if (subpath == "default") {
		subpath = menuTree[getHash()]["default"];
	}
	$("#bg2 li.active").removeClass("active");
	$("#bg2 div").each(function() {
		var _this = $(this);
		if (_this.attr("node-request") == subpath) {
			_this.parent().addClass("active");
		}
	});
}
/**
 * 登录/出监听
 */
$("#logio").click(function() {
	var href = $(this).attr("node-request");
	hMenuRequest(href);
});
/**
 * 上传监听
 */
function listenUpload() {
	$(".upload").click(function() {
		// 没有选择上传的文件
		if ("" == $("#uploadFiles").val()) {
			$.confirm({
				'title' : '温馨提示',
				'message' : '你忘记选择上传的文件了.',
				'buttons' : {
					'确定' : {
						'class' : 'blue',
						'action' : function() {
							$.confirm.hide();
						}
					}
				}
			});
		} else {
			$("#uploadForm").submit();
			uploadListen();
			$("#uploadForm")[0].reset();
			$("#uploadForm").mask("正在上传");
		}
	});
}
/**
 * 上传结果监听 每秒获取一次结果，最多获取60次
 */
function uploadListen() {
	var counter = 0;
	var i = setInterval(check, 1000);
	function check() {
		counter++;
		if (counter == 60) {// 最多上传最多等60秒钟，超过时间，认为上传失败
			clearInterval(i);
			$("#uploadForm").unmask();
			alert("提示", "文件上传中不明原因失败.");
		} else {
			$.ajax({
				url : "CheckUpload.svl",
				dataType : "json"
			}).done(function(data) {
				/*
				 * 返回数据是data格式是json，除了json外 上面dataType还可以传xml, json, script,
				 * html等格式
				 */
				var status = data.status;
				if (typeof (status) == "string") {
					if (status == "on") {

					} else if (status == "success") {
						var files = data.files;
						var fls = "";
						for ( var k = 0; k < files.length; k++) {
							fls += "<br>" + (k + 1) + ":" + files[k];
						}
						alert("提示", "文件上传成功" + fls);
						clearInterval(i);
						$("#uploadForm").unmask();
					} else if (status == "failure") {
						alert("提示", "抱歉，文件上传失败：<br>" + data.message);
						clearInterval(i);
						$("#uploadForm").unmask();
					} else {
						alert("提示", "抱歉，无法获取文件上传信息");
						clearInterval(i);
						$("#uploadForm").unmask();
					}
				}
				// 返回数据后unmask掉
			});
		}
	}
}

/**
 * 处理Student.svl传回来的数据
 * 
 * @param data
 */
function student(data) {
	// console.log(data);
}
function overView(data, subpath) {
	$("#content").empty();
	if (data.xxx == undefined) {//若某个XXX属性的值没有，说明返回值是空的
		$("#content")
				.append(
						"<textarea id='overViewEditor' style='height:400px;width:600px'></textarea><br><br><input id='submitOverView' type='button' value='预览'><div id='overViewPreview'></div>");
		$("#overViewEditor").wysiwyg({
			"formWidth" : "600px",
			"maxHeight" : "200px",
			"initialContent" : "请在这里输入文字",
			"css":{
				"background-color":"#101010",
				"color":"#72809F",
				"height":"200px",
				"font-family": "微软雅黑"
			},
			"controls":{
				"removeFormat": { "visible" : false },
				"insertImage": { "visible" : false }
			}
		});
		$("#submitOverView").click(function() {
			$("#overViewPreview").empty();
			$("#overViewPreview").append($("#overViewEditor").val());
		});
	}
}
function rules(data, subpath) {
	$("#content").empty();
}
function home(data, subpath) {
	if (subpath == "fileUpload") {
		buildUploadForm();
	} else if (subpath == "uploadedFils") {
		showAllFiles(data);
	}
}
/**
 * 构建文件上传界面
 */
function buildUploadForm() {
	var form = $("<form id=\"uploadForm\" action=\"Upload.svl\" method=\"post\" enctype=\"multipart/form-data\" target=\"hidden_frame\"></form>");
	form
			.append("<input id =\"uploadFiles\"type=\"file\" name =\"1\" multiple=\"multiple\">");
	form.append("<br>");
	form.append("<input class=\"upload\" type=\"button\" value=\"上传\">");
	$("#content").empty();
	$("#content").append(form);
	listenUpload();
}

function showAllFiles(data) {
	var fileFolder = $("meta[name=fileSavePath]").attr("content");
	$("#content").empty();
	for (index in data) {
		var file = data[index];
		var fileType = file["fileType"].toLowerCase();
		if (image[fileType]) {
			var currentFileNode = $("<div><table class=\"img_100\"> <tbody><tr><td><div><img src=\""
					+ fileFolder
					+ "/"
					+ file.saveName
					+ "_100x100.jpg\" title=\""
					+ file.fileName
					+ "\"></div></td></tr></tbody></table><div>");
			currentFileNode.data("data", file);
			$("#content").append(currentFileNode);
		} else {
			var currentFileNode = $("<div><table class=\"img_100\"> <tbody><tr><td><a title=\""
					+ file.fileName
					+ "\">"
					+ file.fileName
					+ "</a></td></tr></tbody></table></div>");
			currentFileNode.data("data", file);
			$("#content").append(currentFileNode);
		}
	}
}
/**
 * alert 警告提示框
 * 
 * @param title
 *            标题
 * @param message
 *            警告提示消息
 */

function alert(title, message) {
	if (typeof (title) != "string") {
		title = "";
	}
	if (typeof (message) != "string") {
		message = "";
	}
	$.confirm({
		'title' : title,
		'message' : message,
		'buttons' : {
			'确定' : {
				'class' : 'blue',
				'action' : function() {
					$.confirm.hide();
				}
			}
		}
	});
}
/**
 * 目录树配置最外面一层是顶级目录，往下一层是子目录
 */
var menuTree = {
	"Home.svl" : {
		"value" : "首页",
		"default" : "fileUpload",
		"fileUpload" : "文件上传",
		"uploadedFils" : "查看上传文件列表"
	},
	"OverView.svl" : {
		"value" : "实验室概况",
		"default" : "introduce",
		"introduce" : "介绍"
	},
	"Rules.svl" : {
		"value" : "规章制度",
		"default" : "allRules",
		"allRules" : "规则列表"
	},
	"Students.svl" : {
		"value" : "学生风采",
		"default" : "students",
		"students" : "学生秀"
	}
};
var ignor = {
	"说明" : "配置那些Servlet不需要记录历史，防止刷新，前进后退时重复提交数据",
	"Logout.svl" : "true",
	"Login.svl" : "true"
};
/**
 * 返回数据处理方法切换器，配置方式：URL的hash做键，返回数据的处理方法做值
 */
var callSwitcher = {
	"Students.svl" : student,
	"OverView.svl" : overView,
	"Home.svl" : home,
	"Rules.svl" : rules
};
var image = {
	"jpg" : true,
	"jpeg" : true,
	"gif" : true,
	"png" : true,
	"bmp" : true
};