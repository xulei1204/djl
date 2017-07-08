<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML >
<html>
<head>
<base href="<%=basePath%>">
<title>系统管理后台</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="icon" href="front/images/iconn.ico" type="image/x-icon" />
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
<link href="css/animate.min.css" rel="stylesheet">
<link href="css/style.min.css" rel="stylesheet">
<link href="css/login.min.css" rel="stylesheet">
<script src="js/jquery.min.js?v=2.1.4"></script>
<script>
	if (window.top !== window.self) {
		window.top.location = window.location
	};
	function login() {
		$.post('${sysUrl}'+"login", $("#form").serialize(), function(data) {
			if (data.success) {
				window.location.href = '${sysUrl}'+"index";
			} else {
				alert(data.message);
			}
		});

	}
</script>

</head>

<body class="signin">
	<div class="signinpanel">
		<div class="row">
		
			<div class="col-sm-7"></div>
			<div class="col-sm-5">
				<form id="form" onsubmit="return false;">
					<h4 class="no-margins">登录：</h4>
					<p class="m-t-md">登录到系统管理后台</p>
					<input type="text" class="form-control uname" name="userName"
						placeholder="用户名" /> <input type="password"
						class="form-control pword m-b" name="passWord" placeholder="密码" />
					<button class="btn btn-success btn-block" onclick="login()">登录</button>
				</form>
			</div>
		</div>
		<div class="signup-footer">
			<div class="pull-left"><%
					Calendar calendar = Calendar.getInstance();
					 %>
					&copy; 2007-<%out.print(calendar.get(Calendar.YEAR)); %> <a href="http://www.xiaheng.net/" target="_blank">夏恒网络</a></div> 
		</div>
	</div>
</body>
</html>
