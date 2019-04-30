<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>add board</title>
<style type="text/css">
	.addBoard{
		line-height: 20px;
	}
	select .addBoard{
		size: 20;
		height: 25px;
		
	}
	li {
		margin: 10px;
	}
</style>
</head>

<center>
	<h1>添加版块</h1>
	<s:form action="board_addBoard" method="addBoard">
		<ul>
			<li>上级版块:
			<select name="board.id" class="addBoard">
				<option value="-1">无上级版块</option>
				<s:iterator value="rootBoards" var="row">
				<option value='<s:property value="#row.id"/>'>
					<s:property value="#row.name"/>
				</option>
				</s:iterator>
			</select>
			</li>
			<li>
			<s:textfield name="board.name" label="版块名称" class="addBoard" />
			</li>
			<li>
			<s:textfield name="board.description" label="版块描述" class="addBoard" />
			</li>
			<li>
			<s:submit value="确定"></s:submit>
			</li>
		</ul>
	</s:form>
</center>