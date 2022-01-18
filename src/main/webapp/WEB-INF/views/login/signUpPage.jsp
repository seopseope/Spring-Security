<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html>
<head>
	<title>Insert title here</title>
	<meta charset="UTF-8" />
	<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
</head>
<body>
	<h5>회원가입</h5>
	<form action="#" method="POST" id="frm" name="frm">
		아이디:<input type="text" id="userId" name="userId"/>
			 <button class="btn-idChk">아이디 중복체크</button>
			 <span class="id-chk-txt"></span><br/>
		비밀번호:<input type="password" id="userPwd" name="userPwd"/><br/>
		비밀번호확인:<input type="password" id="userPwdOk" name="userPwdOk"/><br/>
		이름:<input type="text" id="userName" name="userName"/><br/>
		<c:forEach var="authList" items="${authList}">
			권한 : <input type="checkbox" class="checkbox-chk" name="userAuth" value="${authList}"/><label>${authList}</label><br/>
		</c:forEach>
		<a href="#" class="btn-signUp">회원가입하기</a>
	</form>
</body>
<script type="text/javascript">
	let obj = {};
	function addButtonEvent(){
		$(".btn-signUp").click(function(e){
			e.preventDefault();
			fnSignUp();
		});
		
		$(".btn-idChk").click(function(e){
			e.preventDefault();
			$(".id-chk-txt").empty();
			
			fnCheckId();
		});
	}
	
	function fnCheckId(){
		obj.userId = $("input[name='userId']").val();
		
		if(obj.userId == null || obj.userId == ""){
			alert("아이디를 입력하세요.");
			$("#userId").focus();
			return false;
		}
		
		$.ajax({
			  url : "<c:url value="/login/userIdChk.do"/>"
			, type : "POST"
			, data : obj
			, dataType : "json"
			, isModal : true
			, isModalEnd : true
			, success : function(data){
				if(data.callbackType == "success"){
					if(data.chkId == "null"){
						$(".id-chk-txt").append("사용 가능한 아이디입니다.");
					}else{
						$(".id-chk-txt").append("중복된 아이디가 존재합니다.");
					}
				}else{
					alert(data.message);
				}
			}
		});
	}
	
	function fnSignUp(){
		
		let data = $("#frm").serialize();
		let userId = $("input[name='userId']").val();
		let userPwd = $("input[name='userPwd']").val();
		let userPwdOk = $("input[name='userPwdOk']").val();
		let userName = $("input[name='userName']").val();
		let authChkbox = $(".checkbox-chk").is(":checked");		
		
		// 아이디 null 체크
		if(userId == null || userId == ""){
			alert("아이디를 입력하세요.");
			$("#userId").focus();
			return false;
		}
		
		// 비밀번호 null 체크
		if(userPwd == null || userPwd == ""){
			alert("비밀번호를 입력하세요.");
			$("#userPwd").focus();
			return false;
		}
		
		// 비밀번호확인 null 체크
		if(userPwdOk == null || userPwdOk == ""){
			alert("비밀번호 확인을 입력하세요.");
			$("#userPwdOk").focus();
			return false;
		}
		
		// 비밀번호 값 체크
		if(userPwd != userPwdOk){
			alert("비밀번호와 비밀번호확인값이 맞지 않습니다. 다시확인해주세요.");
			$("#userPwdOk").focus();
			return false;
		}
		
		// 이름 null 체크
		if(userName == null || userName == ""){
			alert("이름을 입력하세요.");
			$("#userName").focus();
			return false;
		}
		
		// 체크박스 체크 여부
		if(authChkbox == false){
			alert("체크박스를 선택하세요.");
			return false;
		}
		
		$.ajax({
			 url : "<c:url value="/login/signUp_ajax.do"/>"
			, type : "POST"
			, data : data
			, isModal : true
			, isModalEnd : true
			, success : function(data){
				if(data.callbackType == 'success'){
					alert(data.successMessage);
					location.href = "<c:url value="/login/loginPage.do"/>";
				}else{
					alert(data.failMessage);
				}
			}
		});
	}
	
	$(function(){
		addButtonEvent();
	});
</script>
</html>