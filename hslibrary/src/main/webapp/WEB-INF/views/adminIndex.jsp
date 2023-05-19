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
        .table1{
            border: 1px solid #444444;
            font-size: 15px;
            width: 800px;
            height:150px;
            outline: dashed 1px black;
            text-align: center;
        }
        .table2{
            border: 1px solid #444444;
            font-size: 15px;
            width: 450px;
            height:150px;
            outline: dashed 1px black;
            text-align: center;
        }
        th, td {
            border: 1px solid #444444;
            padding: 5px;
        }
    </style>
</head>
<body>
<header>
    <button><a href="<c:url value="/logout"/>">로그아웃</a></button>
    <button><a href="<c:url value="./adminIndex"/>">홈</a></button>
</header>
<jsp:include page="navbar.jsp"></jsp:include>
무릉서원 관리자 메인페이지입니다.
<%
    // 세션값 가져오기
    if (session.getAttribute("loginStaffName") == null)
    {
        // Object 타입이므로 다운캐스팅
        response.sendRedirect("./login");
        session.setAttribute("loginMsg", "로그인 후 이용해주세요.");
    }
%>

<%=session.getAttribute("loginStaffName")%>님 안녕하세요! <br />
<%=session.getAttribute("loginStaffName")%>님의 행복한 하루를 기원합니다.<br />
비밀번호를 바꾸고 싶으시면 <a href="./adminChangePW">비밀번호 변경</a> 으로 이동해주세요.
+<!--메인화면  *배경에 사진깔기*-->
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
<!--
<div>
    <h3>공지사항</h3>
    <table class="table1">
        <thead>
        <tr>
            <th>공지 번호</th>
            <th>제목</th>
            <th>날짜</th>
            <th>작성자</th>
            <th>공지 링크</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="noticeDTO" items="${noticeList}">
            <tr>
                <td>${noticeDTO.noticeNUM}</td>
                <td>${noticeDTO.noticeTitle}</td>
                <td>${noticeDTO.noticeDate}</td>
                <td>${noticeDTO.noticeAuthor}</td>
                <td><input type="button" value="자세히" onclick="location.href='${pageContext.request.contextPath}/notice_subview?noticeNUM=${noticeDTO.noticeNUM}'" /></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<div style="float:left;margin-right:20px">
<h3>도서/영상 자료</h3>
    <table class="table1">
        <thead>
        <tr>
            <th>별치기호</th>
            <th>제목</th>
            <th>권</th>
            <th>출간 판</th>
            <th>출간 연도</th>
            <th>ISBN</th>
            <th>저자</th>
            <th>출판사</th>
            <th>분류</th>
            <th>종이책(1)/eBook(2)</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="bookDTO" items="${bookList}">
            <tr>
                <td>${bookDTO.bookID}</td>
                <td>${bookDTO.bookName}</td>
                <td>${bookDTO.bookVolume}</td>
                <td>${bookDTO.bookEdition}</td>
                <td>${bookDTO.bookYear}</td>
                <td>${bookDTO.bookISBN}</td>
                <td>${bookDTO.bookWriter}</td>
                <td>${bookDTO.bookCompany}</td>
                <td>${bookDTO.bookGenre}</td>
                <td>${bookDTO.bookType}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<div style="float:left;margin-left:20px">
    <table class="table2">
        <thead>
        <tr>
            <th>영상번호</th>
            <th>영상제목</th>
            <th>감독</th>
            <th>배급사</th>
            <th>개봉연도</th>
            <th>장르</th>
            <th>시리즈</th>
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
<h2>도서/영상 연체 회원</h2>
<div>
    <table class="table1">
        <thead>
        <tr>
            <th>회원ID</th>
            <th>회원이름</th>
            <th>회원주민등록번호</th>
            <th>회원휴대폰번호</th>
            <th>회원주소</th>
            <th>회원이메일</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="memberDTO" items="${bookBlackList}">
            <tr>
                <td>${memberDTO.clientNUM}</td>
                <td>${memberDTO.clientName}</td>
                <td>${memberDTO.clientID}</td>
                <td>${memberDTO.clientPhone}</td>
                <td>${memberDTO.clientAddr}</td>
                <td>${memberDTO.clientEmail}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<div>
    <table class="table1">
        <thead>
        <tr>
            <th>회원ID</th>
            <th>회원이름</th>
            <th>회원주민등록번호</th>
            <th>회원휴대폰번호</th>
            <th>회원주소</th>
            <th>회원이메일</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="memberDTO" items="${videoBlackList}">
            <tr>
                <td>${memberDTO.clientNUM}</td>
                <td>${memberDTO.clientName}</td>
                <td>${memberDTO.clientID}</td>
                <td>${memberDTO.clientPhone}</td>
                <td>${memberDTO.clientAddr}</td>
                <td>${memberDTO.clientEmail}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
-->
</body>
</html>