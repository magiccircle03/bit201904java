<%@page import="dateShare.service.food.EditFoodService"%>
<%@ page import="dateShare.service.food.WriteFoodService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
	//request.setCharacterEncoding("utf-8");
%>
<jsp:useBean id="food" class="dateShare.Model.Food" />
<jsp:setProperty property="*" name="food" />
<%
	EditFoodService service = EditFoodService.getInstance();
	int cnt = service.edit(food);
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>
	글번호 : <%= food.getF_num()%>
		<%= cnt > 0 ? "게시글을 수정하였습니다" : "게시글 수정 실패"%>
	</h1>
	<a href="foodList.jsp">목록보기</a>
</body>
</html>