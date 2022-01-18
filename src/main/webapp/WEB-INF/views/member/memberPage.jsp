<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html>
<head>
	<title>MEMBER PAGE</title>
	<meta charset="UTF-8" />
	<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
</head>
<body>
	<h3>MEMBER PAGE</h3>
	<a href="#" class="move-btn">목록으로</a>
</body>
<script type="text/javascript">
	function addButtonEvent(){
		$(".move-btn").click(function(){
			location.href = "<c:url value="/"/>";
		});
	}
	
	$(function(){
		addButtonEvent();
	});
</script>
</html>