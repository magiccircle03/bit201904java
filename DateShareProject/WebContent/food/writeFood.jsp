<%@page import="java.io.File"%>
<%@page import="java.util.Iterator"%>
<%@page import="org.apache.commons.fileupload.FileItem"%>
<%@page import="java.util.List"%>
<%@page import="org.apache.commons.fileupload.servlet.ServletFileUpload"%>
<%@page import="org.apache.commons.fileupload.disk.DiskFileItemFactory"%>
<%@page import="dateShare.Model.Food"%>
<%@ page import="dateShare.service.food.WriteFoodService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
	//request.setCharacterEncoding("utf-8");
%>
<jsp:useBean id="food" class="dateShare.Model.Food" />
<jsp:setProperty property="*" name="food" />
<%
	/* 	WriteFoodService service = WriteFoodService.getInstance();
		int cnt = service.write(food); */
%>

<%
	int u_num = 0;
	String f_title = "";
	String f_path = "";
	int f_star = 0;
	String f_content = "";
	String saveFileName = "";
	String uploadPath = "/images";
	String dir = request.getSession().getServletContext().getRealPath(uploadPath);
	String fileDBPath = "";
	boolean isMultipart = ServletFileUpload.isMultipartContent(request);
	if (isMultipart) {
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		List<FileItem> files = upload.parseRequest(request);
		Iterator<FileItem> itr = files.iterator();
		while (itr.hasNext()) {
			FileItem file = itr.next();
			if (file.isFormField()) {
				// 타입이 파일이 아닌경우
				if (file.getFieldName().equals("f_title")) {
					f_title = file.getString("utf-8");
				} else if (file.getFieldName().equals("f_star")) {
					f_star = Integer.parseInt(file.getString("utf-8"));
				} else if (file.getFieldName().equals("f_content")) {
					f_content = file.getString("utf-8");
				} else if (file.getFieldName().equals("u_num")) {
					u_num = Integer.parseInt(file.getString("utf-8"));
				}
			} else {
				//(2) 파일형식인 경우 --> 파일 경로 분기처리 : 1. 파일이 있는 경우 2. 파일이 없는 경우
				if (file.getFieldName().equals("f_path")) {
					f_path = file.getName();
					saveFileName = System.nanoTime() + "_" + f_path;
					file.write(new File(dir, saveFileName));
					fileDBPath = "../images/" + saveFileName;
					if (f_path.equals(null) || f_path.equals("")) {
						fileDBPath = "../images/noImg.png";
					}
				}
			}
		}
	}
	Food foodFile = new Food(u_num, f_title, f_content, fileDBPath, f_star);
	WriteFoodService service = WriteFoodService.getInstance();
	int cnt = service.write(foodFile);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>
		<%=cnt > 0 ? "게시글을 작성하였습니다" : "게시글 작성 실패"%>
		제목 :
		<%=f_title%>
		cnt :<%=cnt%>
		유저번호 <%=u_num %>
	</h1>
	<a href="foodList.jsp">목록보기</a>
</body>
</html>