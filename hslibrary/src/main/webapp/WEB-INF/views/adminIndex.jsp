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

        #main{
            background-image: url( "assets/bg1.jpg" );
            color:#ffffff;
        }
    </style>
</head>
<body>

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

<div class="container" id="main">
    <div class="text-center">
        <div class="p-4 p-md-5 mb-4 rounded text-bg-dark">
            <h1 class="display-4 mt-5 mb-4">무릉서원에 오신것을 환영합니다~</h1>
            <p class="lead my-3">무릉서원 관리자 메인페이지입니다.</p>
            <p class="lead my-3"><%=session.getAttribute("loginStaffName")%>님 안녕하세요!</p>
            <p class="lead my-3"><%=session.getAttribute("loginStaffName")%>님의 행복한 하루를 기원합니다.</p>
        </div>
    </div>
</div>

<!--바로가기 카드-->
<div class="container mt-15 text-center">
    <div class="row">
        <div class="col-sm-4">
            <div class="card">
                <div class="card-body">
                    <h5 class="card-title">연체 회원</h5>
                    <p class="card-text">연체 회원의 정보를 조회할 수 있습니다.</p>
                    <a href="${pageContext.request.contextPath}/delayList" class="btn btn-primary">바로가기</a>
                </div>
            </div>
        </div>
        <div class="col-sm-4">
            <div class="card">
                <div class="card-body">
                    <h5 class="card-title">공지사항</h5>
                    <p class="card-text">무릉서원 이용자에게 당부하는 공지를 올리고 수정/삭제 할 수 있는 공간입니다.</p>
                    <a href="${pageContext.request.contextPath}/noticeAdd" class="btn btn-primary">바로가기</a>
                </div>
            </div>
        </div>
        <div class="col-sm-4">
            <div class="card">
                <div class="card-body">
                    <h5 class="card-title">검색서비스</h5>
                    <p class="card-text">무릉서원의 도서/영상 데이터베이스를 통해 검색을 지원합니다.</p>
                    <a href="${pageContext.request.contextPath}/allList" class="btn btn-primary">바로가기</a>
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="footer.jsp"></jsp:include>

</body>
</html>