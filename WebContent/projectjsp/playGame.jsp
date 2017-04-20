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

table.grid td {
	border: 2px solid black;
	padding: 20px;
	font-size: 150%;
	background-color: #005ce6;
}

table.grid td:hover {
	border: 2px solid black;
	padding: 20px;
	font-size: 150%;
	background-color: #66a3ff;
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
</head>
<body>

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
			int numrows = Integer.parseInt((String) request.getParameter("numrows"));
			int numcols = Integer.parseInt((String) request.getParameter("numcols"));
			int num = Integer.parseInt((String) request.getParameter("qnum"));

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
							int currentrow = Integer.parseInt(request.getParameter("row" + i));
							int currentcol = Integer.parseInt(request.getParameter("col" + i));
							if (currentrow == r && currentcol == c) {
								out.print("					<td align=\"center\"><font color=\"white\">"
										+ request.getParameter("score" + i) + "</font></td>");
								madecell = true;
							}
						} catch (Exception e) {
							out.print(
									"<td>Error at printing stage at row " + (r + 1) + " and column" + (c + 1) + "</p>");
							out.print("<p>" + e + "</p>");
						}
					}
					if (!madecell) {
						out.print("					<td align=\"center\"></td>");
					}
				}
				out.print("					</tr>");
			}
			out.print("			</table>");
			out.print("		</center>");
		%>


		</table>

		<br>
		<table class="teamgrid" border="1">
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

			<%!int scoreval = 100;

			public void addScore(int value) {
		
			}

			public void subScore(int value) {

			}%>
			<tr>
				<%
					for (int i = 1; i <= Integer.parseInt(request.getParameter("teamnum")); i++) {
				%>
				<td>
					<center><%=session.getAttribute("score" + i)%></center> <br>
					<center>
						<input type="button" class="subtract" id="subtract"	name="subtract" value="-" onClick=<% subScore(scoreval); %> /> 
						<input type="button" class="add" id="add" name="add" value="+" onClick=<% addScore(scoreval); %>/>
					</center>
				</td>
				<%
					}
				%>
			</tr>
		</table>

		<br>
		<form action="http://localhost:8080/webPLServlet/browse" method="post">
			<center>
				<input type="submit" class="edit" value="Return to Browse Screen">
			</center>
		</form>
</body>
</html>

<%
	String LoginServlet = "http://localhost:8080/webPLServlet/login";
	String LogoutServlet = "http://localhost:8080/webPLServlet/logout";

	String user = (String) session.getAttribute("UserID");
	//System.out.println(user);
	if (user == null || user.length() == 0)
		response.sendRedirect(LoginServlet);
%>