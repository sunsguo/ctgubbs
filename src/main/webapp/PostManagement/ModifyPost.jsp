<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ModifyPost</title>
<script type="text/javascript" src="<%=request.getContextPath() %>/ckeditor/ckeditor.js"></script>
<style type="text/css">

</style>
</head>

<s:debug></s:debug>
<center>
<s:url var="modifyPost" action="post_modifyPost.action">
	<%-- <s:param name="pid"><%=request.getParameter("pid") %></s:param> --%>
</s:url>
<s:form action="%{modifyPost}" method="modifyPost">
	<table>
		<tr style="display: none;">
			<td><b>帖子id：</b></td>
			<td><input name="pid" value="<%=request.getParameter("pid") %>" size="50"></td>			
		</tr>
		<tr>
			<td><b>帖子标题：</b></td>
			<td><input name="post.name" value="${post.name}" size="50"></td>			
		</tr>
		<tr>
			<td valign="top"><b>帖子内容：</b></td>
			<td><textarea id="content" name="post.content">
				<s:property value="post.content"/>
			</textarea>		</td>
		</tr>
		<tr>
			<td></td>
			<td><input type="submit" value="发表" /></td>
		</tr>
	</table>
</s:form>
<script type="text/javascript">
	CKEDITOR.replace('content',{
		filebrowserImageUploadUrl: 'uploadImg.action',
		filebrowserImageBrowserUrl: 'showImage.jsp?type=image',
		toolbar: 'Full',
		width: '700',
		height: '400',
		filebrowserWindowWidth: 700,
		fileborwserWindowHeight: 400
	});
</script>
</center>