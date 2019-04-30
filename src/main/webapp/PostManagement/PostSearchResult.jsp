<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>PostSearchResult</title>
<style type="text/css">
	#postSearch_img{
		display: inline-block;
		margin: 4px 5px 0 0;
	}
	#search_table{
		/* border-collapse: collapse; */
		text-align: center;
		width: 98%;
	}
	#search_table th{
		
	}
	#search_table tr, td{
		border: 1px solid silver;
		line-height: 20px;
	}
	#search_table th, tr, td{
		border-left: none;
		border-right: none;
		padding-bottom: 5px;
	}
	#search_table th, tr, td:HOVER{
		background: #f0f0f0;
	}
</style>
</head>

<s:debug></s:debug>
<s:property value="post.name"/>
<h1>Hello world!</h1>
<center>
<table id="search_table">
		<tr style="background-color: #e7efef;">
			<th style="text-align: left;">帖子标题</th>
			<th>作者</th>
			<th>回复/点击</th>
			<th>发布时间</th>
		</tr>
		<s:iterator var="row" value="list" status="st">
		<tr>
			<s:url action="post_viewDetail" var="url_search">
				<s:param name="pid"><s:property value="#row.id"/></s:param>
			</s:url>
			<td style="text-align: left;">
			<img id="postSearch_img" alt="" src="<%=request.getContextPath() %>/images/folder_new.gif">
			<s:a href='%{url_search}'><s:property value="#row.name"/></s:a>
			<!-- <s:property value="name"/> 也可以 -->
			<td><s:property value="#row.getStudent().getNickName()"/>
			<td><s:property value="#row.getReplies().size()"/>/<s:property value="#row.count"/>
			<td><s:date name="#row.publishTime" format="yyyy-mm-dd hh:mm:ss" />
		</tr>
		</s:iterator>
	</table>
</center>