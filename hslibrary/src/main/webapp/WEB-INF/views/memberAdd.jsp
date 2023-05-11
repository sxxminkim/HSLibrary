<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="true" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@page import="com.manage.hslibrary.service.*"%>
<%@page import="com.manage.hslibrary.DAO.*"%>
<%@page import="com.manage.hslibrary.DTO.*"%>
<%@page import="java.sql.*"%>
<%@page import="java.util.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
    <title>무릉서원</title>
    <script src="https://code.jquery.com/jquery-3.6.3.min.js" integrity="sha256-
pvPw+upLPUjgMXY0G+8O0xUf+/Im1MZjXxxgOcBQBXU=" crossorigin="anonymous"></script>
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" integrity="sha384-
xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-Fy6S3B9q64WdZWQUiU+q4/2Lc9npb8tCaSX9FK7E8HnRr0Jz8D6OP9dO5Vg3Q9ct"
            crossorigin="anonymous">
    </script>
</head>
<body>
<header>
    <button><a href="<c:url value="/logout"/>">로그아웃</a></button>
</header>
<jsp:include page="navbar.jsp"></jsp:include>
<%
    // 세션값 가져오기
    if (session.getAttribute("loginStaffName") == null)
    {
        // Object 타입이므로 다운캐스팅
        response.sendRedirect("./login");
        session.setAttribute("loginMsg", "로그인 후 이용해주세요.");
    }
%>
<h2>무릉서원 회원 추가 페이지입니다.</h2>
<div>
    <a href="./memberAdd">회원추가</a>
    <a href="./memberDelete">회원삭제</a>
    <a href="./memberUpdate">회원수정</a>
    <a href="./adminRegister">관리자 추가</a>
    <a href="./adminDelete">관리자 삭제</a>
</div>
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
    <form action="${pageContext.request.contextPath}/memberAdd" method="post" enctype="multipart/form-data">
        회원ID:<input class="form-control" id="inputClientNUM" type="text" name="inputClientNUM"/>
        회원이름:<input class="form-control" id="inputClientName" type="text" name="inputClientName"/>
        회원주민등록번호:<input class="form-control" id="inputClientID" type="text" name="inputClientID"/>
        회원전화번호:<input class="form-control" id="inputClientPhone" type="text" name="inputClientPhone"/>
        회원주소:<input class="form-control" id="inputClientAddr" type="text" name="inputClientAddr"/>
        회원이메일:<input class="form-control" id="inputClientEmail" type="text" name="inputClientEmail"/>
        <input type="submit" value="회원 추가">
    </form>
</div>
</body>
</html>