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
    <link href="https://cdn.datatables.net/1.13.4/css/dataTables.bootstrap5.min.css" rel="stylesheet">
    <script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.js"></script>
    <script type="text/javascript" src="https://cdn.datatables.net/1.13.4/js/jquery.dataTables.min.js"></script>
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.13.4/css/jquery.dataTables.min.css"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
    <script type="text/javascript" src="assets/js/table.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
</head>
<body>
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
<div class="container">
    <div class="text-center">
        <div class="p-4 p-md-5 mb-4 rounded text-bg-dark">
            <h1 class="display-4 mt-5 mb-4">회원 수정</h1>
            <div>
                <a href="${pageContext.request.contextPath}/memberAdd" class="btn btn-primary">회원추가</a>
                <a href="${pageContext.request.contextPath}/adminRegister" class="btn btn-primary">관리자 추가</a>
                <a href="${pageContext.request.contextPath}/adminDelete" class="btn btn-primary">관리자 삭제</a>
                <a href="${pageContext.request.contextPath}/adminUpdate" class="btn btn-primary">관리자 수정</a>
            </div>
        </div>
    </div>
</div>
<div class="container">
    <div class="text-center">
        <div class="p-4 p-md-5 mb-4 rounded">
            <h1 class="display-4 mt-5 mb-4">수정하려는 회원 정보를 확인해보세요.</h1>
            <div>
                <p class="lead my-3">회원ID: ${memberDTO.clientNUM}</p>
                <p class="lead my-3">회원이름: ${memberDTO.clientName}</p>
                <p class="lead my-3">주민등록번호: ${memberDTO.clientID}</p>
                <p class="lead my-3">휴대폰번호: ${memberDTO.clientPhone}</p>
                <p class="lead my-3">회원 주소: ${memberDTO.clientAddr}</p>
                <p class="lead my-3">회원 이메일: ${memberDTO.clientEmail}</p>
                <input type="button" value="목록" onclick="location.href='${pageContext.request.contextPath}/memberAdd'" />
            </div>
        </div>
    </div>
</div>
<div class="container col-6 mx-auto mt-5">
    <form action="${pageContext.request.contextPath}/memberUpdate" method="post" enctype="multipart/form-data">
        <div class="mb-3">
            <label for="inputClientNUM" class="form-label">회원ID</label>
            <input type="text" class="form-control" id="inputClientNUM" placeholder="회원ID" name="inputClientNUM">
        </div>
        <div class="mb-3">
            <label for="inputClientName" class="form-label">회원이름</label>
            <input type="text" class="form-control" id="inputClientName" placeholder="이름" name="inputClientName">
        </div>
        <div class="mb-3">
            <label for="inputClientID" class="form-label">회원주민등록번호</label>
            <input type="text" class="form-control" id="inputClientID" placeholder="000000-0000000" name="inputClientID">
        </div>
        <div class="mb-3">
            <label for="inputClientPhone" class="form-label">회원전화번호</label>
            <input type="text" class="form-control" id="inputClientPhone" placeholder="000-0000-0000" name="inputClientPhone">
        </div>
        <div class="mb-3">
            <label for="inputClientAddr" class="form-label">회원주소</label>
            <input type="text" class="form-control" id="inputClientAddr" placeholder="주소" name="inputClientAddr">
        </div>
        <div class="mb-3">
            <label for="inputClientEmail" class="form-label">회원이메일</label>
            <input type="text" class="form-control" id="inputClientEmail" placeholder="example@email.com" name="inputClientEmail">
        </div>
        <input class="btn btn-primary" type="submit" value="회원 수정">
    </form>
</div>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>