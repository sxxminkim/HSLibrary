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
    <link rel="icon" type="image/x-icon" href="/assets/favicon.ico" />
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
<h2>무릉서원 시청각실 대여 페이지입니다.</h2>
<div>
    <table>
        <thead>
        <tr>
            <th>시청각실 번호</th>
            <th>시청각실 이름</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="videoRoomDTO" items="${videoRoomList}">
            <tr>
                <td>${videoRoomDTO.vid_roomNUM}</td>
                <td>${videoRoomDTO.vid_roomName}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<div>
    <table>
        <thead>
        <tr>
            <th>시청각실 번호</th>
            <th>시청각실 빌린 사람</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="vidRoomRentDTO" items="${vidRoomRentList}">
            <tr>
                <td>${vidRoomRentDTO.vid_roomNUM}</td>
                <td>${vidRoomRentDTO.clientNUM}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<div>
    <form action="${pageContext.request.contextPath}/VideoRoomAdd" method="post" enctype="multipart/form-data">
        대여 번호:<input class="form-control" id="inputVidRoomRentNUM" type="text" name="inputVidRoomRentNUM"/>
        시청각실 번호:<input class="form-control" id="inputVidRoomNUM" type="text" name="inputVidRoomNUM"/>
        빌리는 사람:<input class="form-control" id="inputClientNUM" type="text" name="inputClientNUM"/>
        <input type="submit" value="시청각실 추가">
    </form>
</div>
</body>
</html>