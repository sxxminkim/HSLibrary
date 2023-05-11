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
</header>
<h2>무릉서원 도서 반납 페이지입니다.</h2>
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
    <a href="./bookRent">도서대출</a>
    <a href="./bookReturn">도서반납</a>
    <a href="./bookExtend">도서연장</a>
</div>
<div style="float:left;margin-right:20px">
    <table>
        <thead>
        <th>도서대출번호</th>
        <th>도서번호</th>
        <th>회원번호</th>
        <th>대출일</th>
        <th>반납예정일</th>
        </thead>
        <tbody>
        <c:forEach var="bookRentDTO" items="${bookRentList}">
            <tr>
                <td>${bookRentDTO.bookRentalNUM}</td>
                <td>${bookRentDTO.bookID}</td>
                <td>${bookRentDTO.clientNUM}</td>
                <td>${bookRentDTO.bookRental_start}</td>
                <td>${bookRentDTO.bookRental_end}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<hr>
<div>
    <form action="${pageContext.request.contextPath}/bookReturn" method="post" enctype="multipart/form-data">
        도서대출번호:<input class="form-control" id="inputBookRentNUM" type="text" name="inputBookRentNUM"/>
        도서번호:<input class="form-control" id="inputBookID" type="text" name="inputBookID"/>
        도서번호확인:<input class="form-control" id="inputBookIDConfirm" type="text" name="inputBookIDConfirm"/>
        <input type="submit" value="도서 반납">
    </form>
</div>
</body>
</html>