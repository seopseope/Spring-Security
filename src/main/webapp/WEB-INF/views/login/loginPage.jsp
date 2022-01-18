<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html>
<head>
	<title>Security 로그인 페이지</title>
	<meta charset="UTF-8" />
	<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
</head>
<body>
	<h1>LOGIN PAGE</h1>
	<c:if test="${LoginFailMessage != null}">
			<p style="color:red">Error: ${LoginFailMessage}</p>
	</c:if>
	<form action="/login" method="post">
		<input name="${_csrf.parameterName}" type="hidden" value="${_csrf.token}"/>
		<input type="text" name="loginId" placeholder="아이디"/><br/>
		<input type="password" name="loginPwd" placeholder="비밀번호"/>
		<button type="submit">Sign in</button><br/>
		
		<label for="remember-me">자동 로그인</label>
		<input type="checkbox" id="remember-me" name="remember-me"/>
	</form>
</body>
<script type="text/javascript">
</script>
</html>