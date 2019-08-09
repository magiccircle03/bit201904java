<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<link href="<c:url value="/css/default.css"/>" rel="stylesheet" type="text/css">
<style>
	input[type = checkbox] {
		display: none;
	}
	.color_red {
		color : red;
	}
	.color_blue {
		color : blue;
	}
</style>
</head>
<body>

<!-- 절대경로 처리 -->
<!-- 해더 시작 -->
<%@ include file="/WEB-INF/views/frame/header.jsp" %>
<!-- 해더 끝 -->

<!-- 네비게이션 시작 -->
<%@ include file="/WEB-INF/views/frame/nav.jsp" %>
<!-- 네비게이션 끝 -->

<!-- 컨텐츠 시작 -->
<div id="contents">
	<h3>회원가입 페이지</h3>
	<hr>
	<!-- 액션이 없으니 자기자신으로 -->
	<form id="regForm" method="post" enctype="multipart/form-data">
		<table>
			<tr>
				<td>아이디(이메일)</td>
				<td>
				<input type="checkbox" id="idcheck">
				<input type="email" id="uId" name="uId" required><span id="idcheckmsg"></span> </td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td><input type="password" name="uPW" required> </td>
			</tr>
			<tr>
				<td>이름</td>
				<td><input type="text" name="uName" required> </td>
			</tr>
			<tr>
				<td>사진</td>
				<td><input type="file" name="uPhoto"> </td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="회원가입"> </td>
			</tr>
		</table>
	</form>
</div>
<!-- 컨텐츠 끝 -->


<!-- 푸터 시작 -->
<%@ include file="/WEB-INF/views/frame/footer.jsp" %>
<!-- 푸터 끝 -->

<script>

	/* $(document).ready(function() {
		
		$('#uId').focusout(function() {
			// ajax 비동기 통신 : id 전송하고 사용유무에 대한 결과 값을 반환 
			
		
			$.ajax({
				url : 'idCheck.do',
				// (그냥 겟으로 보내보겠음. 생략하면 겟임. 스프링가면 나눈다했지만 지금은 안 나누니 뭘 해도 상관x)
				type : 'get',
				data : {
						id : $(this).val()	// (이벤트가 발생한 거니까 캐스팅 따로 안 하고 this로 쓸 수 있다!)
				},
				success: function(data) {
					// alert(data);
					$('#idcheckmsg').html('');
					$('#idcheckmsg').removeClass('color_red');
					$('#idcheckmsg').removeClass('color_blue');
					if(data == 'Y'){
						$('#idcheck').prop('checked', true);
						$('#idcheckmsg').html('사용가능한 멋진 아이디입니다!');
						$('#idcheckmsg').addClass('color_blue');
						
					}else{
						$('#idcheck').prop('checked', false);
						$('#idcheckmsg').html('사용중인 아이디이거나 탈퇴한 아이디 입니다!');
						$('#idcheckmsg').addClass('color_red');
					}
				}
			});
		});
		
		$('#regform').submit(function() {
			if(!$('#idcheck').prop('checked')){
				alert('아이디 중복확인이 필요합니다.');
				return false;
			}
		});
		
	});
	*/
</script>

</body>
</html>