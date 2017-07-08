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
							
								<table id="table" model-name="menu"></table>
								
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
	
		var columns = [ {
			checkbox : true
		}, 
		{
			field : 'menuName',
			title : '菜单名称',
			align : 'center',
			
		},
				
		{
			field : 'count',
			title : '操作',
			align : 'center',
			events:{
				'click .perm':function(e,value, row, index){
					if(value=="0"){
						var json={};
						json.roleId='${id}';
						json.menuId=row.menuId;
						ajaxPOST("role/addPerm",json,function(data){
							swalTip("提示", data.message, data.success,function(res){
														if(res){
														row.count='1';
														$('#table').bootstrapTable('updateRow', {index:index,row:row});
														}
													});
						});
					}else{
						var json={};
						json.id=row.roleMenuId;
						ajaxPOST("role/delPerm",json,function(data){
						
							swalTip("提示", data.message, data.success,function(res){
														if(res){
														row.count='0';
														$('#table').bootstrapTable('updateRow', {index:index,row:row});
														} 
													});
						});
					}
				}
			},
			formatter : function(value, row, index) {
			
				if(value=='0'){ 
				return '<a class="perm">添加权限</a>';	
				}else{
				return '<a class="perm">取消权限</a>';
				}
				
			}
		} ];
		tableInitPanel(columns,'role/showPermMenu',function(para){
			para.id ='${id}';
			return para;
		});
		
		
	</script>

</body>
</html>
