<%@page import="java.util.List"%>
<%@page import="dateShare.Model.Movie"%>
<%@page import="dateShare.service.movie.GetArticleListService"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	//핵심처리할 서비스 객체 
	GetArticleListService service = GetArticleListService.getInstance();
	
	//응답 데이터의 결과 
	List<Movie> movieList = service.getArticleList();	
	
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <title>DATE SHARE | MOVIE</title>
</head>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<link href="../css/index.css" rel="stylesheet" type="text/css">
<style>
	body {
		background-color: transparent;
	}
</style>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
</head>
<body>
<div id="wrap">
    <div id="main_wrap">
        <div id="header">
        	<%@ include file="../frame/my.jsp" %>
            <%@ include file="../frame/header.jsp" %>
        </div>
        <div id="nav">
            <%@ include file="../frame/nav.jsp" %>
        </div>
        <div id="content" class ="album py-5">
            <div id="contentList" class="container">   
            	<div class="row">   
            	<%
            		if(movieList.isEmpty()) {
            			out.println("등록된 게시글이 없습니다.");
            			
            		} else {
            			for(Movie movie : movieList) {            			
            			%>          				
            				<div class="col-md-4">
            				<div class="card mb-4 shadow-sm">
	            				<svg class="bd-placeholder-img card-img-top" width="100%" height="225" xmlns="http://www.w3.org/2000/svg" preserveAspectRatio="xMidYMid slice" focusable="false" role="img">
	            					<image xlink:href="<%= movie.getM_path() %>" width="100%" height="225">
	            				</svg> 
	            				<div class="card-body">
	            				 <p class="card-text">
	            				 	제목 <%= movie.getM_title() %><br>
	           						조회수 <%= movie.getM_hits() %>
	           						좋아요 
	            				 </p> 
	            				 <div class="d-flex justify-content-between align-items-center">
					                <div class="btn-group">
					                  <a href="movieContentView.jsp?articleNum=<%= movie.getM_num() %>">
					                  	<button type="button" class="btn btn-sm btn-outline-secondary">View</button>
					                  </a>
					                  <a href="movieEditView.jsp?articleNum=<%= movie.getM_num() %>">
					                  	<button type="button" class="btn btn-sm btn-outline-secondary">Edit</button>
					                  </a>
					                </div>
					                <small class="text-muted"><%= movie.getM_writedate() %></small>
					              </div>        				            			
		            			</div>
            				</div>
            				</div>
            			<%
            			}
            		}
            	%>
            	</div>
            </div>

            <a href="movieWrite.jsp"><input type="button" value="콘텐츠 등록하기"></a> 
        </div>
        <div id="footer">
            <%@ include file="../frame/footer.jsp" %>
        </div>
    </div>
    </div>
</body>
</html>

