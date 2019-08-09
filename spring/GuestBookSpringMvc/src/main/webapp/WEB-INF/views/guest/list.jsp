<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
div{
	border: 1px solid #888;
	margin: 10px;
	width: 200px;
}
</style>
</head>
<body>
	
	<h1>방명록</h1>
	<div style="text-align: right; border:0;">	
		<a href="<c:url value="/guest/writeForm"/>">글쓰기</a>
	</div>
	
	
	<c:if test="${viewData.messageTotalCount>0}">
	
	<c:forEach items="${viewData.messageList}" var="message">
		<div>
		메시지 번호 : ${message.id}<br> 
		손님 이름 : ${message.guestName}<br>
		메시지 : ${message.message}<br> 
		<a href="delete?messageId=${message.id}">삭제하기</a>
		</div>
	</c:forEach>
	<!-- 상대경로 -->
	<c:forEach begin="1" end="${viewData.pageTotalCount}" step="1" var="num">
		<a href="list?page=${num}">[${num}]</a> 
	</c:forEach>

	</c:if>
	
	
	
</body>
</html>