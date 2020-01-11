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
	<link rel="stylesheet" href="${APP_PATH}/css/theme.css">
	<style>
		#footer {
		    padding: 15px 0;
		    background: #fff;
		    border-top: 1px solid #ddd;
		    text-align: center;
		}
		.seltype {
		    position: absolute;
		    margin-top: 70px;
		    margin-left: 10px;
		    color: red;
		}
	</style>
  </head>
  <body>
	<jsp:include page="/WEB-INF/jsp/common/member_top.jsp"></jsp:include>

    <div class="container theme-showcase" role="main">

      <div class="page-header">
        <h1>实名认证 - 账户类型选择</h1>
      </div>
	  <div style="padding-top:10px;">
		<div class="row">
      <div class="col-xs-6 col-md-3">
      
      <h2>商业公司</h2>
        <a href="#" class="thumbnail" accttype="0">
          <img alt="100%x180" src="${APP_PATH}/img/services-box1.jpg" data-holder-rendered="true" style="height: 180px; width: 100%; display: block;">
        </a>
      </div>
      <div class="col-xs-6 col-md-3">
        <h2>个体工商户</h2>
        <a href="#" class="thumbnail" accttype="1">
          <img alt="100%x180" src="${APP_PATH}/img/services-box2.jpg" data-holder-rendered="true" style="height: 180px; width: 100%; display: block;">
        </a>
      </div>
      <div class="col-xs-6 col-md-3">
        <h2>个人经营</h2>
        <a href="#" class="thumbnail" accttype="2">
          <img alt="100%x180" src="${APP_PATH}/img/services-box3.jpg" data-holder-rendered="true" style="height: 180px; width: 100%; display: block;">
        </a>
      </div>
      <div class="col-xs-6 col-md-3">
        <h2>政府及非营利组织</h2>
        <a href="#" class="thumbnail" accttype="3">
          <img alt="100%x180" src="${APP_PATH}/img/services-box4.jpg" data-holder-rendered="true" style="height: 180px; width: 100%; display: block;">
        </a>
      </div>
    </div>
	<button type="button" class="btn btn-danger btn-lg btn-block" id="applyBtn">认证申请</button>
    </div> <!-- /container -->
      <!-- /END THE FEATURETTES -->
    <jsp:include page="/WEB-INF/jsp/common/member_foot.jsp"></jsp:include>
    
    <script src="${APP_PATH}/jquery/jquery-2.1.1.min.js"></script>
    <script src="${APP_PATH}/bootstrap/js/bootstrap.min.js"></script>
	<script src="${APP_PATH}/script/docs.min.js"></script>
	<script>
		var accttype='0'; 
	    $(".thumbnail").click(function(){
	        $('.seltype').remove();
	        $(this).prepend('<div class="glyphicon glyphicon-ok seltype"></div>');
	        accttype = $(this).attr('accttype');	// 选择账户类型
	    });
	    $("#applyBtn").click(function(){
	    	$.ajax({
	    		method : "post",
	    		url : "${APP_PATH}/mbAuthen/accttype.do",
	    		data : {"accttype" : accttype},
	    		success : function(data){
	    			if(data.success){
	    				window.location.href="${APP_PATH}/member/apply.html";
	    			}
	    		}
	    	});
	    });
	</script>
  </body>
</html>