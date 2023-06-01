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
    <script>
        $(document).ready(function () {
            $('#videoTable').DataTable({
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
            <h1 class="display-4 mt-5 mb-4">영상 추가</h1>
        </div>
    </div>
</div>
<div class="container">
    <table id="videoTable">
        <thead>
        <tr>
            <th>영상번호</th>
            <th>영상제목</th>
            <th>감독</th>
            <th>배급사</th>
            <th>개봉연도</th>
            <th>장르</th>
            <th>시리즈</th>
            <th>자세히</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="videoDTO" items="${videoList}">
            <tr>
                <td>${videoDTO.videoID}</td>
                <td>${videoDTO.videoName}</td>
                <td>${videoDTO.videoDirector}</td>
                <td>${videoDTO.videoCompany}</td>
                <td>${videoDTO.videoRelease}</td>
                <td>${videoDTO.videoGenre}</td>
                <td>${videoDTO.videoSequel}</td>
                <td><input type="button" value="자세히" onclick="location.href='${pageContext.request.contextPath}/video_detail?videoID=${videoDTO.videoID}'" /></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<div class="container">
    <form action="${pageContext.request.contextPath}/videoAdd" method="post" enctype="multipart/form-data">
        <div class="mb-3">
            <label for="videoID" class="form-label">영상번호</label>
            <input type="text" class="form-control" id="videoID" placeholder="videoID" name="inputVideoID"/>
        </div>
        <div class="mb-3">
            <label for="inputVideoName" class="form-label">제목</label>
            <input type="text" class="form-control" id="inputVideoName" placeholder="제목" name="inputVideoName"/>
        </div>
        <div class="mb-3">
            <label for="inputVideoDirector" class="form-label">감독</label>
            <input type="text" class="form-control" id="inputVideoDirector" placeholder="ID" name="inputVideoDirector"/>
        </div>
        <div class="mb-3">
            <label for="inputVideoCompany" class="form-label">배급사</label>
            <input type="text" class="form-control" id="inputVideoCompany" placeholder="ID" name="inputVideoCompany"/>
        </div>
        <div class="mb-3">
            <label for="inputVideoRelease" class="form-label">개봉연도</label>
            <input type="text" class="form-control" id="inputVideoRelease" placeholder="ID" name="inputVideoRelease"/>
        </div>
        <div class="mb-3">
            <label for="inputVideoGenre" class="form-label">장르</label>
            <input type="text" class="form-control" id="inputVideoGenre" placeholder="ID" name="inputVideoGenre"/>
        </div>
        <div class="mb-3">
            <label for="inputVideoSequel" class="form-label">시리즈</label>
            <input type="text" class="form-control" id="inputVideoSequel" placeholder="ID" name="inputVideoSequel"/>
        </div>
        <input type="submit" value="영상 추가">
    </form>
</div>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>