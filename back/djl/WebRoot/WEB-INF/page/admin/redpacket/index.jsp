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
										      <input class="form-control" name="ADID" 
										      		 type="text" placeholder="请输入广告id">
										    </div>
										</div>
										
										<div class="form-group">
										    <div class="input-group">
										      <input class="form-control" name="memeberId" 
										      		 type="text" placeholder="请输入用户id">
										    </div>
										</div>
										
										<div class="form-group">
										    <div class="input-group">
										      <input class="form-control" name="total" 
										      		 type="text" placeholder="请输入固定红包金额">
										    </div>
										</div>
										
										<div class="form-group">
										    <div class="input-group">
										      <input class="form-control" name="max" 
										      		 type="text" placeholder="请输入最大红包数量">
										    </div>
										</div>
										
										<div class="form-group">
										    <div class="input-group">
										      <input class="form-control" name="second" 
										      		 type="text" placeholder="请输入次大红包数量">
										    </div>
										</div>
										
										<div class="form-group">
										    <div class="input-group">
										      <input class="form-control" name="maxMoney" 
										      		 type="text" placeholder="请输入最大红包金额">
										    </div>
										</div>
										
										<div class="form-group">
										    <div class="input-group">
										      <input class="form-control" name="secondMoney" 
										      		 type="text" placeholder="请输入次大红包金额">
										    </div>
										</div>
										
										<div class="form-group">
										    <div class="input-group">
										      <input class="form-control" name="random" 
										      		 type="text" placeholder="请输入随机红包数量">
										    </div>
										</div>
										
										<div class="form-group">
										    <div class="input-group">
										      <input class="form-control" name="empty" 
										      		 type="text" placeholder="请输入空红包数量">
										    </div>
										</div>
										
										<div class="form-group">
										    <div class="input-group">
										      <input class="form-control" name="randomMoney" 
										      		 type="text" placeholder="请输入随机红包总金额">
										    </div>
										</div>
										
										<button type="button" class="btn btn-white" style="margin-bottom: 0px;" onclick="tableSearch()">
											<span class="glyphicon glyphicon-search" aria-hidden="true"></span>搜索
										</button>
									</form>
								</div>
								<table id="table" model-name="redpacket"></table>
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
			field : 'ADID',
			title : '广告id',
			align : 'center'
		},{
			field : 'memeberId',
			title : '用户id',
			align : 'center'
		},{
			field : 'total',
			title : '固定红包金额',
			align : 'center'
		},{
			field : 'max',
			title : '最大红包数量',
			align : 'center'
		},{
			field : 'second',
			title : '次大红包数量',
			align : 'center'
		},{
			field : 'maxMoney',
			title : '最大红包金额',
			align : 'center'
		},{
			field : 'secondMoney',
			title : '次大红包金额',
			align : 'center'
		},{
			field : 'random',
			title : '随机红包数量',
			align : 'center'
		},{
			field : 'empty',
			title : '空红包数量',
			align : 'center'
		},{
			field : 'randomMoney',
			title : '随机红包总金额',
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
