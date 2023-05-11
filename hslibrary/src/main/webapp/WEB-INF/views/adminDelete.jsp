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
<h3>리스트에서 퇴사한 관리자를 찾아보세요.</h3>
<div>
    <a href="./memberAdd">회원추가</a>
    <a href="./memberDelete">회원삭제</a>
    <a href="./memberUpdate">회원수정</a>
    <a href="./adminRegister">관리자 추가</a>
    <a href="./adminDelete">관리자 삭제</a>
</div>
<div>
    <table>
        <thead>
        <tr>
            <th>관리자ID</th>
            <th>관리자PW</th>
            <th>관리자이름</th>
            <th>관리자주민등록번호</th>
            <th>관리자주소</th>
            <th>관리자휴대폰번호</th>
            <th>관리자부서</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="staffDTO" items="${memberList}">
            <tr>
                <td>${staffDTO.staffNUM}</td>
                <td>${staffDTO.staffPW}</td>
                <td>${staffDTO.staffName}</td>
                <td>${staffDTO.staffID}</td>
                <td>${staffDTO.staffAddr}</td>
                <td>${staffDTO.staffPhone}</td>
                <td>${staffDTO.staffDepartment}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<div>
    <form action="${pageContext.request.contextPath}/adminDelete" method="post" enctype="multipart/form-data">
        관리자ID:<input class="form-control" id="inputStaffNUM" type="text" name="inputStaffNUM"/>
        관리자이름:<input class="form-control" id="inputStaffName" type="text" name="inputStaffName"/>
        관리자이름 확인:<input class="form-control" id="inputStaffNameConfirm" type="text" name="inputStaffNameConfirm"/>
        <input type="submit" value="관리자 삭제">
    </form>
</div>
</body>
</html>