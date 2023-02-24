<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.Array"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<% 
 	ArrayList foodList = (ArrayList)session.getAttribute("foodList");
	
	session.setAttribute("fruit", request.getParameter("fruit"));
	
	String selectedItem = (String)session.getAttribute("fruit");
	
	foodList.add(selectedItem);
	session.setAttribute("key", foodList);
	
	System.out.println("<script> alert('" + selectedItem + "가 장바구니에 담겼습니다.'); </script>");	


%>
<script type="text/javascript">
    history.go(-1);
</script>
 
       
</body>
</html>