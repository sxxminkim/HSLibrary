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
    <button><a href="<c:url value="/logout"/>">로그아웃</a></button>
    <button><a href="<c:url value="./adminIndex"/>">홈</a></button>
</header>
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
<h2>무릉서원 도서 수정 페이지입니다.</h2>
<h3>수정하실 도서를 확인해보세요.</h3>
<div>
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
<div>
    <form action="${pageContext.request.contextPath}/bookUpdate" method="post" enctype="multipart/form-data">
        bookID:<input class="form-control" id="inputBookID" type="text" name="inputBookID"/>
        책제목:<input class="form-control" id="inputBookName" type="text" name="inputBookName"/>
        지은이:<input class="form-control" id="inputBookWriter" type="text" name="inputBookWriter"/>
        장르:<input class="form-control" id="inputBookGenre" type="text" name="inputBookGenre"/>
        출판사:<input class="form-control" id="inputBookCompany" type="text" name="inputBookCompany"/>
        ISBN10:<input class="form-control" id="inputBookISBN" type="text" name="inputBookISBN"/>
        bookYear:<input class="form-control" id="inputBookYear" type="text" name="inputBookYear"/>
        bookEdition:<input class="form-control" id="inputBookEdition" type="text" name="inputBookEdition"/>
        bookVolume:<input class="form-control" id="inputBookVolume" type="text" name="inputBookVolume"/>
        bookIssue:<input class="form-control" id="inputBookIssue" type="text" name="inputBookIssue"/>
        bookSummary:<input class="form-control" id="inputBookSummary" type="text" name="inputBookSummary"/>
        <input type="submit" value="도서 수정">
    </form>
</div>

</body>
</html>