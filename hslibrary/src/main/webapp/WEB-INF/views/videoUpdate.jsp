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
</head>
<body>
<header>
    <button><a href="<c:url value="/logout"/>">로그아웃</a></button>
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
<h2>무릉서원 영상 수정 페이지입니다.</h2>
<div>
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
<div>
    <form action="${pageContext.request.contextPath}/videoUpdate" method="post" enctype="multipart/form-data">
        videoID:<input class="form-control" id="inputVideoID" type="text" name="inputVideoID"/>
        영상제목:<input class="form-control" id="inputVideoName" type="text" name="inputVideoName"/>
        감독:<input class="form-control" id="inputVideoDirector" type="text" name="inputVideoDirector"/>
        배급사:<input class="form-control" id="inputVideoCompany" type="text" name="inputVideoCompany"/>
        개봉연도:<input class="form-control" id="inputVideoRelease" type="text" name="inputVideoRelease"/>
        장르:<input class="form-control" id="inputVideoGenre" type="text" name="inputVideoGenre"/>
        sequel:<input class="form-control" id="inputVideoSequel" type="text" name="inputVideoSequel"/>
        <input type="submit" value="영상 수정">
    </form>
</div>


</body>
</html>