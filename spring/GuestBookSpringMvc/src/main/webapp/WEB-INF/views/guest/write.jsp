<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- jstl 사용위해서 추가하고 -->
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
	<h1>${resultCnt}</h1>
	<h1>
		<%-- 만약 문자열로 보낸다면 이래도됨 <c:if test="${resultCnt=='1'}"> --%>	
		<!-- EL익셉션.. 괄호치니까 되었다! 연산자우선순위상 -->	
		<c:if test="${resultCnt==1}">
			정상적으로 입력되었습니다.
		</c:if>
		<c:if test="${!(resultCnt==1)}">
			입력 실패!
		</c:if>
	</h1>
	
	<!-- url태그는 이 앞쪽에 자동으로 컨텍스트경로 넣어준다했음! -->
	<a href="<c:url value="/guest/list"/>">리스트</a>
</body>
</html>