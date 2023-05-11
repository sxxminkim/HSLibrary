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
<h2>무릉서원 시청각실 대여 및 반납 페이지입니다.</h2>
<div>
    <table>
        
    </table>
</div>

</body>
</html>