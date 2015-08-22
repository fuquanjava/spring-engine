<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page pageEncoding="UTF-8" %>
<html>
<head>
    <title>hello</title>
</head>
<body>
    <%
        String name = (String) request.getSession().getAttribute("name");
        response.getWriter().write("名字:"+name);
    %>
</body>
</html>
