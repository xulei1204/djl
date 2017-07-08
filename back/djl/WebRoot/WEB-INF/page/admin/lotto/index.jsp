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
											<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>设置本月抽奖
										</button>							
					   				
									</form>
								</div>
								<table id="table" model-name="lotto"></table>
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
			field : 'money',
			title : '奖金',
			align : 'center'
		},{
			field : 'month',
			title : '期数（月份）',
			align : 'center'
		},{
			field : 'id',
			title : '参与会员',
			align : 'center',
			formatter : function(value, row, index) {
				return  '<a onclick="seeJoin(\''
				+ value
				+ '\')">查看</a> ';
			}
		},{
			field : 'statusStr',
			title : '开奖状态',
			align : 'center'
		},{
			field : 'id',
			title : '操作',
			align : 'center',
			formatter : function(value, row, index) {
				var v="";
				if(row.status==1){
				v+="<a title='开奖' onclick='openLotto(\""+value+"\")'><i class='glyphicon glyphicon-gift'></i></a>  ";
				}
				return v+operator(false, true, true, value);
			}
		} ];
		tableInitPanel(columns,"lotto/showList");
	</script>

</body>
</html>
