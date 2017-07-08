<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
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
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link rel="icon" href="front/images/iconn.ico" type="image/x-icon" />
<title>系统管理后台</title>
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="shortcut icon" href="favicon.ico">
<link href="css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
<link href="css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
<link href="css/animate.min.css" rel="stylesheet">
<link href="css/style.min862f.css?v=4.1.0" rel="stylesheet">
</head>

<body class="fixed-sidebar full-height-layout gray-bg"
	style="overflow:hidden">
	<div id="wrapper">
		<!--左侧导航开始-->
		<nav class="navbar-default navbar-static-side" role="navigation">
		<div class="nav-close">
			<i class="fa fa-times-circle"></i>
		</div>
		<div class="sidebar-collapse">
			<ul class="nav" id="side-menu">
				<li class="nav-header">
					<div class="dropdown profile-element">
						<span><img alt="image" class="img-circle"
							src="img/profile_small.jpg" /></span> <a data-toggle="dropdown"
							class="dropdown-toggle" href="#"> <span class="clear">
								<span class="block m-t-xs"><strong class="font-bold">${admin.userName}</strong></span>
								<span class="text-muted text-xs block">${admin.roleName}<b
									class="caret"></b></span>
						</span>
						</a>
						<ul class="dropdown-menu animated fadeInRight m-t-xs">
							<li><a href="${sysUrl}logout">安全退出</a></li>
						</ul>
					</div>
				</li>
				<%-- <c:forEach items="${menuList}" var="v">
					<li><c:if test="${v.pMenuId eq 0 }">
							<a href="#"><i class="fa fa-table"></i> <span
								class="nav-label">${v.menuName }</span><span class="fa arrow"></span></a>
						</c:if> <c:if test="${v.pMenuId ne 0}">
							<ul class="nav nav-second-level">
								<c:forEach items="${menuList }" var="c">
									<c:if test="${c.pMenuId eq v.id }">
										<li><a class="J_menuItem" href="${c.menuUrl }">${c.menuName }</a></li>
									</c:if>
								</c:forEach>
							</ul>
						</c:if></li>
				</c:forEach> --%>
				<c:forEach items="${menuList}" var="v">
					<li>
						<c:if test="${v.pMenuId eq '0'}">
							<a href="#"> <i class="fa fa-table"></i> <span
								class="nav-label">${v.menuName }</span><span class="fa arrow"></span>
							</a>
						</c:if>
						<ul class="nav nav-second-level">
								<c:forEach items="${menuList }" var="c">
									<c:if test="${c.pMenuId eq v.menuId }">
										<li><a class="J_menuItem" href="${sysUrl}${c.menuUrl}">${c.menuName}</a>
										</li>
									</c:if>
								</c:forEach>
							
						</ul></li>
				</c:forEach>
			</ul>
		</div>
		</nav>
		<!--左侧导航结束-->
		<!--右侧部分开始-->
		<div id="page-wrapper" class="gray-bg dashbard-1">

			<div class="row content-tabs">
				<button class="roll-nav roll-left J_tabLeft">
					<i class="fa fa-backward"></i>
				</button>
				<nav class="page-tabs J_menuTabs">
				<div class="page-tabs-content">
					<a href="javascript:;" class="active J_menuTab"
						data-id="index_v1.html">首页</a>
				</div>
				</nav>
				<button class="roll-nav roll-right J_tabRight">
					<i class="fa fa-forward"></i>
				</button>
				<div class="btn-group roll-nav roll-right">
					<button class="dropdown J_tabClose" data-toggle="dropdown">
						关闭操作<span class="caret"></span>

					</button>
					<ul role="menu" class="dropdown-menu dropdown-menu-right">
						<li class="J_tabShowActive"><a>定位当前选项卡</a></li>
						<li class="divider"></li>
						<li class="J_tabCloseAll"><a>关闭全部选项卡</a></li>
						<li class="J_tabCloseOther"><a>关闭其他选项卡</a></li>
					</ul>
				</div>
				<a href="${sysUrl }loginout" class="roll-nav roll-right J_tabExit"><i
					class="fa fa fa-sign-out"></i> 退出</a>
			</div>
			<div class="row J_mainContent" id="content-main">
				<iframe class="J_iframe" name="iframe0" width="100%" height="100%"
					src="" frameborder="0" data-id="index_v1.html" seamless></iframe>
			</div>
			<div class="footer">
				 <div class="pull-right">
				 	<%
					Calendar calendar = Calendar.getInstance();
					 %>
					&copy; 2007-<%out.print(calendar.get(Calendar.YEAR)); %>
					
					
					 <a href="http://www.xiaheng.net/" target="_blank">夏恒网络</a>
				</div> 
			</div>
		</div>
	</div>
	<script src="js/jquery.min.js?v=2.1.4"></script>
	<script src="js/bootstrap.min.js?v=3.3.6"></script>
	<script src="js/plugins/metisMenu/jquery.metisMenu.js"></script>
	<script src="js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
	<script src="js/plugins/layer/layer.min.js"></script>
	<script src="js/hplus.min.js?v=4.1.0"></script>
	<script type="text/javascript" src="js/contabs.min.js"></script>
	<script src="js/plugins/pace/pace.min.js"></script>
</body>
</html>