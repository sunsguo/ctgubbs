<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ViewPostDetail</title>
<link href="<%=request.getContextPath() %>/css/viewPostDetail.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath() %>/ckeditor/ckeditor.js"></script>
</head>

<div id="nav">
当前位置：&nbsp;<a href="index.action">首页</a>
&nbsp;>><s:property value="%{post.board.name}"/>
&nbsp;>><s:property value="%{post.name}"/>
</div>
<!-- 帖子主题 -->
<center>
<div id="pmain">
<table>
	<th>作者</th>
	<th>正文</th>
	<tr>
		<td class="left_td" valign="top">
		<br>
		<s:if test="%{post.student.photoPath!=null}">
		<img id="myImg" alt="" src='<%=request.getContextPath() %>/upload/<s:property value="%{post.student.photoPath}"/>'>
		</s:if>
		<s:else>
		<img alt="" src="<%=request.getContextPath() %>/images/bbsPhoto.jpg">
		</s:else>
		<br>
		<ul>
			<s:if test="#session.student!=null">
			<li><h5><p>昵称：<s:property value="%{post.getStudent().nickName}" /></p></h5><li>
			<li><h5><p>专业：<s:property value="%{post.getStudent().major}" /></p></h5><li>
			<li><h5><p>班级：<s:property value="%{post.getStudent().className}" /></p></h5><li>
			</s:if>
			<s:elseif test="#session.admin!=null">	<!-- 把感叹号打掉了，坑 -->
			<li><h5><p>管理员</p></h5><li>
			<li><h5><p>姓名：<s:property value="%{post.getAdmin.name}" /></p></h5><li>
			</s:elseif>
		</ul>
		</td>
		<td class="right_td">
			<h5>发帖时间：<s:date name="post.publishTime" format="yyyy-mm-dd hh:mm:ss" /></h5>
			<s:property value="post.content" escapeHtml="false"/>	<!-- 不对内容进行转义 -->
			<s:if test="#session.admin!=null">			
			<s:url var="url_prepareModify" action="post_prepareModify">
				<s:param name="pid"><s:property value="post.id"/></s:param>
			</s:url> 
			<s:a href='%{url_prepareModify}' action="post_prepareModify" method="prepareModify">修改</s:a>
			<%-- <s:url var="url_deletePost" action="post_deletePost">
				<s:param name="pid"><s:property value="#row.id"/></s:param>
			</s:url> --%>
			|<s:a href='#' method="deletePost" onclick="return confirmDel()">删除</s:a>
			</s:if>
		</td>
	</tr>
	<s:iterator value="replies" var="row" status="st">
	<tr>
		<td class="left_td" valign="top">
		<br>
		<s:if test="#row.getStudent().photoPath!=null">
		<img id="myImg" alt="" src='<%=request.getContextPath() %>/upload/<s:property value="#row.student.photoPath"/>'>
		</s:if>
		<s:else>
		<img alt="" src="<%=request.getContextPath() %>/images/bbsPhoto.jpg">
		</s:else>
		<br>
		<h5><p>昵称：<s:property value="#row.student.nickName" /></p></h5>
		<h5><p>专业：<s:property value="#row.student.major" /></p></h5>
		<h5><p>班级：<s:property value="#row.student.className" /></p></h5>			
		</td>
		<td class="right_td">
		<h5>发帖时间：<s:date name="#row.publishTime" format="yyyy-mm-dd hh:mm:ss" /></h5>
		<s:property value="#row.content" escapeHtml="false" />
		</td>
	</tr>
	</s:iterator>
</table>
<div id="fastReply">
	
	<s:url var="test" action="reply_stuReply.action">
		<s:param name="pid"><s:property value="post.id"/></s:param>
	</s:url>
	<!-- 用post方式提交表单就不会覆盖action中的参数 -->
	<s:form action="%{test}" method="post">
		<tr>
			<td style="width: 19.5%;" valign="middle" align="center">
				<s:if test="#session.admin==null">
				<img id="myImg" alt="" src='<%=request.getContextPath() %>/upload/<s:property value="%{student.photoPath}"/>'>
				</s:if>
				<h2>回复：</h2>
			</td>
			<td><textarea id="content" rows="" cols="" name="reply.content"></textarea>
		</tr>
		<tr>
			<td></td>
			<td><input type="submit" value="回复" id="reply"></td>
		</tr>
	</s:form>
	<script type="text/javascript">

	CKEDITOR.replace('content',{
		filebrowserImageUploadUrl: 'uploadImg.action',
		filebrowserImageBrowserUrl: 'showImage.jsp?type=image',
		toolbar: 'Full',
		width: '100%',
		height: '50%',
		filebrowserWindowWidth: 700,
		fileborwserWindowHeight: 400
	});
</script>
</div>
</div>
</center>