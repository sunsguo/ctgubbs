<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>AddPost</title>
<script type="text/javascript" src="<%=request.getContextPath() %>/ckeditor/ckeditor.js"></script>
</head>

<center>
	<s:url var="addPost" action="post_addPost">
		<s:param name="bid"><%=request.getParameter("bid") %></s:param>
	</s:url>
	<s:form action="%{addPost}" method="post">
	 	<tr>
	 		<td><b>帖子标题：</b></td>
	 		<td><input type="text" name="post.name" size="50"></td>
	 	</tr>
	 	<tr>
	 		<td valign="top"><b>帖子内容：</b></td>
	 		<td><textarea id="content" name="post.content"></textarea>
	 	</tr>
	 	<tr>
	 		<td></td>
	 		<td><input type="submit" value="发表">
	 	</tr>
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