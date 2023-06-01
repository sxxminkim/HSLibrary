<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<!--navbar-->
<nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
        <div class="container-fluid">
                <a class="navbar-brand" href="${pageContext.request.contextPath}/adminIndex">무릉서원</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse justify-content-end" id="navbarCollapse">
                        <ul class="navbar-nav me-auto mb-2 mb-md-0">
                                <li class="nav-item">
                                        <a class="navbar-brand" href="${pageContext.request.contextPath}/memberAdd">회원관리</a>
                                </li>
                                <li class="nav-item">
                                        <a class="navbar-brand" href="${pageContext.request.contextPath}/bookAdd">도서관리</a>
                                </li>
                                <li class="nav-item">
                                        <a class="navbar-brand" href="${pageContext.request.contextPath}/videoAdd">영상관리</a>
                                </li>
                                <li class="nav-item">
                                        <a class="navbar-brand" href="${pageContext.request.contextPath}/bookRent">도서대여/반납/연장</a>
                                </li>
                                <li class="nav-item">
                                        <a class="navbar-brand" href="${pageContext.request.contextPath}/videoRent">영상대여/반납/연장</a>
                                </li>
                                <li class="nav-item">
                                        <a class="navbar-brand" href="${pageContext.request.contextPath}/VideoRoomRent">영상관람실</a>
                                </li>
                                <li class="nav-item">
                                        <a class="navbar-brand" href="${pageContext.request.contextPath}/noticeAdd">공지사항</a>
                                </li>
                        </ul>
                        <button type="button" class="btn btn-light ml-3"><a href="<c:url value="/logout"/>" > logout </a></button>
                </div>
        </div>
</nav>
