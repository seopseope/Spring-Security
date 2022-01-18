<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html>
<head>
<title>HOME</title>
<meta charset="UTF-8" />
</head>
<body>
	
	<sec:authorize access="isAnonymous()">
		<h3><a href="<c:url value="/login/loginPage.do"/>">로그인</a></h3>
	</sec:authorize>
	
	<sec:authorize access="isAuthenticated()">
		<h3>${userName}님 안녕하세요. ${userName}님의 권한은 ${userAuth}입니다.</h3>
		
		<form action="/logout" method="POST">
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
			<button type="submit">로그아웃</button>
		</form>
		
		<h2><a href="<c:url value="/guestPage.do"/>">GUEST</a></h2>
		<h2><a href="<c:url value="/user/userPage.do"/>">USER</a></h2>
		<h2><a href="<c:url value="/member/memberPage.do"/>">MEMBER</a></h2>
		<h2><a href="<c:url value="/admin/adminPage.do"/>">ADMIN</a></h2>
	</sec:authorize>
</body>
</html>