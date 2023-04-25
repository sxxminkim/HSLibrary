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
<h2>무릉서원 관리자 등록페이지</h2>
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
<div>
    <form action="./adminRegister" method="post" enctype="multipart/form-data">
        관리자ID:<input class="form-control" id="inputStaffNUM" type="text" name="inputStaffNUM"/>
        관리자PW:<input class="form-control" id="inputStaffPW" type="text" name="inputStaffPW"/>
        관리자이름:<input class="form-control" id="inputStaffName" type="text" name="inputStaffNam"/>
        관리자주민등록번호:<input class="form-control" id="inputStaffID" type="text" name="inputStaffID"/>
        관리자주소:<input class="form-control" id="inputStaffAddr" type="text" name="inputStaffAddr"/>
        관리자휴대폰번호:<input class="form-control" id="inputStaffPhone" type="text" name="inputStaffPhone"/>
        관리자부서:<input class="form-control" id="inputStaffDeparture" type="text" name="inputStaffDeparture"/>
        <input type="submit" value="관리자 추가">
    </form>
</div>
</body>
</html>