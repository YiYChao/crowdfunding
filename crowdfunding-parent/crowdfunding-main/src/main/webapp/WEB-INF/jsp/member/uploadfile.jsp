<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
	<link rel="stylesheet" href="${APP_PATH}/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="${APP_PATH}/css/font-awesome.min.css">
	<link rel="stylesheet" href="${APP_PATH}/css/theme.css">
	<style>
#footer {
    padding: 15px 0;
    background: #fff;
    border-top: 1px solid #ddd;
    text-align: center;
}
	</style>
  </head>
  <body>
 	<jsp:include page="/WEB-INF/jsp/common/member_top.jsp"></jsp:include>

    <div class="container theme-showcase" role="main">
      <div class="page-header">
        <h1>实名认证 - 申请</h1>
      </div>

		<ul class="nav nav-tabs" role="tablist">
		  <li role="presentation" ><a href="#"><span class="badge">1</span> 基本信息</a></li>
		  <li role="presentation" class="active"><a href="#"><span class="badge">2</span> 资质文件上传</a></li>
		  <li role="presentation"><a href="#"><span class="badge">3</span> 邮箱确认</a></li>
		  <li role="presentation"><a href="#"><span class="badge">4</span> 申请确认</a></li>
		</ul>
        
		<form role="form" style="margin-top:20px;" id="certForm" method="post" enctype="multipart/form-data">
			  <%-- <c:forEach items="${certList}" var="cert">
			  		<div class="form-group">
						<label for="exampleInputEmail1">${cert.name}</label>
						<input type="file" class="form-control" >
			            <br>
			            <img src="img/pic.jpg">
					  </div>
			  </c:forEach> --%>
		</form>
		<hr>
    </div> <!-- /container -->
    <jsp:include page="/WEB-INF/jsp/common/member_foot.jsp"></jsp:include>
    
    <script src="${APP_PATH}/jquery/jquery-2.1.1.min.js"></script>
    <script src="${APP_PATH}/bootstrap/js/bootstrap.min.js"></script>
	<script src="${APP_PATH}/script/docs.min.js"></script>
	<script src="${APP_PATH}//jquery/jquery-form.min.js"></script>
	<script>
        $('#myTab a').click(function (e) {
          e.preventDefault()
          $(this).tab('show')
        });
        $.ajax({
        	method : "post",
        	url : "${APP_PATH}/acctypecert/picneed.do",
        	success : function(data){
        		if(data.success){
        			var content = '';
        			$.each(data.obj, function(i, tmp){
        				content += '<div class="form-group">';
        				content += '<label for="exampleInputEmail1">'+tmp.name+'</label>';
        				content += '<input type="hidden" name="membercert['+ i +'].certid" value="'+tmp.id+'">';
        				content += '<input type="file" class="form-control" name="membercert['+ i +'].imgfile"><br>';
        				content += '<img src="#" style="display:none; width:30%"></div>';
        			})
        			content += '<button type="button" onclick="window.location.href=\'${APP_PATH}/member/basicinfo.html\'" class="btn btn-default">上一步</button>';
        			content += '<button type="button" id="nextBtn"  class="btn btn-success">下一步</button>';
        			$("#certForm").html(content);
        		}
        	}
        });
       $("#certForm").on("mouseenter", ":file", function(){
	        $(this).change(function(event){
	  	      	// 获取当前选择的文件 event.target.files
	            var files = event.target.files, file;
	            if (files && files.length > 0) {
	                file = files[0]; 
	            }
	            // 本地生成上传文件后的临时文件地址
	            var URL = window.URL || window.webkitURL;
	            var imgURL = URL.createObjectURL(file);
	            var imgObj = $(this).next().next();
	            imgObj.attr("src", imgURL);
	            imgObj.show();
	  		});
        });
       $("#certForm").on("mouseenter", "#nextBtn", function(){
	        $(this).click(function(){
	        	var options = {
	            	url : "${APP_PATH}/mbAuthen/uploadMemberCertFile.do",
	            	success : function(data){
	            		if(data.success){
	            			window.location.href="${APP_PATH}/member/apply.html";
	            		}
	            	}
	        	};
	        	$("#certForm").ajaxSubmit(options);
	        	return false;	// 阻止事件冒泡
	        });
       });
	</script>
  </body>
</html>