<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>郑州轻工业学院</title>
<link href="<%=request.getContextPath() %>/css/index.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath() %>/js/index.js"></script>
</head>

<body>
	<s:set var="total" value="total" scope="application"></s:set>
	<s:set var="yesterdayNum" value="yesterdayNum" scope="application"></s:set>
	<s:set var="todayNum" value="todayNum" scope="application"></s:set>
	<s:set var="student" value="student" scope="application"></s:set>
	<div id="banner">
		<div id="banner_bg"></div>
		<!-- 标题背景 -->
		<div id="banner_info"></div>
		<!-- 标题 -->
		<ul>
			<li class="on">1</li>
			<li>2</li>
			<li>3</li>
			<li>4</li>
		</ul>
		<div id="banner_list">
			<a href="#" target="_blank"> <img alt="郑州轻工业学院" title="郑州轻工业学院"
				src="images/imgs/10794203.jpg">
			</a> <a href="#" target="_blank"> <img alt="橡树小木屋的blog"
				title="橡树小木屋的blog" src="images/imgs/p5.jpg">
			</a> <a href="#" target="_blank"> <img alt="橡树小木屋的blog"
				title="橡树小木屋的blog" src="images/imgs/p3.jpg">
			</a> <a href="#" target="_blank"> <img alt="橡树小木屋的blog"
				title="橡树小木屋的blog" src="images/imgs/p4.jpg">
			</a>
		</div>
	</div>
	<div id="rank">
		<h3 style="color: red;">帖子排行榜：</h3>
		<ul>
			<s:iterator value="hotPosts" var="row" status="st">
				<s:url var="url_viewDetail" action="post_viewDetail" >
							<s:param name="pid"><s:property value="#row.id"/></s:param>
				</s:url>
				<li><s:a
						href='%{url_viewDetail}'
						action="post_viewDetail" method="viewDetail">
						<s:property value="#row.name" />&nbsp;&nbsp;
					</s:a> 【点击量<s:property value="#row.count" />】</li>
			</s:iterator>
		</ul>
	</div>
	<div class="clear"></div>
	<div id="Lboard">
		<s:iterator value="rootBoard" var="row">
			<div class="btitle">
				<s:property value="#row.name" />
			</div>
			<div class="subBoad">
				<ul>
					<s:iterator value="#row.boards" var="sub">
					<s:url var="url_showAll" action="login_showAll">
						<s:param name="bid"><s:property value="#sub.id"/></s:param>
					</s:url>
						<li><s:a
								href="%{url_showAll}"
								action="login_showAll" method="showAll">

								<s:if test="#sub.boardImg!=null">
									<img class="bimg" width="60" height="60"
										src="<%=request.getContextPath() %>/upload/<s:property value="#sub.boardImg"/>"
										alt="">
								</s:if>
								<s:else>
									<img class="bimg" width="60" height="60"
										src="<%=request.getContextPath() %>/images/bimg.gif" />
								</s:else>
								<h4>
									<s:property value="#sub.name" />
								</h4>
							</s:a></li>
					</s:iterator>
				</ul>
			</div>
			<div class="clear"></div>
		</s:iterator>
	</div>
	<div id="btitle">友情链接</div>
	<div class="subBoad">
		<ul>
			<li><a href="http://www.zzuli.edu.cn/">郑州轻工业学院</a></li>
			<li><a href="http://www.tup.tsinqhua.edu.cn/">清华大学出版社</a></li>
		</ul>
	</div>
</body>
</html>