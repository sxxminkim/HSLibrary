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

<h2>무릉서원 도서 추가 페이지입니다.</h2>
<div>
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
                <td>${bookDTO.bookID}</td>
                <td>${bookDTO.bookName}</td>
                <td>${bookDTO.bookVolume}</td>
                <td>${bookDTO.bookEdition}</td>
                <td>${bookDTO.bookYear}</td>
                <td>${bookDTO.bookISBN}</td>
                <td>${bookDTO.bookWriter}</td>
                <td>${bookDTO.bookCompany}</td>
                <td>${bookDTO.bookGenre}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<div>
    <form action="./bookAdd" method="post" enctype="multipart/form-data">
        bookID:<input class="form-control" id="inputBookID" type="text" name="inputBookID"/>
        책제목:<input class="form-control" id="inputBookName" type="text" name="inputBookName"/>
        지은이:<input class="form-control" id="inputBookWriter" type="text" name="inputBookWriter"/>
        장르:<input class="form-control" id="inputBookGenre" type="text" name="inputBookGenre"/>
        출판사:<input class="form-control" id="inputBookCompany" type="text" name="inputBookCompany"/>
        ISBN10:<input class="form-control" id="inputBookISBN" type="text" name="inputBookISBN"/>
        bookYear:<input class="form-control" id="inputBookYear" type="text" name="inputBookYear"/>
        bookEdition:<input class="form-control" id="inputBookEdition" type="text" name="inputBookEdition"/>
        bookVolume:<input class="form-control" id="inputBookVolume" type="text" name="inputBookVolume"/>
        bookIssue:<input class="form-control" id="inputBookIssue" type="text" name="inputBookIssue"/>
        bookSummary:<input class="form-control" id="inputBookSummary" type="text" name="inputBookSummary"/>
        <input type="submit" value="책 추가">
    </form>
</div>

</body>
</html>