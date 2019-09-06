<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/main.css" /> 
<%-- <link href="<c:url value='/assets/css/main.css' />"  rel="stylesheet" /> --%>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1" />

<script src="https://kit.fontawesome.com/8653072c68.js"></script>

<style type="text/css">

.btn{
	height: 30px;
}

.btn1{
	line-height: 30px;
	height:30px;	
	font-size: 14px;
}

.paddingtop{
	padding-top: 30px;
}

body{
	background-color: #F2F2F2;
}

.yellow {
	color: #FBAB00;
}


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
<div class="container">
	<p style="text-align: right; padding: 0px; padding-top:30px"><button class="btn" onclick="showWrite()">글쓰기</button> </p>
	<!-- 조회 -->
	<div id="reviewlist" class="row">

<!-- 		<div class="col-sm-4">
          <div class="card">
            <img class="card-img-top" src="images/card-image.png" alt="" />
            <div class="card-body">
              <h5 class="card-title">Lorem</h5>
              <p class="card-text">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam egestas sed sem ut malesuada.</p>
              <a href="#" class="btn btn-primary">More</a>
            </div>
          </div>
        </div>  -->
        
	</div>

	<!-- 수정 -->
	<div id="editArea" style="display: none" class="modal">
		<div class="modal-contents">
			<span class="close" id="editClose">x</span>
			<h1>수정</h1>
			<hr>
			<form id="editForm">
				<input type="hidden" name="r_num" id="r_num"> 공연/전시 <input
					type="text" name="r_ptitle" id="r_ptitle" readonly> 작성자 <input
					type="text" name="u_id" id="u_id" readonly> <br> 제목 <input
					type="text" name="r_title" id="r_title" required> <br>
				내용
				<textarea name="r_content" id="r_content" placeholder="내용을 입력하세요."
					rows="6"></textarea>
				<br> 
				별점 
				<!-- <input type="range" name="r_star" id="r_star" required>  -->
				<select name="r_star" id="r_star" required>
					<option value="5">5</option>
					<option value="4">4</option>
					<option value="3">3</option>
					<option value="2">2</option>
					<option value="1">1</option>
				</select>
				<br>
				<input type="submit" value="수정" id="editBtn">
			</form>
		</div>
	</div>
	
	<!-- 작성 -->
	
	<div id="writeArea" style="display: none" class="modal">
		<div class="modal-contents">
			<span class="close" id="writeClose">x</span>
			
	<h1>리뷰 작성</h1>
	<hr>
	
	<!-- api 검색이 들어가야 한다 -->

	<div class="row uniform">
		<div class="9u 12u$(small)">
			<input type="text" name="apiName" id="apiName" value="" readonly />
		</div>
		<div class="3u$ 12u$(small)">
			<button class="fit" id="showSearchBtn">공연/전시 찾기</button>
			<br>
		</div>
	</div>

	<!-- 검색 모달창 -->
	<div id="apiSearchArea" style="display: none" class="modal">
		<div class="modal-contents">
			<span class="close" id="apiSearchClose">x</span>
			<h1>전시/공연 검색</h1>
			<hr>
			<div class="row uniform">
				<div class="9u 12u$(small)">
					<input type="text" name="keyword" id="keyword" placeholder="공연/전시 이름을 입력하세요." />
				</div>
				<div class="3u$ 12u$(small)">
					<button class="fit" id="apiSearchBtn" onclick="apiSearch()">검색</button>
				</div>
			</div>
			<hr>

			<h1>검색 결과</h1>
			<div id="searchList">

			</div>
		</div>
	</div>

	<!-- 작성 폼 -->
	<form method="post" id="writeForm">
		<div class="row uniform">

			<input type="hidden" name="r_seq" id="r_seq" value=""/>
			<input type="hidden" name="r_ptitle" id="r_ptitle" value=""/>
			<input type="hidden" name="r_thumb" id="r_thumb" value=""/>
			<input type="hidden" name="r_gpsX" id="r_gpsX" value=""/>
			<input type="hidden" name="r_gpsY" id="r_gpsY" value=""/>
			
			
			<!-- 아이디는 우선 테스트용으로 넣어놓음 -->
			<input type="hidden" name="u_id" id="u_id" value="testid">
			

			<div class="12u$">
				<input type="text" name="r_title" id="r_title" value=""
					placeholder="제목" />
			</div>
			<div class="12u$">
				<textarea name="r_content" id="r_content" placeholder="내용을 입력하세요."
					rows="6"></textarea>
			</div>

			<!-- Break -->
			<div class="12u$">
			<!-- 	별점 <input type="range" name="r_star" id="r_star" required> -->
				
				<select name="r_star" id="r_star" required>
					<option value="5">5</option>
					<option value="4">4</option>
					<option value="3">3</option>
					<option value="2">2</option>
					<option value="1">1</option>
				</select>
			</div>
			<!-- Break -->

			<!-- Break -->
			<div class="12u$">
				<ul class="actions">
					<li><input type="submit" value="리뷰 작성" /></li>
				</ul>
			</div>
		</div>
	</form>
			
		</div>
	</div>
	<!-- 작성 끝 -->
	
