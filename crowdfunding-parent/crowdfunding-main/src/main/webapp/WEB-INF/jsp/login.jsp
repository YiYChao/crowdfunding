<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="keys" content="">
    <meta name="author" content="">
	<link rel="stylesheet" href="${APP_PATH}/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="${APP_PATH}/css/font-awesome.min.css">
	<link rel="stylesheet" href="${APP_PATH}/css/login.css">
  </head>
  <body>
    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
      <div class="container">
        <div class="navbar-header">
          <div><a class="navbar-brand" href="index.html" style="font-size:32px;">尚筹网-创意产品众筹平台</a></div>
        </div>
      </div>
    </nav>

    <div class="container">

      <form id="loginForm" class="form-signin" role="form" action="${APP_PATH }/doLogin.do" method="post">
      	${exception.message}
        <h2 class="form-signin-heading"><i class="glyphicon glyphicon-log-in"></i> 用户登录</h2>
		  <div class="form-group has-success has-feedback">
			<input type="text" class="form-control" id="floginacct" placeholder="请输入登录账号" name="loginacct" value="zhangsan" autofocus>
			<span class="glyphicon glyphicon-user form-control-feedback"></span>
		  </div>
		  <div class="form-group has-success has-feedback">
			<input type="password" class="form-control" id="fuserpswd" placeholder="请输入登录密码"  name="userpswd" value="12345" style="margin-top:10px;">
			<span class="glyphicon glyphicon-lock form-control-feedback"></span>
		  </div>
		  <div class="form-group has-success has-feedback">
			<select class="form-control" id="ftype" name="type">
                <option value="member">会员</option>
                <option value="user" selected>管理</option>
            </select>
		  </div>
        <div class="checkbox">
          <label>
            <input type="checkbox" value="remember-me"> 记住我
          </label>
          <br>
          <label>
            忘记密码
          </label>
          <label style="float:right">
            <a href="reg.html">我要注册</a>
          </label>
        </div>
        <a class="btn btn-lg btn-success btn-block" onclick="dologin()" > 登录</a>
      </form>
    </div>
    <script src="${APP_PATH}/jquery/jquery-2.1.1.min.js"></script>
    <script src="${APP_PATH}/bootstrap/js/bootstrap.min.js"></script>
    <script src="${APP_PATH}/jquery/layer/layer.js"></script>
    <script>
    	
	    function dologin() {
	    	loginacct = $("#floginacct");
	    	userpswd = $("#fuserpswd");
	    	type = $("#ftype");
	    	var loadingIndex = -1;
	    	$.ajax({
	    		url : "${APP_PATH}/doLogin.do",
	    		type : "POST",
	    		data : {
	    			"loginacct" : loginacct.val(),
	    			"userpswd" : userpswd.val(),
	    			"type" : type.val()
	    		},
	    		beforeSend : function(){
	    			// 表单提交前进行校验
	    			if($.trim(loginacct.val()) == ""){
	    				layer.msg("用户名不能为空！", {time:1000,icon:5,shift:6},function(){
		    				loginacct.focus();
		    				loginacct.val() = "";
	    				});
	    				return false;
	    			}
	    			if($.trim(userpswd.val()) == ""){
	    				layer.msg("密码不能为空！", {time:1000,icon:5,shift:6},function(){
	    					userpswd.focus();
	    					userpswd.val() = "";
	    				});
	    				return false;
	    			}
	    			loadingIndex = layer.msg("处理中...", {icon:16});
	    			return true;
	    		},
	    		success : function(data){
	    			if(!data.success){
	    				layer.msg(data.message, {time:1000});
	    				loadingIndex.close();
	    				//alert(data.message);
	    			}else{
	    				layer.msg("登录成功", {time:2000});
	    				window.location.href = "${APP_PATH}/main.html";
	    			}
	    			
	    		},
	    		error : function(){
	    			layer.msg("网络异常！", {time:1000,icon:5,shift:6});
	    		}

	    	});
	    	/* $("#loginForm").submit(); */
	        /* var type = $(":selected").val();
	        if ( type == "user" ) {
	            window.location.href = "main.html";
	        } else {
	            window.location.href = "index.html";
	        } */
	    }
    </script>
  </body>
</html>