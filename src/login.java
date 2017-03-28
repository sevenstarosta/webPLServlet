
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import java.io.*;
import java.util.Scanner;

//*********************************************************************

@WebServlet("/login")
public class login extends HttpServlet {
	private static final long serialVersionUID = 2L;

	// **** setting for local ****/
	// private static String LoginServlet =
	// "http://localhost:8080/CS4640/examples.login";
	// private static String SurveyServlet =
	// "http://localhost:8080/CS4640/examples.survey";

	private static String LoginServlet = "http://localhost:8080/webPLServlet/login";
	private static String browseScreen = "http://localhost:8080/webPLServlet/browse";
	private static String datareader = "http://localhost:8080/webPLServlet/datareader";
	// private static String classWebsite =
	// "http://www.cs.virginia.edu/upsorn/cs4640/";

	// a data file containing username and password
	// note: this is a simple login information without encryption.
	// In reality, credential must be encrypted for security purpose
	public static String user_info = "D:\\eclipse\\tomcat9\\webapps\\projectdata\\WEB-INF\\data\\user-info.txt";
	// public static String user_info = System.getProperty("user.home") +
	// "\\Desktop\\user-info.txt";
	// public static String survey_info =
	// "D:\\eclipse\\tomcat9\\webapps\\cs4640\\WEB-INF\\data\\survey-info.txt";

	// Form parameters.
	private static String btn;
	// info for returning users
	private static String userID;
	private static String pwd;
	// info for new users
	private static String newUserID;
	private static String newPwd;
	private static String confirmPwd;
	// private static String email;

	// doPost() tells doGet() when the login is invalid.
	private static boolean invalidID = false;

	private int number_attempts = 0;

	private boolean lockedOut = false;

	// private Timer time = new Timer();

