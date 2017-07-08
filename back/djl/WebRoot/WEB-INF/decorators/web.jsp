<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"
	prefix="decorator"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
<head>
<title><decorator:title default="系统管理后台" /></title>

<decorator:head />

<link rel="shortcut icon" href="favicon.ico">
<link href="css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
<link href="css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
<link href="css/animate.min.css" rel="stylesheet">
<link href="css/plugins/bootstrap-table/bootstrap-table.min.css"
	rel="stylesheet">	
<link href="css/plugins/sweetalert/sweetalert.css" rel="stylesheet">
<link href="css/style.min862f.css?v=4.1.0" rel="stylesheet">
 
</head>
<body class="gray-bg">
	
<script src="js/jquery.min.js?v=2.1.4"></script>
<script src="js/plugins/layer/layer.min.js"></script>
<script src="js/bootstrap.min.js?v=3.3.6"></script>
<script src="js/plugins/bootstrap-table/bootstrap-table.js"></script>
<script src="js/plugins/sweetalert/sweetalert.min.js"></script>
<script type="text/javascript" src="js/SysCommon.js" charset="UTF-8"></script>

<script>
	var sysUrl = '${sysUrl}';
</script>
<decorator:body/>
</body>
</html>
