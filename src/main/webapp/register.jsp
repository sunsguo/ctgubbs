<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>register page</title>
<style type="text/css">
	#wrap_register{
		margin: 0 auto;
		width: 300px;
		border: 1px solid pink;
		font-family: 宋体 ;
		font-size: 14px;
		border-radius: 15px 15px 0 0;
		box-shadow: 15px 2px 4px #808080;
	}
	#wrap_register td{
		padding-top: 10px;
	}
</style>
<script type="text/javascript">

	function checkStu(thisfiled){	
		var show_info = document.getElementById('show_info');
		if(thisfiled.value == "" || thisfiled.value == null){
			show_info.innerHTML = "学号不能为空！";
		}else{
			show_info.innerHTML = "";
		}
	}
	function checkPass(thisfiled){
		var show_info = document.getElementById('show_info');
		var password = document.getElementById('password');
		if(password.value != thisfiled.value){
			show_info.innerHTML = "两次密码不一致！";
		}else{
			show_info.innerHTML = "";
		}
	}
</script>
</head>

<center>
	<div id="wrap_register">
		<s:form action="register" method="post" onsubmit="return chcek()">
		<h2 style="background: #0080ff; width: 80px;">欢迎注册！</h2>
			<em id="show_info" style="color: red"></em>
			<s:fielderror fieldName="stuNum"></s:fielderror>
			<s:textfield name="student.stuNum" label="学号" onblur="checkStu(this)"></s:textfield>
			<s:password id="password" name="student.password" label="密码"></s:password>
			<s:password name="confirm" label="确认密码" onblur="checkPass(this)"></s:password>
			<s:textfield name="student.realName" label="真实姓名"></s:textfield>
			<s:textfield name="student.nickName" label="昵称"></s:textfield>
			<s:textfield name="student.qq" label="qq"></s:textfield>
			<s:textfield type="email"  name="student.email" label="email"></s:textfield>
			<s:textfield name="student.major" label="专业"></s:textfield>
			<s:textfield name="student.className" label="班级"></s:textfield>
			<s:submit style="cursor: pointer; margin-left: 30px;" value="注册"></s:submit>
		</s:form>
	</div>
</center>