	/**
	 * ***************************************************** Overrides
	 * HttpServlet's doGet(). prints the login form.
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		out.println("<html>");

		out.println("<head>");
		out.println("  <title>Login Screen</title>");

		out.println("   <style>");
		out.println("      body, html {");
		out.println("         margin: 10 auto;");
		// out.println(" padding: 10;");
		// out.println(" color: #202020;");
		// out.println(" background-color: #ddeeff;");
		// out.println(
		// " font-family: 'Lucida
		// Grande',Verdana,Helvetica,Arial,Geneva,'Bitstream Vera
		// Sans',Sans-Serif;");
		// out.println(" font-size: 12px;");
		out.println("      }");

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

		out.print(" 		.register {");
		out.print("				background-color: white;");
		out.print("				border: 2px solid blue;");
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

		out.print("			.register:hover {");
		out.print("				background-color: blue;");
		out.print("				color: white;");
		out.print("			}");

		// out.println(" input[type=text] {");
		// out.println(" border: 1px solid #cccccc;");
		// out.println(" font: 11px Verdana;");
		// out.println(" color: black;");
		// out.println(" line-height: 1.4em;");
		// out.println(" }");

		out.println("   </style>");

		out.println("</head>");

		out.println("<body onLoad=\"setFocus()\" >");
		out.println("<b><center><h1>Jeopardy Login Screen<h1></center></b>");
		out.print("     <h2 align=\"center\">By Khanh Tran (knt3tb) and Seven Starosta (sbs3bx)</h2>");

		out.println("<br /><br />");

		// I can't figure out why the text doesn't appear until the end of the
		// timer


		if (number_attempts >= 3 && invalidID && btn.equals("Log in")) {
			
			int countdown = 30;


			out.println("<br><font color=\"red\"><center>Too many failed attempts. Please try again after " + countdown
					+ " seconds</center></font><br><br>");

			lockedOut = true;
			
			if (lockedOut == true) {

//				while (countdown > 0) {
//
//					System.out.println(countdown);
//					countdown--;
//					try {
//
//						Thread.sleep(1000);
//					} catch (InterruptedException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//				}
//				System.out.println("done!");
//				number_attempts--;
//				System.out.println(number_attempts);
//				lockedOut = false;
//				System.out.println(lockedOut);
//
//			}

			// runTime(lockedOut);

		}


		// while (lockedOut) {
		// timer.schedule(new TimerTask() {
		// int countdown = 30;
		//
		// public void run() {
		// while (countdown > 0) {
		// countdown = countdown -1;
		// System.out.println(countdown);
		// }
		// System.out.println("done!");
		// number_attempts = 0;
		// System.out.println(number_attempts);
		// lockedOut = false;
		// System.out.println(lockedOut);
		// //label.setText(countdown + "seconds left");
		// }
		// }, 1000, 10000);
		// }

		if (invalidID && btn.equals("Log in") && number_attempts < 3 && !lockedOut) { // called
																						// from
																						// doPost(),
																						// invalid
																						// ID
																						// entered.
			invalidID = false;
			out.println(
					"<br><font color=\"red\"><center>Invalid user ID or password. Please try again.</center></font><br><br>");
			number_attempts++;
		}

		out.println("<center>");
		// Returning users
		out.println("<form method=\"post\"");
		out.println("        action=\"" + LoginServlet + "\" id=\"form1\" name=\"form1\">");
		out.println("  <table Cellspacing=\"0\" Cellpadding=\"3\" Border=\"0\" >");
		out.println("    <tr align=\"center\"><b>Returning Users:</b></tr><br><br>");
		out.println("    <tr>");
		out.println("      UserID:");
		out.println("      <input autofocus type=\"text\" name=\"UserID\" size=\"15\" maxLength=\"20\">");
		out.println("		&nbsp;");
		out.println("      Password:");
		out.println("      <input type=\"password\" name=\"pwd\" size=\"15\" maxlength=\"20\">");
		out.println("    </tr>");
		out.println("    <tr>");
		out.println(" 	<td></td>");
		out.println(" 	<td></td>");
		out.println("      <td><input type=\"submit\" class=\"submit\" value=\"Log in\" name=\"btn\"></input");
		out.println("    </tr>");
		out.println("  </table>");
		out.println("</form>");
		out.println("</center>");

		out.println("<br />");
		out.println("<hr />");
		out.println("<br />");

		if (invalidID && btn.equals("Register")) { // called from doPost(),
													// invalid ID entered.
			invalidID = false;
			out.println(
					"<br><font color=\"red\"><center>Invalid input. Please make sure passwords match and that no fields are left blank and try again.</center></font><br><br>");
		}

		out.print("<center>");
		// Register new user
		out.println("<form method=\"post\"");
		out.println("        action=\"" + LoginServlet + "\" id=\"form2\" name=\"form2\">");
		out.println("  <table Cellspacing=\"0\" Cellpadding=\"3\" Border=\"0\" >");
		out.println("    <tr align=\"center\"><b>Register New User:</b></tr><br><br>");
		out.println("    <tr>");
		out.println("      UserID:");
		out.println("      <input type=\"text\" name=\"newUserID\" size=\"15\" maxLength=\"20\"><br><br>");
		out.println("      Password:");
		out.println("      <input type=\"password\" name=\"newPwd\" size=\"15\" maxlength=\"20\"><br><br>");
		out.println("      Confirm Password:");
		out.println("      <input type=\"password\" name=\"confirmPwd\" size=\"15\" maxlength=\"20\">");
		out.println("    </tr>");
		out.println("    <tr>");
		out.println(" 	<td></td>");
		out.println(" 	<td></td>");
		out.println("      <td ><input type=\"submit\" class=\"register\" value=\"Register\" name=\"btn\"></input");
		out.println("    </tr>");
		out.println("  </table>");
		out.println("</form>");
		out.println("</center>");

		// out.println("<br />");
		// out.println("<hr />");
		// out.println("<br />");
		// out.println("<a href=\"" + classWebsite + "\">CS4640 &mdash; class
		// website</a>");
		// out.println("<br />");

		out.println("</body>");
		out.println("</html>");

		out.close();
	}

	// private void runTime(boolean lockedOut2) {
	//
	// if (lockedOut2 == true) {
	// int countdown = 30;
	// while (countdown > 0) {
	//
	// System.out.println(countdown);
	// countdown--;
	// try {
	//
	// Thread.sleep(1000);
	// } catch (InterruptedException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }
	// System.out.println("done!");
	// number_attempts--;
	// System.out.println(number_attempts);
	// lockedOut = false;
	// System.out.println(lockedOut);
	//
	// }
	//
	// }

	/*******************************************************
	 * Overrides HttpServlet's doPost().
	 * 
	 * // assume an account will locked after 3 failed attempts // write code to
	 * check and handle this business logic // (optional)
	 * 
	 * @return
	 ********************************************************* 
	 */

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);

		btn = request.getParameter("btn");

		// userID = "";
		// pwd = "";

		userID = request.getParameter("UserID");
		pwd = request.getParameter("pwd");

		newUserID = request.getParameter("newUserID");
		newPwd = request.getParameter("newPwd");
		confirmPwd = request.getParameter("confirmPwd");
		// email = request.getParameter("email");

		// need a more sophisticated input validation (not implemented yet)

		if (btn.equals("Register")) {
			if (newPwd.equals(confirmPwd) && newUserID.length() > 0 && newPwd.length() > 0) {
				registerNewUser(newUserID, newPwd);
				userID = newUserID;
				pwd = newPwd;
			}
		}

		if (isValid(userID, pwd)) { // successful
			session.setAttribute("isLogin", "Yes");
			session.setAttribute("UserID", userID);

			response.sendRedirect(browseScreen);

			// for URL rewriting
			// String url_with_param = SurveyServlet + "?uid=" + userID;
			// response.sendRedirect(url_with_param);

		} else { // unsuccessful
			session.setAttribute("isLogin", "No");
			session.setAttribute("UserID", "");

			invalidID = true;
			doGet(request, response);
		}

	}

	/**
	 * isValid: verify userid and password
	 * 
	 * @param userid
	 * @param password
	 * @return true -- userid/password matches, false -- non-existent userid or
	 *         userid/password does not match
	 */
	private boolean isValid(String userid, String password) {
		// System.out.println(userid);
		// System.out.println(password);
		if (userid == null)
			userid = "";
		if (password == null)
			password = "";
		// System.out.println(userid);
		// System.out.println(password);
		boolean is_valid = false;
		if (userid.length() == 0 || password.length() == 0)
			return false;

		try {
			BufferedWriter fileOut = new BufferedWriter(new FileWriter(user_info, true));
			Scanner scanner = new Scanner(new BufferedReader(new FileReader(user_info)));

			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				String existing_user = line.substring(0, line.indexOf(","));
				// System.out.println("existing_user = " + existing_user);
				String existing_pwd = line.substring(line.indexOf(",") + 1, line.length());
				// System.out.println("existing_pwd = " + existing_pwd);
				if (userid.equals(existing_user) && password.equals(existing_pwd)) {
					is_valid = true;
					number_attempts = 0;
					break;
				}
			}

			scanner.close();
			fileOut.close();

		} catch (Exception e) {
			System.out.println("Unable to verify the user information ");

			// One potential cause of this exception is the path to the data
			// file
			// To verify, open the data file in a web browser
			// Use the path you saw in the browser's address bar to access the
			// data file
			// (note: excluding "file:///")

		}

		return is_valid;
	}

	/**
	 * registerNewUser: register a new userid/password/email to user_info.txt
	 * 
	 * @param userid
	 * @param pwd
	 * @param email
	 *            note: need to verify if userid already exists (not
	 *            implemented) need to encrypt the credential (not implemented)
	 */
	private void registerNewUser(String userid, String pwd) {
		FileWriter fw = null;
		PrintWriter pw = null;

		try {
			fw = new FileWriter(user_info, true);
			pw = new PrintWriter(fw);

			pw.println(userid + "," + pwd);
		} catch (Exception e) {
			System.out.println("ERROR while registering new users " + e);
		} finally {
			try {
				pw.flush(); // flush output stream to file
				pw.close(); // close print writer
			} catch (Exception ignored) {
			}
			try {
				fw.close();
			} catch (Exception ignored) {
			}
		}
	}

}
