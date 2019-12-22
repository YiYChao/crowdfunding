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
	<style>
	.tree li {
        list-style-type: none;
		cursor:pointer;
	}
	</style>
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
				  <li><a href="${APP_PATH}/permission/index.html">许可列表</a></li>
				  <li class="active">修改许可</li>
				</ol>
			<div class="panel panel-default">
              <div class="panel-heading">表单数据<div style="float:right;cursor:pointer;" data-toggle="modal" data-target="#myModal"><i class="glyphicon glyphicon-question-sign"></i></div></div>
			  <div class="panel-body">
				<form role="form" id="userForm">
				  <div class="form-group">
					<label for="fname">许可名称</label>
					<input type="text" class="form-control" id="fname" placeholder="请输入许可名称" value="${permission.name}">
				  </div>
				  <div class="form-group">
					<label for="ficon">许可图标</label>
					<input type="text" class="form-control" id="ficon" placeholder="请输入许可图标" value="${permission.icon}">
				  </div>
				  <div class="form-group">
					<label for="furl">许可地址</label>
					<input type="email" class="form-control" id="furl" placeholder="请输入许可地址" value="${permission.url}">
				  </div>
				  <button type="button" class="btn btn-success" id="editBtn"><i class="glyphicon glyphicon-plus"></i> 修改</button>
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
		</div>
	  </div>
	</div>
    <script src="${APP_PATH}/jquery/jquery-2.1.1.min.js"></script>
    <script src="${APP_PATH}/bootstrap/js/bootstrap.min.js"></script>
	<script src="${APP_PATH}/script/docs.min.js"></script>
	<script src="${APP_PATH}/jquery/layer/layer.js"></script>
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
            // 添加许可信息
            $("#editBtn").click(function(){
            	var name = $("#fname");
            	var icon = $("#ficon");
            	var url = $("#furl");
            	$.ajax({
            		type : "POST",
            		url : "${APP_PATH}/permission/doEdit.do",
            		data : {
            			"id" : "${param.id}",
            			"name" : name.val(),
            			"icon" : icon.val(),
            			"url" : url.val()
            		},
            		beforeSend : function(){
            			return true;
            		},
            		success : function(result){
            			console.log(result);
            			if(result.success){
            				window.location.href="${APP_PATH}/permission/index.html"
            			}else{
            				layer.msg("修改许可失败！", {time:2000});
            			}
            		}
            		
            	});
            });
            // 重置表单
			$("#resetBtn").click(function(){
            	$("#userForm")[0].reset();	// 将JQuery对象装化为DOM对象，调用DOM对象的重置方法
            });
        </script>
  </body>
</html>
