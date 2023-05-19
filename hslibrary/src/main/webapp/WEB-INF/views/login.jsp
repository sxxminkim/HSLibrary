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
<html lang="ko">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>무릉서원 로그인</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">

    <style>
        *{
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body{
            height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
            background: #d9d9d9;
        }

        .login{
            width: 360px;
            height: min-content;
            padding: 20px;
            border-radius: 12px;
            background: #ffffff;
        }

        .login h1{
            font-size: 20px;
            margin-bottom: 25px;
        }

        .login form{
            font-size: 20px;
        }

        .login form .form-group{
            margin-bottom: 12px;
        }

        .login form input[type="submit"]{
            font-size: 20px;
            margin-top: 15px;
        }
    </style>

</head>
<body>
    <div class="login">
        <h1 class="text-center">Please sign in</h1>
        <form name="login" method="post" action="login">
            <div class="form-group">
                <label class="form-label" for="inputStaffNUM">ID</label>
                <input class="form-control" type="text" id="inputStaffNUM" name="inputStaffNUM">
            </div>

            <div class="form-group">
                <label class="form-label" for="inputPW">Password</label>
                <input class="form-control" type="password" id="inputPW" name="inputPW">
            </div>

            <div class="form-group form-check">
                <input class="form-check-input" type="checkbox" id="check">
                <label class="form-check-label" for="check">Remember me</label>
            </div>

            <input class="btn btn-success w-100" type="submit" value="Sign in">
        </form>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
</body>
</html>