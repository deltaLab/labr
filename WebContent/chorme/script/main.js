$(".hmenu a").click(function() {
	var href = $(this).attr("node-request");
	$.ajax({
		url:"发送请求的地址，比如这里是“Test”发送请求地址是“/Test”",
		url : '/'+href,
		dataType:"这里注明返回类型是json格式",
		dataType:"json",
	}).done(function(data) {
		/*返回数据是data，上面注明了返回json格式，所以自动处理成json
		 * 当然上面返回类型也可以传xml, json, script, html等值*/
		alert(data);
		alert(data.name);
	});
});