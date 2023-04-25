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

<h2>무릉서원 도서 삭제 페이지입니다.</h2>
<h3>삭제하실 도서를 확인해보세요.</h3>
<table>
    <thead>
    <tr>
        <th>도서번호</th>
        <th>도서제목</th>
        <th>volume</th>
        <th>edition</th>
        <th>발행년도</th>
        <th>ISBN</th>
        <th>저자</th>
        <th>출판사</th>
        <th>분류</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="bookDTO" items="${bookList}">
        <tr>
            <td>${bookDTO.bookNUM}</td>
            <td>${bookDTO.bookName}</td>
            <td>${bookDTO.bookVolume}</td>
            <td>${bookDTO.bookEdition}</td>
            <td>${bookDTO.bookRelease}</td>
            <td>${bookDTO.bookISBN}</td>
            <td>${bookDTO.bookWriter}</td>
            <td>${bookDTO.bookCompany}</td>
            <td>${bookDTO.bookGenre}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>