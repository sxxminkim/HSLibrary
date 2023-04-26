<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>무릉서원</title>
</head>
<body>
<header>
    <button><a href="<c:url value="/logout"/>">로그아웃</a></button>
</header>
<jsp:include page="navbar.jsp"></jsp:include>
<h2>무릉서원 관리자 삭제페이지</h2>
<h3>리스트에서 퇴사한 관리자를 찾아보세요.</h3>
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