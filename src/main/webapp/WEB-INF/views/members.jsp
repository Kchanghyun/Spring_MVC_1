<%@ page import="hello.servlet.domain.member.Member" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<a href="/index.html">메인</a>
<table>
  <thead>
  <th>id</th>
  <th>username</th>
  <th>age</th>
  </thead>
  <tbody>
  <c:forEach var="item" items="${members}">
    <tr>
      <td>${item.id}</td>
      <td>${item.name}</td>
      <td>${item.age}</td>
    </tr>
  </c:forEach>
<%--  <%--%>
<%--    for (Member member : ((List<Member>)request.getAttribute("members")) {--%>
<%--      out.write("<tr>");--%>
<%--      out.write("<td>" + member.get)--%>
<%--  }--%>
<%--  %>--%>
  </tbody>
</table>
</body>
</html>
