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
<header>
    <button><a href="<c:url value="/logout"/>">로그아웃</a></button>
</header>
<h2>무릉서원 관리자 삭제페이지</h2>
<div>
    <table>
        <thead>
        <tr>
            <th>관리자ID</th>
            <th>관리자PW</th>
            <th>관리자이름</th>
            <th>관리자주민등록번호</th>
            <th>관리자주소</th>
            <th>관리자휴대폰번호</th>
            <th>관리자부서</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="memberDTO" items="${memberList}">
            <tr>
                <td>${memberDTO.staffNUM}</td>
                <td>${memberDTO.staffPW}</td>
                <td>${memberDTO.staffName}</td>
                <td>${memberDTO.staffID}</td>
                <td>${memberDTO.staffAddr}</td>
                <td>${memberDTO.staffPhone}</td>
                <td>${memberDTO.staffDeparture}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

</body>
</html>