</div>
	<script>
		$(document).ready(function() {
			list();
		});

		var path='http://15.164.162.21:8080/culturefork';
		
		// 목록 조회
		function list() {

			$.ajax({
				
//				url : 'http://15.164.162.21:8080/culturefork/review',
				url : path+'/review',
				type : 'GET',
				success : function(data) {
					//alert(JSON.stringify(data));// 확인을 위해 문자열로 출력해봄.

					var html = '';
					

					for (var i = 0; i < data.length; i++) {

						var r_info = data[i].r_info;
						
			        	html += '<div class="col-sm-4 paddingtop">\n';
						html += '<div class="card">\n';
						//html += '<a onclick="apiDetail('+data[i].r_seq+')"><img style="height:400px;" class="card-img-top" src="'+data[i].r_thumb+'" alt="" /></a>\n';
						html += '<a onclick="viewDetail('+data[i].r_num+')" style="cursor:pointer"><img style="height:400px;" class="card-img-top" src="'+data[i].r_thumb+'" alt="" /></a>\n';
						html += '<div class="card-body">\n';	
						html += '<a onclick="viewDetail('+data[i].r_num+')" style="font-weight:bold; font-size:14px;">' + data[i].r_title + '</a>\n';		
						html += '<p class="card-text">';
						html += '<p style="height:24px; color:#055D9C" style="font-weight:bold">' + data[i].r_ptitle + '<p>\n<p style="height:10px">';
						html += '<i class="fas fa-user-edit"></i> ' + data[i].u_id + ' \n';
						html += '<i class="far fa-eye"></i> ' + data[i].r_hits + ' \n';
						html += '<i class="fas fa-star yellow"></i> ' + data[i].r_star + ' <br></p>\n';
						/* html += '작성일: ' + data[i].r_date + ' <br>\n'; */
						var formatdate = new Date(data[i].r_date);
						html += '<p style="height:24px; color:#808080; font-size:14px">' + formatdate.format('yyyy년 MM월 dd일')+ '<br></p>\n'; 
						html += '<button class="btn1" onclick="del('+data[i].r_num+')">삭제하기</button> \n';
						html += '<button class="btn1" onclick="showEdit(' +data[i].r_num+ ')">수정하기</button><br>\n';
						html += '</div>\n';
						html += '</div>\n';
						html += '</div>\n';
						
					}

					$('#reviewlist').html(html);
				}

			});
		}

		// 삭제
		function del(r_num) {
			if (confirm('삭제하시겠습니까?')) {
				$.ajax({
					//url : 'http://15.164.162.21:8080/culturefork/review/post/'+ r_num,
					url : path+'/review/post/'+ r_num,
					type : 'DELETE',
					success : function(data) {
						//alert(JSON.stringify(data));
						if (data == 'success') {
							alert('삭제되었습니다!');
							list();
						}
					}
				});
			}
		}

		// 수정 ======================================================
		function showEdit(r_num) {
			// 창 띄우기
			$('#editArea').css('display', 'block');
			// 창에 정보 담기
			$.ajax({
				//url : 'http://15.164.162.21:8080/culturefork/review/post/' + r_num,
				url : path+'/review/post/' + r_num,
				type : 'GET',
				success : function(data) {
					//alert(JSON.stringify(data));
					$('#editArea #r_num').val(r_num);
					$('#editArea #r_title').val(data.r_title);
					$('#editArea #r_content').val(data.r_content);
					$('#editArea #u_id').val(data.u_id);
					$('#editArea #r_star').val(data.r_star);
					$('#editArea #r_ptitle').val(data.r_ptitle);
				}
			});

		}

		$('#editForm').submit(
				function() {
					$.ajax({
						//url : 'http://15.164.162.21:8080/culturefork/review/post/'+ $('#r_num').val(),
						url : path+'/review/post/'+ $('#r_num').val(),
						type : 'PUT',
						data : JSON.stringify({
							r_num : $('#editArea #r_num').val(),
							r_title : $('#editArea #r_title').val(),
							r_content : $('#editArea #r_content').val(),
							r_star : $('#editArea #r_star').val(),
						}),
						contentType : 'application/json; charset=utf-8',
						success : function(data) {
						//	alert(data);
							if (data == 'success') {
// 								$('#editArea').css('display', 'none');
// 								list();
							}
						},
						error : function(e) {
						//	alert('error');
						}
					});
					$('#editArea').css('display', 'none');
					list();
					return false;
				});

		// 창 닫기
		$('#editClose').click(function() {
			$('#editArea').css('display', 'none');
		});

		// 작성 =======================================================
		
		// 작성 창 띄우기
		function showWrite() {
			$('#writeArea').css('display', 'block');
		}
		
		// 작성 창 닫기
		$('#writeClose').click(function() {
			$('#writeArea').css('display', 'none');
		});
		
		// 작성 
		$('#writeForm').submit(function() {
			$.ajax({
				//url : 'http://15.164.162.21:8080/culturefork/review/post',
				url : path+'/review/post',
				type : 'POST',
				data : JSON.stringify({
					r_title : $('#writeArea #r_title').val(),
					r_content : $('#writeArea #r_content').val(),
					u_id : $('#writeArea #u_id').val(),
					r_star : $('#writeArea #r_star').val(),
					r_seq : $('#writeArea #r_seq').val(),
					r_ptitle : $('#writeArea #r_ptitle').val(),
					r_thumb : $('#writeArea #r_thumb').val(),
					r_gpsX : $('#writeArea #r_gpsX').val(),
					r_gpsY : $('#writeArea #r_gpsY').val()
				}),
				contentType : 'application/json; charset=utf-8', //전달해줄 때 타입
				dataType : 'json', //데이터타입
				success : function(data) {
					//alert(JSON.stringify(data));
				}
			});
			$('#writeArea').css('display', 'none');
			list();
			return false;
		});
		
		// api 검색창 열기
		$('#showSearchBtn').click(function() {
			$('#apiSearchArea').css('display', 'block');
		});
		
		// api 검색창 창 닫기
		$('#apiSearchClose').click(function() {
			$('#apiSearchArea').css('display', 'none');
		});
		
		// api 검색
		function apiSearch() {
			var serviceKey = 'JLSkzmHpfUzFKrIc6Rj3ctOhrcxkpJseabRpcv2orln2GXYDEjUztm4fRtQOCNn9rBWJTNhA3Os79GYiZkJEIA%3D%3D';
			var keyword = $('#keyword').val();
			var from = '20170101';
			var to = '20190828';
			var url = 'http://www.culture.go.kr/openapi/rest/publicperformancedisplays/period?serviceKey=' + serviceKey + '&sortStdr=1&RequestTime=&from=' + from + '&to=' + to + '&cPage=1&rows=100&place=&keyword='+keyword;

			$.ajax({
				url : url,
				type : 'GET',
				dataType : 'xml', //데이터타입
				success : function(data) {
					//alert(data);
					var html='<table>';
					html+='<tr><td>분류</td><td>공연명</td><td>공연기간</td></tr>';
					var performList = new Array();
					$(data).find('perforList').each(
							function(index) {
                                var perform = {
                                    title : $(this).find('title').text(),
                                    seq : $(this).find('seq').text(),
                                    startDate : $(this).find('startDate').text(),
                                    endDate : $(this).find('endDate').text(),
                                    realmName : $(this).find('realmName').text(),
                                    area : $(this).find('area').text(),
                                    thumbnail : $(this).find('thumbnail').text(),
                                    gpsX : $(this).find('gpsX').text(),
                                    gpsY : $(this).find('gpsY').text(),
                                    
                                }
                                performList.push(perform);
								//alert(seq + '/' + title + '/' + area + '/'+ realmName);
                              	//html+='<tr><td>'+perform.realmName+'</td><td><a onclick="select('+perform.seq+')">'+perform.title+'</a></td><td>'+perform.startDate+'~'+perform.endDate+'</td></tr>';	
            					
					});

					var sortedPerformList = performList.reverse();
					sortedPerformList.forEach(function(val) {
						//alert(val.title);
						//html += '<tr><td>'+val.realmName+'</td><td><a onclick="select(\''+val.seq+'\',\''+val.title+'\',\''+val.thumbnail+'\',\''+val.gpsX+'\',\''+val.gpsY+'\')">'+val.title+'</a></td><td>'+val.startDate+'~'+val.endDate+'</td></tr>';	
					//	html += '<tr><td><img src="'+val.thumbnail+'" style="width:150px"></td><td><a onclick="select(\''+val.seq+'\',\''+val.title+'\',\''+val.thumbnail+'\',\''+val.gpsX+'\',\''+val.gpsY+'\')">'+val.title+'</a></td><td>'+val.startDate+'~'+val.endDate+'</td></tr>';		
						html += '<tr><td><img src="'+val.thumbnail+'" style="width:150px"></td><td><a onclick="select(\''+val.seq+'\',\''+val.title+'\',\''+val.thumbnail+'\',\''+val.gpsX+'\',\''+val.gpsY+'\')">'+val.title+'</a></td><td>'+val.startDate+'~'+val.endDate+'</td></tr>';		
					});
					
					html+='</table>';
					$('#searchList').html(html);
				}
			});
		}
		
		// api 선택 시
		function select(seq,ptitle,thumb,gpsX,gpsY) {
			$('#writeArea #r_seq').val(seq);
			$('#writeArea #r_ptitle').val(ptitle);
			$('#writeArea #r_thumb').val(thumb);
			$('#writeArea #apiName').val(ptitle);
			$('#writeArea #r_gpsX').val(gpsX);
			$('#writeArea #r_gpsY').val(gpsY);
			
			$('#apiSearchArea').css('display', 'none');
		}
			
		//==============
			
		// 게시물 상세보기 페이지로 가기
		function viewDetail(r_num) {
			//alert('뷰디테일 누름');
			location.href="reviewDetail.jsp?r_num="+r_num;
		}
		
		// api 상세보기
		function apiDetail(seq) {
			var url = 'http://www.culture.go.kr/openapi/rest/publicperformancedisplays/d/?serviceKey=JLSkzmHpfUzFKrIc6Rj3ctOhrcxkpJseabRpcv2orln2GXYDEjUztm4fRtQOCNn9rBWJTNhA3Os79GYiZkJEIA%3D%3D&seq='+seq;
			
			$.ajax({
				url : url,
				type : 'GET',
				dataType : 'xml', //데이터타입
				success : function(data) {
					$(data).find('perforInfo').each(function(index) {
						//alert($(this).find('price').text());
					});
				}
			});
		}
		//=========================================================
				
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
</body>
</html>