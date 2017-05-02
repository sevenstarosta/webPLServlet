<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>playGame Screen</title>

<style>
body, html {
	margin: 10 auto;
	padding-left: 100px;
	padding-right: 100px;
}

table.grid .cellbutt {
	width: 100%;
	border: 2px solid black;
	padding: 25px;
	font-size: 150%;
	background-color: #005ce6;
	color: white;
	border: 2px solid black;
}

table.grid .cellbutt:hover {
	background-color: #66a3ff;
}

.empty {
	width: 100%;
	border: 2px solid black;
	padding: 25px;
	font-size: 150%;
	background-color: #005ce6;
	color: #005ce6;
	cursor: not-allowed;
	border: 2px solid black;
}

table.grid .dead {
	width: 100%;
	position: relative;
	display: inline-block;
	border: 2px solid black;
	padding: 25px;
	color: white;
	font-size: 150%;
	background-color: #005ce6;
	cursor: not-allowed;
}

table.grid .dead:hover {
	background-color: #005ce6;
	cursor: not-allowed;
}

.dead::before, .dead::after {
	content: '';
	width: 100%;
	position: absolute;
	right: 0;
	top: 50%;
}

.dead::before {
	border-bottom: 3px solid red;
	-webkit-transform: skewY(-40deg);
	transform: skewY(-40deg);
}

.dead::after {
	border-bottom: 3px solid red;
	-webkit-transform: skewY(40deg);
	transform: skewY(40deg);
}

table.teamgrid td {
	border: 2px solid black;
	padding: 15px;
	font-size: 120%;
	background-color: white;
}

.subtract {
	background-color: white;
	border: 2px solid red;
	color: black;
	text-align: center;
	text-decoration: none;
	display: inline-block;
	padding: 1px 8px;
	font-size: 16px;
	font-weight: bold;
	transition-duration: 0.4s;
	cursor: pointer;
	font-size: 16px;
}

.subtract:hover {
	background-color: red;
	color: white;
}

.add {
	background-color: white;
	border: 2px solid green;
	color: black;
	text-align: center;
	text-decoration: none;
	display: inline-block;
	font-size: 16px;
	font-weight: bold;
	transition-duration: 0.4s;
	cursor: pointer;
}

.add:hover {
	background-color: green;
	color: white;
}

.submit {
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

.submit:hover {
	background-color: green;
	color: white;
}

.edit {
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

.edit:hover {
	background-color: blue;
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
</style>

<script type=text/javascript
	src=http://localhost:8080/webPLServlet/projectjsp/ajax.js>
	
	

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

		<br> <br>
		<%
			int numrows = Integer.parseInt(session.getAttribute("numrows").toString());
			int numcols = Integer.parseInt(session.getAttribute("numcols").toString());
			int num = Integer.parseInt(session.getAttribute("qnum").toString());

			int score = 0;
			if (request.getParameter("qnumber") != null) {
				int qnumber = Integer.parseInt((String) request.getParameter("qnumber"));
				score = Integer.parseInt(session.getAttribute("score" + qnumber).toString());
				session.setAttribute("valid" + qnumber, "false");
				//out.println(score);
			}

			//if (numrows == 0 || numcols == 0 || num == 0) {
			//	response.sendRedirect("http://localhost:8080/webPLServlet/browse");
			//}

			out.print("		<center>");
			out.print("			<table class=\"grid\">");
			for (int r = 1; r <= numrows; r++) {
				out.print("					<tr>");
				for (int c = 1; c <= numcols; c++) {
					boolean madecell = false;
					for (int i = 0; i < num; i++) {
						try {
							int currentrow = Integer.parseInt(session.getAttribute("row" + i).toString());
							int currentcol = Integer.parseInt(session.getAttribute("col" + i).toString());
							if (currentrow == r && currentcol == c) {
								if (Boolean.parseBoolean(session.getAttribute("valid" + i).toString())) {
		%>
		<td>
			<form action="questionInfo.jsp" method="post">
				<input type="submit" class="cellbutt" id="btn" name="button"
					value="<%=session.getAttribute("score" + i)%>" />
					<input type="hidden" id="qnumber" name="qnumber" value="<%=i%>" />
					<input type="hidden" id="teamnum" name="teamnum" value="<%=request.getParameter("teamnum").toString()%>" />
					<!-- <input type="hidden" id="storedscore" name="storedscore" value="0" />
					<input type="hidden" id="refteam" name="refteam" value="0" /> -->
			</form>
		</td>
		<%
			} else {
									//make invalid
		%>
		<td><button class="dead"><%=session.getAttribute("score" + i)%></button></td>
		<%
			}
								madecell = true;
							}
						} catch (Exception e) {
							out.print(
									"<td>Error at printing stage at row " + (r + 1) + " and column" + (c + 1) + "</p>");
							out.print("<p>" + e + "</p>");
						}
					}
					if (!madecell) {
		%>
		<td><button class="empty"><%=session.getAttribute("emptyscore")%></button></td>
		<%
			}
				}
				out.print("					</tr>");
			}
			out.print("			</table>");
			out.print("		</center>");
		%>


		</table>

		<br>
		<form name=scoreform>
			<table class="teamgrid" border="4">
				<tr>
					<%
						for (int i = 1; i <= Integer.parseInt(request.getParameter("teamnum")); i++) {
					%>
					<td><font color="black">Team <%=i%></font></td>
					<%
						if (session.getAttribute("score" + i) == null) {
								session.setAttribute("score" + i, 0);
							}
						}
					%>
				</tr>

				<tr>
					<%
						for (int i = 1; i <= Integer.parseInt(request.getParameter("teamnum")); i++) {
							String temp = "txtHint" + i;
							//parseInt(document.getElementById(temp).innerHTML)
					%>
					<td>
						<!-- <center><%--session.getAttribute("score" + i)--%></center> <br> -->
						<center>
							<%
								//int current = Integer.parseInt(session.getAttribute("hint"+i).toString());
									//System.out.println("current " + current);
								//String curhint = "hint"+i;
							%>
							<%--temp --%>
							<%-- curhint --%>
							<span id="<%=temp%>"><%=session.getAttribute(temp).toString()%></span>
						</center> <br>
						<center>
							<input type="button" class="subtract" id="subtract" name="subtract" value="-" onclick=showHint(<%=score%>,"<%=temp%>",0) />

							<input type="button" class="add" id="add" name="add" value="+" onclick=showHint(<%=score%>,"<%=temp%>",1) />
							<%-- use hidden input and target with ajax --%>

						</center>
					</td>
					<%			
					
						}
					
					%>
				</tr>
			</table>
		</form>

		<br>
		<form action="http://localhost:8080/webPLServlet/browse" method="post">
			<center>
				<input type="submit" class="edit" value="Return to Browse Screen">
			</center>
		</form>
</body>
</html>

