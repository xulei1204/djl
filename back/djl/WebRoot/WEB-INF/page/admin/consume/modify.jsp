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
							<input type="hidden" name="id" value="${v.id}" class="layui-input">
							
							<div class="layui-form-item">
								<label class="layui-form-label"></label>
								<div class="layui-input-block">
									<input type="text" name="id" <c:if test="${op eq 'see'}">disabled="disabled"</c:if>
										value="${v.id}" placeholder="请输入名称" required  lay-verify="required" autocomplete="off"
										class="layui-input">
								</div>
							</div>
							
							<div class="layui-form-item">
								<label class="layui-form-label">消费途径：1.充值 2.发布广告（红包）</label>
								<div class="layui-input-block">
									<input type="text" name="type" <c:if test="${op eq 'see'}">disabled="disabled"</c:if>
										value="${v.type}" placeholder="请输入消费途径：1.充值 2.发布广告（红包）名称" required  lay-verify="required" autocomplete="off"
										class="layui-input">
								</div>
							</div>
							
							<div class="layui-form-item">
								<label class="layui-form-label">金额</label>
								<div class="layui-input-block">
									<input type="text" name="money" <c:if test="${op eq 'see'}">disabled="disabled"</c:if>
										value="${v.money}" placeholder="请输入金额名称" required  lay-verify="required" autocomplete="off"
										class="layui-input">
								</div>
							</div>
							
							<div class="layui-form-item">
								<label class="layui-form-label">类型 1.获得 2，支出</label>
								<div class="layui-input-block">
									<input type="text" name="genre" <c:if test="${op eq 'see'}">disabled="disabled"</c:if>
										value="${v.genre}" placeholder="请输入类型 1.获得 2，支出名称" required  lay-verify="required" autocomplete="off"
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
									ajaxPOST("modify/consume", {
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
