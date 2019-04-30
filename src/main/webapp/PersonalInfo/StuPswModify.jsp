<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%--<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<base href="<%=basePath %>">--%>
<!DOCTYPE >

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>StuPswModify</title>
	<link href="../css/Style.css" rel="stylesheet" type="text/css"/>
</head>


<body>
	<center>
		<h1>请定期维护您的账户信息！</h1>
		<s:debug></s:debug>
		<h4><s:actionmessage/></h4>
		<s:form action="student_modifyPsw">
			<s:password name="psw" label="原始密码"></s:password>
			<s:textfield name="newPsw" label="新密码"></s:textfield>
			<s:textfield name="confirmPsw" label="确认密码"></s:textfield>
			<s:submit value="修改"></s:submit>
		</s:form>
	</center>
</body>