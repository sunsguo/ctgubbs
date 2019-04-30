<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>adminPersonalInfo</title>
<style type="text/css">
	table{
		border-collapse: collapse;
	}
	th, td{
		height: 29px;
	}
	#profile_act{
		margin-left: 5%;
	}
	#profile_act >li{
		height: 20px;
	}
	#profile_act >li >a{
		padding-left: 20px;
	}
	#silder{
		float: left;
	}
	#info{
		float: left;
		margin-left: 5%; 
		width: 300px;
	}
	#modifyInfo{
		float: left;
		padding-left: 3% ;
		width: 400px;
		margin-top: 100px;
	}
	#container{
		float: left;
	}
</style>

<script type="text/javascript">
	function checkForm(){
		if(document.getElementById('nick').value == null){
			document.getElementById('msg').value = "昵称不能为空！";
			return false;
		}else{
			return true;
		}
	}

</script>
</head>

<s:debug></s:debug>
<div style="margin-left: 2%;">
	<div id="container">
		<div id="slider">
		<s:if test="admin.photoPath!=null">
				<img alt="" src="<%=request.getContextPath() %>/upload/<s:property value="admin.photoPath"/>"
						style="width: 200px;height: 200px;" />
		</s:if>
		<s:else>
			<img alt="" src="<%=request.getContextPath() %>/images/bbsPhoto.jpg" 
						style="width: 200px;height: 200px;" />
		</s:else>
		<br>
		<ul id="profile_act">
			<li><a href="#" style="background: url(<%=request.getContextPath() %>/images/pmto.gif) no-repeat;">发短消息</a></li>
			<li><a href="#" style="background: url(<%=request.getContextPath() %>/images/addbuddy.gif) no-repeat;">加为好友</a></li>
			<li><a href="#" style="background: url(<%=request.getContextPath() %>/images/fastreply.gif) no-repeat;">搜索帖子</a></li>
			<li><a href="#" style="background: url(<%=request.getContextPath() %>/images/home.gif) no-repeat;">个人空间</a></li>
		</ul>
	</div>
	<div id="info">
		<table>
			<tr>
				<th>昵称：
				<td><s:property value="admin.nickName"/>
			</tr>
			<tr>
				<th>姓名：
				<td><s:property value="admin.name"/>
			</tr>
		</table>
	</div>
	</div>
	<div id="modifyInfo">
		信息修改
		<s:form action="admin_modifyAdminInfo" method="modifyAdminInfo" onsubmit="return checkForm()">
			<s:textfield id="nick" name="admin.nickName" value="%{admin.nickName}" label="昵称" required="true"></s:textfield>
			<s:textfield name="admin.qq" value="%{admin.qq}" label="QQ"></s:textfield>
			<s:textfield name="admin.email" value="%{admin.email}" label="邮箱"></s:textfield>
			<em id="msg" style="color: red;"></em>
			<s:submit value="提交" onclick="return checkModify()"></s:submit>
		</s:form>
		<h3><a href="<%=request.getContextPath() %>/upload.jsp">上传图片</a></h3>
	</div>
	<div style="clear: both;"></div>
</div>
