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
								</div>
								<table id="table" model-name="lottoRecord"></table>
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
			field : 'nickname',
			title : '用户昵称',
			align : 'center'
		},{
			field : 'tel',
			title : '用户手机号',
			align : 'center'
		},{
			field : 'time',
			title : '参与时间',
			align : 'center',
		},{
			field : 'status',
			title : '中奖状态',
			align : 'center',
			formatter : function(value, row, index) {
				if(value==1){
				return "<a title='已中奖'><i class='glyphicon glyphicon-star'></i></a>";
				}
				
			}
		},{
			field : 'id',
			title : '操作',
			align : 'center',
			formatter : function(value, row, index) {
				return "<a title='排除' onclick='tableDel(\""+value+"\")' ><i class='glyphicon glyphicon-remove'></i></a>";;
			}
		} ];
		tableInitPanel(columns,"lottoRecord/joinMember",{id:'${id}'});
	</script>

</body>
</html>
