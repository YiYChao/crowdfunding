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
				<h3 class="panel-title"><i class="glyphicon glyphicon-th"></i> 数据列表</h3>
			  </div>
			  <div class="panel-body">
<form class="form-inline" role="form" style="float:left;">
  <div class="form-group has-feedback">
    <div class="input-group">
      <div class="input-group-addon">查询条件</div>
      <input class="form-control has-success" type="text" placeholder="请输入查询条件">
    </div>
  </div>
  <button type="button" class="btn btn-warning"><i class="glyphicon glyphicon-search"></i> 查询</button>
</form>
<button type="button" class="btn btn-danger" style="float:right;margin-left:10px;"  id="bantchDelete"><i class=" glyphicon glyphicon-remove"></i> 删除</button>
<button type="button" class="btn btn-primary" style="float:right;" onclick="window.location.href='${APP_PATH}/cert/add.html'"><i class="glyphicon glyphicon-plus"></i> 新增</button>
<br>
 <hr style="clear:both;">
          <div class="table-responsive">
            <table class="table  table-bordered">
              <thead>
                <tr >
                  <th width="30">#</th>
				  <th width="30"><input type="checkbox"></th>
                  <th>名称</th>
                  <th width="100">操作</th>
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
			    queryAll();
            });
            function queryAll(){
            	var loadingIndex = -1;
            	$.ajax({
            		type : "POST",
            		url : "${APP_PATH}/cert/list.do",
            		data : "",
            		beforeSend : function(){
            			loadingIndex = layer.load(2, {time:1000});
            			return true;
            		},
            		success : function(data){
            			if(data.success){
            				var pageContent = data.obj;
            				var content = '';
            				$.each(pageContent.resultList, function(i,rst){
            					content+='<tr>';
            					content+='<td>'+(i+1)+'</td>';
            					content+='<td><input type="checkbox" name="'+ rst.id +'"></td>';
            					content+='<td>'+rst.name+'</td>';
            					content+='<td>';
            					content+='<button type="button" class="btn btn-primary btn-xs" onclick="editCert('+ rst.id +')"><i class=" glyphicon glyphicon-pencil"></i></button>';
            					content+='<button type="button" class="btn btn-danger btn-xs" onclick="deleteCert('+ rst.id +',\''+rst.name +'\')"><i class=" glyphicon glyphicon-remove"></i></button>';
            					content+='</td>';
            					content+='</tr>';
            				});
            				$("tbody").html(content);
            			}else{
            				layer.mg(data.message, {time:1000,icon:5,shift:6});
            			}
            		},
            		errror : function(){
            			layer.mg("加载资质数据失败！", {time:1000,icon:5,shift:6});
            		}

            	});
            };
            function editCert(cid){
            	window.location.href = "${APP_PATH}/cert/edit.html?id="+ cid;
            };
            function deleteCert(cid,name){
            	layer.confirm("确认要删除["+name+"]吗？",  {icon: 3, title:'提示'}, function(cindex){
    			    layer.close(cindex);
    	        	$.ajax({
    	        		type : "POST",
    	        		url : "${APP_PATH}/cert/doDelete.do",
    	        		data : {"id" : cid},
    	        		beforeSend : function(){
    	        			return true;
    	        		},
    	        		success : function(result){
    	        			if(result.success){
    	        				layer.msg("删除资质成功", {time:2000});
    	        				queryAll();
    	        			}else{
    	        				layer.msg("删除资质失败！", {time:2000});
    	        			}
    	        		}
    	        	});
    			}, function(cindex){
    			    layer.close(cindex);
    			});
            };
        </script>
  </body>
</html>