<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	int r_num = Integer.parseInt(request.getParameter("r_num"));
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<%-- <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/main.css" />  --%>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://kit.fontawesome.com/8653072c68.js"></script>
<style type="text/css">

/* 모달 */
.modal {
	display: none; /* Hidden by default */
	position: fixed; /* Stay in place */
	z-index: 1; /* Sit on top */
	left: 0;
	top: 0;
	width: 100%; /* Full width */
	height: 100%; /* Full height */
	overflow: auto; /* Enable scroll if needed */
	background-color: rgb(0, 0, 0); /* Fallback color */
	background-color: rgba(0, 0, 0, 0.4); /* Black w/ opacity */
}

.modal-contents {
	background-color: #fefefe;
	margin: 5% auto; /* 15% from the top and centered */
	padding: 20px;
	border: 1px solid #ddd;
	width: 50%; /* Could be more or less, depending on screen size */
	box-shadow: 5px 5px #ccc;
}

.close {
	color: #aaa;
	float: right;
	font-size: 28px;
	font-weight: bold;
}

.close:hover, .close:focus {
	color: black;
	text-decoration: none;
	cursor: pointer;
}

body{
	background-color: #f2f2f2;
}


</style>
</head>
<body>
<div class="container" style="background-color: #fefefe; margin-top:30px">

	<div>
		<div id="reviewDetail1">
		</div>
		<hr>
		<div id="apiDetail">					
		</div>
		<hr>
		<div id="reviewDetail3">
		</div>
		<div id="reviewDetail2">
		</div>
	</div>

	<div id="map" style="width: 100%; height: 350px;"></div>
	
