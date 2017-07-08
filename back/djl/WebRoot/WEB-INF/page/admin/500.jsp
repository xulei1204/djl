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

<title>500</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="icon" href="front/images/iconn.ico" type="image/x-icon" />
<link href="css/animate.min.css" rel="stylesheet">
<link href="css/style.min862f.css?v=4.1.0" rel="stylesheet">

</head>

<body class="gray-bg">
	<div class="middle-box text-center animated fadeInDown">
		<h1>500</h1>
		<h3 class="font-bold">服务器内部错误</h3>

		<div class="error-desc">服务器好像出错了...</div>
	</div>
	<script src="js/jquery.min.js?v=2.1.4"></script>
	<script src="js/bootstrap.min.js?v=3.3.6"></script>
	<script type="text/javascript"
		src="http://tajs.qq.com/stats?sId=9051096" charset="UTF-8"></script>
</body>
</html>
