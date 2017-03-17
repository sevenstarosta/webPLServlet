import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class HtmlToServlet {

	public static void main(String[] args) throws IOException {
		/*Read input as command line arg, create a new line, add out.println(" to the line, 
		 * add the html content to the line, including a backslash in front of quotation marks*/
		String fileName = "holder.html";
		Scanner scanner = new Scanner(new FileReader(fileName));
		try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("ServletHtmlText.txt"), "UTF-8"))) {
			while (scanner.hasNext()) {
				String line = scanner.nextLine();
				String outputLine = "out.println(\"";
				String[] characters = line.split("");
				for (String character : characters) {
					if (character.equals("\""))
						outputLine += "\\\"";
					else
						outputLine += character;
				}
				outputLine += "\");";
				writer.write(outputLine);
				writer.newLine();
			}
		}
	}
}