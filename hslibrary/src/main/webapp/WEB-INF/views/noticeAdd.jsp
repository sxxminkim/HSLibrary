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
    <style>
        .container1{

        }
        .container{
            position: absolute;
            width: 1645px;
            height: 686px;
            left: 136px;
            top: 500px;
        }
    </style>
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
<div class="container1">
    <a href="./noticeAdd">공지사항 등록</a>
    <a href="./noticeDelete">공지사항 삭제</a>
    <a href="./noticeUpdate">공지사항 수정</a>
</div>
<div class="container">
    <form action="${pageContext.request.contextPath}/noticeAdd" method="post" enctype="multipart/form-data">
        <div class="mb-3">
            <label for="inputNoticeTitle" class="form-label">제목</label>
            <input type="text" class="form-control" id="inputNoticeTitle" placeholder="제목" name="inputNoticeTitle">
        </div>
        <div class="mb-3">
            <label for="inputNoticeAuthor" class="form-label">글쓴이</label>
            <input type="text" class="form-control" id="inputNoticeAuthor" placeholder="글쓴이" name="inputNoticeAuthor">
        </div>
        <div class="mb-3">
            <label for="inputNoticeMain" class="form-label">내용</label>
            <textarea class="form-control" id="inputNoticeMain" rows="3"  name="inputNoticeMain"></textarea>
        </div>
        <input type="submit" value="공지사항 등록">
    </form>
</div>
</body>
</html>