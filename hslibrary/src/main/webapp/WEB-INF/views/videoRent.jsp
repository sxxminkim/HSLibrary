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
</head>
<body>
<header>
    <button><a href="<c:url value="/logout"/>">로그아웃</a></button>
</header>
<jsp:include page="navbar.jsp"></jsp:include>
<h2>무릉서원 영상 대출 페이지입니다.</h2>
<%
    // 세션값 가져오기
    if (session.getAttribute("loginStaffName") == null)
    {
        // Object 타입이므로 다운캐스팅
        response.sendRedirect("./login");
        session.setAttribute("loginMsg", "로그인 후 이용해주세요.");
    }
%>
<div>
    <a href="./videoRent">영상대출</a>
    <a href="./videoReturn">영상반납</a>
    <a href="./videoExtend">영상연장</a>
</div>
<div style="float:left;margin-right:20px">
    <table>
        <thead>
        <th>videoID</th>
        <th>영상제목</th>
        <th>감독</th>
        <th>배급사</th>
        </thead>
        <tbody>
        <c:forEach var="videoDTO" items="${videoList}">
            <tr>
                <td>${videoDTO.videoID}</td>
                <td>${videoDTO.videoName}</td>
                <td>${videoDTO.videoDirector}</td>
                <td>${videoDTO.videoCompany}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<div style="float:left;margin-right:20px">
    <table>
        <thead>
        <th>clientNUM</th>
        <th>회원이름</th>
        <th>전화번호</th>
        <th>이메일</th>
        </thead>
        <tbody>
        <c:forEach var="memberDTO" items="${memberList}">
            <tr>
                <td>${memberDTO.clientNUM}</td>
                <td>${memberDTO.clientName}</td>
                <td>${memberDTO.clientPhone}</td>
                <td>${memberDTO.clientEmail}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<div style="float:left;margin-right:20px">
    <table>
        <thead>
        <th>영상대출번호</th>
        <th>영상번호</th>
        <th>회원번호</th>
        <th>대출일</th>
        <th>반납예정일</th>
        </thead>
        <tbody>
        <c:forEach var="videoRentDTO" items="${videoRentList}">
            <tr>
                <td>${videoRentDTO.videoRentalNUM}</td>
                <td>${videoRentDTO.videoID}</td>
                <td>${videoRentDTO.clientNUM}</td>
                <td>${videoRentDTO.videoRental_start}</td>
                <td>${videoRentDTO.videoRental_end}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<hr>
<div>
    <form action="${pageContext.request.contextPath}/videoRent" method="post" enctype="multipart/form-data">
        영상대출번호:<input class="form-control" id="inputVideoRentNUM" type="text" name="inputVideoRentNUM"/>
        영상번호:<input class="form-control" id="inputVideoID" type="text" name="inputVideoID"/>
        회원아이디:<input class="form-control" id="inputClientNUM" type="text" name="inputClientNUM"/>
        <input type="submit" value="영상 대출">
    </form>
</div>
</body>
</html>