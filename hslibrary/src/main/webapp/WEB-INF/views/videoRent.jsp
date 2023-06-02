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
    <script>
        $(document).ready(function () {
            $('#videoTable').DataTable({
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
            $('#videoRentTable').DataTable({
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
<h2>무릉서원 영상 대출 페이지입니다.</h2>
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
            <h1 class="display-4 mt-5 mb-4">영상 대여</h1>
        </div>
    </div>
</div>
<div style="float:left;margin-left:300px;margin-right:200px">
    <table class="table table-striped" id="videoTable">
        <thead>
        <th>videoID</th>
        <th>영상제목</th>
        <th>감독</th>
        <th>배급사</th>
        </thead>
        <tbody>
        <c:forEach var="videoDTO" items="${videoList}">
            <tr>
                <td>${videoDTO.videoID}</td>
                <td>${videoDTO.videoName}</td>
                <td>${videoDTO.videoDirector}</td>
                <td>${videoDTO.videoCompany}</td>
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
    <table id="videoRentTable">
        <thead class="table-light">
        <th>영상대출번호</th>
        <th>영상번호</th>
        <th>회원번호</th>
        <th>대출일</th>
        <th>반납예정일</th>
        <th>자세히</th>
        </thead>
        <tbody>
        <c:forEach var="videoRentDTO" items="${videoRentList}">
            <tr>
                <td>${videoRentDTO.videoRentalNUM}</td>
                <td>${videoRentDTO.videoID}</td>
                <td>${videoRentDTO.clientNUM}</td>
                <td>${videoRentDTO.videoRental_start}</td>
                <td>${videoRentDTO.videoRental_end}</td>
                <td><input type="button" value="자세히" onclick="location.href='${pageContext.request.contextPath}/videoRent_detail?videoRentalNUM=${videoRentDTO.videoRentalNUM}'" /></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<div class="container col-6 mx-auto mt-5">
    <form action="${pageContext.request.contextPath}/videoRent" method="post" enctype="multipart/form-data">
        <div class="mb-3">
            <label for="inputVideoRentNUM" class="form-label">영상대출번호</label>
            <input type="text" class="form-control" id="inputVideoRentNUM" placeholder="영상대여번호" name="inputVideoRentNUM"/>
        </div>
        <div class="mb-3">
            <label for="inputVideoID" class="form-label">영상번호</label>
            <input type="text" class="form-control" id="inputVideoID" placeholder="영상번호" name="inputVideoID"/>
        </div>
        <div class="mb-3">
            <label for="inputClientNUM" class="form-label">아이디</label>
            <input type="text" class="form-control" id="inputClientNUM" placeholder="ID" name="inputClientNUM"/>
        </div>
        <input class="btn btn-primary" type="submit" value="영상 대출">
    </form>
</div>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>