<%@page import="dateShare.service.user.UpdateMemberService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// 1. 폼으로 부터 데이터를 받는다.
	// 2. Message 객체에 저장 : useBean 액션테그
	// 3. WriteMessageService  객체 생성
	// 4. write 메서드 실행  : 1/0
%>
<jsp:useBean id="user" class="dateShare.Model.DateUser"/>
<jsp:setProperty property="*" name="user"/>
<%
	UpdateMemberService service = UpdateMemberService.getInstance();
	int cnt = service.update(user);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<style>
</style>
</head>
<body>

	<script>
	alert("회원정보가 수정되었습니다.")
	location.href="../main.jsp"; 
	</script>
	
</body>
</html>