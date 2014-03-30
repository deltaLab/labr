ignor = {
	"Logout.svl" : "true",
	"Login.svl" : "true"
};
$(function() {
	rebuild();
	$(window).hashchange(function() {
		rebuild();
	});
});

function rebuild() {
	var href = getHash();
	hMenuRequest(href);
}

function hMenuRequest(href,deal) {
	if (ignor[href]=="true") {
	}else{
		location.hash = href;
	}
	$.ajax({
		url : "真正的请求地址，如这里href是test.svl",
		url : href,
		dataType : "这里指定json类型数据格式，方便今后处理",
		dataType : "json",
	}).done(function(data) {
		/*
		 * 返回数据是data格式是json，除了json外 上面dataType还可以传xml, json, script, html等格式
		 */
		if(typeof(deal)=="function"){
			deal(data);
		}
		hMenuTaggle();
	});
}

function getHash() {
	var hash = location.hash;
	if (hash != "") {
		hash = hash.substring(1);
	} else {
		hash = "Test.svl";
	}
	return hash;
}

function hMenuTaggle() {
	var href = getHash();
	$(".hmenu.active").removeClass("active");
	$("#menu a").each(function() {
		var _this = $(this);
		if (_this.attr("node-request") == href) {
			_this.parent().addClass("active");
		}
	});
}
/**
 * 横向导航栏监听器
 */
$(".hmenu a").click(function() {
	var href = $(this).attr("node-request");
	hMenuRequest(href);
});
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
$(".upload").click(function() {
	//没有选择上传的文件
	if(""==$("#uploadFiles").val()){
		$.confirm({
            'title'		: '温馨提示',
            'message'	: '你忘记选择上传的文件了.',
            'buttons'	: {
                '确定'	: {
                    'class'	: 'blue',
                    'action': function(){
                    	 $.confirm.hide();
                    }
                }
            }
        });
	}else{
		uploadListen();
		$("#uploadForm")[0].reset();
		$("#uploadForm").mask("正在上传");
//		$(this).parent().submit();
//		hMenuRequest("Upload.svl",function(data){
//			if(data.satus=="false"){
//				$.confirm({
//		            'title'		: '提示',
//		            'message'	: '文件上传失败.'+data.message,
//		            'buttons'	: {
//		                '确定'	: {
//		                    'class'	: 'blue',
//		                    'action': function(){
//		                    	 $.confirm.hide();
//		                    }
//		                }
//		            }
//		        });
//			}else{
//				$.confirm({
//		            'title'		: '提示',
//		            'message'	: '文件上传成功：'+data.files,
//		            'buttons'	: {
//		                '确定'	: {
//		                    'class'	: 'blue',
//		                    'action': function(){
//		                    	 $.confirm.hide();
//		                    }
//		                }
//		            }
//		        });
//			}
//		});
	}
});

function uploadListen(){
	var counter =0;
	var i = setInterval(check, 1000);
	function check(){
		counter++;
		if(counter==10){
			clearInterval(i);
			$("#uploadForm").unmask();
		}else{
			console.log(counter);
		}
	};
}
