
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import java.io.*;
import java.util.Scanner;

//*********************************************************************

@WebServlet("/browse")
public class browse extends HttpServlet {
	private static final long serialVersionUID = 2L;

	// **** setting for local ****/
	private static String LoginServlet = "http://localhost:8080/webPLServlet/login";
	private static String LogoutServlet = "http://localhost:8080/webPLServlet/logout";
	private static String browseScreen = "http://localhost:8080/webPLServlet/browse";

	private static String q1_Servlet = "http://localhost:8080/CS4640/examples.question1";

	private static String classWebsite = "http://www.cs.virginia.edu/upsorn/cs4640/";

	// a data file containing username and password
	// note: this is a simple login information without encryption.
	// In reality, credential must be encrypted for security purpose
	public static String user_info = "D:\\eclipse\\tomcat9\\webapps\\cs4640\\WEB-INF\\data\\user-info.txt";

	public static String survey_info = "D:\\eclipse\\tomcat9\\webapps\\cs4640\\WEB-INF\\data\\survey-info.txt";

	// doPost() tells doGet() when the login is invalid.
	private static boolean invalidID = false;

	private String user;

	/**
	 * ***************************************************** Overrides
	 * HttpServlet's doGet(). prints the login form.
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		user = (String) session.getAttribute("UserID");

		// URL rewriting
		// user = request.getParameter("uid");

		// if the survey servlet is accessed without logging in,
		// redirect to login servlet
		if (user == null || user.length() == 0)
			response.sendRedirect(LoginServlet);

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		PrintHead(out);
		PrintBody(out);
		//PrintBottom(out);

		out.close();
	}

	/*******************************************************
	 * Overrides HttpServlet's doPost().
	 * 
	 * // assume an account will locked after 3 failed attempts // write code to
	 * check and handle this business logic // (optional)
	 ********************************************************* 
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response); // if an HTTP POST request comes in, simple
									// redraw the page by calling doGet()
	}

	public void PrintHead(PrintWriter out) {
		out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		out.println("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" "
				+ " \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\"> ");
		out.println("<html xmlns=\"http://www.w3.org/1999/xhtml\" xml:lang=\"en\" lang=\"en\">");

		out.println("<head>");
		out.println("   <meta http-equiv=\"content-type\" content=\"text/xhtml; charset=utf-8\">");
		out.println("   <title>CS4640 Session example</title>");

		out.println("   <style>");
		out.println("      body, html {");
		out.println("         margin: 10 auto;");
//		out.println("         padding: 10;");
//		out.println("         color: #202020;");
//		out.println("         background-color: #ddeeff;");
//		out.println(
//				"         font-family: 'Lucida Grande',Verdana,Helvetica,Arial,Geneva,'Bitstream Vera Sans',Sans-Serif;");
//		out.println("         font-size: 12px;");
		out.println("      }");


		out.println("      #note {");
		out.println("         font: 10px Verdana;");
		out.println("         color: red;");
		out.println("      }");

		out.println("      .errorMsg {");
		out.println("         background-color: #eeeeee;");
		out.println("         width: 100%; ");
		out.println("      }");

		out.println("      .list {");
		out.println("         width: 90%;");
		out.println("         background-color: #eeeeee;");
		out.println("      }");

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

		out.println("   </style>");
		out.println("</head>");
	}

	public void PrintBody(PrintWriter out) {
		out.println("<body >");

		out.println(
				"  <table width=\"20%\" align=\"right\" bgcolor=\"white\" border=\"0\" cellspacing=\"2\"");
		out.println("    <tr>");
		out.println("      <td align=\"right\"><font size=4><b>UserID:  " + user + "</b></font></td>");
		out.println("      <td>");
		out.println("        <form action=\"" + LogoutServlet + "\" method=\"post\">");
		out.println("          <center><input type=\"submit\" class=\"logout\" value=\"Logout\"></input></center>");
		out.println("        </form>");
		out.println("      </td>");
		out.println("    </tr>");
		out.println("  </table>");

		out.println("  <br /><br />");

		out.print("		<center>");
		out.print("		<h1 align=\"center\">Jeopardy Game Browse Screen</h1>");
		out.print("     <h2 align=\"center\">By Khanh Tran (knt3tb) and Seven Starosta (sbs3bx)</h2>");
		out.print("		</center>");

		out.println("  <br /><h3>Greetings " + user + "</h3>");
		out.println("  <p>");
		out.println("    Some information that we need to fill in later.");
		out.println("  </p>");


	}

	public void PrintBottom(PrintWriter out) {
		out.println("<br />");
		out.println("<hr />");
		out.println("<br />");
		out.println("<a href=\"" + classWebsite + "\">CS4640 Jeopardy Assignment</a>");
		out.println("<br />");

		out.println("</body>");
		out.println("</html>");
	}

}
