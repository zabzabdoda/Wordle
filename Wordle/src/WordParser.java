import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class WordParser {

	private static ArrayList<String> correctWords;
	
	public static void main(String[] args) {
		correctWords = new ArrayList<String>();
		File f = new File("dictionary2.txt");
		try {
			BufferedReader br = new BufferedReader(new FileReader(f));
			String line = br.readLine();
			do{
				if(line.length() == 5) {
					correctWords.add(line);
				}
				line = br.readLine();
			}while(line != null);
			br.close();
			BufferedWriter bw = new BufferedWriter(new FileWriter(new File("correctWords2.txt")));
			for(String s : correctWords) {
				bw.write(s.toUpperCase());
				bw.newLine();
			}
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
