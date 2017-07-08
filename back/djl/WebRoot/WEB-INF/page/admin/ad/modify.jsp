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
							<input type="hidden" name="video" id="video" value="${v.video}" class="layui-input">
							<input type="hidden" name="memberId" value="${admin.U_id}" class="layui-input">
							
						<div class="layui-form-item">
								<label class="layui-form-label">标题</label>
								<div class="layui-input-block">
									<input type="text"  name="title" <c:if test="${op eq 'see'}">disabled="disabled"</c:if>
										value="${v.title}" placeholder="请输入标题"  autocomplete="off"
										class="layui-input">
								</div>
							</div>							
							<div class="layui-form-item">
								<label class="layui-form-label">广告分类</label>
								<div class="layui-input-block">
									 <select name="classifyId" <c:if test="${op eq 'see'}">disabled="disabled"</c:if>>
     									 	<option value="">请选择分类</option>
     									 	<c:forEach items="${classify}" var="cla">     									 	
     									 	  <option <c:if test="${cla.id eq v.classifyId}">selected</c:if> value="${cla.id}">${cla.title}</option>
     									 	</c:forEach>
      									</select>  
								</div>
							</div>
							
								<div class="layui-form-item">
    							 <label class="layui-form-label">选择投放地点</label>
    								<div class="layui-input-block">
     									 <select name="Pid" lay-filter="pro" <c:if test="${op eq 'see'}">disabled="disabled"</c:if>>
     									 	<option value="">请选择省</option>
     									 	<c:forEach items="${proList}" var="pro">
     									 	  <option <c:if test="${pro.code eq v.Pid}">selected</c:if> value="${pro.code }">${pro.name}</option>
     									 	</c:forEach>
      									</select>      									
    								</div>
  									</div>
  								
  								<div class="layui-form-item">
    								<div class="layui-input-block" >
     									 <select name="Cid" id="city" <c:if test="${op eq 'see'}">disabled="disabled"</c:if>>
     										<c:choose>
     										<c:when test="${v.Cid eq ''}">
     									 	<option value="">请选择市</option>
     										</c:when>
     										<c:otherwise>
     									 	<option value="${v.Cid}">${v.cname}</option>
     										</c:otherwise>
     										</c:choose>
      									</select>      									
    								</div>
  								</div>
							
							
							<c:if test="${op ne 'see'}">
							<div class="layui-form-item">
									<label class="layui-form-label">封面</label>
									<div class="layui-input-block">
										<input type="file" lay-ext="jpg|jpeg|png" id="cover"  name="cover" >
									</div>
								</div>
							</c:if>
								<div class="layui-form-item">
									<label class="layui-form-label">封面</label>
									<div class="layui-input-block">
										<img alt="暂无图片" src="${v.cover}" id="pic" style="width: 200px;height: 200px">
									</div>
								</div>
								<c:if test="${op ne 'see'}">
								<div class="layui-form-item">
									<label class="layui-form-label">视频</label>
									<div class="layui-input-block" style="width:50%">
										<input type="file"  lay-title="请选择视频文件" lay-type="video" name="videoFile" id="videoFile" ><i id="videoSta" class="layui-icon" style="display:none;padding-left:10px;font-size: 16px; color: #59c95d;">&#xe605; 视频上传成功</i>  
									</div>
								</div>
								</c:if>
							
							<div class="layui-form-item">
								<label class="layui-form-label">内容</label>
								<div class="layui-input-block">
									<textarea id="content"  name="content" <c:if test="${op eq 'see'}">disabled="disabled"</c:if>
										 placeholder="请输入内容"  autocomplete="off"
										class="layui-input layui-textarea">${v.content}</textarea>
								</div>
							</div>
					
							<c:if test="${op ne 'see'}">
								<div class="layui-form-item">
									<div class="layui-input-block">
										<button class="layui-btn" lay-submit lay-filter="form">下一步</button>
									</div>
								</div>
							</c:if>
						</form>
						<script type="text/javascript">
							//Demo
							layui.use(['form','upload','layedit'], function() {
							
								var layedit = layui.layedit;
 					 		layedit.set({
 								uploadImage: {
    							url: sysUrl+'file/uploadImgByEdit', //接口url
    							type: 'post' //默认post
  										}
										}); 					 			
 						 			var index=layedit.build('content'); //建立编辑器
 						 			layedit.sync(index);//同步编辑器内容到textarea
									
								//上传图片的
								layui.upload({
								  elem:"#cover",
								  url: sysUrl+'file/uploadImg',								  
								  before: function(input){
								    //返回的参数item，即为当前的input DOM对象
								    console.log('文件上传中');
								  },success: function(res){
								  	$('#pic').attr("src",res.data[0]);
								  }
								}); 
								//上传视频文件
								layui.upload({
								  elem:"#videoFile",
								  url: sysUrl+'file/uploadVideo',								  
								  before: function(input){
			 					    //返回的参数item，即为当前的input DOM对象
								    console.log('文件上传中');
								  },success: function(res){
								  	if(res.code=="200"){
								  		console.log(res);
								  		$("#videoSta").css("display","inline-block");
										$('#video').val(res.data[0]);
								  	}
								  }
								}); 
								var form = layui.form();
								//监听下拉
								form.on('select(pro)', function(data){
								  ajaxPOST("china/cityList",{proId:data.value},function(data){
										var html = '<option value="">请选择</option>';
										$.each(data.data,function(i,n){
											html+='<option value="'+n.code+'">'+n.name+'</option>';
										});
								  		$("#city").html(html);
								  		form.render('select')
								  });
								}); 
								
								//监听提交
								form.on('submit(form)', function(data) {								
								data.field.cover = $('#pic').attr("src");
								data.field.content=layedit.getContent(index);
									if(checkVals(data.field.title)){
										swalTip("提示","请输入标题！",false);
										return false;
									}
									if(checkVals(data.field.cover)){
										swalTip("提示","请上传封面！",false);
										return false;
									}
									if(checkVals(data.field.classifyId)){
										swalTip("提示","请选择广告类目！",false);
										return false;
									}	
									if(checkVals(data.field.Pid)||checkVals(data.field.Cid)){
										swalTip("提示","请选择投放城市！",false);
										return false;
									}	
									
									ajaxPOST("ad/next", {
										where:JSON.stringify(data.field)
									}, 
										function(data) {
											var result=data.data;
											if(data.success&&${op ne 'modify'}&&result.is){											
											tableWin("设置红包","redpacket/setRedPacketIndex?ADID="+result.id);
											}else{								
											swalTip("提示", data.message, data.success,function(res){
												if(res){
													closeLayer();
												}
											});
											}
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
