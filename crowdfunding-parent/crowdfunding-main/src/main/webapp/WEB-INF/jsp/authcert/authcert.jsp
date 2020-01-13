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
<br>
 <hr style="clear:both;">
          <div class="table-responsive">
            <table class="table  table-bordered">
              <thead>
                <tr >
                  <th width="30">#</th>
                  <th>流程名称</th>
                  <th>流程版本</th>
                  <th>任务名称</th>
                  <th>申请会员</th>
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
	    	$(".list-group-item").click(function () {
                if ($(this).find("ul")) {
                    $(this).toggleClass("tree-closed");
                    if ($(this).hasClass("tree-closed")) {
                        $("ul", this).hide("fast");
                    } else {
                        $("ul", this).show("fast");
                    }
                }
                
            });
	    	setLocation();
	    	queryAuthCertPage(0);
		});
	    // 上下页切换
		function changePage(page){
    		pageParams.currentPage = page;
    		queryAdvertPage(page);
    	}
	    // 页面参数
		var pageParams = {
    			"currentPage" : 0,
    			"pageSizes" : 10
    	};
	    // 查询列表
		function queryAuthCertPage(page) {
			var loadingIndex = -1;
			pageParams.currentPage = page + 1;	// 设置当前页
			$.ajax({
				type : "POST",
				url : "${APP_PATH}/authcert/list.do",
				data : pageParams,
				beforeSend : function() {
					loadingIndex = layer.msg('数据查询中', {icon: 16});
					return true;
				},
				success : function(data) {
					layer.close(loadingIndex);
					if (data.success) {
						// 将查询结果循环遍历，将数据展现出来
						var page = data.pageResult;
						var taskList = page.resultList;
						
						var content = "";
						$.each(taskList, function(i, tmp){
					        content += '<tr>';
					        content += '  <td>'+(i+1)+'</td>';
					        content += '  <td>'+tmp.procDefName+'</td>';
					        content += '  <td>'+tmp.procDefVersion+'</td>';
					        content += '  <td>'+tmp.name+'</td>';
					        content += '  <td>'+tmp.memberName+'</td>';
					        content += '  <td>';
					        content += '      <button type="button" onclick="window.location.href=\'${APP_PATH}/authcert/view.html?taskid='+tmp.id+'&memberid='+tmp.memberid+'\'" class="btn btn-success btn-xs"><i class=" glyphicon glyphicon-check"></i></button>';
					   		content += '  </td>';
					        content += '</tr>';
						});  				       				
						$("tbody").html(content);
						
						// 创建分页
						var num_entries = page.totalRecords ;
						$("#Pagination").pagination(num_entries, {
							num_edge_entries: 2, //边缘页数
							num_display_entries: 4, //主体页数
							callback: queryAuthCertPage, //查询当前页的数据.
							items_per_page:page.pagesize, //每页显示1项
							current_page:(page.currentPage-1), //当前页,索引从0开始
							prev_text:"上一页",
							next_text:"下一页"
						});
					} else {
						layer.msg("审核任务分页查询失败", {time:1000, icon:5, shift:6});
					}
				}
			});
		}

    </script>
  </body>
</html>
