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
<style>

</style>
</head>
<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeInRight">
		<div class="row">
			<div class="col-sm-12">
				<div class="ibox float-e-margins">
					<div class="ibox-content layui-form">
						<form class="layui-form" action="">
							<input type="hidden" name="id" value="${v.id}" class="layui-input">
							<input type="hidden" name="ADID" value="${ADID}" class="layui-input">
							
							<div class="layui-form-item">
								<label class="layui-form-label ">红包总金额</label>
								<div class="layui-input-block" >
									<input style="width:40%"  type="text" id="total" name="total" <c:if test="${op eq 'see'}">disabled="disabled"</c:if>
										value="${v.total}" onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}" onafterpaste="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}" onblur="changeRandomMoney(this)" placeholder="请设置红包金额" required  lay-verify="required" autocomplete="off"
										class="layui-input">元
								</div>
							</div>
							<blockquote class="layui-elem-quote">最大红包设置</blockquote>
							<div class="layui-form-item">
								<label class="layui-form-label">红包金额</label>
								<div class="layui-input-block" >
									<input style="width:40%" type="text" id="maximalMoney" name="maximalMoney" <c:if test="${op eq 'see'}">disabled="disabled"</c:if>
										value="${v.maximalMoney}" onkeyup="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')" onblur="changeRandomMoney(this)" placeholder="请设置最大红包金额" required  lay-verify="required" autocomplete="off"
										class="layui-input">元
								</div>
							</div>
							
							<div class="layui-form-item">
								<label class="layui-form-label ">红包数量</label>
								<div class="layui-input-block" >
									<input style="width:40%" type="text" id="maximalNum" name="maximalNum" <c:if test="${op eq 'see'}">disabled="disabled"</c:if>
										value="${v.maximalNum}" onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}" onafterpaste="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}" onblur="changeRandomMoney(this)" placeholder="请设置最大红包数量" required  lay-verify="required" autocomplete="off"
										class="layui-input">个
								</div>
							</div>
							
						
							<blockquote class="layui-elem-quote">次大红包设置</blockquote>
							<div class="layui-form-item">
								<label class="layui-form-label">红包金额</label>
								<div class="layui-input-block" >
									<input style="width:40%" type="text" id="secondMoney" name="secondMoney" <c:if test="${op eq 'see'}">disabled="disabled"</c:if>
										value="${v.secondMoney}" onkeyup="if(isNaN(value))execCommand('undo')" onblur="changeRandomMoney(this)" placeholder="请设置次大红包金额" required  lay-verify="required" autocomplete="off"
										class="layui-input">元
								</div>
							</div>
							
							<div class="layui-form-item">
								<label class="layui-form-label">红包数量</label>
								<div class="layui-input-block" >
									<input style="width:40%" type="text" id="secondNum" name="secondNum" <c:if test="${op eq 'see'}">disabled="disabled"</c:if>
										value="${v.secondNum}" onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}" onafterpaste="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}" onblur="changeRandomMoney(this)" placeholder="请设置次大红包数量" required  lay-verify="required" autocomplete="off"
										class="layui-input">个
								</div>
							</div>
													
							<blockquote class="layui-elem-quote">随机红包设置</blockquote>
							<div class="layui-form-item">
								<label class="layui-form-label">红包总金额</label>
								<div class="layui-input-block" >
									<input style="width:40%" type="text" id="randomMoney" name="randomMoney"  disabled="disabled"
										value="${v.randomMoney}" placeholder="请设置随机红包总金额" required  lay-verify="required" autocomplete="off"
										class="layui-input">元
								</div>
							</div>
							
							<div class="layui-form-item">
								<label class="layui-form-label">真实红包数量</label>
								<div class="layui-input-block" >
									<input style="width:40%" type="text" id="randomNum" name="randomNum" <c:if test="${op eq 'see'}">disabled="disabled"</c:if>
										value="${v.randomNum}" onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}" onafterpaste="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}" onblur="checkNum(this)" placeholder="请设置随机红包数量" required  lay-verify="required" autocomplete="off"
										class="layui-input">个
								</div>
							</div>

								<div class="layui-form-item">
								<label class="layui-form-label">红包平均金额</label>
								<div class="layui-input-block" >
									<input style="width:40%" type="text" id="randomAvg"  disabled="disabled"
										value="${v.randomAvg}" placeholder="" required  lay-verify="required" autocomplete="off"
										class="layui-input">元
								</div>
							</div>							
							<div class="layui-form-item">
								<label class="layui-form-label">空红包数量</label>
								<div class="layui-input-block" >
									<input style="width:40%" type="text" id="vacantNum" name="vacantNum" <c:if test="${op eq 'see'}">disabled="disabled"</c:if>
										value="${v.vacantNum}" onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}" onafterpaste="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}" onblur="checkNum(this)" placeholder="请设置空红包数量" required  lay-verify="required" autocomplete="off"
										class="layui-input">个
								</div>
							</div>


							<c:if test="${op ne 'see'}">
								<div class="layui-form-item">
									<div class="layui-input-block">
										<button class="layui-btn" lay-submit lay-filter="form">完成并发布</button>
									</div>
								</div>
							</c:if>
						</form>
						<script type="text/javascript">
							//修改随机红包金额
							function changeRandomMoney(obj){
								var total=filterNum($("#total").val());//红包总金额
								var maximalNum=filterNum($("#maximalNum").val());//最大红包数量
								var maximalMoney=filterNum($("#maximalMoney").val());//最大红包单个金额
								var secondNum=filterNum($("#secondNum").val());//次大红包数量
								var secondMoney=filterNum($("#secondMoney").val());//次大红包单个金额
								var randomMoney=total-(maximalNum*maximalMoney)-(secondNum*secondMoney);
								if(randomMoney<0){
								swalTip("警告","超出红包总金额！",false);
								$(obj).val("");
								return;
								}
								
								$("#randomMoney").val(randomMoney.toFixed(2));
								$("#maximalMoney").val(maximalMoney.toFixed(2));
								$("#secondMoney").val(secondMoney.toFixed(2));
								countAvg();
							}
							
							//过滤数字
							function filterNum(vars){
								if(vars==""||vars==undefined||vars==null||isNaN(vars)){
									return 0.00;
								}
								return parseFloat(vars);
							}
							
							//计算平均金额
							function countAvg(){
							var randomMoney=filterNum($("#randomMoney").val());//随机红包金额
							var randomNum=filterNum($("#randomNum").val());//真实红包数量
							if(randomNum==0)return;							
							var avg=isNaN(randomMoney/randomNum)?0:randomMoney/randomNum;						
							$("#randomAvg").val(avg.toFixed(2));
							}
							
							//限制真实红包数量
							function checkNum(obj){
							var randomNum=filterNum($("#randomNum").val());//真实红包数量
							var vacantNum=filterNum($("#vacantNum").val());//空红包数量
							if(randomNum/(randomNum+vacantNum)<0.3){
							swalTip("警告","真实红包必≥30！",false);
							$(obj).val("");
							return ;
							}							
							countAvg();
							}
							//Demo
							layui.use(['form'], function() {							
								var form = layui.form();						
								//监听提交
								form.on('submit(form)', function(data) {
								console.log(data);
									ajaxPOST("redpacket/publish", {
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
