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
										      		 type="text" placeholder="请输入">
										    </div>
										</div>
										
										<div class="form-group">
										    <div class="input-group">
										      <input class="form-control" name="money" 
										      		 type="text" placeholder="请输入金额">
										    </div>
										</div>
										
										<div class="form-group">
										    <div class="input-group">
										      <input class="form-control" name="superiorId" 
										      		 type="text" placeholder="请输入上级id">
										    </div>
										</div>
										
										<div class="form-group">
										    <div class="input-group">
										      <input class="form-control" name="subordinateId" 
										      		 type="text" placeholder="请输入下级id">
										    </div>
										</div>
										
										<div class="form-group">
										    <div class="input-group">
										      <input class="form-control" name="dividend" 
										      		 type="text" placeholder="请输入提成金额">
										    </div>
										</div>
										
										<div class="form-group">
										    <div class="input-group">
										      <input class="form-control" name="ratio" 
										      		 type="text" placeholder="请输入提成比例">
										    </div>
										</div>
										
										<div class="form-group">
										    <div class="input-group">
										      <input class="form-control" name="type" 
										      		 type="text" placeholder="请输入1.一级提成  2.二级提成">
										    </div>
										</div>
										
										<div class="form-group">
										    <div class="input-group">
										      <input class="form-control" name="isDel" 
										      		 type="text" placeholder="请输入">
										    </div>
										</div>
										
										<div class="form-group">
										    <div class="input-group">
										      <input class="form-control" name="addTime" 
										      		 type="text" placeholder="请输入">
										    </div>
										</div>
										
										<button type="button" class="btn btn-white" style="margin-bottom: 0px;" onclick="tableSearch()">
											<span class="glyphicon glyphicon-search" aria-hidden="true"></span>搜索
										</button>
									</form>
								</div>
								<table id="table" model-name="dividend"></table>
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
			title : '',
			align : 'center'
		},{
			field : 'money',
			title : '金额',
			align : 'center'
		},{
			field : 'superiorId',
			title : '上级id',
			align : 'center'
		},{
			field : 'subordinateId',
			title : '下级id',
			align : 'center'
		},{
			field : 'dividend',
			title : '提成金额',
			align : 'center'
		},{
			field : 'ratio',
			title : '提成比例',
			align : 'center'
		},{
			field : 'type',
			title : '1.一级提成  2.二级提成',
			align : 'center'
		},{
			field : 'isDel',
			title : '',
			align : 'center'
		},{
			field : 'addTime',
			title : '',
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
