<%@page import="dateShare.Model.Food"%>
<%@page import="dateShare.service.food.ViewFoodDetailService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
	int f_num = Integer.parseInt(request.getParameter("f_num"));
	ViewFoodDetailService service = ViewFoodDetailService.getInstance();
	Food food = service.viewDetail(f_num);

	session = request.getSession(false);
	LoginInfo currentUser = (LoginInfo) session.getAttribute("userInfo");
%>
<html>
<head>
<meta charset="UTF-8">
<title>DATE SHARE</title>

<link href="../css/index.css" rel="stylesheet" type="text/css">
<style>
#detail_wrap {
	width: 940px;
	margin: 0 auto;
	padding: 20px;
}

#pic {
	width: 50%;
	float: left;
}
</style>
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
				<h3>상세보기 페이지</h3>
				<br>

				<div id="detail_wrap">
					<div id="pic">
						<img src="<%=food.getF_path()%>">
					</div>
					<div id="txt">
						<p>
							별점
							<%=food.getF_star()%></p>
						<p>
							제목
							<%=food.getF_title()%></p>
						<div>
							<p>
								글쓴이
								<%=food.getU_num()%></p>
							<p>
								작성시간
								<%=food.getF_writedate()%></p>
						</div>

						<p>
							내용
							<%=food.getF_content()%></p>
						<p>
							<a href="likeProcess.jsp?f_num=<%=food.getF_num()%>">좋아요</a> :<%=food.getF_like()%>
						</p>
						<form id="likeForm">
							<input type="button" onclick="like()" value="좋아요">
						</form>
					</div>


					<br>
					<%-- 					<a href="confirmDeletion.jsp?f_num=<%=food.getF_num()%>">삭제하기</a> 
					<a href="confirmEdit.jsp?f_num=<%=food.getF_num()%>">수정하기 </a> --%>

						
							<button type="button" onclick="editFood(<%=food.getF_num()%>, <%=currentUser.getU_num()%>, <%=food.getU_num()%>)">수정</button>
							<button type="button" onclick="deleteFood(<%=food.getF_num()%>, <%=currentUser.getU_num()%>, <%=food.getU_num()%>)">삭제</button>
					
				</div>

			</div>
			<div id="footer">
				<%@include file="../frame/footer.jsp"%>
			</div>
		</div>
	</div>
</body>

<script>

	// 일단 해보고 되면 자신의 글만 수정삭제 버튼이 보이게 개선하자
	function editFood(f_num, currentUser_num, writeUser_num) {
		alert('수정하기 눌렀으니 여기서 유효성(글쓴 유저와 세션 유저가 같은가) 체크');
		if((currentUser_num == writeUser_num) && (confirm("게시글을 수정하시겠습니까?"))){
			alert("같음! 현재로그인유저번호:"+currentUser_num+"\n 글쓴유저번호:"+writeUser_num);
 			/* 
			$.ajax ({
				url: 'editFoodForm.jsp',
				type: 'post',
				data : {
					f_num : f_num,
					u_num : currentUser_num
				},
				success: function(){
					alert('ㅁㅁ');
				}
			});  */
		}else{
			alert("다름! 현재로그인유저번호:"+currentUser_num+"\n 글쓴유저번호:"+writeUser_num);
			alert("자신이 작성한 글만 수정 가능합니다!");
		}
		return false;
	}

	// 삭제는 일단 성공@
	function deleteFood(f_num, currentUser_num, writeUser_num) {
		//alert('삭제하기 눌렀으니 여기서 유효성(글쓴 유저와 세션 유저가 같은가) 체크');
		//같으면,
		if((currentUser_num == writeUser_num) && (confirm("정말 삭제하시겠습니까?"))){
			//alert("같음! 현재로그인유저번호:"+currentUser_num+"\n 글쓴유저번호:"+writeUser_num);
 			$.ajax ({
				url: 'deleteFood.jsp',
				type: 'post',
				data : {
					f_num : f_num,
					u_num : currentUser_num
				},
				success: function(data){
					alert($.trim(data)+'번 글이 삭제되었습니다.');
					location.href="foodList.jsp";
				}
			}); 
		}else{
			//alert("다름! 현재로그인유저번호:"+currentUser_num+"\n 글쓴유저번호:"+writeUser_num);
			alert("자신이 작성한 글만 삭제 가능합니다!");
		}
	}


	
	function like() {
		alert('버튼 누르면 여기서 좋아요 처리하게');
	}
</script>
</html>