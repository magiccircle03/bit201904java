<%@page import="dateShare.Model.Food"%>
<%@page import="dateShare.service.food.ViewFoodDetailService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
	int f_num= Integer.parseInt(request.getParameter("f_num"));
	ViewFoodDetailService service = ViewFoodDetailService.getInstance();
	Food food = service.viewDetail(f_num);
%>
<html>
<head>
<meta charset="UTF-8">
<title>DATE SHARE</title>
<link href="../css/index.css" rel="stylesheet" type="text/css">
<style></style>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
</head>
<!-- 빈으로 가져오기 -->
<body>
	<div id="wrap">
		<div id="main_wrap">
			<div id="header">
				<%@include file="../frame/my.jsp"%>
				<%@include file="../frame/header.jsp"%>
			</div>
			<div id="nav">
				<%@include file="../frame/nav.jsp"%>
			</div>
			<div id="content">
				<h3>
					상세보기 페이지<br>
					<br>
					글번호 : <%= food.getF_num()%> <br>
					작성자번호 : <%= food.getU_num()%> <br>
					작성일 : <%= food.getF_writedate()%><br>
					조회수 : <%= food.getF_hits()%> <br>
					좋아요 : <%= food.getF_like()%> <br>
					글제목 : <%= food.getF_title()%> <br>
					글내용 : <%= food.getF_content()%> <br>
					평점 : <%= food.getF_star()%> <br>
					사진경로 : <%= food.getF_path()%> <br>
					
				</h3>
			</div>
			<div id="footer">
				<%@include file="../frame/footer.jsp"%>
			</div>
		</div>
	</div>
</body>
</html>