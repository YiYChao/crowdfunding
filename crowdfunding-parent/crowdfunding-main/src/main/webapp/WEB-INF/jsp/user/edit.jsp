<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	<link rel="stylesheet" href="${APP_PATH}/css/main.css">
	<link rel="stylesheet" href="${APP_PATH}/css/doc.min.css">
  </head>

  <body>
    <jsp:include page="/WEB-INF/jsp/common/top.jsp"></jsp:include>

    <div class="container-fluid">
      <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
			<jsp:include page="/WEB-INF/jsp/common/menu.jsp"></jsp:include>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<ol class="breadcrumb">
				  <li><a href="${APP_PATH}/main.html">首页</a></li>
				  <li><a href="${APP_PATH}/user/index.html">数据列表</a></li>
				  <li class="active">修改</li>
				</ol>
			<div class="panel panel-default">
              <div class="panel-heading">表单数据<div style="float:right;cursor:pointer;" data-toggle="modal" data-target="#myModal"><i class="glyphicon glyphicon-question-sign"></i></div></div>
			  <div class="panel-body">
				<form role="form" id="userForm">
				  <div class="form-group">
					<label for="floginacct">登陆账号</label>
					<!-- 设置隐藏域，用于保存id -->
					<input type="hidden" class="form-control" id="fid" value="${user.id}">
					<input type="text" class="form-control" id="floginacct" value="${user.loginacct}">
				  </div>
				  <div class="form-group">
					<label for="fusername">用户名称</label>
					<input type="text" class="form-control" id="fusername" value="${user.username}">
				  </div>
				  <div class="form-group">
					<label for="femail">邮箱地址</label>
					<input type="email" class="form-control" id="femail" value="${user.email}">
					<p class="help-block label label-warning">请输入合法的邮箱地址, 格式为： xxxx@xxxx.com</p>
				  </div>
				  <button type="button" class="btn btn-success" id="editBtn"><i class="glyphicon glyphicon-edit"></i> 修改</button>
				  <button type="button" class="btn btn-danger" id="resetBtn"><i class="glyphicon glyphicon-refresh"></i> 重置</button>
				</form>
			  </div>
			</div>
        </div>
      </div>
    </div>
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	  <div class="modal-dialog">
		<div class="modal-content">
		  <div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
			<h4 class="modal-title" id="myModalLabel">帮助</h4>
		  </div>
		  <div class="modal-body">
			<div class="bs-callout bs-callout-info">
				<h4>测试标题1</h4>
				<p>测试内容1，测试内容1，测试内容1，测试内容1，测试内容1，测试内容1</p>
			  </div>
			<div class="bs-callout bs-callout-info">
				<h4>测试标题2</h4>
				<p>测试内容2，测试内容2，测试内容2，测试内容2，测试内容2，测试内容2</p>
			  </div>
		  </div>
		  <!--
		  <div class="modal-footer">
			<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			<button type="button" class="btn btn-primary">Save changes</button>
		  </div>
		  -->
		</div>
	  </div>
	</div>
    <script src="${APP_PATH}/jquery/jquery-2.1.1.min.js"></script>
    <script src="${APP_PATH}/bootstrap/js/bootstrap.min.js"></script>
	<script src="${APP_PATH}/script/docs.min.js"></script>
	<script src="${APP_PATH}/script/common.js"></script>
        <script type="text/javascript">
            $(function () {
			    $(".list-group-item").click(function(){
				    if ( $(this).find("ul") ) {
						$(this).toggleClass("tree-closed");
						if ( $(this).hasClass("tree-closed") ) {
							$("ul", this).hide("fast");
						} else {
							$("ul", this).show("fast");
						}
					}
				});
			    setLocation();
            });
         	// 重置表单
			$("#resetBtn").click(function(){
            	$("#userForm")[0].reset();	// 将JQuery对象装化为DOM对象，调用DOM对象的重置方法
            });
         	// 修改用户
         	$("#editBtn").click(function(){
         		var id = $("#fid");
         		var loginacct = $("#floginacct");
            	var username = $("#fusername");
            	var email = $("#femail");
            	$.ajax({
            		type : "POST",
            		url : "${APP_PATH}/user/doEdit.do",
            		data : {
            			"id" : id.val(),
            			"loginacct" : loginacct.val(),
            			"username" : username.val(),
            			"email" : email.val()
            		},
            		beforeSend : function(){
            			return true;
            		},
            		success : function(result){
            			console.log(result);
            			if(result.success){
            				window.location.href="${APP_PATH}/user/index.html"
            			}else{
            				layer.msg("修改用户失败！", {time:2000});
            			}
            		}
            		
            	});
         	});
        </script>
  </body>
</html>
