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
										<button type="button" class="btn btn-white" style="margin-bottom: 0px;" onclick="tableAdd()">
											<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
										</button>
										
					   					<div class="form-group">
										    <div class="input-group">
										      <input class="form-control" name="id" 
										      		 type="text" placeholder="请输入我的地盘/id">
										    </div>
										</div>
										
										<div class="form-group">
										    <div class="input-group">
										      <input class="form-control" name="tel" 
										      		 type="text" placeholder="请输入电话">
										    </div>
										</div>
										
										<div class="form-group">
										    <div class="input-group">
										      <input class="form-control" name="linkman" 
										      		 type="text" placeholder="请输入联系人">
										    </div>
										</div>
										
										<div class="form-group">
										    <div class="input-group">
										      <input class="form-control" name="e-mail" 
										      		 type="text" placeholder="请输入邮箱">
										    </div>
										</div>
										
										<div class="form-group">
										    <div class="input-group">
										      <input class="form-control" name="address" 
										      		 type="text" placeholder="请输入地址">
										    </div>
										</div>
										
										<div class="form-group">
										    <div class="input-group">
										      <input class="form-control" name="intro" 
										      		 type="text" placeholder="请输入简介">
										    </div>
										</div>
										
										<button type="button" class="btn btn-white" style="margin-bottom: 0px;" onclick="tableSearch()">
											<span class="glyphicon glyphicon-search" aria-hidden="true"></span>搜索
										</button>
									</form>
								</div>
								<table id="table" model-name="myspace"></table>
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
			field : 'id',
			title : '我的地盘/id',
			align : 'center'
		},{
			field : 'tel',
			title : '电话',
			align : 'center'
		},{
			field : 'linkman',
			title : '联系人',
			align : 'center'
		},{
			field : 'e-mail',
			title : '邮箱',
			align : 'center'
		},{
			field : 'address',
			title : '地址',
			align : 'center'
		},{
			field : 'intro',
			title : '简介',
			align : 'center'
		},{
			field : 'id',
			title : '操作',
			align : 'center',
			formatter : function(value, row, index) {
				return operator(true, true, true, value);
			}
		} ];
		tableInitPanel(columns);
	</script>

</body>
</html>
