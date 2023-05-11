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
            <th>번호</th>
            <th>제목</th>
            <th>날짜</th>
            <th>작성자</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>1</td>
            <td>title</td>
            <td>2023-05-10</td>
            <td>박건달</td>
        </tr>
        <tr>
            <td>2</td>
            <td>title2</td>
            <td>2023-05-10</td>
            <td>김수민</td>
        </tr>
        <tr>
            <td>3</td>
            <td>title3</td>
            <td>2023-05-11</td>
            <td>박건달</td>
        </tr>
        </tbody>
    </table>
</div>
<h3>도서/영상 자료</h3>
<div style="float:left;margin-right:20px">
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
            <th>분류</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="bookDTO" items="${bookList}">
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
            <th>Sequel</th>
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