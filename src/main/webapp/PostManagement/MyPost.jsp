<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>myPost</title>
<style type="text/css">
	#postManage_img{
		display: inline-block;
		margin: 4px 5px 0 0;		
	}
	#myPost{
		border-collapse: collapse;
		text-align: center;
		width: 98%;
	}
	#myPost tr, td{
		border: 1px solid silver;
		line-height: 20px;
	}
	#myPost th, tr, td{
		border-left: none;
		border-right: none;
		padding-bottom: 5px;
	}
	#myPost th, tr, td:HOVER{
		background: #f0f0f0;
	}
</style>
</head>

<h3 style="padding-left: 20px;">
<s:property value="session.student.nickName"/>
你好，欢迎查看你发布过的帖子：</h3>
<center>
<!-- 帖子列表 -->
<table id="myPost">
	<tr style="background: #e7efef;">
		<th style="width: 10px;">
		<th style="text-align: left;">帖子标题</th>
		<th>作者</th>
		<th>查看</th>
		<th>发布时间</th>
	</tr>

	<s:iterator value="myPosts" var="row" status="st">
	<tr>
		<td><img id="postManage_img" alt="" src="<%=request.getContextPath() %>/images/folder_new.gif">
		<s:url var="url_viewDetail" action="post_viewDetail">
			<s:param name="pid"><s:property value="#row.id"/></s:param>
		</s:url> 
		<td style="text-align: left;"><s:a href='%{url_viewDetail}'><s:property value="#row.name"/></s:a></td>
		<!-- <s:property value="name"/> 也可以 -->
		<td><s:property value="#row.getStudent().getNickName()"/></td>
		<td><s:property value="#row.count"/></td>
		<%-- <s:date name="#row.publishTime" format="yyyy-mm-dd hh:mm:ss" /> --%>
		<td><s:property value="#row.publishTime"/></td>
		</tr>
	</s:iterator>
</table>
</center>