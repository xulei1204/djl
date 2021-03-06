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
								<button type="button" class="btn btn-white" onclick="tableAdd()">
									<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
								</button>
							</div>
								<table id="table" model-name="role"></table>
								
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
	
		var columns = [ {
			checkbox : true,
		}, 
		{
			field : 'roleName',
			title : '角色名称',
			align : 'center'
		},
		{
			field : 'roleId',
			title : '菜单权限管理',
			align : 'center',
			formatter : function(value, row, index) {
				return '<a onclick="setPerm(\''
				+ value
				+ '\')">管理</a> ';
			}
		},
		
		{
			field : 'roleId',
			title : '操作',
			align : 'center',
			formatter : function(value, row, index) {
				return operator(true, value!="2",  value!="2", value);
			}
		} ];
		tableInitPanel(columns);
	</script>

</body>
</html>
