<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ReplySuccess</title>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jumpTo.js"></script>

</head>
<style>
	#title{
		border-bottom: 1px solid #c8dcec;
		width: 50%;
		text-align: left;
	}
	#right_big{
		position: relative;
		left: -180px;
		top: 40px;
	}
</style>
<center>
	<div style="margin-top: 5%;">
		<div id="title">
			<h3>论坛提示：</h3>
		</div>
		<span id="right_big">
			<img alt="" src="<%=request.getContextPath() %>/images/right_big.gif">
		</span>
		<h3>回复成功！
		<span id="jumpTo" style="color:orange;">
			5
		</span>
		&nbsp;秒后自动跳转到论坛首页</h3>
		<script type="text/javascript">
			countDown(5, '<%=request.getContextPath() %>/post_viewDetail.action?pid=<s:property value="%{#request.pid}"/>');
		</script>
		<h3><a href="/bbs/index.action">[若没有自动跳转，请点击这里]</a></h3>
	</div>
</center>