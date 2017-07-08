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
								<div class="form-group">
										    <div class="input-group">
										      <input class="form-control" name="username" 
										      		 type="text" placeholder="请输入用户名">
										    </div>
										</div>
						<div class="form-group">
										    <div class="input-group">
										      <input class="form-control" name="nickname" 
										      		 type="text" placeholder="请输入昵称/公司名称">
										    </div>
										</div>
										<input type="hidden" name="type" value="${para.type}">
										<button type="button" class="btn btn-white" style="margin-bottom: 0px;" onclick="tableSearch()">
											<span class="glyphicon glyphicon-search" aria-hidden="true"></span>搜索
										</button>
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
		}]
		//业主端才有的充值平台收益比例
		if(${para.type eq 2}){
			var ratio={
			field : 'ratio',
			title : '充值平台收益',
			align : 'center',
			formatter: function(value,row,index){
                                    return '<input id="'+row.U_id+'"  value="'+value+'" class="form-control" type="text" style="width:50px" >%<button style="margin-left:10px" class="layui-btn layui-btn-radius layui-btn-primary" onclick="modifyRatio(\''+row.U_id+'\')">修改</button>';
                                }
		};
			columns.push(ratio);
			}
		
		var columns2=[{
			field : 'U_id',
			title : '操作',
			align : 'center',
			formatter : function(value, row, index){
				return operator(true, false, false,value,row.type);
			}
		}];
		tableInitPanel(columns.concat(columns2));
	</script>

</body>
</html>
