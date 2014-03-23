$(".hmenu a").click(function() {
	var href = $(this).attr("node-request");
	$.ajax({
		url:"真正的请求地址，如这里href是test.svl",
		url : href,
		dataType:"这里指定json类型数据格式，方便今后处理",
		dataType:"json",
	}).done(function(data) {
		/*返回数据是data格式是json，除了json外
		 * 上面dataType还可以传xml, json, script, html等格式*/
		$(this).parent().class("active");
		alert(data);
		alert(data.name);
	});
});