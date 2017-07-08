<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>

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
								<div id="toolbar" class="btn-group">
									<form class="form-inline" id="searchForm">
								<div class="form-group">
										    <div class="input-group">
										      <input class="form-control" name="username" 
										      		 type="text" placeholder="请输入用户名">
										    </div>
										</div>
							
										<div class="form-group">
										    <div class="input-group">
										      <input class="form-control" name="tel" 
										      		 type="text" placeholder="请输入手机号码">
										    </div>
										</div>
										
										<div class="form-group">
										    <div class="input-group">
										      <input class="form-control" name="nickname" 
										      		 type="text" placeholder="请输入昵称/公司名称">
										    </div>
										</div>
										
										<button type="button" class="btn btn-white" style="margin-bottom: 0px;" onclick="tableSearch()">
											<span class="glyphicon glyphicon-search" aria-hidden="true"></span>搜索
										</button>
										<div class="layui-form"> 									
   										<label class="layui-form-label" style="width:120px">开启注册审核</label>
      									<input lay-filter="final" type="checkbox"  name="switch"  lay-skin="switch"<c:if test="${finalSet.value eq 1}"> checked </c:if>>
      									
										</div>
									</form>
								</div>
								<table id="table" model-name="member"></table>
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
			field : 'username',
			title : '用户名',
			align : 'center'
		},{
			field : 'password',
			title : '密码',
			align : 'center'
		},{
			field : 'money',
			title : '余额',
			align : 'center'
		},{
			field : 'tel',
			title : '手机号码',
			align : 'center'
		},{
			field : 'nickname',
			title : '昵称/公司名称',
			align : 'center'
		},{
			field : 'pic',
			title : '头像',
			align : 'center',
			formatter: function(value,row,index){
                                    return '<img  src="'+value+'" style="width:200px;height:200px" >';
                                }
		},{
			field : 'U_id',
			title : '操作',
			align : 'center',
			formatter : function(value, row, index) {
				console.log(value)
				var add="<a onclick='memberCheck(\""+value+"\")' title='审核'><i class='glyphicon glyphicon-check'></i></a>";				
				return operator(true, false, false,value,row.type)+add;
			}
		}];
		tableInitPanel(columns,"member/showCheckList");
		
		//Demo
		layui.use('form', function() {
		var form = layui.form();
			//监听提交
			form.on('switch(final)', function(data) {
			var value=data.elem.checked?"1":"0";
			var json={id:${finalSet.id},value:value}
			ajaxPOST("modify/finalset", {where:JSON.stringify(json)},function(res){
			console.log(res);
			});
			});
			});
	
			
	</script>

</body>
</html>
