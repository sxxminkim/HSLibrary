<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
    <title>무릉서원</title>
    <link href="https://cdn.datatables.net/1.13.4/css/dataTables.bootstrap5.min.css" rel="stylesheet">
    <script type="text/javascript" src="https://cdn.datatables.net/1.13.4/js/jquery.dataTables.min.js"></script>
    <script type="text/javascript" src="https://cdn.datatables.net/1.13.4/js/dataTables.bootstrap5.min.js"></script>
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.13.4/css/dataTables.bootstrap5.min.css"/>
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/searchbuilder/1.4.2/css/searchBuilder.dataTables.min.css"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
    <script src="https://cdn.datatables.net/searchbuilder/1.4.2/js/dataTables.searchBuilder.min.js"></script>
    <script>
        $(document).ready(function () {
            $('#noticeTable').DataTable({
                lengthChange: true,
                searching: true,
                ordering: true,
                info: true,
                paging: true
            });
        });
        $(document).ready(function () {
            $('#paperBookTable').DataTable({
                lengthChange: true,
                searching: true,
                ordering: true,
                info: true,
                paging: true
            });
        });
        $(document).ready(function () {
            $('#eBookTable').DataTable({
                lengthChange: true,
                searching: true,
                ordering: true,
                info: true,
                paging: true
            });
        });
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
<header>
    <button><a href="<c:url value="/login"/>" > login </a></button>
    <button><a href="<c:url value="/"/>">home</a></button>
</header>
<h2>무릉서원에 오신 것을 환영합니다.</h2>
<hr>
<h3>공지사항</h3>
<div>
    <table class="table" id="noticeTable">
        <thead>
        <tr>
            <th>공지 번호</th>
            <th>제목</th>
            <th>날짜</th>
            <th>작성자</th>
            <th>공지 링크</th>
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
<hr>
<h3>도서/영상 자료</h3>
<div style="float:left;margin-right:20px">
    <p>종이책</p>
    <table class="table" id="paperBookTable">
        <thead>
        <tr>
            <th>별치기호</th>
            <th>제목</th>
            <th>권</th>
            <th>출간 판</th>
            <th>출간 연도</th>
            <th>ISBN</th>
            <th>저자</th>
            <th>출판사</th>
            <th>분류</th>
            <th>자세히</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="bookDTO" items="${paperBookList}">
            <tr>
                <td>${bookDTO.bookID}</td>
                <td>${bookDTO.bookName}</td>
                <td>${bookDTO.bookVolume}</td>
                <td>${bookDTO.bookEdition}</td>
                <td>${bookDTO.bookYear}</td>
                <td>${bookDTO.bookISBN}</td>
                <td>${bookDTO.bookWriter}</td>
                <td>${bookDTO.bookCompany}</td>
                <td>${bookDTO.bookGenre}</td>
                <td><input type="button" value="자세히" onclick="location.href='${pageContext.request.contextPath}/book_detail?bookID=${bookDTO.bookID}'" /></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<div style="float:left;margin-right:20px">
    <p>전자책</p>
    <table class="table" id="eBookTable">
        <thead>
        <tr>
            <th>도서번호</th>
            <th>도서제목</th>
            <th>volume</th>
            <th>edition</th>
            <th>발행년도</th>
            <th>ISBN</th>
            <th>저자</th>
            <th>출판사</th>
            <th>분류</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="bookDTO" items="${eBookList}">
            <tr>
                <td>${bookDTO.bookID}</td>
                <td>${bookDTO.bookName}</td>
                <td>${bookDTO.bookVolume}</td>
                <td>${bookDTO.bookEdition}</td>
                <td>${bookDTO.bookYear}</td>
                <td>${bookDTO.bookISBN}</td>
                <td>${bookDTO.bookWriter}</td>
                <td>${bookDTO.bookCompany}</td>
                <td>${bookDTO.bookGenre}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<div style="float:left;margin-left:20px">
    <table class="table" id="videoTable">
        <thead>
        <tr>
            <th>영상번호</th>
            <th>영상제목</th>
            <th>감독</th>
            <th>배급사</th>
            <th>개봉연도</th>
            <th>장르</th>
            <th>시리즈</th>
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
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

</body>
</html>