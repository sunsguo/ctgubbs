<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>modigyBoardImg</title>
</head>


<center>
	<h2>版块图标上传</h2>
	<!-- 封装url -->
	<s:url var="b" action="upload_boardImgUpload" >
		<s:param name="bid"><%=request.getParameter("bid")%>
		</s:param>
	</s:url>
	<s:form action="%{b}" enctype="multipart/form-data">
		<s:file name="doc" label="选择上传文件" />
		<s:submit value="上传" />
	</s:form>
</center>
