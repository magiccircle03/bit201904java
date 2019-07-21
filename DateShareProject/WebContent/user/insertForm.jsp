<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>DATE SHARE</title>
</head>
<link href="../css/index.css" rel="stylesheet" type="text/css">
<style></style>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script>
$(document).ready(function () {

		$('#u_id').focusout(function() {
        		
        		$('#idchkMsg').html("");
        		
        		$.ajax({
                    url: 'idCheck.jsp',
                    type: 'GET',
                    data: {
                        u_id: $('#u_id').val()
                    },
                    success: function (data) {
                    	if(data=='Y') {
                    		$('#idchkMsg').html('사용가능한 아이디입니다.');
                    		$('#idchkMsg').css('color','green');
                    	}else if(data== 'N') {
                    		$('#idchkMsg').html('사용할수없는 아이디입니다.');
                    		$('#idchkMsg').css('color','red');
                    	}else {
                    		$('#idchkMsg').html('필수사항입니다.');
                    		$('#idchkMsg').css('color','red');
                    	}
                    }
                });
        	});
});
    </script>
</head>
<body>
	<div id="wrap">
		<div id="main_wrap">
			<div id="header">
				<%@include file="../frame/header.jsp"%>
			</div>
			<div id="content">
				<div id="login_form_wrap">
					<h1>회원가입</h1>
					<form action="insertMember.jsp" method="post">
						<div>
							ID<input class="input_t" id="u_id" type="text" name="u_id" required>
							<h5 id="idchkMsg"></h5>
						</div>
						<div>
							PW<input class="input_t" type="password" name="u_pw">
						</div>
						<div>
							이름<input class="input_t" type="text" name="u_name" required>
						</div>
						<div>
							생년월일<input class="input_t" type="date" name="u_bday" required>
						</div>
						<div>
							성별 <br>
							<span class="input_r"><input type="radio" name=u_gender
								value="M">남자</span> <span class="input_r"><input
								type="radio" name=u_gender value="F">여자</span>
						</div>
						<div>
							<input class="input_b" type="submit" value="회원가입"> <a
								href="../index.jsp" class="input_ba">이전으로</a>
						</div>
					</form>
				</div>
			</div>
			<div id="footer">
				<%@include file="../frame/footer.jsp"%>
			</div>
		</div>
	</div>
</body>
</html>