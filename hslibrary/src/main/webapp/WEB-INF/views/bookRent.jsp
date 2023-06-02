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
    <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
    <meta charset="UTF-8">
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
    <script>
        $(document).ready(function () {
            $('#bookTable').DataTable({
                "pageLength": 5,
                lengthChange: true,
                searching: true,
                ordering: true,
                info: true,
                paging: true
            });
        });
        $(document).ready(function () {
            $('#memberTable').DataTable({
                "pageLength": 5,
                lengthChange: true,
                searching: true,
                ordering: true,
                info: true,
                paging: true
            });
        });
        $(document).ready(function () {
            $('#bookRentTable').DataTable({
                "pageLength": 5,
                lengthChange: true,
                searching: true,
                ordering: true,
                info: true,
                paging: true
            });
        });
    </script>
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
            <h1 class="display-4 mt-5 mb-4">도서 대여</h1>
        </div>
    </div>
</div>
<div style="float:left;margin-right:20px">
<table class="table table-striped" id="bookTable">
    <thead class="table-light">
        <th>bookID</th>
        <th>책제목</th>
        <th>지은이</th>
        <th>출판사</th>
    </thead>
    <tbody>
    <c:forEach var="bookDTO" items="${bookList}">
        <tr>
            <td>${bookDTO.bookID}</td>
            <td>${bookDTO.bookName}</td>
            <td>${bookDTO.bookWriter}</td>
            <td>${bookDTO.bookCompany}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</div>
<div style="float:left;margin-right:20px">
    <table class="table table-striped" id="memberTable">
        <thead class="table-light">
        <th>clientNUM</th>
        <th>회원이름</th>
        <th>전화번호</th>
        <th>이메일</th>
        </thead>
        <tbody>
        <c:forEach var="memberDTO" items="${memberList}">
            <tr>
                <td>${memberDTO.clientNUM}</td>
                <td>${memberDTO.clientName}</td>
                <td>${memberDTO.clientPhone}</td>
                <td>${memberDTO.clientEmail}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<div class="container">
    <table class="table table-striped" id="bookRentTable">
        <thead class="table-light">
        <th>도서대출번호</th>
        <th>도서번호</th>
        <th>회원번호</th>
        <th>대출일</th>
        <th>반납예정일</th>
        <th>자세히</th>
        </thead>
        <tbody>
        <c:forEach var="bookRentDTO" items="${bookRentList}">
            <tr>
                <td>${bookRentDTO.bookRentalNUM}</td>
                <td>${bookRentDTO.bookID}</td>
                <td>${bookRentDTO.clientNUM}</td>
                <td>${bookRentDTO.bookRental_start}</td>
                <td>${bookRentDTO.bookRental_end}</td>
                <td><input type="button" value="자세히" onclick="location.href='${pageContext.request.contextPath}/bookRent_detail?bookRentalNUM=${bookRentDTO.bookRentalNUM}'" /></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<div class="container col-6 mx-auto mt-5">
    <form action="${pageContext.request.contextPath}/bookRent" method="post" enctype="multipart/form-data">
        <div class="mb-3">
            <label for="inputBookRentNUM" class="form-label">도서대출번호</label>
            <input type="text" class="form-control" id="inputBookRentNUM" placeholder="videoID" name="inputBookRentNUM"/>
        </div>
        <div class="mb-3">
            <label for="inputBookID" class="form-label">도서번호</label>
            <input type="text" class="form-control" id="inputBookID" placeholder="제목" name="inputBookID"/>
        </div>
        <div class="mb-3">
            <label for="inputClientNUM" class="form-label">아이디</label>
            <input type="text" class="form-control" id="inputClientNUM" placeholder="ID" name="inputClientNUM"/>
        </div>
        <input class="btn btn-primary" type="submit" value="도서 대출">
    </form>
</div>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>