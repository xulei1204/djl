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
		<div class="row">
			<div class="col-sm-12">
				<div class="ibox float-e-margins">
					<div class="ibox-content layui-form">
						<form class="layui-form" action="">
							<input type="hidden" name="U_id" value="${v.U_id}" class="layui-input">					
							<div class="layui-form-item">
								<label class="layui-form-label">用户名</label>
								<div class="layui-input-block">
									<input type="text" name="username" <c:if test="${op eq 'see'}">disabled="disabled"</c:if>
										value="${v.username}" placeholder="请输入用户名名称" required  lay-verify="required" autocomplete="off"
										class="layui-input">
								</div>
							</div>
							<div class="layui-form-item">
								<label class="layui-form-label">头像</label>
								<div class="layui-input-block">
									<img src="${v.pic}" style="width:200px;height:200px">
								</div>
							</div>
							
							<div class="layui-form-item">
								<label class="layui-form-label">手机号码</label>
								<div class="layui-input-block">
									<input type="text" name="tel" <c:if test="${op eq 'see'}">disabled="disabled"</c:if>
										value="${v.tel}" placeholder="请输入手机号码名称" required  lay-verify="required" autocomplete="off"
										class="layui-input">
								</div>
							</div>							
							<div class="layui-form-item">
								<label class="layui-form-label">昵称/公司名称</label>
								<div class="layui-input-block">
									<input type="text" name="nickname" <c:if test="${op eq 'see'}">disabled="disabled"</c:if>
										value="${v.nickname}" placeholder="请输入昵称/公司名称名称" required  lay-verify="required" autocomplete="off"
										class="layui-input">
								</div>
							</div>
							<c:if test="${param.type eq 2}">
							<div class="layui-form-item">
								<label class="layui-form-label">业主审核资料</label>
								<div class="layui-input-block">
									<img src="${v.idcardA}" style="width:300px;height:200px">
									<img src="${v.idcardB}" style="width:300px;height:200px">
									<img src="${v.license}" style="width:300px;height:200px">
								</div>
							</div>
							
							<div class="layui-form-item">
								<label class="layui-form-label">每日消费限额</label>
								<div class="layui-input-block">
									<input type="text" name="quota" <c:if test="${op eq 'see'}">disabled="disabled"</c:if>
										value="${v.quota}" placeholder="" required  lay-verify="required" autocomplete="off"
										class="layui-input">
								</div>
							</div>
							</c:if>
													
							<div class="layui-form-item">
								<label class="layui-form-label">账户余额</label>
								<div class="layui-input-block">
									<input type="text" name="money" <c:if test="${op eq 'see'}">disabled="disabled"</c:if>
										value="${v.money}" placeholder="请输入余额名称" required  lay-verify="required" autocomplete="off"
										class="layui-input">
								</div>
							</div>
							
						</form>
						
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
