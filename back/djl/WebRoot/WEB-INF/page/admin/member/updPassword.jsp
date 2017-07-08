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
								<label class="layui-form-label">输入原密码</label>
								<div class="layui-input-block">
									<input type="password" name="oldPsword" 
										value="${v.quota}" placeholder="请输入原密码" required  lay-verify="required" autocomplete="off"
										class="layui-input">
								</div>
							</div>
							
							<div class="layui-form-item">
								<label class="layui-form-label">输入新密码</label>
								<div class="layui-input-block">
									<input type="password" name="newPsword" 
										value="${v.quota}" placeholder="请输入新密码" required  lay-verify="required" autocomplete="off"
										class="layui-input">
								</div>
							</div>
							<div class="layui-form-item">
								<label class="layui-form-label">再次确认新密码</label>
								<div class="layui-input-block">
									<input type="password" name="agPsword" 
										value="${v.quota}" placeholder="请再次输入新密码" required  lay-verify="required" autocomplete="off"
										class="layui-input">
								</div>
							</div>
							<div class="layui-form-item">
									<div class="layui-input-block">
										<button class="layui-btn" lay-submit lay-filter="form">立即提交</button>
										<button type="reset" class="layui-btn layui-btn-primary">重置</button>
									</div>
								</div>										
						</form>
						<script type="text/javascript">
							//Demo
							layui.use(['form'], function() {
								var form = layui.form();
								//监听提交
								form.on('submit(form)', function(data) {
									ajaxPOST("member/updPsword", {
										where:JSON.stringify(data.field)
									}, 
										function(data) {
											swalTip("提示", data.message, data.success,function(res){
												if(res){
													var index = parent.layer.getFrameIndex(window.name);
													parent.layer.close(index);													
													if(data.success){
														window.location.href = '${sysUrl}'+"login";
													}
												}
											});
									});
									return false;
								});
							});
						</script>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
