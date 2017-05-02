<%@page import="java.io.File"%>
<%@page import="java.io.InputStreamReader"%>
<%@page import="java.net.URL"%>
<%@page import="java.io.FileReader"%>
<%@page import="java.io.BufferedReader"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>startGame Screen</title>

<style>
body, html {
	margin: 10 auto;
	padding-left: 100px;
	padding-right: 100px;
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

ol {
	list-style-position: inside; font-size : 105%;
	color: red;
	font-size: 105%;
}

li {
	margin-top: 10px;
}
</style>

<script type="text/javascript">
	function validate() {
		//alert("Enters function");
		var valid = true;
		var teamnum = document.getElementById("teamnum").value;
		if (teamnum == 0) {
			document.getElementById("inval_msg").innerHTML = "You must enter number of teams";
			valid = false;
		} else if (teamnum > 8) {
			document.getElementById("inval_msg").innerHTML = "The maximum number of teams is 8";
			valid = false;
		}
		return valid;
	}
</script>

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
		<h1>Jeopardy Team Page</h1>
		<h2>By Khanh Tran (knt3tb) and Seven Starosta (sbs3bx)</h2>

		<br> <br>

		<form action="playGame.jsp" method="post" id="teamform"
			name="teamform" onsubmit="return validate();">
			<center>
				Enter Number of Teams <br> <br> <input type="number"
					id="teamnum" name="teamnum" value="" placeholder="Max: 8"><br>
				<span id="inval_msg"
					style="color: red; font-style: italic; font-size: 100%"></span>

				<h3>
					<font color="red"> Rules: </font>
				</h3>

				<ol>
					<li>Choose a question by clicking on any of the squares on the
						grid.</li>
					<li>Only one team member raises hand to answer question.</li>
					<li>The first correct answer recieves the points that was
						displayed on the square. <br> If you answer incorrectly, your team loses that amount of
						points and another team has the opportunity to steal the points.<br>
					</li>
					<li>Highest score at the end wins!</li>
				</ol>

				<br>
				<input type="submit" class="submit" value="Start"> <input
					type="hidden" id="gameid" name="gameid"
					value=<%=request.getParameter("gameid")%>>


				<%
					File f = new File("D:\\eclipse\\tomcat9\\webapps\\projectdata\\WEB-INF\\data\\data-file.txt");
					BufferedReader data = new BufferedReader(new FileReader(f));
					String line = data.readLine();
				%>


				<%
					int qnum = 0;
					int numrows = 0;
					int numcols = 0;
					while (line != null && line.length() > 0) {
						if (line.indexOf(';') >= 1 && line.indexOf(';', line.indexOf(';') + 1) >= 2) {

							String username = line.substring(0, line.indexOf(';'));
							String gameid = line.substring(line.indexOf(';') + 1, line.indexOf(';', line.indexOf(';') + 1));
							if (((String) request.getParameter("gameid")).equals(gameid)) {
								// print out whole form here. Use a while loop
								String remainder = line.substring(line.indexOf(';', line.indexOf(';') + 1) + 1, line.length());
								while (remainder != null && remainder.length() > 0) {
									String qid = "q" + qnum;
									session.setAttribute("valid"+qnum, true);
									String rowid = "row" + qnum;
									String colid = "col" + qnum;
									String scoreid = "score" + qnum;
									String rightansid = "rightans" + qnum;
									String ansid = "ans" + qnum;
									String savedquestion = remainder.substring(0, remainder.indexOf(';'));
									remainder = remainder.substring(remainder.indexOf(';') + 1, remainder.length());
									String savedanswer = remainder.substring(0, remainder.indexOf(';'));
									remainder = remainder.substring(remainder.indexOf(';') + 1, remainder.length());
									String answers = remainder.substring(9, remainder.indexOf(';', 9));
									if (answers.charAt(answers.length() - 1) == ',') {
										answers = answers.substring(0, answers.length() - 1);
									}
									remainder = remainder.substring(remainder.indexOf(';') + 1, remainder.length());
									int row = Integer.parseInt(remainder.substring(0, remainder.indexOf(';')));
									remainder = remainder.substring(remainder.indexOf(';') + 1, remainder.length());
									int col = Integer.parseInt(remainder.substring(0, remainder.indexOf(';')));
									remainder = remainder.substring(remainder.indexOf(';') + 1, remainder.length());
									int score = Integer.parseInt(remainder.substring(0, remainder.indexOf('|')));
									remainder = remainder.substring(remainder.indexOf('|') + 1);
				%>
				<input type="hidden" id=<%=qid%> name=<%=qid%>
					value='<%=savedquestion%>'> <input type="hidden"
					id=<%=rowid%> name=<%=rowid%> value=<%=row%>> <input
					type="hidden" id=<%=colid%> name=<%=colid%> value=<%=col%>>
				<input type="hidden" id=<%=scoreid%> name=<%=scoreid%>
					value=<%=score%>> <input type="hidden" id=<%=rightansid%>
					name=<%=rightansid%> value='<%=savedanswer%>'> <input
					type="hidden" id=<%=ansid%> name=<%=ansid%> value='<%=answers%>'>
				<%
					session.setAttribute(qid, savedquestion);
					session.setAttribute(rowid, row);
					session.setAttribute(colid, col);
					session.setAttribute(scoreid, score);
					session.setAttribute(rightansid, savedanswer);
					session.setAttribute(ansid, answers);
					session.setAttribute("emptyscore", 0);
				%>
					

				<%
					qnum++;
									if (col > numcols) {
										numcols = col;
									}
									if (row > numrows) {
										numrows = row;
									}
								}
							}

						}
						line = data.readLine();
					}
				%>
				<input type="hidden" id="qnum" name="qnum" value='<%=qnum%>'>
				<input type="hidden" id="numrows" name="numrows" value=<%=numrows%>>
				<input type="hidden" id="numcols" name="numcols" value=<%=numcols%>>
				<input type="hidden" id="init" name="init" value="true">
				
				<%
				session.setAttribute("qnum", qnum);
				session.setAttribute("numrows", numrows);
				session.setAttribute("numcols", numcols);
				session.setAttribute("init", true);
				session.setAttribute("txtHint1",0);
				session.setAttribute("txtHint2",0);
				session.setAttribute("txtHint3",0);
				session.setAttribute("txtHint4",0);
				session.setAttribute("txtHint5",0);
				session.setAttribute("txtHint6",0);
				session.setAttribute("txtHint7",0);
				session.setAttribute("txtHint8",0);
				%>



			</center>
		</form>

		<form action="http://localhost:8080/webPLServlet/browse" method="post">
			<center>
				<input type="submit" class="edit" value="Return to Browse Screen">
			</center>
		</form>
	</center>

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
