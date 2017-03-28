
import java.io.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.*;
import javax.servlet.http.*;

import javafx.scene.shape.Line;

import javax.servlet.annotation.*;

//import org.apache.tomcat.jni.File;

import java.net.URL;
import java.nio.channels.FileChannel;

@WebServlet("/edit_datareader")
public class edit_datareader extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static String LoginServlet = "http://localhost:8080/webPLServlet/login";
	private static String LogoutServlet = "http://localhost:8080/webPLServlet/logout";

	public edit_datareader() {
		super();

	}

	private String user;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		user = (String) session.getAttribute("UserID");

		if (user == null || user.length() == 0)
			response.sendRedirect(LoginServlet);

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		out.print("<html>");
		out.print("	<head>");
		out.print("		<meta charset=\"utf-8>\"");
		out.print("		<title></title>");
		out.print("		<style>");
		
		out.println("      body, html {");
		//out.println("		  display: inline-block;");
		out.println("         margin: 10 auto;");
		out.println("         padding-left: 100px;");
		out.println("         padding-right: 100px;");
		// out.println(" color: #202020;");
		// out.println(" background-color: #ddeeff;");
		// out.println(
		// " font-family: 'Lucida
		// Grande',Verdana,Helvetica,Arial,Geneva,'Bitstream Vera
		// Sans',Sans-Serif;");
		// out.println(" font-size: 12px;");
		out.println("      }");
		
		out.print("			ol {");
		out.print("				list-style-position: inside;");
		out.print("				font-size: 105%;");
		out.print("				color: red;");
		out.print("			}");

		out.print("			li{");
		out.print("				margin-top: 10px;");
		out.print("			}");

		out.print(" 		.submit {");
		out.print("				background-color: white;");
		out.print("				border: 2px solid green;");
		out.print("				color: black;");
		out.print("				padding: 16px 32px;");
		out.print("				text-align: center;");
		out.print("				text-decoration: none;");
		out.print("				display: inline-block;");
		out.print("				font-size: 16px;");
		// out.print(" margin: 4px 2px;");
		out.println("			margin-top: 25px;");
		out.print("				transition-duration: 0.4s;");
		out.print("				cursor: pointer;");
		out.print("			}");

		out.print("			.submit:hover {");
		out.print("				background-color: green;");
		out.print("				color: white;");
		out.print("			}");

		out.print(" 		.back {");
		out.print("				background-color: white;");
		out.print("				border: 2px solid red;");
		out.print("				color: black;");
		out.print("				padding: 16px 32px;");
		out.print("				text-align: center;");
		out.print("				text-decoration: none;");
		out.print("				display: inline-block;");
		out.print("				font-size: 16px;");
		// out.print(" margin: 4px 2px;");
		out.print("				margin-top: 25px;");
		out.print("				transition-duration: 0.4s;");
		out.print("				cursor: pointer;");
		out.print("			}");

		out.print("			.back:hover {");
		out.print("				background-color: red;");
		out.print("				color: white;");
		out.print("			}");

		out.print("			.ansForm {");
		out.print("				display: inline-block;");
		out.print("				border: 2px solid black;");
		out.print("				margin-top: 20px;");
		out.print("				padding-bottom: 20px;");
		out.print("				padding-left: 25px;");
		out.print("				padding-right: 25px;");
		out.print("				padding-top: 20px;");
		out.print("			}");
		
		out.print(" 		.logout {");
		out.print("				background-color: white;");
		out.print("				border: 2px solid red;");
		out.print("				color: black;");
		out.print("				padding: 8px 16px;");
		out.print("				text-align: center;");
		out.print("				text-decoration: none;");
		out.print("				display: inline-block;");
		out.print("				font-size: 16px;");
		out.print(" 			margin-bottom: 5px;");
		out.print("				margin-top: 5px;");
		out.print("				transition-duration: 0.4s;");
		out.print("				cursor: pointer;");
		out.print("			}");

		out.print("			.logout:hover {");
		out.print("				background-color: red;");
		out.print("				color: white;");
		out.print("			}");
		
		out.print("		</style>");
		out.print("	</head>");
		
		out.print("	<body>");

		out.println("  <table width=\"20%\" align=\"right\" bgcolor=\"white\" border=\"0\" cellspacing=\"2\">");
		out.println("    <tr>");
		out.println("      <td align=\"right\" style=\"padding-bottom:20px\"><font size=4><b>UserID:  " + user + "</b></font></td>");
		out.println("      <td>");
		out.println("        <form action=\"" + LogoutServlet + "\" method=\"post\">");
		out.println("          <center><input type=\"submit\" class=\"logout\" value=\"Logout\"></input></center>");
		out.println("        </form>");
		out.println("      </td>");
		out.println("    </tr>");
		out.println("  </table>");

		out.println("  <br><br><br>");

		out.print("		<center>");
		out.print("		<h1 align=\"center\">Jeopardy Game Creator</h1>");
		out.print("     <h2 align=\"center\">By Khanh Tran (knt3tb) and Seven Starosta (sbs3bx)</h2>");
		out.print(
				"		<h3> Use this form to assign a row position, column position, and score to your questions <br>and proceed to creating a jeopardy grid for your game! </h3>");
		out.print("		<h3> <font color=\"red\"> Instuctions: </font></h3>");
		out.print("		<ol>");
		out.print(
				"			<li> Notice your questions and related answer(s) in the left column. <br> Note: Correct multiple choice answer is in <b>bold</b> </li>");
		out.print("			<li> Enter <b>numerical</b> values into the form under row, column, and score. </li>");
		out.print(
				"			<li> Click on Add More Questions to create additional questions for your game <br>or Create the Game! to see your grid. </li>");
		out.print("		</ol>");
		out.print("		</center>");
		out.print("		<center>");
		out.print(
				"			<form class=\"ansForm\" action=\"datareader\" method =\"post\" onSubmit=\"doPost(request, response)\">");
		out.print("				<table>");
		out.print("					<tr>");
		out.print(
				"                 	<th align=\"left\"><font color=\"red\" size=5>&nbsp;Question & Answer(s)</font></th>");
		out.print("						<th align=\"center\"><font color=\"red\" size=5>Row</font></th>");
		out.print("						<th align=\"center\"><font color=\"red\" size=5>Column</font></th>");
		out.print("						<th align=\"center\"><font color=\"red\" size=5>Score</font></th>");
		out.print("					</tr>");

		// Form printer
		///////////////////////////////////////////////////////////
		File f = new File("D:\\eclipse\\tomcat9\\webapps\\projectdata\\WEB-INF\\data\\data-file.txt");
		BufferedReader data = new BufferedReader(new FileReader(f));
		String line = data.readLine();
		int qnum = 0;
		// NEED TO ALSO WRITE LINES TO FILE, EXCEPT FOR FOUND LINE, THIS WAY
		// DELETED.
		while (line != null && line.length() > 0) {
			if (line.indexOf(';') >= 1 && line.indexOf(';', line.indexOf(';') + 1) >= 2) {

				// out.println("another hello?");
				String username = line.substring(0, line.indexOf(';'));
				// out.println("username = " + username);
				String gameid = line.substring(line.indexOf(';') + 1, line.indexOf(';', line.indexOf(';') + 1));
				// need to test without having stuff passed?
				// System.out.println((String)session.getAttribute("UserID") + "
				// a "+ (String)request.getParameter("gameid"));
				if (username.equals((String) session.getAttribute("UserID"))
						&& ((String) request.getParameter("gameid")).equals(gameid)) {
					// print out whole form here. Use a while loop
					String remainder = line.substring(line.indexOf(';', line.indexOf(';') + 1) + 1, line.length());
					while (remainder != null && remainder.length() > 0) {
						String qid = "q" + qnum;
						String rowid = "row" + qnum;
						String colid = "col" + qnum;
						String scoreid = "score" + qnum;
						String rightansid = "rightans" + qnum;
						String ansid = "ans" + qnum;
						String savedquestion = remainder.substring(0, remainder.indexOf(';'));
						out.print("					<tr>");
						out.print("						<td style=\"padding: 10px; max-width: 500px;\"> Question: "
								+ savedquestion + "<br>");
						out.print("Answer(s): ");
						// ORDER OF WRITING TO FILE?
						remainder = remainder.substring(remainder.indexOf(';') + 1, remainder.length());
						String savedanswer = remainder.substring(0, remainder.indexOf(';'));
						remainder = remainder.substring(remainder.indexOf(';') + 1, remainder.length());
						String answers = remainder.substring(9, remainder.indexOf(';', 9));
						if (answers.charAt(answers.length() - 1) == ',') {
							answers = answers.substring(0, answers.length() - 1);
						}
						out.print(answers.replace(",", " &nbsp;"));
						out.print("<br>Right answer: " + savedanswer);
						remainder = remainder.substring(remainder.indexOf(';') + 1, remainder.length());
						int row = Integer.parseInt(remainder.substring(0, remainder.indexOf(';')));
						remainder = remainder.substring(remainder.indexOf(';') + 1, remainder.length());
						int col = Integer.parseInt(remainder.substring(0, remainder.indexOf(';')));
						remainder = remainder.substring(remainder.indexOf(';') + 1, remainder.length());
						int score = Integer.parseInt(remainder.substring(0, remainder.indexOf('|')));
						out.print("						</td>");
						out.print("						<td><input type=\"text\" id=" + rowid + " name=" + rowid
								+ " value=\'" + row + "\' style=\"width: 90px\"/></td>");
						out.print("						<td><input type=\"text\" id=" + colid + " name=" + colid
								+ " value=\'" + col + "\'  style=\"width: 90px\"/></td>");
						out.print("						<td><input type=\"text\" id=" + scoreid + " name=" + scoreid
								+ " value=\'" + score + "\' style=\"width: 90px\"/></td>");
						out.print("					</tr>");
						savedquestion += ";";
						out.print("					<input name=" + qid + " value=\'" + savedquestion
								+ "\' type='hidden'>");
						out.print("					<input name=" + ansid + " value=\'" + answers
								+ "\' type='hidden'>");
						out.print("					<input name=" + rightansid + " value=\'" + savedanswer
								+ "\' type='hidden'>");
						remainder = remainder.substring(remainder.indexOf('|') + 1);
						qnum++;
					}
				}
			}
			line = data.readLine();
		}

		///////////////////////////////////////////////////////////

		/**********************************************************
		 * // Read the text file, scanning for questions. String line =
		 * data.readLine(); // the number of questions, will also be used in
		 * loop to generate unique // IDs for row, col, score values int qnum =
		 * 0; try { while (line != null) { // may want to change 'q' to be some
		 * other signal character for // the line meaning a question follows. if
		 * (line.length() > 0 && line.charAt(0) == 'q') { line =
		 * data.readLine();
		 * 
		 * if (line != null) { String qid = "q" + qnum; String rowid = "row" +
		 * qnum; String colid = "col" + qnum; String scoreid = "score" + qnum;
		 * String rightansid = "rightans" + qnum; String ansid = "ans" + qnum;
		 * String savedquestion = line + ";"; out.print("
		 * <tr>
		 * "); out.print(" <td style=\"padding: 10px; max-width: 500px;\">
		 * Question: " + line + "<br>
		 * "); out.print("Answer(s): "); line = data.readLine(); String answers
		 * = ""; String savedanswer = line; // out.print(line); while
		 * (line.length() > 0) { // out.print("current line value: " + line); if
		 * (line.length()>3 && line.substring(0, 3).equals("ans")) { line =
		 * data.readLine(); answers += line + ","; out.print("<b>" + line + "
		 * </b>"); savedanswer = line; } else { answers += line + ",";
		 * out.print(line + " "); } line = data.readLine(); } if
		 * (answers.charAt(answers.length() - 1) == ',') { answers =
		 * answers.substring(0, answers.length() - 1); } out.print("</td>");
		 * out.print("
		 * <td><input type=\"text\" id=" + rowid + " name=" + rowid + "
		 * value=\"\" placeholder=\"1\" style=\"width: 90px\"/></td>");
		 * out.print("
		 * <td><input type=\"text\" id=" + colid + " name=" + colid + "
		 * value=\"\" placeholder=\"1\" style=\"width: 90px\"/></td>");
		 * out.print("
		 * <td><input type=\"text\" id=" + scoreid + " name=" + scoreid + "
		 * value=\"\" placeholder=\"100\" style=\"width: 90px\"/></td>");
		 * out.print("
		 * </tr>
		 * "); // passing the question itself as a separate value in // the form
		 * out.print(" <input name=" + qid + " value=\'" + savedquestion + "\'
		 * type='hidden'>"); out.print(" <input name=" + ansid + " value=\'" +
		 * answers + "\' type='hidden'>"); out.print(" <input name=" +
		 * rightansid + " value=\'" + savedanswer + "\' type='hidden'>");
		 * qnum++; } } else { line = data.readLine(); } } } // catch invalid
		 * reading of file catch (Exception ex) { out.print("
		 * <tr>
		 * "); out.print("
		 * <td>" + ex + "</td>"); out.print("
		 * <td>1</td>"); out.print("
		 * <td>2</td>"); out.print("
		 * <td>3</td>"); out.print("
		 * </tr>
		 * "); }
		 * 
		 * 
		 ***********************/
		// now qnum will have number of questions, which also is number of
		// unique IDs for row, column, and score put into the form

		out.print("				</table>");
		out.print("				<input type=\"hidden\" id=\"gamename\" name=\"gamename\" value=\'"
				+ (String) request.getParameter("gameid") + "\' />");
		// out.print(" <input type=\"submit\" class=\"submit\" value=\"Create
		// the game!\">");
		// back button to add more questions
		// out.print(" <br>");
		out.print("				<input name=\"number\" value=" + qnum + " type='hidden'>");
		out.print("				<input name=\"userID\" value=" + (String) session.getAttribute("UserID")
				+ " type='hidden'>");
		out.print("<p>" + (String) session.getAttribute("UserID") + "</p>");
		out.print(
				"				<input type=\"button\" class=\"back\" onclick=\"location.href=\'http://plato.cs.virginia.edu/~knt3tb/hw2/main.php\'\"value=\"Add More Questions\">");
		out.print("				&nbsp;");
		out.print("				<input type=\"submit\" class=\"submit\" value=\"Create the Game!\">");
		out.print("			</form>");
		out.print("		</center>");
		out.print("	</body>");
		out.print("</html>");
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int num = Integer.parseInt(request.getParameter("number"));
		int numrows = 0;
		int numcols = 0;

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		// out.print(num);
		out.print("<html>");
		out.print("	<head>");
		out.print("		<meta charset=\"utf-8>\"");
		out.print("		<title></title>");
		out.print("		<style>");
		out.print("			table.grid td {");
		out.print("				border: 2px solid black;");
		out.print("				padding: 20px;");
		out.print("				font-size: 150%;");
		out.print("				background-color: #005ce6");
		// out.print(" font color: white;");
		out.print("			}");
		out.print("			table.grid td:hover {");
		out.print("				border: 2px solid black;");
		out.print("				padding: 20px;");
		out.print("				font-size: 150%;");
		out.print("				background-color: #66a3ff");
		// out.print(" font color: white;");
		out.print("			}");
		out.print(" 		.back {");
		out.print("				background-color: white;");
		out.print("				border: 2px solid red;");
		out.print("				color: black;");
		out.print("				padding: 16px 32px;");
		out.print("				text-align: center;");
		out.print("				text-decoration: none;");
		out.print("				display: inline-block;");
		out.print("				font-size: 16px;");
		// out.print(" margin: 4px 2px;");
		out.print("				margin-top: 25px;");
		out.print("				transition-duration: 0.4s;");
		out.print("				cursor: pointer;");
		out.print("			}");
		out.print("			.back:hover {");
		out.print("				background-color: red;");
		out.print("				color: white;");
		out.print("			}");
		out.print("		</style>");
		out.print("	</head>");
		out.print("	<body>");
		out.print("		<h1 align=\"center\">Jeopardy Game Creator</h1>");
		out.print("     <h2 align=\"center\">By Khanh Tran (knt3tb) and Seven Starosta (sbs3bx)");
		out.print("		<h2 align=\"center\"> Welcome to the Grid Screen! </h2>");
		out.print(
				"		<h3 align=\"center\"> <font color=\"red\"> If you would like to make changes, click Go Back and Edit to edit anything from the previous form. </font></h3>");

		// If you can't get the file to create in the tomcat folder, comment out
		// line 283 and uncomment lines 280 and 211 for it to write to your
		// desktop instead
		// String userHomeFolder = System.getProperty("user.home");
		// File f = new File(userHomeFolder + "\\Desktop\\data-file.txt");

		File f = new File("D:\\eclipse\\tomcat9\\webapps\\projectdata\\WEB-INF\\data\\data-file.txt");
		File temp = new File("D:\\eclipse\\tomcat9\\webapps\\projectdata\\WEB-INF\\data\\temp-data-file.txt");
		temp.delete();
		temp = new File("D:\\eclipse\\tomcat9\\webapps\\projectdata\\WEB-INF\\data\\temp-data-file.txt");
		// File f = new File("D:\\data-file.txt");
		// f.mkdirs();
		// f.createNewFile();

		// PrintWriter writer = new PrintWriter("data-file.txt", "UTF-8");

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(temp))) {
			// now loop through writer, checking if userID matches this user ID,
			// and if gamename matches to overwrite. If no matches,
			BufferedReader data = new BufferedReader(new FileReader(f));
			boolean haswritten = false;
			String line = data.readLine();
			while (line != null && line.length() > 0) {
				if (line.indexOf(';') >= 1 && line.indexOf(';', line.indexOf(';') + 1) >= 2) {

					// out.println("another hello?");
					String username = line.substring(0, line.indexOf(';'));
					// out.println("username = " + username);
					String gameid = line.substring(line.indexOf(';') + 1, line.indexOf(';', line.indexOf(';') + 1));
					if (username.equals((String) request.getParameter("userID"))
							&& ((String) request.getParameter("gamename")).equals(gameid)) {
						haswritten = true;
						writer.write(request.getParameter("userID") + ";");
						writer.write(request.getParameter("gamename") + ";");
						for (int i = 0; i < num; i++) {
							try {
								int currentrow = Integer.parseInt(request.getParameter("row" + i));
								int currentcol = Integer.parseInt(request.getParameter("col" + i));
								// want to write username, game name to file.
								writer.write(request.getParameter("q" + i));
								writer.write(request.getParameter("rightans" + i) + ";Answers: ");
								writer.write(request.getParameter("ans" + i) + ";");
								writer.write(request.getParameter("row" + i) + ";");
								writer.write(request.getParameter("col" + i) + ";");
								writer.write(request.getParameter("score" + i) + "|");
								if (currentrow > numrows) {
									numrows = currentrow - 1;
								}
								if (currentcol > numcols) {
									numcols = currentcol - 1;
								}
							} catch (Exception e) {

							}
						}
						writer.write("\n");
					} else {
						writer.write(line);
						writer.write("\n");
					}

				}
				line = data.readLine();
			}
			if (!haswritten) {
				writer.write(request.getParameter("userID") + ";");
				writer.write(request.getParameter("gamename") + ";");
				for (int i = 0; i < num; i++) {
					try {
						int currentrow = Integer.parseInt(request.getParameter("row" + i));
						int currentcol = Integer.parseInt(request.getParameter("col" + i));
						// want to write username, game name to file.
						writer.write(request.getParameter("q" + i));
						writer.write(request.getParameter("rightans" + i) + ";Answers: ");
						writer.write(request.getParameter("ans" + i) + ";");
						writer.write(request.getParameter("row" + i) + ";");
						writer.write(request.getParameter("col" + i) + ";");
						writer.write(request.getParameter("score" + i) + "|");
						if (currentrow > numrows) {
							numrows = currentrow - 1;
						}
						if (currentcol > numcols) {
							numcols = currentcol - 1;
						}
					} catch (Exception e) {

					}
				}
				writer.write("\n");
			}
			writer.close();
			copyFile(temp, f);
		}

		/*
		 * Checking that arguments read properly
		 * out.print(" 	<p>"+num+"</p>"); out.print(" 	<p>"+numcols+"</p>");
		 * out.print(" 	<p>"+numrows+"</p>");
		 */
		out.print("		<center>");
		out.print("			<table class=\"grid\">");
		for (int r = 1; r <= numrows + 1; r++) {
			out.print("					<tr>");
			for (int c = 1; c <= numcols + 1; c++) {
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
						out.print("<td>Error at printing stage at row " + (r + 1) + " and column" + (c + 1) + "</p>");
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
		out.print(
				"			<input type=\"button\" class=\"back\" value=\"Go Back and Edit\" onClick=history.back()-1>");
		out.print("		</center>");
		out.print("	</body>");
		out.print("</html>");
	}

	public static void copyFile(File sourceFile, File destFile) throws IOException {
		if (!destFile.exists()) {
			destFile.createNewFile();
		}

		FileChannel source = null;
		FileChannel destination = null;

		try {
			source = new FileInputStream(sourceFile).getChannel();
			destination = new FileOutputStream(destFile).getChannel();
			destination.transferFrom(source, 0, source.size());
		} finally {
			if (source != null) {
				source.close();
			}
			if (destination != null) {
				destination.close();
			}
		}
	}

}
