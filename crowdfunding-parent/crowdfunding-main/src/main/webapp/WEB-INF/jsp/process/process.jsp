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
	<link rel="stylesheet" href="${APP_PATH}/jquery/pagination/pagination.css">
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

<button type="button" class="btn btn-primary" style="float:right;" onclick="window.location.href='form.html'"><i class="glyphicon glyphicon-upload"></i> 上传流程定义文件</button>
<br>
 <hr style="clear:both;">
          <div class="table-responsive">
            <table class="table  table-bordered">
              <thead>
                <tr >
                  <th width="30">#</th>
                  <th>流程名称</th>
                  <th>流程版本</th>
                  <th>流程定义Key</th>
                  <th width="100">操作</th>
                </tr>
              </thead>
              <tbody>
                
              </tbody>
			  <tfoot>
			     <tr >
				     <td colspan="6" align="center">
						<div id="Pagination" class="pagination"></div><!-- 这里显示分页 -->
					 </td>
				 </tr>

			  </tfoot>
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
    <script src="${APP_PATH}/jquery/pagination/jquery.pagination.js"></script>
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
			    queryProcessPage(0);
            });
            var pageParams = {
        			"currentPage" : 0,
        			"pageSizes" : 10
        		};
            function changePage(page){
        		pageParams.currentPage = page;
        		queryProcessPage(page);
        	}
            function queryProcessPage(page){
            	var loadingIndex = -1;
            	pageParams.currentPage = page + 1;	// 设置当前页
            	$.ajax({
            		type : "POST",
            		url : "${APP_PATH}/process/list.do",
            		data : pageParams,
            		beforeSend : function(){
            			loadingIndex = layer.load(2, {time:1000});
            			return true;
            		},
            		success : function(data){
            			//layer.close(loadingIndex);
            			if(data.success){
            				var pageContent = data.pageResult;
            				var content = '';
            				$.each(pageContent.resultList, function(i,rst){
            					content+='<tr>';
            					content+='<td>'+ (i+1) +'</td>';
            					content+='<td>'+ rst.name +'</td>';
            					content+='<td>'+ rst.version +'</td>';
            					content+='<td>'+ rst.key +'</td>';
            					content+='<td>';
            					content+='<button type="button" class="btn btn-primary btn-xs" onclick="viewProcess(\''+ rst.id +'\')"><i class="glyphicon glyphicon-eye-open"></i></button>';
            					content+='<button type="button" class="btn btn-danger btn-xs" onclick="deleteProcess(\''+ rst.id +'\',\''+rst.name +'\')"><i class="glyphicon glyphicon-remove"></i></button>';
            					content+='</td>';
            					content+='</tr>';
            				});
            				$("tbody").html(content);
            				// 创建分页
            				var num_entries = pageContent.totalRecords;	// 总记录数
            				$("#Pagination").pagination(num_entries, {
            					num_edge_entries: 2, //边缘页数
            					num_display_entries: 4, //主体页数
            					callback: queryProcessPage,
            					items_per_page:pageContent.pageSize, //每页显示1项
            					current_page:(pageContent.currentPage-1), //当前页,索引从0开始
            					prev_text:"上一页",
            					next_text:"下一页"
            				});
            			}else{
            				layer.mg(data.message, {time:1000,icon:5,shift:6});
            			}
            		},
            		errror : function(){
            			layer.mg("加载流程数据失败！", {time:1000,icon:5,shift:6});
            		}

            	});
            };
            $("#queryBtn").click(function(){
            	var condition = $("#advertCondition").val();
           		pageParams.condition = $.trim(condition);
           		queryProcessPage(0);
            });
            function viewProcess(processId){
            	window.location.href = "${APP_PATH}/process/view.html?processId="+ processId;
            };
            function deleteProcess(processId,name){
            	layer.confirm("确认要删除["+name+"]吗？",  {icon: 3, title:'提示'}, function(cindex){
    			    layer.close(cindex);
    	        	$.ajax({
    	        		type : "POST",
    	        		url : "${APP_PATH}/process/doDelete.do",
    	        		data : {"processId" : processId},
    	        		beforeSend : function(){
    	        			return true;
    	        		},
    	        		success : function(result){
    	        			console.log(result);
    	        			if(result.success){
    	        				layer.msg("删除流程成功", {time:2000});
    	        				queryProcessPage(pageParams.currentPage - 1);
    	        			}else{
    	        				layer.msg("删除流程失败！", {time:2000});
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
    