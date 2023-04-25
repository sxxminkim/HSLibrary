<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>무릉서원</title>
</head>
<body>

<h2>무릉서원 회원 삭제 페이지입니다.</h2>
<table>
    <thead>
    <tr>
        <th></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="memberDTO" items="${memberList}">
        <tr>
            <td></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>