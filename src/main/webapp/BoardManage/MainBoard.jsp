<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="<%=request.getContextPath() %>/css/MainBoard.css" rel="stylesheet" type="text/css"/>
</head>

<s:debug></s:debug>
<center>
	<h2><s:a href="board_prepareAddBoard" action="board_prepareAddBoard" method="prepareAddBoard">添加版块</s:a></h2>
</center>
<div id="Lboard">
	<s:iterator value="rootBoards" var="row">
	<div class="btitle">
		<s:property value="#row.name" />
	</div>
	<div class="subBoard">
		<s:iterator value="#row.boards" var="sub">
		<ul>
			<li>
				<s:a href='login_showAll.action?bid=<s:property value="#sub.id"/>' action="login" method="showAll">
				<s:if test="#sub.boardImg!=null">
					<img alt="" src="<%=request.getContextPath() %>/upload/<s:property value="#sub.boardImg" />" 
						width="50" height="50" class="bimg">
				</s:if>
				<s:else>
					<img alt="" src="<%=request.getContextPath() %>/images/bimg.gif" width="50" height="50" class="bimg">
				</s:else>
					<strong><s:property value="#sub.name"/></strong>
					<em><a href='<%=request.getContextPath() %>/BoardManage/modigyBoardImg.jsp?bid=<s:property value="#sub.id"/>'>上传图片</a></em>
				</s:a>
			</li>
		</ul>
		</s:iterator>
	</div>
	<div class="clear"></div>
	</s:iterator>		
	<div style="clear: both;"></div>
</div>