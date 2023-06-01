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
            <input type="button" value="목록" onclick="location.href='${pageContext.request.contextPath}/bookAdd'" />
            <input type="button" value="삭제" onclick="location.href='${pageContext.request.contextPath}/bookDelete?bookID=${bookDTO.bookID}'" />
            <input type="button" value="수정" onclick="location.href='${pageContext.request.contextPath}/bookUpdate?bookID=${bookDTO.bookID}'" />
        </div>
    </div>
</div>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>