<!-- 	<div id="apiDetail2" style="display: none;">	
					
	</div> -->
	
	<div id="apiDetailArea" style="display: none" class="modal">
		<div class="modal-contents">
			<span class="close" id="apiDetail2Close">x</span>
			<h1>상세보기</h1>
			<hr>
			<div id="apiDetail2">	
					
			</div> 
		</div>
	</div>

	</div>


	<script>
		$(document).ready(function() {
			viewReviewDetail(<%=r_num%>);
			<%-- viewApiDetail2(<%=r_num%>); --%>
		});
		function showApiDetailArea() {
			
			$('#apiDetailArea').css('display', 'block');

		}

		// 창 닫기
		$('#apiDetail2Close').click(function() {
			$('#apiDetailArea').css('display', 'none');
		});
		
		
		function viewReviewDetail(r_num) {
			//alert(r_num);

			$.ajax({
				url : 'http://15.164.162.21:8080/culturefork/review/' + r_num,
				type : 'GET',
				success : function(data) {
					viewApiDetail(data.r_seq);
					//alert(JSON.stringify(data));

					var html1 = '';
					html1 += '<div style="padding-top:70px; font-size:25px;">' + data.r_title + '</div><br>';
					var formatdate= new Date(data.r_date);
					html1 += '<div style="text-align:right;"> <i class="fas fa-user-edit"></i> ' + data.u_id+' <i class="far fa-clock"></i> '+formatdate.format('yyyy. MM. dd. HH:mm:ss')+' <i class="far fa-eye"></i> '+data.r_hits+ '</div><br>';

					$('#reviewDetail1').html(html1);
					
					var html3 = '';
					
					html3 += '<div> 사용자 평점 : <p style="display:inline; color:#FBAB00">'
					
					for(i=1; i<=data.r_star;i++){
						html3 += '★';
					}
					
					html3 += '<p></div><br>';
					html3 += '<div>' + data.r_content + '</div><br>';
					
					$('#reviewDetail3').html(html3);

				}
			});
		}

		
		function viewApiDetail(seq) {

			var url = 'http://www.culture.go.kr/openapi/rest/publicperformancedisplays/d/?serviceKey=JLSkzmHpfUzFKrIc6Rj3ctOhrcxkpJseabRpcv2orln2GXYDEjUztm4fRtQOCNn9rBWJTNhA3Os79GYiZkJEIA%3D%3D&seq='
					+ seq;

			$.ajax({
				url : url,
				type : 'GET',
				dataType : 'xml', //데이터타입
				success : function(data) {
					var html = '';
					var html2 = '';
					$(data).find('perforInfo').each(
							function(index) {
	
								html += '<table>';
								html += '<tr>';
								html += '<td><img src="'+$(this).find('imgUrl').text()+'"></td>';
								html += '<td style="font-weight:bold; line-height:35px; padding:10px; padding-left:20px; width:100px;">작품명 <br>장 소  <br>가 격 <br>기 간 <br>비 고 <br><a onclick="showApiDetailArea()" style="color:#FFAEC9; cursor:pointer;">상세보기</a></td>';
								html += '<td style="line-height:35px;">'+$(this).find('title').text()+'<br>';
								html += ''+$(this).find('area').text()+ ' | ' + $(this).find('place').text()+'<br>';
								html += '' + $(this).find('price').text()+ '<br>';
								html += '' + $(this).find('startDate').text() + ' ~ ' + $(this).find('endDate').text()+ '<br>';
								html += '' + $(this).find('realmName').text()+ '<br>';
								html += '<br>';
								
								html += '</td>';
								html += '</tr>';
								html += '</table>';
								
								html2 += '<center>'+$(this).find('contents1').text()+'<center>';
								

				//지도 써보기
					var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
				    mapOption = {
				        center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
				        level: 3 // 지도의 확대 레벨
				    };  

					// 지도를 생성합니다    
					var map = new kakao.maps.Map(mapContainer, mapOption); 
					
					// 주소-좌표 변환 객체를 생성합니다
					var geocoder = new kakao.maps.services.Geocoder();

					
					// 주소로 좌표를 검색합니다
					geocoder.addressSearch($(this).find('placeAddr').text(), function(result, status) {
					
					    // 정상적으로 검색이 완료됐으면 
					     if (status === kakao.maps.services.Status.OK) {
					
					        var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
					
					        // 결과값으로 받은 위치를 마커로 표시합니다
					        var marker = new kakao.maps.Marker({
					            map: map,
					            position: coords
					        });
					
					        // 인포윈도우로 장소에 대한 설명을 표시합니다
					        var infowindow = new kakao.maps.InfoWindow({
					            content: '<div style="width:150px;text-align:center;padding:6px 0;">여기!</div>'
					        });
					        infowindow.open(map, marker);
					
					        // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
					        map.setCenter(coords);
					    } 
					});    
					

					//=========== 지도 써보기 끝

				});

					$('#apiDetail').html(html);
					$('#apiDetail2').html(html2);
				}
			});
		}
		
		
		//========================
			
			Date.prototype.format = function (f) {

		    if (!this.valueOf()) return " ";
		
		
		
		    var weekKorName = ["일요일", "월요일", "화요일", "수요일", "목요일", "금요일", "토요일"];
		
		    var weekKorShortName = ["일", "월", "화", "수", "목", "금", "토"];
		
		    var weekEngName = ["Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"];
		
		    var weekEngShortName = ["Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"];
		
		    var d = this;



	    	return f.replace(/(yyyy|yy|MM|dd|KS|KL|ES|EL|HH|hh|mm|ss|a\/p)/gi, function ($1) {
	
	        switch ($1) {
	
	            case "yyyy": return d.getFullYear(); // 년 (4자리)
	
	            case "yy": return (d.getFullYear() % 1000).zf(2); // 년 (2자리)
	
	            case "MM": return (d.getMonth() + 1).zf(2); // 월 (2자리)
	
	            case "dd": return d.getDate().zf(2); // 일 (2자리)
	
	            case "KS": return weekKorShortName[d.getDay()]; // 요일 (짧은 한글)
	
	            case "KL": return weekKorName[d.getDay()]; // 요일 (긴 한글)
	
	            case "ES": return weekEngShortName[d.getDay()]; // 요일 (짧은 영어)
	
	            case "EL": return weekEngName[d.getDay()]; // 요일 (긴 영어)
	
	            case "HH": return d.getHours().zf(2); // 시간 (24시간 기준, 2자리)
	
	            case "hh": return ((h = d.getHours() % 12) ? h : 12).zf(2); // 시간 (12시간 기준, 2자리)
	
	            case "mm": return d.getMinutes().zf(2); // 분 (2자리)
	
	            case "ss": return d.getSeconds().zf(2); // 초 (2자리)
	
	            case "a/p": return d.getHours() < 12 ? "오전" : "오후"; // 오전/오후 구분
	
	            default: return $1;
	
	        }
	
	    });
	
	};



			String.prototype.string = function (len) { var s = '', i = 0; while (i++ < len) { s += this; } return s; };
			
			String.prototype.zf = function (len) { return "0".string(len - this.length) + this; };
			
			Number.prototype.zf = function (len) { return this.toString().zf(len); };
	</script>
	
		
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=9bfa1557017c054b179cee46e7fa005c&libraries=services"></script>
</body>
</html>