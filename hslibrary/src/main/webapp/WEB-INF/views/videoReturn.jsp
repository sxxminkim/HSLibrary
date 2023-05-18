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
<h2>무릉서원 영상 반납 페이지입니다.</h2>
<div>
    <a href="./videoRent">영상대출</a>
    <a href="./videoReturn">영상반납</a>
    <a href="./videoExtend">영상연장</a>
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
    <form action="${pageContext.request.contextPath}/videoReturn" method="post" enctype="multipart/form-data">
        영상대출번호:<input class="form-control" id="inputVideoRentNUM" type="text" name="inputVideoRentNUM"/>
        영상번호:<input class="form-control" id="inputVideoID" type="text" name="inputVideoID"/>
        영상번호확인:<input class="form-control" id="inputVideoIDConfirm" type="text" name="inputVideoIDConfirm"/>
        <input type="submit" value="영상 반납">
    </form>
</div>

</body>
</html>