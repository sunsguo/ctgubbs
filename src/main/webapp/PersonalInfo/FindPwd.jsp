<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: gy
  Date: 2017/4/29
  Time: 22:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>FindPwd</title>
</head>
<body>
    <center>
        <h2>密码忘了?, 别着急，马上就可以找回了！</h2>
        <s:form action="student_sendPwd">
            <div style="margin-top: 20px">
                <font style="color: red"><s:fielderror value="student.stuNum"></s:fielderror></font>
                您的账号：<input type="text" name="student.stuNum">  <span><input type="submit" value="找回"></span>
            </div>
        </s:form>
    </center>
</body>
</html>
