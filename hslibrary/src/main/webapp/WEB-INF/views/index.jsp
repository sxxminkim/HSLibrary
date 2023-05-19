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
    <script src="https://code.jquery.com/jquery-3.6.3.min.js" integrity="sha256-
pvPw+upLPUjgMXY0G+8O0xUf+/Im1MZjXxxgOcBQBXU=" crossorigin="anonymous"></script>
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" integrity="sha384-
xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-Fy6S3B9q64WdZWQUiU+q4/2Lc9npb8tCaSX9FK7E8HnRr0Jz8D6OP9dO5Vg3Q9ct"
            crossorigin="anonymous">
    </script>
    <style>
        table{
            border: 1px solid #444444;
            font-size: 20px;
            width: 800px;
            height:200px;
            outline: dashed 1px black;
            text-align: center;
        }
        th, td {
            border: 1px solid #444444;
            padding: 10px;
        }
    </style>
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
    <table>
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
    <table>
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
    <table>
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
    <table>
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