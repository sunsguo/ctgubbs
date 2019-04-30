<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
	<link href="css/login.css" rel="stylesheet" type="text/css"/>
	<script type="text/javascript" src="js/login.js"></script>
</head>

<center>
	<h3>Sorry, 您还未登陆，请登录：</h3>
	<div id="tabs">
		<ul>
			<li id="tabS" style="background: #2fb4d6; margin-right: 5px;"><b>学生登陆</b></li>
			<li id="tabA"><b>管理员登陆</b></li>
		</ul>
	</div>
	<div id="tabContent">
		<div id="stuLoginF" style="display: block;">
			<s:form action="login_stuLogin" method="stuLogin">
				<s:textfield name="student.stuNum" label="学号" cssClass="outerBorder"></s:textfield>
				<s:password name="student.password" label="密码" cssClass="outerBorder"></s:password>
				<s:submit value=" " cssClass="loginBtn" />
			</s:form>
			<s:actionmessage/>
		</div>
		<div id="adminLoginF" style="display: none;">
			<s:form action="login_adminLogin" method="adminLogin" >
				<s:textfield name="admin.account" label="账号" cssClass="outerBorder"></s:textfield>
				<s:password name="admin.password" label="密码" cssClass="outerBorder"></s:password>
				<s:submit value=" " cssClass="loginBtn" />
			</s:form>
			<s:actionmessage/>
		</div>
		<div>
			忘记密码?&nbsp|&nbsp<a href="student_findPwd">找回密码</a>
		</div>
	</div>
</center>


