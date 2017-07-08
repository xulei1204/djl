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
<link rel="stylesheet" href="plugins/layui/css/layui.css">
<script type="text/javascript" src="plugins/layui/layui.js"></script>
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
								
							
<div class="layui-tab layui-tab-brief" lay-filter="dataTab">
  <ul class="layui-tab-title">
    <li class="layui-this" >全部</li>
    <li >充值明细</li>
    <li >消费明细</li>

  </ul>
</div>      
      

		<table id="table" model-name="consume"></table>
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
			field : 'type',
			title : '消费途径',
			align : 'center'
		},{
			field : 'money',
			title : '金额',
			align : 'center'
		},{
			field : 'genre',
			title : '类型',
			align : 'center'
		},{
			field : 'addTime',
			title : '记录时间',
			align : 'center'
		} ];
		tableInitPanel(columns,"consume/showList");
		
		layui.use('element', function(){
  		var element = layui.element();
 		//监听选项卡 
 		 element.on('tab(dataTab)', function(data){
 		 	
			$('#table').bootstrapTable('refresh',{query:{type:data.index}});
   			
  		});
		});	
	</script>

</body>
</html>
