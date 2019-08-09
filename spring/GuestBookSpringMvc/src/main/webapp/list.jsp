<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>제이슨 테스트용 list</title>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<style>
div{
	border: 1px solid #888;
	margin: 10px;
	width: 200px;
}

a{
	cursor: pointer;
}
</style>
</head>
<body>
	
	<h1>방명록</h1>
	<div style="text-align: right; border:0;"> <a href="writeForm">글쓰기</a>
	
		<article id="list">
		</article>
		
		<div id="paging">
		</div>
	
	</div>

<script>
	$(document).ready(function(){
		page(1);
	});
	
	function page(num) {
		$.ajax({
			//우리가 현재 들어와있는건 웹앱루트에서 사용하는 list. 저건 실행할시점의 컨텍스트경로의 루트니까...이렇게 해줘야 (상대경로 사용해봤음) 같은 서버니까 일케 요청함
			url : 'guest/listJson',
			type : 'get', //get으로 한번 해봤음
			data : {page:num}, // 1 2 3 이런거 보내줘야할것
			success : function(data) {
				//json을 ajax메서드를 사용해 응답받을 땐 객체형식으로 받게 됨
// 				alert(data); // 대괄호 한 오브젝트 형식으로~?
// 				console.log(data);
// 				alert(data.messageTotalCount); // 객체안의 변수도 프로퍼티이름으로 이렇게 바로 받아올 수 있다
// 				alert(JSON.stringfy(data)); //문자열형식으로 바꿔줌. 확인을 위해 해보자
				
				var output = '';
				var list = data.messageList;
				
				for(var i=0 ; i<list.length ; i++){
					console.log(list[i]);
					var id = list[i].id;
					var guestName = list[i].guestName;
					var message = list[i].message;
					
					output += '<div>\n'
					output += '메시지번호 : '+id+'<br>\n';
					output += '손님 이름 : '+guestName+'<br>\n';
					output += '메시지 : '+message+'<br>\n';
					output += '</div>\n';
				}
				//alert(output);
				
				var paging = '';
				
				for(var j=1 ; j<=data.pageTotalCount; j++){
					paging += '<span class="paging"><a onclick="page('+j+')">'+j+'</a> </span>'; //a를 줘도 되고 클릭이벤트를 줘도 되고
				}
				
				$('#list').html(output);
				$('#paging').html(paging);
			}
		});
	}
</script>

</body>
</html>