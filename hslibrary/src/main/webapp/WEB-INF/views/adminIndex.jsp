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
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" integrity="sha384-
xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">


</head>
<body>
  <!--navbar-->
  <nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
  <div class="container-fluid">
    <a class="navbar-brand" href="#">무릉서원</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse justify-content-end" id="navbarCollapse">
      <ul class="navbar-nav me-auto mb-2 mb-md-0">
        <li class="nav-item">
          <a class="navbar-brand" href="./memberAdd">회원관리</a>
        </li>
        <li class="nav-item">
          <a class="navbar-brand" href="./bookAdd">도서관리</a>
        </li>
        <li class="nav-item">
          <a class="navbar-brand" href="./videoAdd">영상관리</a>
        </li>
        <li class="nav-item">
          <a class="navbar-brand" href="./bookRent">도서대여/반납/연장</a>
        </li>
        <li class="nav-item">
          <a class="navbar-brand" href="./videoRent">영상대여/반납/연장</a>
        </li>
        <li class="nav-item">
          <a class="navbar-brand" href="./videoRoom">영상관람실</a>
        </li>
      </ul>
        <button type="button" class="btn btn-light ml-3"><a href="c:url value="/logout"/">로그아웃</a></button>
    </div>
  </div>
</nav>

<!--<jsp:include page="navbar.jsp"></jsp:include>-->

<!--세션을 어디다 넣어야할지 모르겠어서 주석처리 했어요 -->
<!--<%
    // 세션값 가져오기
    if (session.getAttribute("loginStaffName") == null)
    {
        // Object 타입이므로 다운캐스팅
        response.sendRedirect("./login");
        session.setAttribute("loginMsg", "로그인 후 이용해주세요.");
    }
%>


<%=session.getAttribute("loginStaffName")%>님 안녕하세요! <br />
<%=session.getAttribute("loginStaffName")%>님의 행복한 하루를 기원합니다.<br />-->


<!--메인화면  *배경에 사진깔기*-->
<div class="container">
  <div class="text-center">
    <div class="p-4 p-md-5 mb-4 rounded text-bg-dark">
      <h1 class="display-4 mt-5 mb-4">무릉서원에 오신것을 환영합니다~</h1>
      <p class="lead my-3">무릉서원 관리자 메인페이지입니다.</p>
    </div>
  </div>
</div>

<!--바로가기 카드-->
<div class="container mt-15 text-center">
<div class="row">
  <div class="col-sm-4">
    <div class="card">
      <div class="card-body">
        <h5 class="card-title">무릉서원 소개</h5>
        <p class="card-text">무릉서원의 탄생과정, 운영목적과 만든이를 소개하는 페이지입니다.</p>
        <a href="#" class="btn btn-primary">바로가기</a>
      </div>
    </div>
  </div>
  <div class="col-sm-4">
    <div class="card">
      <div class="card-body">
        <h5 class="card-title">공지사항</h5>
        <p class="card-text">무릉서원 이용자에게 당부하는 공지를 올리고 수정/삭제 할 수 있는 공간입니다.</p>
        <a href="#" class="btn btn-primary">바로가기</a>
      </div>
    </div>
  </div>
  <div class="col-sm-4">
    <div class="card">
      <div class="card-body">
        <!--없어도 됩니다-->
        <h5 class="card-title">검색서비스</h5>
        <p class="card-text">무릉서원의 도서/영상 데이터베이스를 통해 검색을 지원합니다.</p>
        <a href="#" class="btn btn-primary">바로가기</a>
      </div>
    </div>
  </div>
</div>
</div>

<!--<main class="flex-shrink-0">
  <div class="container">
    <h1 class="mt-5">무릉서원 관리자 메인페이지입니다.</h1>
    <p class="lead">Pin a footer to the bottom of the viewport in desktop browsers with this custom HTML and CSS. A fixed navbar has been added with <code class="small">padding-top: 60px;</code> on the <code class="small">main &gt; .container</code>.</p>
    <p>Back to <a href="/docs/5.2/examples/sticky-footer/">the default sticky footer</a> minus the navbar.</p>
  </div>
</main>-->

<footer class="footer mt-auto py-3 bg-light">
  <div class="container">
    <span class="text-muted">무릉서원 관리자 페이지</span>
  </div>
</footer>

    <!--<script src="https://code.jquery.com/jquery-3.6.3.min.js" integrity="sha256-
    pvPw+upLPUjgMXY0G+8O0xUf+/Im1MZjXxxgOcBQBXU=" crossorigin="anonymous"></script>-->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"
    integrity="sha384-Fy6S3B9q64WdZWQUiU+q4/2Lc9npb8tCaSX9FK7E8HnRr0Jz8D6OP9dO5Vg3Q9ct"
    crossorigin="anonymous">
    </script>
</body>
</html>