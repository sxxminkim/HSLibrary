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
            <h1 class="display-4 mt-5 mb-4">공지번호 ${noticeDTO.noticeNUM}의 공지입니다.</h1>
            <div>
                <p class="lead my-3">${noticeDTO.noticeNUM}</p>
                <p class="lead my-3">${noticeDTO.noticeTitle}</p>
                <p class="lead my-3">${noticeDTO.noticeDate}</p>
                <p class="lead my-3">${noticeDTO.noticeAuthor}</p>
                <p class="lead my-3">${noticeDTO.noticeMain}</p>
                <input type="button" value="목록" onclick="location.href='${pageContext.request.contextPath}/noticeList'" />
            </div>
        </div>
    </div>
</div>
<div class="container">
    <form action="${pageContext.request.contextPath}/noticeUpdate" method="post" enctype="multipart/form-data">
        공지번호:<input class="form-control" id="inputNoticeNUM" type="text" name="inputNoticeNUM"/>
        제목:<input class="form-control" id="inputNoticeTitle" type="text" name="inputNoticeTitle"/>
        작성자:<input class="form-control" id="inputNoticeAuthor" type="text" name="inputNoticeAuthor"/>
        내용:<input class="form-control" id="inputNoticeMain" type="text" name="inputNoticeMain"/>
        <input type="submit" value="공지사항 수정">
    </form>
</div>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
