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
								작성자
								<%-- <%=food.getU_num()%> --%>
								<%=food.getU_name()%></p>
							<p>
								작성시간
								<%=food.getF_writedate()%></p>
						</div>

						<p>
							내용
							<%=food.getF_content()%></p>
<%-- 						<p>
							<a href="likeProcess.jsp?f_num=<%=food.getF_num()%>">좋아요</a> :<%=food.getF_like()%>
						</p> --%>
						<p>좋아요 : <p id="likeCount"><%=food.getF_like()%></p></p>
						<form id="likeForm">
							<input type="button" onclick="like(<%=food.getF_num()%>)" value="좋아요">
						</form>
					</div>


					<br>
					<%-- <a href="confirmDeletion.jsp?f_num=<%=food.getF_num()%>">삭제하기</a> 
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
//좋아요
function like(f_num) {
	//alert('버튼 누르면 여기서 좋아요 처리하게');
	
	$.ajax ({
		url: 'likeProcess.jsp?f_num='+f_num,
		type: 'post',
		data : {
			f_num : f_num
		},
		success: function(data){
			$('#likeCount').html(data);
		}
		});
	
}


// 게시글 수정
function editFood(f_num, currentUser_num, writeUser_num) {
	var answer =  confirm("게시글을 수정하시겠습니까?");
	if (!answer){
		//alert("취소하였습니다.");
	} else if(currentUser_num == writeUser_num){
		//alert("같음! 현재로그인유저번호:"+currentUser_num+"\n 글쓴유저번호:"+writeUser_num);
			$.ajax ({
			url: 'editFoodForm.jsp',
			type: 'post',
			data : {
				f_num : f_num,
				u_num : currentUser_num
			},
			success: function(data){
				alert('쨘');
				location.href='editFoodForm.jsp?f_num='+f_num;
			}
		}); 
	} else{
		alert('자신이 작성한 글만 수정 가능합니다!');
	}
}

	
	// 게시글 삭제
	function deleteFood(f_num, currentUser_num, writeUser_num) {
		var answer =  confirm("정말 삭제하시겠습니까?");
		if (!answer){
			//alert("취소하였습니다.");
		} else if(currentUser_num == writeUser_num){
			//alert("같음! 현재로그인유저번호:"+currentUser_num+"\n 글쓴유저번호:"+writeUser_num);
 			$.ajax ({
				url: 'deleteFood.jsp',
				type: 'post',
				data : {
					f_num : f_num,
					u_num : currentUser_num
				},
				//success: function(data){
				success: function(data){
					alert($.trim(data)); //삭제했는지 성공했는지 
					location.href="foodList.jsp";
				}
			}); 
		} else{
			alert('자신이 작성한 글만 삭제 가능합니다!');
		}
	}
	
	

</script>
</html>