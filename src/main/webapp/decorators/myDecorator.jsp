<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="d" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="p" uri="http://www.opensymphony.com/sitemesh/page" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- 替换被修饰的页面的标题 -->
<title><d:title default="装饰器页面"></d:title></title>

<link href="../css/myDecorator.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="../js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="../js/myDecorator.js" charset=UTF-8></script>

<!-- 被修饰的页面的head部分 -->
<d:head></d:head>

</head>
<body>
	<div id="outer"></div>
	<%-- <%@ include file="../top.jsp" %> --%>
	<jsp:include page="../top.jsp"></jsp:include>
	<div id="searchBar">
		<form action="post_searchPost.action" id="searchForm" style="float:left;">
			<span>
				<img style="position: relative; top: 10px;" alt="" src="<%=request.getContextPath() %>/images/search_1.png">
				<input id="searchKey" type="text" name="post.name" placeholder="搜索其实很简单">
				<input type="submit" value="搜索" style="
					border: none;
					background: url(images/search_btn.png) no-repeat;
					width: 45px;
					height: 25px;
					margin-left: 5px;
					text-align: left;
					text-indent: 10px;
					line-height: 30px;
					position: relative;
					top: 0px;
					cursor: pointer;
				" onclick="return checkForm()">
			</span>
		</form>
		<ul>
			<li>
				昨日帖子:<%=application.getAttribute("yesterdayNum") %>
				今日帖子:<%=application.getAttribute("todayNum") %>&nbsp;
				共:<s:property value="#application.total" />&nbsp;&nbsp;&nbsp;&nbsp;
			</li>
			<li id="time"></li>
			<script>
				show();
			</script>
		</ul>
	</div>
	<div id="main">
	<!-- 被修饰的页面的body部分 -->
		<d:body></d:body>
	</div>
	<hr>
	<div>
		<center>
			<%@ include file="../bottom.html" %>
		</center>
	</div>
</body>
</html>