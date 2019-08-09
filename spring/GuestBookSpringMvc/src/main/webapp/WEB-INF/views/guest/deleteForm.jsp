<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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

	<h1>메시지 삭제 비밀번호 확인</h1>
	<!-- 액션 지우면 자기 자신으로 온댔져? -->
	<form method="post">
<!-- 	<form action="guestDel" method="post"> -->
	<!-- el에서 제공하는 내장객체 파람은 파라미터 데이터를 맵형식으로 저장해서 제공해준다고했음 서비스나 얘나 같은 ~를 공유ㅗ하여 사용하기 때문에 
	만약  ${param.messageId} 이렇게 쓰면 DeleteFormService 에서 
		String id = request.getParameter("messageId");
		request.setAttribute("messageId", id);
		이 코드를 사용하지 않고도 사용할 수 있다. 그걸 확인하려고 파람.메시지아이디 해봤음-->
		${param.messageId}번메시지를 삭제하시려면 비밀번호를 입력하세요.<br>
		<%-- <input type="hidden" name="messageId" value="${messageId}"> --%>
		<input type="hidden" name="messageId" value="${param.messageId}">
		
		비밀번호 <input type="password" name="password" required> <br>
		<input type="submit" value="메시지삭제">	
	
	</form>

</body>
</html>