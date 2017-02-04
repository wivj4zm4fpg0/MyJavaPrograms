package ex14;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class StdInToFile {
	public static void main(String[] args) {
		String filename = "Test.txt";
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(filename)));
			String line;
			while((line = reader.readLine()) != null) {
				writer.println(line);
			}
			reader.close();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
