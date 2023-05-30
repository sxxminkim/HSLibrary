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
    <form action="${pageContext.request.contextPath}/adminRegister" method="post" enctype="multipart/form-data">
        <div class="mb-3">
            <label for="inputStaffNUM" class="form-label">관리자 ID</label>
            <input type="text" class="form-control" id="inputStaffNUM" placeholder="ID" name="inputStaffNUM"/>
        </div>
        <div class="mb-3">
            <label for="inputStaffPW" class="form-label">관리자 PW</label>
            <input type="text" class="form-control" id="inputStaffPW" placeholder="PW" name="inputStaffPW"/>
        </div>
        <div class="mb-3">
            <label for="inputStaffName" class="form-label">관리자 이름</label>
            <input type="text" class="form-control" id="inputStaffName" placeholder="이름" name="inputStaffName"/>
        </div>
        <div class="mb-3">
            <label for="inputStaffID" class="form-label">관리자 주민등록번호</label>
            <input type="text" class="form-control" id="inputStaffID" placeholder="000000-0000000" name="inputStaffID"/>
        </div>
        <div class="mb-3">
            <label for="inputStaffAddr" class="form-label">관리자 주소</label>
            <input type="text" class="form-control" id="inputStaffAddr" placeholder="주소" name="inputStaffAddr"/>
        </div>
        <div class="mb-3">
            <label for="inputStaffPhone" class="form-label">관리자 휴대폰번호</label>
            <input type="text" class="form-control" id="inputStaffPhone" placeholder="000-0000-0000" name="inputStaffPhone"/>
        </div>
        <div class="mb-3">
            <label for="inputStaffDepartment" class="form-label">관리자 부서</label>
            <input type="text" class="form-control" id="inputStaffDepartment" placeholder="부서명" name="inputStaffDepartment"/>
        </div>
        <input type="submit" value="관리자 추가">
    </form>
</div>
</body>
</html>