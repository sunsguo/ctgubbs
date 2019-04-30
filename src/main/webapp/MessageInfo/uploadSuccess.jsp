<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>uploadSuccess</title>
</head>

<center>
	<h3>文件上传成功</h3>
	<hr>
	<img alt="" src="<%=request.getContextPath() %>/upload/<s:property value="docFileName"/>">
	<br>
	<s:property value="docFileName"/>
</center>