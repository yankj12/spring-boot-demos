$(function() {
	$("#searchBtn").click(function() {
	$.ajax({
		url : '/searchFood',
		data : 'keyWord=' + $("input[name='keyWord']").val(),
		success : function(jd) {
			$("div[name='c']").remove();
			for (var i = 0; i < jd.length; i++) {
				var html = "<div name='c'>" +
					"<h3>" + jd[i].foodname + "</h3>" +
					"<span>" + jd[i].foodname + "价格是：" + jd[i].price + "</span>" +
					"<ht>" +
					"</div>"
				$("#foodDiv").append(html);
				}
			}
		})
	})
})