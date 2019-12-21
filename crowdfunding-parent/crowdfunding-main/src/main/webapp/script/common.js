function setLocation(){
	var url =  window.location.href;	// http://localhost:8080/crowdfunding-main/user/index.html
	var host = window.location.host; // localhost:8080
	var proj = "${APP_PATH}"; // /crowdfunding-main
	var target = url.substring(7 + host.length + proj.length);	// 截取服务器目标地址
	
	// 排除问号传参的情况
	if(target.indexOf("?") != -1){
		target = target.substring(0,target.indexOf("?"));
	}
	// 路径的模糊匹配
	var targetTag = $(".list-group a[href*='"+target+"']");
	targetTag.css("color", "red");	// 设置红色样式
	
	targetTag.parent().parent().show();	// 展开
	targetTag.parent().parent().parent().removeClass("tree-closed");	// 相应地移除关闭地类样式
}