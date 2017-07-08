<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
</head>

<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeInRight">
		<div class="ibox float-e-margins">
			<div class="ibox-content">
				<div class="row row-lg">
					<!-- Basic Columns -->
					<div class="col-sm-12">
						<!-- Example Basic Columns -->
						<div class="example-wrap" style="height: 76%;">
							<div class="example">
								<div id="toolbar" class="btn-group">
									<form class="form-inline" id="searchForm">
										<button type="button" class="btn btn-white" style="margin-bottom: 0px;" onclick="tableWin('添加','ad/showAdd')">
											<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
										</button>
										<div class="form-group">
										    <div class="input-group">
										      <input class="form-control" name="title" 
										      		 type="text" placeholder="请输入标题">
										    </div>
										</div>
																				
										<button type="button" class="btn btn-white" style="margin-bottom: 0px;" onclick="tableSearch()">
											<span class="glyphicon glyphicon-search" aria-hidden="true"></span>搜索
										</button>
									</form>
								</div>
								<table id="table" model-name="ad"></table>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
	
		var columns = [ 
		{
			field : 'title',
			title : '标题',
			align : 'center'
		},{
			field : 'cover',
			title : '封面',
			align : 'center',
			formatter: function(value,row,index){
                                    return '<img  src="'+value+'" style="width:200px;height:200px" >';
                                }
		},{
			field : 'location',
			title : '定位信息',
			align : 'center'
		},{
			field : 'classifyId',
			title : '分类/专区',
			align : 'center'
		},{
			field : 'addTime',
			title : '发布时间',
			align : 'center'
		},{
			field : 'id',
			title : '评论管理',
			align : 'center',
			formatter: function(value,row,index){
               return '<a onclick="commentList(\''
				+ value
				+ '\')">查看</a> ';
                                }
		},{
			field : 'id',
			title : '操作',
			align : 'center',
			formatter : function(value, row, index) {
			var v="";
			  v+="<a onclick='tableWin(\"查看\",\"ad/showAdd?id="+row.id+"&op=see\")' title='查看'><i class='glyphicon glyphicon-eye-open'></i></a> ";
			 if(${admin.U_id ne null}){
			
			  v+="<a onclick='tableWin(\"修改\",\"ad/showAdd?id="+row.id+"&op=modify\")' title='修改'><i class='glyphicon glyphicon-pencil'></i></a> ";				
			  v+="<a onclick='refresPublish(\""+row.id+"\")' title='刷新发布时间'><i class='glyphicon glyphicon-repeat'></i></a> ";
				}
				return v+operator(false, false, true, value);
			}
		} ];
		tableInitPanel(columns,"ad/showList");
		//刷新发布
		var refresPublish=function(id){
				ajaxPOST("ad/refresPublish",{id:id},
				function(data) {			
					swalTip("提示", data.message, data.success,function(res){
						if(res){
							refresh();
						}
						swal.close();
					});
				});
		} 
	</script>

</body>
</html>
