<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

     <% 
     
     	String team = request.getParameter("teamnum").toString();
     	String score = request.getParameter("ScoreSoFar").toString();
     	
     	System.out.println("team = " + team);
     	System.out.println("score = " + score);
     
     	session.setAttribute(request.getParameter("teamnum").toString(), request.getParameter("ScoreSoFar").toString());
     	System.out.println("stored session score = " + session.getAttribute(request.getParameter("teamnum").toString()));
     
     %>

</body>
</html>