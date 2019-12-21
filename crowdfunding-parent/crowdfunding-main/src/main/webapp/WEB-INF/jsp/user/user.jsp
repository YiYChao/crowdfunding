<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <style>
        .tree li {
            list-style-type: none;
            cursor: pointer;
        }

        table tbody tr:nth-child(odd) {
            background: #F4F4F4;
        }

        table tbody td:nth-child(even) {
            color: #C00;
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
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title"><i class="glyphicon glyphicon-th"></i> 数据列表</h3>
                    </div>
                    <div class="panel-body">
                        <form class="form-inline" role="form" style="float:left;">
                            <div class="form-group has-feedback">
                                <div class="input-group">
                                    <div class="input-group-addon">查询条件</div>
                                    <input class="form-control has-success" id="usreCondition"type="text" placeholder="请输入查询条件">
                                </div>
                            </div>
                            <button type="button" class="btn btn-warning" id="queryBtn"><i class="glyphicon glyphicon-search"></i> 查询</button>
                        </form>
                        <button type="button" class="btn btn-danger" style="float:right;margin-left:10px;"  id="batchDelete"><i
                                class=" glyphicon glyphicon-remove"></i> 删除</button>
                        <button type="button" class="btn btn-primary" style="float:right;"
                            onclick="window.location.href='${APP_PATH}/user/add.html'"><i class="glyphicon glyphicon-plus"></i> 新增</button>
                        <br>
                        <hr style="clear:both;">
                        <div class="table-responsive">
                            <table class="table  table-bordered">
                                <thead>
                                    <tr>
                                        <th width="30">#</th>
                                        <th width="30"><input type="checkbox" id="checkAll"></th>
                                        <th>账号</th>
                                        <th>名称</th>
                                        <th>邮箱地址</th>
                                        <th width="100">操作</th>
                                    </tr>
                                </thead>
                                <tbody>
									
                                </tbody>
                                <tfoot>
                                    <tr>
                                        <td colspan="6" align="center">
                                            <ul class="pagination">
	                                            <c:if test="${page.currentPage == 1}">
	                                            	<li class="disabled"><a href="#">上一页</a></li>
	                                            </c:if>
	                                            <c:if test="${page.currentPage != 1}">
	                                            	<li><a href="#" onclick="changePage(${page.currentPage - 1})">上一页</a></li>
	                                            </c:if>
                                                <c:forEach begin="1" end="${page.totalPages}" var="num">
                                                	<li
                                                		<c:if test="${num == page.currentPage}">
                                                			class="active"
                                                		</c:if>
                                                	><a href="#" onclick="changePage(${num})">${num}</a></li>
                                                </c:forEach>
                                                <c:if test="${page.currentPage == page.totalPages}">
                                            		<li class="disabled"><a href="#">下一页</a></li>
	                                            </c:if>
	                                            <c:if test="${page.currentPage < page.totalPages}">
	                                            	<li><a href="#" onclick="changePage(${page.currentPage + 1})">下一页</a></li>
	                                            </c:if>
                                            </ul>
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
            queryUserPage(1);
            setLocation();
        });
        
        $("tbody .btn-success").click(function () {
            window.location.href = "assignRole.html";
        });
        var pageParams = {
    			"currentPage" : 1,
    			"pageSizes" : 10
    		};
        function changePage(page){
    		pageParams.currentPage = page;
    		queryUserPage(page);
    	}
        function queryUserPage(page){
        	var loadingIndex = -1;
        	pageParams.currentPage = page;	// 设置当前页
        	$.ajax({
        		type : "POST",
        		url : "${APP_PATH}/user/list.do",
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
        					content+='<td>'+rst.loginacct+'</td>';
        					content+='<td>'+rst.username+'</td>';
        					content+='<td>'+rst.email+'</td>';
        					content+='<td>';
        					content+='<button type="button" class="btn btn-success btn-xs"><i class=" glyphicon glyphicon-check"></i></button>';
        					content+='<button type="button" class="btn btn-primary btn-xs" onclick="editUser('+ rst.id +')"><i class=" glyphicon glyphicon-pencil"></i></button>';
        					content+='<button type="button" class="btn btn-danger btn-xs" onclick="deleteUser('+ rst.id +',\''+rst.loginacct +'\')"><i class=" glyphicon glyphicon-remove"></i></button>';
        					content+='</td>';
        					content+='</tr>';
        				});
        				$("tbody").html(content);
        				var pageBar = '';
        				/* 拼接上一页 */
        				if(pageContent.currentPage == 1){
        					pageBar+='<li class="disabled"><a href="#">上一页</a></li>';
        				}else{
        					pageBar+='<li><a href="#" onclick="changePage('+(pageContent.currentPage - 1)+')">上一页</a></li>';
        				}
        				/* 拼接中间页码 */
        				for(var i = 1; i <= pageContent.totalPages; i++){
        					if(i == pageContent.currentPage){
        						pageBar+='<li class="active"><a href="#" onclick="changePage('+i+')">'+i+'</a></li>';
        					}else{
        						pageBar+='<li><a href="#" onclick="changePage('+i+')">'+i+'</a></li>';
        					}
        				}
        				/* 拼接下一页 */
        				if(pageContent.currentPage == pageContent.totalPages){
        					pageBar+='<li class="disabled"><a href="#">下一页</a></li>';
        				}else{
        					pageBar+='<li><a href="#" onclick="changePage('+(pageContent.currentPage + 1)+')">下一页</a></li>';
        				}
        				$(".pagination").html(pageBar);
        			}else{
        				layer.mg(result.message, {time:1000,icon:5,shift:6});
        			}
        		},
        		errror : function(){
        			layer.mg("加载数据失败！", {time:1000,icon:5,shift:6});
        		}

        	});
        };
        $("#queryBtn").click(function(){
        	var condition = $("#usreCondition").val();
       		pageParams.condition = $.trim(condition);
       		queryUserPage(1);
        });
        function editUser(uid){
        	window.location.href = "${APP_PATH}/user/edit.html?id="+ uid;
        };
        function deleteUser(uid,loginacct){
        	layer.confirm("确认要删除["+loginacct+"]用户吗？",  {icon: 3, title:'提示'}, function(cindex){
			    layer.close(cindex);
	        	$.ajax({
	        		type : "POST",
	        		url : "${APP_PATH}/user/doDelete.do",
	        		data : {"id" : uid},
	        		beforeSend : function(){
	        			return true;
	        		},
	        		success : function(result){
	        			console.log(result);
	        			if(result.success){
	        				layer.msg("批量删除成功", {time:2000});
	        				queryUserPage(pageParams.currentPage);
	        			}else{
	        				layer.msg("批量删除用户失败！", {time:2000});
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
        		layer.mg("请选择要删除的用户！", {time:1000,icon:5,shift:6});
        	}else{
        		layer.confirm("确认要删除这"+ boxChecked.length +"个用户吗？",  {icon: 3, title:'提示'}, function(cindex){
    			    layer.close(cindex);
    			    var target = "";
    			    for(var i = 0; i < boxChecked.length; i++){
    			    	target += (i == 0) ? ("ids=" + boxChecked[i].name):("&ids=" + boxChecked[i].name) ;
    			    }
    	        	$.ajax({
    	        		type : "POST",
    	        		url : "${APP_PATH}/user/batchDelete.do",
    	        		data : target,
    	        		beforeSend : function(){
    	        			return true;
    	        		},
    	        		success : function(result){
    	        			console.log(result);
    	        			if(result.success){
    	        				queryUserPage(pageParams.currentPage);
    	        			}else{
    	        				layer.msg("批量删除用户失败！", {time:2000});
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