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
							<input type="hidden" name="roleMenuId" value="${v.roleMenuId}" class="layui-input">
							
							<div class="layui-form-item">
								<label class="layui-form-label"></label>
								<div class="layui-input-block">
									<input type="text" name="roleMenuId" <c:if test="${op eq 'see'}">disabled="disabled"</c:if>
										value="${v.roleMenuId}" placeholder="请输入名称" required  lay-verify="required" autocomplete="off"
										class="layui-input">
								</div>
							</div>
							
							<div class="layui-form-item">
								<label class="layui-form-label">菜单ID</label>
								<div class="layui-input-block">
									<input type="text" name="menuId" <c:if test="${op eq 'see'}">disabled="disabled"</c:if>
										value="${v.menuId}" placeholder="请输入菜单ID名称" required  lay-verify="required" autocomplete="off"
										class="layui-input">
								</div>
							</div>
							
							<div class="layui-form-item">
								<label class="layui-form-label"></label>
								<div class="layui-input-block">
									<input type="text" name="roleId" <c:if test="${op eq 'see'}">disabled="disabled"</c:if>
										value="${v.roleId}" placeholder="请输入名称" required  lay-verify="required" autocomplete="off"
										class="layui-input">
								</div>
							</div>
							
							<div class="layui-form-item">
								<label class="layui-form-label">添加时间</label>
								<div class="layui-input-block">
									<input type="text" name="addTime" <c:if test="${op eq 'see'}">disabled="disabled"</c:if>
										value="${v.addTime}" placeholder="请输入添加时间名称" required  lay-verify="required" autocomplete="off"
										class="layui-input">
								</div>
							</div>
							
							<div class="layui-form-item">
								<label class="layui-form-label">更新时间</label>
								<div class="layui-input-block">
									<input type="text" name="updTime" <c:if test="${op eq 'see'}">disabled="disabled"</c:if>
										value="${v.updTime}" placeholder="请输入更新时间名称" required  lay-verify="required" autocomplete="off"
										class="layui-input">
								</div>
							</div>
							
							<div class="layui-form-item">
								<label class="layui-form-label">-1 删除 0 未删除</label>
								<div class="layui-input-block">
									<input type="text" name="isDel" <c:if test="${op eq 'see'}">disabled="disabled"</c:if>
										value="${v.isDel}" placeholder="请输入-1 删除 0 未删除名称" required  lay-verify="required" autocomplete="off"
										class="layui-input">
								</div>
							</div>
							
							<c:if test="${op ne 'see'}">
								<div class="layui-form-item">
									<div class="layui-input-block">
										<button class="layui-btn" lay-submit lay-filter="form">立即提交</button>
										<button type="reset" class="layui-btn layui-btn-primary">重置</button>
									</div>
								</div>
							</c:if>
						</form>
						<script type="text/javascript">
							//Demo
							layui.use('form', function() {
								var form = layui.form();
								//监听提交
								form.on('submit(form)', function(data) {
									ajaxPOST("modify/roleMenu", {
										where:JSON.stringify(data.field)
									}, 
										function(data) {
											swalTip("提示", data.message, data.success,function(res){
												if(res){
													closeLayer();
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
