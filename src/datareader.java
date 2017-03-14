

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
		out.print("				background-color: #71FF71;");
		out.print("			}");
		
		out.print(" 		.back {");
		out.print("				background-color: white;");
		out.print("				border: 2px solid #AF0000;");
		out.print("				color: black;");
		out.print("				padding: 8px 8px;");
		out.print("				text-align: center;");
		out.print("				text-decoration: none;");
		out.print("				display: inline-block;");
		out.print("				font-size: 16px;");
		out.print("				margin: 4px 2px;");
		out.print("				transition-duration: 0.4s;");
		out.print("				cursor: pointer;");
		out.print("			}");
		out.print("			.back:hover {");
		out.print("				background-color: pink;");
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
		out.print("						<th align=\"left\">Score</th>");
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
						String qid="q"+qnum;
						String rowid="row"+qnum;
						String colid="col"+qnum;
						String scoreid="score"+qnum;
						out.print("					<tr>");
						out.print("						<td>"+line+"</td>");
						out.print("						<td><input type=\"text\" id="+rowid+" name="+rowid+" value=\"\" placeholder=\"1\" style=\"width: 60px\"/></td>");
						out.print("						<td><input type=\"text\" id="+colid+" name="+colid+" value=\"\" placeholder=\"1\" style=\"width: 60px\"/></td>");
						out.print("						<td><input type=\"text\" id="+scoreid+" name="+scoreid+" value=\"\" placeholder=\"100\" style=\"width: 60px\"/></td>");
						out.print("					</tr>");
						//passing the question itself as a separate value in the form
						out.print("					<input name="+qid+" value="+line+" type='hidden'>");
						qnum++;
					}
				}
				else
				{
					line = data.readLine();
				}
			}
		}
		//catch invalid reading of file
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
		out.print("				<input type=\"submit\" class=\"submit\" value=\"Create the game!\">");
		//back button to add more questions
		out.print("				<br>");
		out.print("				<input name=\"number\" value="+qnum+" type='hidden'>");
		out.print("				<input type=\"button\" class=\"back\" onclick=\"location.href=\'http://plato.cs.virginia.edu/~knt3tb/hw2/main.php\'\"value=\"Add more questions\">");
		out.print("			</form>");
		out.print("		</center>");
		out.print("	</body>");
		out.print("</html>");
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		int num = Integer.parseInt(request.getParameter("number"));
		int numrows=0;
		int numcols=0;

		
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.print("<html>");
		out.print("	<head>");
		out.print("		<meta charset=\"utf-8>\"");
		out.print("		<title>Jeopardy Game Maker</title>");
		out.print("	</head>");
		out.print("	<body>");
		
		//NEED TO WRITE TO A LOCAL FILE HERE
		//include question on one line, row on next, col on next, score on last, then blank line
		//Code below may display questions without having to read file, reading file will be for later HW assignments
		//questions passed in as "q0", "q1", etc
		//row values for questions passed in with ids as "row0", "row1" etc
		//same for column and score values as "col0", "score0"
		for(int i=0;i<num;i++)
		{
			try {
				int currentrow= Integer.parseInt(request.getParameter("row"+i));
				int currentcol= Integer.parseInt(request.getParameter("col"+i));
				if (currentrow>numrows)
				{
					numrows=currentrow-1;
				}
				if(currentcol>numcols)
				{
					numcols=currentcol-1;
				}
				//write to some file here... here is my pseudocode. You should overwrite the file each time you submit so that there is only one game at a time.
				//file.append(getParameter(q+i))
				//file.append(getParameter(row+i))
				//file.append(getParameter(col+i))
				//file.append(getParameter(score+i))
				//file.append(\n)
			} 
			catch (Exception e) 
			{
				
			}
		}
		
		/* Checking that arguments read properly
		out.print(" 	<p>"+num+"</p>");
		out.print(" 	<p>"+numcols+"</p>");
		out.print(" 	<p>"+numrows+"</p>");
		*/
		out.print("		<center>");
		
		out.print("			<table>");
		//create nested for loops, with try catch to prevent errors if no question at r, c
		int r = 1;
		int c = 1;
		
		//ERROR PROPBABLY IN THESE LOOPS. also, format the table so it has a border
		for(;r<=numrows;r++);
		{
			out.print("					<tr>");
			for(;c<=numcols;c++)
			{
				for(int i=0;i<num;i++)
				{
					try{
						int currentrow= Integer.parseInt(request.getParameter("row"+i));
						int currentcol= Integer.parseInt(request.getParameter("col"+i));
						if(currentrow == r && currentcol == c)
						{
							out.print("					<td>"+request.getParameter("score"+i)+"</td>");
						}
					}
					catch (Exception e)
					{
						out.print("<td>Error at printing stage at row "+(r+1)+" and column"+(c+1)+"</p>");
						out.print("<p>"+e+"</p>");
					}
				}
			}
			out.print("					</tr>");
		}
		out.print("			</table>");
		out.print("		</center>");
		out.print("	</body>");
		out.print("</html>");
		
		
		//need to write to file based on input data
		
	}

}
