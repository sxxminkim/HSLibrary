<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>


<nav id="menu">
    <ul>
        <li><a href="#">회원관리</a>
            <ul>
                <li><a href="./memberAdd">회원추가</a></li>
                <li><a href="./memberUpdate">회원수정</a></li>
                <li><a href="./memberDelete">회원삭제</a></li>
                <li><a href="./adminRegister">관리자추가</a></li>
                <li><a href="./adminDelete">관리자삭제</a></li>
            </ul>
        </li>
        <li><a href="#">도서관리</a>
            <ul>
                <li><a href="./bookAdd">도서추가</a></li>
                <li><a href="./bookUpdate">도서수정</a></li>
                <li><a href="./bookDelete">도서삭제</a></li>
            </ul>
        </li>
        <li><a href="#">영상관리</a>
            <ul>
                <li><a href="./videoAdd">영상추가</a></li>
                <li><a href="./videoUpdate">영상수정</a></li>
                <li><a href="./videoDelete">영상삭제</a></li>
            </ul>
        </li>
        <li><a href="#">도서대여/반납/연장</a>
            <ul>
                <li><a href="./bookRent">도서대여</a></li>
                <li><a href="./bookReturn">도서반납</a></li>
                <li><a href="./bookExtend.jsp">도서연장</a></li>
            </ul>
        </li>
        <li><a href="#">영상대여/반납</a>
            <ul>
                <li><a href="./videoRent">영상대여</a></li>
                <li><a href="./videoReturn">영상반납</a></li>
                <li><a href="./videoExtend.jsp">영상연장</a></li>
            </ul>
        </li>
        <li><a href="#">영상관람실</a>
            <ul>
            <li><a href="./videoRoom">영상관람실 예약</a></li>
                <!--
            <li><a href="#">영상관람실 추가</a></li>
            <li><a href="#">영상관람실 삭제</a></li>
            -->
            </ul>
        </li>
    </ul>
</nav>
