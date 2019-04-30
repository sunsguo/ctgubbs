<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="com.orm.Student" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>this is top page</title>
    <link href="css/Style.css" rel="stylesheet" type="text/css"/>
</head>
<body>
    <div id="wrap">
        <div id="Top">
           <div id="logo">
               <img alt="郑州轻工业学院" src="<%=request.getContextPath() %>/images/media.jpg" border="0" width="80	" height="80">
               <label style="font-size: 30px; font-weight: bold;">三峡大学Java论坛</label>
               <div id="logRegist">
                   <s:if test="#session.student!=null">
                    <span id="po">
                        <img alt=""
                             src="<%=request.getContextPath() %>/upload/<s:property value="#session.student.photoPath"/>"
                             style="float: right;width: 50px;height: 50px;"/>
                    </span>
                       <h4>
                           <s:a href="student_personalStuInfo.action" action="student_student_personalStuInfo"
                                method="student_personalStuInfo">
                               <s:property value="#session.student.nickName"/>
                           </s:a>欢迎你！
                       </h4>
                   </s:if>
                   <s:else>
                        <span id="po">
                            <img alt="" src="<%=request.getContextPath() %>/images/bbsPhoto.jpg"
                                 style="float: right;width: 50px;height: 50px;"/>
                        </span>
                       <h4>
                           <s:a href="student_personalStuInfo.action" action="student_personalStuInfo"
                                method="personalStuInfo">
                               <s:property value="#session.student.nickName"/>
                           </s:a>
                       </h4>
                   </s:else>
                   <%
                       if (request.getSession().getAttribute("admin") != null) {
                   %>
                   <h4><a href="#">管理员，</a>欢迎你！</h4>
                   <%
                       }
                       if (request.getSession().getAttribute("student") == null && request.getSession().getAttribute("admin") == null) {
                   %>
                   <h4>你好，请<a href="<%=request.getContextPath() %>/login.jsp">登 陆</a>
                       ||<a href='<%=request.getContextPath() %>/register.jsp'>注册</a></h4>
                   <%
                       }
                   %>
               </div>
           </div>
        </div>
        <div id="s_head">
        <div id="menu">
            <ul id="menu_left" style="">
                <li id="m_01">
                    <a id="a_01" href="<%=request.getContextPath() %>/index.action">首&nbsp;页</a>
                </li>
                <li class="menu_ge"></li>
                <li id="m_03">
                    <s:a href="post_viewPostByUser" action="post_viewPostByUser" method="viewPostByUser">我的帖子</s:a>
                </li>
                <s:if test="#session.student!=null">
                    <li class="menu_ge"></li>
                    <li id="m_04">
                        <s:a href="student_personalStuInfo" action="student_personalStuInfo"
                             method="personalStuInfo">个人资料</s:a>
                    </li>
                    <li class="menu_ge"></li>
                    <li id="m_07">
                        <%--为了不污染url,此处使用action转发--%>
                        <%--<a href="<%=request.getContextPath() %>/PersonalInfo/StuPswModify.jsp">修改密码</a>--%>
                            <a href="student_goToModigyPsw">修改密码</a>
                    </li>
                </s:if>
                <s:elseif test="#session.admin!=null">
                    <li class="menu_ge"></li>
                    <li id="m_04">
                        <s:a href="admin_personalAdminInfo" action="admin_personalAdminInfo"
                             method="personalAdminInfo">个人资料</s:a>
                    </li>

                    <li class="menu_ge"></li>
                    <li id="m_07">
                        <a href="post_post">帖子管理</a>
                    </li>
                    <li class="menu_ge"></li>
                    <li id="m_07">
                        <s:a href="board_loadRootBoards.action" action="board_loadRootBoards"
                             method="loadRootBoards">版块管理</s:a>
                    </li>
                    <li class="menu_ge"></li>
                    <li id="m_07">
                        <a href="#">系统维护</a>
                    </li>
                </s:elseif>
                <li id="m_08">
                    <s:a href="login_exit" action="login_exit" method="exit">退出</s:a>
                </li>
            </ul>
        </div>
    </div>
    </div>
</body>
</html>