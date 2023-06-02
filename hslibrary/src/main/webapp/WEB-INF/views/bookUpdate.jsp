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
<div class="container">
    <div class="text-center">
        <div class="p-4 p-md-5 mb-4 rounded">
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
        </div>
    </div>
    <div class="container col-6 mx-auto mt-5">
        <form action="${pageContext.request.contextPath}/bookUpdate" method="post" enctype="multipart/form-data">
            <div class="mb-3">
                <label for="bookID" class="form-label">별치기호</label>
                <input type="text" class="form-control" id="bookID" placeholder="bookID" name="inputBookID"/>
            </div>
            <div class="mb-3">
                <label for="inputBookName" class="form-label">제목</label>
                <input type="text" class="form-control" id="inputBookName" placeholder="제목" name="inputBookName"/>
            </div>
            <div class="mb-3">
                <label for="inputBookWriter" class="form-label">저자</label>
                <input type="text" class="form-control" id="inputBookWriter" placeholder="저자" name="inputBookWriter"/>
            </div>
            <div class="mb-3">
                <label for="inputBookGenre" class="form-label">분류</label>
                <input type="text" class="form-control" id="inputBookGenre" placeholder="분류" name="inputBookGenre"/>
            </div>
            <div class="mb-3">
                <label for="inputBookCompany" class="form-label">출판사</label>
                <input type="text" class="form-control" id="inputBookCompany" placeholder="출판사" name="inputBookCompany"/>
            </div>
            <div class="mb-3">
                <label for="inputBookISBN" class="form-label">ISBN</label>
                <input type="text" class="form-control" id="inputBookISBN" placeholder="ISBN" name="inputBookISBN"/>
            </div>
            <div class="mb-3">
                <label for="inputBookYear" class="form-label">출간연도</label>
                <input type="text" class="form-control" id="inputBookYear" placeholder="출간연도" name="inputBookYear"/>
            </div>
            <div class="mb-3">
                <label for="inputBookEdition" class="form-label">출간판</label>
                <input type="text" class="form-control" id="inputBookEdition" placeholder="출간판" name="inputBookEdition"/>
            </div>
            <div class="mb-3">
                <label for="inputBookVolume" class="form-label">권</label>
                <input type="text" class="form-control" id="inputBookVolume" placeholder="권" name="inputBookVolume"/>
            </div>
            <div class="mb-3">
                <label for="inputBookIssue" class="form-label">(잡지) 호</label>
                <input type="text" class="form-control" id="inputBookIssue" placeholder="(잡지) 호" name="inputBookIssue"/>
            </div>
            <div class="mb-3">
                <label for="inputBookSummary" class="form-label">줄거리</label>
                <textarea class="form-control" id="inputBookSummary" rows="3"  name="inputBookSummary"></textarea>
            </div>
            <div class="mb-3">
                <label for="inputBookType" class="form-label">종이책(1)/전자책(2)</label>
                <input type="text" class="form-control" id="inputBookType" placeholder="종이책(1)/전자책(2)" name="inputBookType"/>
            </div>
            <input class="btn btn-primary" type="submit" value="도서 수정">
        </form>
    </div>
</div>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>