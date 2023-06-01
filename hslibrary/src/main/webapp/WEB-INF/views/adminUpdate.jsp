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
        <div class="p-4 p-md-5 mb-4 rounded">
            <h1 class="display-4 mt-5 mb-4">관리자 정보 수정</h1>
            <div>
                <a href="${pageContext.request.contextPath}/memberAdd">회원 추가</a>
                <a href="${pageContext.request.contextPath}/adminDelete">관리자 삭제</a>
            </div>
        </div>
    </div>
</div>
<div class="container">
    <div class="text-center">
        <div class="p-4 p-md-5 mb-4 rounded text-bg-dark">
            <h1 class="display-4 mt-5 mb-4">수정할 관리자 정보를 확인하세요.</h1>
            <p class="lead my-3">ID: ${staffDTO.staffNUM}</p>
            <p class="lead my-3">PW: ${staffDTO.staffPW}</p>
            <p class="lead my-3">이름: ${staffDTO.staffName}</p>
            <p class="lead my-3">주민등록번호: ${staffDTO.staffID}</p>
            <p class="lead my-3">주소: ${staffDTO.staffAddr}</p>
            <p class="lead my-3">휴대폰번호: ${staffDTO.staffPhone}</p>
            <p class="lead my-3">부서: ${staffDTO.staffDepartment}</p>
            <input type="button" value="목록" onclick="location.href='${pageContext.request.contextPath}/adminRegister'" />
        </div>
    </div>
</div>
<div>
    <form action="${pageContext.request.contextPath}/adminUpdate" method="post" enctype="multipart/form-data">
        <div class="mb-3">
            <label for="inputStaffNUM" class="form-label">관리자 ID</label>
            <input type="text" class="form-control" id="inputStaffNUM" placeholder="ID" name="inputStaffNUM"/>
        </div>
        <div class="mb-3">
            <label for="inputStaffPW" class="form-label">관리자 PW</label>
            <input type="text" class="form-control" id="inputStaffPW" placeholder="PW" name="inputStaffPW"/>
        </div>
        <div class="mb-3">
            <label for="inputStaffName" class="form-label">관리자 이름</label>
            <input type="text" class="form-control" id="inputStaffName" placeholder="이름" name="inputStaffName"/>
        </div>
        <div class="mb-3">
            <label for="inputStaffID" class="form-label">관리자 주민등록번호</label>
            <input type="text" class="form-control" id="inputStaffID" placeholder="000000-0000000" name="inputStaffID"/>
        </div>
        <div class="mb-3">
            <label for="inputStaffAddr" class="form-label">관리자 주소</label>
            <input type="text" class="form-control" id="inputStaffAddr" placeholder="주소" name="inputStaffAddr"/>
        </div>
        <div class="mb-3">
            <label for="inputStaffPhone" class="form-label">관리자 휴대폰번호</label>
            <input type="text" class="form-control" id="inputStaffPhone" placeholder="000-0000-0000" name="inputStaffPhone"/>
        </div>
        <div class="mb-3">
            <label for="inputStaffDepartment" class="form-label">관리자 부서</label>
            <input type="text" class="form-control" id="inputStaffDepartment" placeholder="부서명" name="inputStaffDepartment"/>
        </div>
        <input type="submit" value="관리자 수정">
    </form>
</div>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>