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
  </head>

  <body>
	<jsp:include page="/WEB-INF/jsp/common/top.jsp"></jsp:include>

    <div class="container-fluid">
      <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
			<jsp:include page="/WEB-INF/jsp/common/menu.jsp"></jsp:include>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
			<div class="panel panel-default">
			  <div class="panel-heading">
				<h3 class="panel-title"><i class="glyphicon glyphicon-th"></i>数据矩阵</h3>
			  </div>
			  <div class="panel-body">
          <div class="table-responsive">
            <table class="table  table-bordered">
              <thead>
                <tr id="ts20">
                  <th>序号</th>
                  <th>名称</th>
                  <th>商业公司</th>
                  <th>个体工商户</th>
                  <th>个人经营</th>
                  <th>政府及非营利组织</th>
                </tr>
              </thead>
              <tbody>
              </tbody>
            </table>
          </div>
			  </div>
			</div>
        </div>
      </div>
    </div>

    <script src="${APP_PATH}/jquery/jquery-2.1.1.min.js"></script>
    <script src="${APP_PATH}/bootstrap/js/bootstrap.min.js"></script>
	<script src="${APP_PATH}/script/docs.min.js"></script>
	<script src="${APP_PATH}/script/common.js"></script>
	<script src="${APP_PATH}/jquery/layer/layer.js"></script>
        <script type="text/javascript">
            $(function () {
			    setLocation();
			    queryList();
            });
            // 加载页面标签
            function queryList(){
            	var loadingIndex = -1;
	            $.ajax({
	            	method : "GET",
	            	url : "${APP_PATH}/acctypecert/list.do",
	            	beforeSend : function(){
	            		loadingIndex = layer.load(2, {time:1000});
	            		return true;
	            	},
	            	success : function(data){
	            		if(data.success){
	            			var tabelContent = '';
	            			$.each(data.obj, function(i, tmp){
	            				tabelContent += '<tr id="ts'+i+'">';
	            				tabelContent += '<td>'+ (i+1) +'</td>';
	            				tabelContent += '<td>'+ tmp.name +'</td>';
	            				tabelContent += '<td><input type="checkbox" accttype="0" certid="'+ tmp.id +'"></td>';
	            				tabelContent += '<td><input type="checkbox" accttype="1" certid="'+ tmp.id +'"></td>';
	            				tabelContent += '<td><input type="checkbox" accttype="2" certid="'+ tmp.id +'"></td>';
	            				tabelContent += '<td><input type="checkbox" accttype="3" certid="'+ tmp.id +'"></td>';
	            				tabelContent += '</tr>';
	            			});
	            			$("tbody").html(tabelContent);
	            			updateRelations();
	            		}else{
	            			layer.mg(data.message, {time:1000,icon:5,shift:6});
	            		}
	            	}, 
	            	error : function(){
	            		layer.mg("加载类型矩阵数据失败！", {time:1000,icon:5,shift:6});
	            	}
	            });
            };
            // 通过*委托*的形式添加复选框的点击事件，因为jQuery获取不到动态加载的节点数据
            $("tbody").on("mouseenter", ":checkbox", function(){
            	// 目标节点的点击事件
            	$(this).click(function(){
	            	var flag = this.checked;
	            	//通过this.certid能否获取自定义的属性值?
	            	var certid = $(this).attr("certid");
	            	var accttype = $(this).attr("accttype");
	            	if (flag) {
	            		// 增加账户类型和资质的关系
	            		$.ajax({
	            			type : "POST",
	            			url  : "${APP_PATH}/acctypecert/saveAcctTypeCert.do",
	            			data : {
	            				certid : certid,
	            				accttype : accttype
	            			},
	            			success : function(data) {
	            				if(!data.success){
	            					layer.msg(data.message, {time:1000, icon:5, shift:6});
	            				}
	            				updateRelations();
	            				return false;
	            			}
	            		});
	            	} else {
	            		// 删除账户类型和资质的关系
	            		$.ajax({
	            			type : "POST",
	            			url  : "${APP_PATH}/acctypecert/deleteAcctTypeCert.do",
	            			data : {
	            				certid : certid,
	            				accttype : accttype
	            			},
	            			success : function(data) {
	            				if(!data.success){
	            					layer.msg(data.message, {time:1000, icon:5, shift:6});
	            				}
	            				updateRelations();
	            				return false;
	            			}
	            		});
	            	}
            	});
            });
			function updateRelations(){
				$.ajax({
        			type : "GET",
        			url  : "${APP_PATH}/acctypecert/updateRelations.do",
        			success : function(data) {
        				if(data.success){
        					$.each(data.obj, function(i,tmp){
        						$(":checkbox[certid='"+tmp.certid+"'][accttype='"+tmp.accttype+"']")[0].checked = true;
        					});
        				}else{
        					layer.msg(data.message, {time:1000, icon:5, shift:6});
        				}
        			}
        		});
			}
        </script>
  </body>
</html>
