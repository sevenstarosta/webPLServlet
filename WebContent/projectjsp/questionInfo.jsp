<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>questionInfo Screen</title>

<style>
body, html {
	margin: 10 auto;
	padding-left: 100px;
	padding-right: 100px;
}

.displaybtn {
	background-color: white;
	border: 2px solid blue;
	color: black;
	padding: 16px 32px;
	text-align: center;
	text-decoration: none;
	display: inline-block;
	font-size: 16px;
	margin: 4px 2px;
	transition-duration: 0.4s;
	cursor: pointer;
}

.displaybtn:hover {
	background-color: blue;
	color: white;
}

.return {
	background-color: white;
	border: 2px solid green;
	color: black;
	padding: 16px 32px;
	text-align: center;
	text-decoration: none;
	display: inline-block;
	font-size: 16px;
	margin: 4px 2px;
	transition-duration: 0.4s;
	cursor: pointer;
}

.return:hover {
	background-color: green;
	color: white;
}

.logout {
	background-color: white;
	border: 2px solid red;
	color: black;
	padding: 8px 16px;
	text-align: center;
	text-decoration: none;
	display: inline-block;
	font-size: 16px;
	margin-bottom: 5px;
	margin-top: 5px;
	transition-duration: 0.4s;
	cursor: pointer;
}

.logout:hover {
	background-color: red;
	color: white;
}

div.display {
	width: 300px;
	border: 5px solid green;
	padding: 20px;
	margin: 20px;
	background-color: #66ff66;
	font-size: 24px;
}

div.question {
	width: 300px;
	padding: 20px;
	margin: 20px;
	font-size: 24px;
}
</style>

<script type="text/javascript">
	function display_answer() {
		document.getElementById("displayans").style.display = "";
	}
</script>
</head>
<body>

	<%
		String LoginServlet = "http://localhost:8080/webPLServlet/login";
		String LogoutServlet = "http://localhost:8080/webPLServlet/logout";
		String user = (String) session.getAttribute("UserID");

		//System.out.println(user);
		if (user == null || user.length() == 0)
			response.sendRedirect(LoginServlet);
	%>

	<table width="20%" align="right" bgcolor="white" border="0"
		cellspacing="2">
		<tr>
			<td align="right"><font size=4><b>UserID: <%=session.getAttribute("UserID")%></b></font></td>
			<td>
				<form action="http://localhost:8080/webPLServlet/logout"
					method="post">
					<center>
						<input type="submit" class="logout" value="Logout"></input>
					</center>
				</form>
			</td>
		</tr>
	</table>

	<br />
	<br />
	<center>
		<h1>Jeopardy Game Page</h1>
		<h2>By Khanh Tran (knt3tb) and Seven Starosta (sbs3bx)</h2>
		<hr>
		<br> 
		<div class="question">
		For <font color="red"><b><%=session.getAttribute("score" + request.getParameter("qnumber"))%></b></font> points:<br>

		<font size="6"><%=session.getAttribute("q" + request.getParameter("qnumber"))%></font>
		<%if (session.getAttribute("ans"+request.getParameter("qnumber")).toString().contains(","))
		{
			String remainder = session.getAttribute("ans"+request.getParameter("qnumber")).toString();
			while(remainder.length()>0)
			{
					if(remainder.contains(","))
					{%>
					<p>&bull; <%= remainder.substring(0,remainder.indexOf(',')) %></p>
					<%
					remainder=remainder.substring(remainder.indexOf(',')+1,remainder.length());
					}
					else
					{
						%>
						<p>&bull; <%= remainder%></p>
						<%
						remainder="";
					}
			}
		}
			
			
			%>
		</div>

		<%--session.getAttribute("ans" + request.getParameter("qnumber"))--%>
		<%--session.getAttribute("col" + request.getParameter("qnumber"))--%>
		<%--session.getAttribute("row" + request.getParameter("qnumber"))--%>
		<%
			//System.out.println("refteam = " + request.getParameter("refteam").toString());
			//System.out.println("storedscore = " + request.getParameter("storedscore").toString());
		%>
		<% //session.setAttribute(request.getParameter("refteam").toString(), Integer.parseInt(request.getParameter("storedscore").toString())); %>
		<% //System.out.println("refteam score = " + session.getAttribute(request.getParameter("refteam").toString())); %>

		<div class="display" id="displayans" name="displayans"
			style="display: none">

			<center>The correct answer is:</center>
			<br>
			<center><font size="6"><%=session.getAttribute("rightans" + request.getParameter("qnumber"))%></font></center>

		</div>
		<br>

		<input type="button" class="displaybtn" id="displaybtn"
			name="displaybtn" value="Show Answer" onclick="display_answer()" />
		<%
			request.getParameter("qnumber");
			//System.out.println("txtHint1 = " + session.getAttribute("txtHint1"));
		%>
		<form action="playGame.jsp" method="post">
			<input type="submit" id="btn" class="return" value="Continue" />
			<input type="hidden" id="qnumber" name="qnumber"
				value="<%=request.getParameter("qnumber").toString()%>" /> <input
				type="hidden" id="teamnum" name="teamnum"
				value="<%=request.getParameter("teamnum").toString()%>" /> <input
				type="hidden" id="init" name="init" value="false" />
		</form>

	</center>
</body>
</html>