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
<jsp:include page="navbar.jsp"></jsp:include>
<h2>무릉서원 회원 삭제 페이지입니다.</h2>
<div>
    <table>
        <thead>
        <tr>
            <th>회원ID</th>
            <th>회원이름</th>
            <th>회원주민등록번호</th>
            <th>회원휴대폰번호</th>
            <th>회원주소</th>
            <th>회원이메일</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="memberDTO" items="${memberList}">
            <tr>
                <td>${memberDTO.clientNUM}</td>
                <td>${memberDTO.clientName}</td>
                <td>${memberDTO.clientID}</td>
                <td>${memberDTO.clientPhone}</td>
                <td>${memberDTO.clientAddr}</td>
                <td>${memberDTO.clientEmail}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<div>
    <form action="${pageContext.request.contextPath}/memberDelete" method="post" enctype="multipart/form-data">
        회원ID:<input class="form-control" id="inputClientNUM" type="text" name="inputClientNUM"/>
        회원이름:<input class="form-control" id="inputClientName" type="text" name="inputClientName"/>
        회원이름확인:<input class="form-control" id="inputClientNameConfirm" type="text" name="inputClientNameConfirm"/>
        <input type="submit" value="회원 삭제">
    </form>
</div>
</body>
</html>