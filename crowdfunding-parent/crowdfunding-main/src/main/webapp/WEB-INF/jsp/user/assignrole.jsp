<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset=UTF-8">
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
				  <li class="active">分配角色</li>
				</ol>
			<div class="panel panel-default">
			  <div class="panel-body">
				<form role="form" class="form-inline">
				  <div class="form-group">
					<label for="exampleInputPassword1">未分配角色列表</label><br>
					<select class="form-control" id="leftRole" multiple size="10" style="width:200px;overflow-y:auto;">
                        <c:forEach items="${leftRoleList}" var="role">
	                        <option value="${role.id}">${role.name}</option>
                        </c:forEach>
                    </select>
				  </div>
				  <div class="form-group">
                        <ul>
                            <li class="btn btn-default glyphicon glyphicon-chevron-right" id="grantRole"></li><br/>
                            <li class="btn btn-default glyphicon glyphicon-chevron-left" id="revokeRole" style="margin-top:20px;"></li>
                        </ul>
				  </div>
				  <div class="form-group" style="margin-left:40px;">
					<label for="exampleInputPassword1">已分配角色列表</label><br>
					<select class="form-control" id="rightRole" multiple size="10" style="width:200px;overflow-y:auto;">
                        <c:forEach items="${rightRoleList}" var="role">
	                        <option value="${role.id}">${role.name}</option>
                        </c:forEach>
                    </select>
				  </div>
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
            });
            var postData = {
            		"userid" : "${param.userid}",
            };
            
            // 授予权限
            $("#grantRole").click(function(){
            	var leftRoles =  $("#leftRole option:selected");
            	$.each(leftRoles,function(i,rst){
            		postData["ids["+i+"]"] = rst.value ;
        		});
            	$.ajax({
            		type : "POST",
            		url : "${APP_PATH}/role/grantRole.do",
            		data : postData,
            		beforeSend : function(){
            			return true;
            		},
            		success : function(result){
            			if(result.success){
            				$("#rightRole").append(leftRoles.clone());
                        	leftRoles.remove();
            				layer.msg("给用户分配角色成功", {time:2000});
            			}else{
            				layer.mg("给用户分配角色失败！", {time:1000,icon:5,shift:6});
            			}
            		},
            		error : function(){
            			layer.mg("系统异常！", {time:1000,icon:5,shift:6});
            		}
            	});
            });
            // 回收权限
			$("#revokeRole").click(function(){
				var rightRoles =  $("#rightRole option:selected");
            	$.each(rightRoles,function(i,rst){
            		postData["ids["+i+"]"] = rst.value ;
        		});
            	$.ajax({
            		type : "POST",
            		url : "${APP_PATH}/role/revokeRole.do",
            		data : postData,
            		beforeSend : function(){
            			return true;
            		},
            		success : function(result){
            			if(result.success){
            				$("#leftRole").append(rightRoles.clone());
                        	rightRoles.remove();
            				layer.msg("给用户取消配角色成功", {time:2000});
            			}else{
            				layer.mg("给用户取消角色失败！", {time:1000,icon:5,shift:6});
            			}
            		},
            		error : function(){
            			layer.mg("系统异常！", {time:1000,icon:5,shift:6});
            		}
            	});
            });
        </script>
  </body>
</html>
