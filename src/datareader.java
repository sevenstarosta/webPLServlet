

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URL;

@WebServlet("/datareader")
public class datareader extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public datareader() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		InputStream is = new URL("http://plato.cs.virginia.edu/~knt3tb/hw2/data/data.txt").openStream();
		BufferedReader data = new BufferedReader(new InputStreamReader (is, "UTF-8"));

		out.print("<html>");
		out.print("	<head>");
		out.print("		<meta charset=\"utf-8>\"");
		out.print("		<title>Jeopardy Game Maker</title>");
		out.print("		<style>");   
		out.print(" 		.submit {");
		out.print("				background-color: white;");
		out.print("				border: 2px solid green;");
		out.print("				color: black;");
		out.print("				padding: 8px 16px;");
		out.print("				text-align: center;");
		out.print("				text-decoration: none;");
		out.print("				display: inline-block;");
		out.print("				font-size: 16px;");
		out.print("				margin: 4px 2px;");
		out.print("				transition-duration: 0.4s;");
		out.print("				cursor: pointer;");
		out.print("			}");
		out.print("			.submit:hover {");
		out.print("				color: white;");
		out.print("			}");
		out.print("			.ansForm {");
		out.print("				border: 2px solid black;");
		out.print("				margin: 20px 500px 20px 500px;");
		out.print("				padding-bottom: 5px;");
		out.print("			}");
		out.print("		</style>");  
		out.print("	</head>");
		out.print("	<body>");
		out.print("		<h1 align=\"center\">Jeopardy Game Creator</h1>");
		out.print("     <h2 align=\"center\">By Khanh Tran (knt3tb) and Seven Starosta (sbs3bx)");
		out.print("		<center>");
		out.print("			<form class=\"ansForm\" method =\"post\" onSubmit=\"doPost(request, response)\">");
		out.print("				<table>");
		out.print("					<tr>");
		out.print("                 	<th align=\"left\">Question</th>");
		out.print("						<th align=\"left\">Row</th>");
		out.print("						<th align=\"left\">Column</th>");
		out.print("						<th>Score</th>");
		out.print("					</tr>");
		
		//Read the text file, scanning for questions.
		String line = data.readLine();
		//the number of questions, will also be used in loop to generate unique IDs for row, col, score values
		int qnum=0;
		try
		{
			while(line != null)
			{
				//may want to change 'q' to be some other signal character for the line meaning a question follows.
				if(line.length() > 0 && line.charAt(0) =='q')
				{
					line = data.readLine();
					if (line != null)
					{
						String rowid="row"+qnum;
						String colid="col"+qnum;
						String scoreid="score"+qnum;
						out.print("					<tr>");
						out.print("						<td>"+line+"</td>");
						out.print("						<td><input type=\"text\" id="+rowid+" name="+rowid+" value=\"\" placeholder=\"1\" style=\"width: 60px\"/></td>");
						out.print("						<td><input type=\"text\" id="+colid+" name="+colid+" value=\"\" placeholder=\"1\" style=\"width: 60px\"/></td>");
						out.print("						<td><input type=\"text\" id="+scoreid+" name="+scoreid+" value=\"\" placeholder=\"100\" style=\"width: 60px\"/></td>");
						out.print("					</tr>");
						qnum++;
					}
				}
				else
				{
					line = data.readLine();
				}
			}
		}
		catch (Exception ex)
		{
			out.print("					<tr>");
			out.print("						<td>"+ex+"</td>");
			out.print("						<td>1</td>");
			out.print("						<td>2</td>");
			out.print("						<td>3</td>");
			out.print("					</tr>");
		}
		//now qnum will have number of questions, which also is number of unique IDs for row, column, and score put into the form
		
		
		out.print("				</table>");
		//need submit button, also need to submit qnum in POST
		out.print("<input type=\"submit\" class=\"submit\" value=\"Create the game!\">");
		out.print("			</form>");
		out.print("		</center>");
		out.print("	</body>");
		out.print("</html>");
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
