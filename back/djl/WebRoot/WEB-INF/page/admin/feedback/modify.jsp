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
							<input type="hidden" name="memberId" value="${U_id}" class="layui-input">
						
							<div class="layui-form-item">
								<label class="layui-form-label">内容</label>
								<div class="layui-input-block">
									<textarea id="content" name="content" 
										 placeholder="请输入反馈内容"  autocomplete="off"
										class="layui-input layui-textarea"></textarea>
								</div>
							</div>
							<div class="layui-form-item">
								<div class="layui-input-block">
									<input type="file"  accept="image/jpg,image/png,image/jpeg" name="pic" lay-title="添加图片" class="layui-upload-file">
								</div>
								</div>
								<div class="layui-form-item" >
									<div class="layui-input-block" id="imgShow">
									</div>
								</div>
							<c:if test="${op ne 'see'}">
								<div class="layui-form-item">
									<div class="layui-input-block">
										<button class="layui-btn" lay-submit lay-filter="form">立即提交</button>
									</div>
								</div>
							</c:if>
						</form>
						<script type="text/javascript">
							var imgArr=[];
							//Demo
							layui.use(['form','upload'], function() {
							layui.upload({
								  url: sysUrl+'file/uploadImg',
								  ext: 'jpg|png|jpeg',
								  before: function(input){
								    //返回的参数item，即为当前的input DOM对象
								    console.log('文件上传中');
								  },success: function(res){
								  var img="<img src='"+res.data[0]+"' style='width:100px;height:100px;padding-left:10px'>"
								  	$('#imgShow').append(img);
								  	imgArr.push(res.data[0]);
								  	//$('#pic').attr("src",res.data[0]);
								  }
								}); 
								var form = layui.form();
								//监听提交
								form.on('submit(form)', function(data) {
									var content=$("#content").val();
									if(checkVals(content)){
										swalTip("提示","请输入反馈类容",false);
										return false;
									}
									data.field.img=imgArr;
									ajaxPOST("feedback/addFeedback", {
										where:JSON.stringify(data.field)
									}, 
										function(data) {
											swalTip("提示", data.message, data.success,function(res){
												if(res){
													var index = parent.layer.getFrameIndex(window.name);
													parent.layer.close(index);
													$("#content").val("");
													$("#imgShow").empty();
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
