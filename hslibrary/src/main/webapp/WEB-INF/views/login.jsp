<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
    <meta charset="UTF-8">
    <title>무릉서원</title>
</head>
<body>
<h2>
    로그인페이지입니다.
</h2>
<form name="login" method="post" action="login">
    아이디 : <input type="text" id="inputStaffNUM" name="inputStaffNUM" required autofocus required><br>
    비밀번호 : <input type="password" id="inputPW" name="inputPW" required><br>
    <input type="submit" value="로그인">
</form>

</body>
</html>