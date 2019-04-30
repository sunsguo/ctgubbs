<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>    
<!DOCTYPE html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>postManage</title>
<style type="text/css">
	#postManage_img{
		display: inline-block;
		margin: 4px 5px 0 0;		
	}
</style>
<script type="text/javascript">
	function confirmDel(){
		if(!confirm("你确定要删除吗，次操作为级联删除，且不可恢复！")){
			return false;
		}
		return true;
	}
</script>
</head>

<center>
	<s:if test="#request.result=='Mok'">
		<h4 style="color: red;">修改成功，您可以继续操作....</h4>
	</s:if>
	<s:elseif test="#request.result=='Dok'">
		<h4 style="color: red;">删除成功，您可以继续操作....</h4>
	</s:elseif>
	<div>
	<!-- 帖子列表 -->
	</div>
	<table style="width: 98%;">
		<tr style="background-color: #e7efef;">
			<th style="width: 10px;">
			<th style="text-align: left;">帖子标题</th>
			<th>作者</th>
			<th>回复/点击</th>
			<th>发布时间</th>
			<th>可选操作</th>
		</tr>
		<s:iterator var="row" value="list" status="st">
		<tr>
			<td><img id="postManage_img" alt="" src="<%=request.getContextPath() %>/images/folder_new.gif">
			<s:url var="url_viewDetail" action="post_viewDetail">
				<s:param name="pid"><s:property value="#row.id"/></s:param>
			</s:url> 
			<td style="text-align: left;"><s:a href='%{url_viewDetail}'><s:property value="#row.name"/></s:a>
			<!-- <s:property value="name"/> 也可以 -->
			<td><s:property value="#row.getStudent().getNickName()"/>
			<td><s:property value="#row.getReplies().size()"/>/<s:property value="#row.count"/>
			<td><s:date name="#row.publishTime" format="yyyy-mm-dd hh:mm:ss" />
			<td>
			<s:url var="url_prepareModify" action="post_prepareModify">
				<s:param name="pid"><s:property value="#row.id"/></s:param>
			</s:url> 
			<s:a href='%{url_prepareModify}' action="post_prepareModify" method="prepareModify">修改</s:a>
			<s:url var="url_deletePost" action="post_deletePost">
				<s:param name="pid"><s:property value="#row.id"/></s:param>
			</s:url>
			|<s:a href='%{url_deletePost}' method="deletePost" onclick="return confirmDel()">删除</s:a>
		</tr>
		</s:iterator>
	</table>
</center>
<!-- 生成分页 -->
<div style="padding-left: 30px; margin-top: 20px;">
	<img alt="" src="<%=request.getContextPath() %>/images/pn_post.png" style="cursor: pointer;" 
		onclick="javascript: location.href='<%=request.getContextPath() %>/post_preparePost.action?bid=<s:property value="%{#request.bid}"/>'">
</div>
<center>
	<div id="displayPagination">
		<script type="text/javascript">
			var pg = new showPages('pg');
			var total = <s:property value="count"/> ;
			var pageSize =  <s:property value="pageSize"/> ;
			if(total % pageSize == 0){
				pg.pageCount = total/pageSize;
			}
			pg.pageCount = total/pageSize + 1;	//定义总页数
			pg.printHtml(2);
			pg.printHtml(5);
		</script>
	</div>
</center>














