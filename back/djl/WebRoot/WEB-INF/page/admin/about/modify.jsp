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
							
							
							<c:if test="${!(param.type eq 1)}">
							<div class="layui-form-item" >
								<label class="layui-form-label">标题</label>
								<div class="layui-input-block">
									<input type="text" name="title" <c:if test="${op eq 'see'}">disabled="disabled"</c:if>
										value="${v.title}" placeholder="请输入标题名称" required  lay-verify="required" autocomplete="off"
										class="layui-input">
								</div>
							</div>
							</c:if>
							<div class="layui-form-item">
								<label class="layui-form-label">内容</label>
								<div class="layui-input-block">
									<textarea  id="content"  name="content" <c:if test="${op eq 'see'}">disabled="disabled"</c:if>
										  autocomplete="off"
										class="layui-input layui-textarea" >${v.content}</textarea>
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
							layui.use(['form','layedit'], function() {
							
								var layedit = layui.layedit;
 					 			layedit.set({
 								uploadImage: {
    							url: sysUrl+'file/uploadImgByEdit', //接口url
    							type: 'post' //默认post
  										}
										}); 					 			
 						 			var index=layedit.build('content'); //建立编辑器
 						 			layedit.sync(index);//同步编辑器内容到textarea
							
								var form = layui.form();
								//监听提交
								form.on('submit(form)', function(data) {
									data.field.type=${param.type};
								data.field.content=layedit.getContent(index);
									ajaxPOST("modify/about", {
										
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
