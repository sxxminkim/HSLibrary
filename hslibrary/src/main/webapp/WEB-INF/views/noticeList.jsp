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
            $('#noticeTable').DataTable({
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
<div class="container">
    <div class="text-center">
        <div class="p-4 p-md-5 mb-4 rounded">
            <h1 class="display-4 mt-5 mb-4">공지사항</h1>
            <div>
                <a href="${pageContext.request.contextPath}/allList">자료검색</a>
            </div>
        </div>
    </div>
</div>
<div class="container">
    <table class="table table-striped" id="noticeTable">
        <thead class="table-light">
        <tr>
            <th>공지 번호</th>
            <th>제목</th>
            <th>날짜</th>
            <th>작성자</th>
            <th>공지 링크</th>
            <th>수정</th>
            <th>삭제</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="noticeDTO" items="${noticeList}">
            <tr>
                <td>${noticeDTO.noticeNUM}</td>
                <td>${noticeDTO.noticeTitle}</td>
                <td>${noticeDTO.noticeDate}</td>
                <td>${noticeDTO.noticeAuthor}</td>
                <td><input type="button" value="자세히" onclick="location.href='${pageContext.request.contextPath}/notice_detail?noticeNUM=${noticeDTO.noticeNUM}'" /></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>