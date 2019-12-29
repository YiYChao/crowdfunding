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
      <input class="form-control has-success" type="text" placeholder="请输入查询条件" id="advertCondition">
    </div>
  </div>
  <button type="button" class="btn btn-warning" id="queryBtn"><i class="glyphicon glyphicon-search"></i> 查询</button>
</form>
<button type="button" class="btn btn-danger" style="float:right;margin-left:10px;" id="batchDelete"><i class=" glyphicon glyphicon-remove"></i> 删除</button>
<button type="button" class="btn btn-primary" style="float:right;" onclick="window.location.href='${APP_PATH}/advert/add.html'"><i class="glyphicon glyphicon-plus"></i> 新增</button>
<br>
 <hr style="clear:both;">
          <div class="table-responsive">
            <table class="table  table-bordered">
              <thead>
                <tr >
                  <th width="30">#</th>
                  <th width="30"><input type="checkbox" id="checkAll"></th>
                  <th>广告描述</th>
                  <th>状态</th>
                  <th width="100">操作</th>
                </tr>
              </thead>
              <tbody>
              </tbody>
			  <tfoot>
			     <tr >
				     <td colspan="4" align="center">
						<!-- <ul class="pagination">
						</ul> -->
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
			    queryAdvertPage(0);
            });
            var pageParams = {
        			"currentPage" : 0,
        			"pageSizes" : 10
        		};
            function changePage(page){
        		pageParams.currentPage = page;
        		queryAdvertPage(page);
        	}
            function queryAdvertPage(page){
            	var loadingIndex = -1;
            	pageParams.currentPage = page + 1;	// 设置当前页
            	$.ajax({
            		type : "POST",
            		url : "${APP_PATH}/advert/list.do",
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
            					content+='<td>'+(i+1)+'</td>';
            					content+='<td><input type="checkbox" name="'+ rst.id +'"></td>';
            					content+='<td>'+rst.name+'</td>';
            					if(rst.status == 0){
            						content+='<td>草稿</td>';
            					}else if(rst.status == 1){
            						content+='<td>未审核</td>';
            					}else if(rst.status == 2){
            						content+='<td>审核完成</td>';
            					}else{
            						content+='<td>发布</td>';
            					}
            					content+='<td>';
            					content+='<button type="button" class="btn btn-success btn-xs" onclick="window.location.href=\'${APP_PATH}/role/assignpermission.html?id='+ rst.id +'\'"><i class=" glyphicon glyphicon-check"></i></button>';
            					content+='<button type="button" class="btn btn-primary btn-xs" onclick="editAdvert('+ rst.id +')"><i class=" glyphicon glyphicon-pencil"></i></button>';
            					content+='<button type="button" class="btn btn-danger btn-xs" onclick="deleteAdvert('+ rst.id +',\''+rst.loginacct +'\')"><i class=" glyphicon glyphicon-remove"></i></button>';
            					content+='</td>';
            					content+='</tr>';
            				});
            				$("tbody").html(content);
            				/* var pageBar = '';
            				/* 拼接上一页 */
            				/* if(pageContent.currentPage == 1){
            					pageBar+='<li class="disabled"><a href="#">上一页</a></li>';
            				}else{
            					pageBar+='<li><a href="#" onclick="changePage('+(pageContent.currentPage - 1)+')">上一页</a></li>';
            				}
            				/* 拼接中间页码 */
            				/* for(var i = 1; i <= pageContent.totalPages; i++){
            					if(i == pageContent.currentPage){
            						pageBar+='<li class="active"><a href="#" onclick="changePage('+i+')">'+i+'</a></li>';
            					}else{
            						pageBar+='<li><a href="#" onclick="changePage('+i+')">'+i+'</a></li>';
            					}
            				} */
            				/* 拼接下一页 */
            				/* if(pageContent.currentPage == pageContent.totalPages){
            					pageBar+='<li class="disabled"><a href="#">下一页</a></li>';
            				}else{
            					pageBar+='<li><a href="#" onclick="changePage('+(pageContent.currentPage + 1)+')">下一页</a></li>';
            				}
            				$(".pagination").html(pageBar); */ 
            				// 创建分页
            				var num_entries = pageContent.totalRecords;	// 总记录数
            				$("#Pagination").pagination(num_entries, {
            					num_edge_entries: 2, //边缘页数
            					num_display_entries: 4, //主体页数
            					callback: queryAdvertPage,
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
            			layer.mg("加载数据失败！", {time:1000,icon:5,shift:6});
            		}

            	});
            };
            $("#queryBtn").click(function(){
            	var condition = $("#advertCondition").val();
           		pageParams.condition = $.trim(condition);
           		queryAdvertPage(0);
            });
            function editAdvert(advertId){
            	window.location.href = "${APP_PATH}/advert/edit.html?advertId="+ advertId;
            };
            function deleteAdvert(uid,loginacct){
            	layer.confirm("确认要删除["+loginacct+"]广告吗？",  {icon: 3, title:'提示'}, function(cindex){
    			    layer.close(cindex);
    	        	$.ajax({
    	        		type : "POST",
    	        		url : "${APP_PATH}/advert/doDelete.do",
    	        		data : {"id" : uid},
    	        		beforeSend : function(){
    	        			return true;
    	        		},
    	        		success : function(result){
    	        			console.log(result);
    	        			if(result.success){
    	        				layer.msg("批量删除成功", {time:2000});
    	        				queryAdvertPage(pageParams.currentPage);
    	        			}else{
    	        				layer.msg("批量删除广告失败！", {time:2000});
    	        			}
    	        		}
    	        	});
    			}, function(cindex){
    			    layer.close(cindex);
    			});
            };
            // “全选”复选框
            $("#checkAll").click(function(){
            	var res = this.checked;
            	// $("tbody tr input[type='checkbox']").attr("checked", res); // attr方法只能点一次
            	$("tbody tr input[type='checkbox']").prop("checked", res);
            });
            
            $("#batchDelete").click(function(){
            	var boxChecked = $("tbody input:checked"); //获取所有选中的复选框
            	if(boxChecked.length <= 0){
            		layer.mg("请选择要删除的广告！", {time:1000,icon:5,shift:6});
            	}else{
            		layer.confirm("确认要删除这"+ boxChecked.length +"个广告吗？",  {icon: 3, title:'提示'}, function(cindex){
        			    layer.close(cindex);
        			    var target = "";
        			    for(var i = 0; i < boxChecked.length; i++){
        			    	target += (i == 0) ? ("ids=" + boxChecked[i].name):("&ids=" + boxChecked[i].name) ;
        			    }
        	        	$.ajax({
        	        		type : "POST",
        	        		url : "${APP_PATH}/advert/batchDelete.do",
        	        		data : target,
        	        		beforeSend : function(){
        	        			return true;
        	        		},
        	        		success : function(result){
        	        			console.log(result);
        	        			if(result.success){
        	        				queryRolePage(pageParams.currentPage);
        	        			}else{
        	        				layer.msg("批量删除这个广告失败！", {time:2000});
        	        			}
        	        		}
        	        	});
        			}, function(cindex){
        			    layer.close(cindex);
        			});
            	}
            });
        </script>
  </body>
</html>
