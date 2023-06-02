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
    <link href="https://cdn.datatables.net/1.13.4/css/dataTables.bootstrap5.min.css" rel="stylesheet">
    <script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.js"></script>
    <script type="text/javascript" src="https://cdn.datatables.net/1.13.4/js/jquery.dataTables.min.js"></script>
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.13.4/css/jquery.dataTables.min.css"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
    <script type="text/javascript" src="assets/js/table.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
</head>
<body>
<div class="container">
    <div class="text-center">
        <div class="p-4 p-md-5 mb-4 rounded text-bg-dark">
            <h1 class="display-4 mt-5 mb-4">책 상세 설명</h1>
            <p class="lead my-3">별치기호: ${bookDTO.bookID}</p>
            <p class="lead my-3">제목: ${bookDTO.bookName}</p>
            <p class="lead my-3">권: ${bookDTO.bookVolume}</p>
            <p class="lead my-3">저자: ${bookDTO.bookWriter}</p>
            <p class="lead my-3">출판사: ${bookDTO.bookCompany}</p>
            <p class="lead my-3">분류: ${bookDTO.bookGenre}</p>
            <p class="lead my-3">ISBN10/ISBN13: ${bookDTO.bookISBN}</p>
            <p class="lead my-3">출간연도: ${bookDTO.bookYear}</p>
            <p class="lead my-3">출간 판: ${bookDTO.bookEdition}</p>
            <p class="lead my-3">(잡지 호: )${bookDTO.bookIssue}</p>
            <p class="lead my-3">줄거리: ${bookDTO.bookSummary}</p>
            <input type="button" value="목록" onclick="location.href='${pageContext.request.contextPath}/allList'" />
        </div>
    </div>
</div>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>