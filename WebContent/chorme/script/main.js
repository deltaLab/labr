$(".hmenu a").click(function() {
	var href = $(this).attr("node-request");
	$.ajax({
		url:"��������ĵ�ַ�����������ǡ�Test�����������ַ�ǡ�/Test��",
		url : '/'+href,
		dataType:"����ע������������json��ʽ",
		dataType:"json",
	}).done(function(data) {
		/*����������data������ע���˷���json��ʽ�������Զ������json
		 * ��Ȼ���淵������Ҳ���Դ�xml, json, script, html��ֵ*/
		alert(data);
		alert(data.name);
	});
});