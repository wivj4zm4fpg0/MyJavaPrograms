package ex14;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ReadDic {
	public static void main(String[] args) {
		String filename = "dic.txt";
		try {
			BufferedReader reader = new BufferedReader(new FileReader(filename));
			String line;
			while((line = reader.readLine()) != null) {
				System.out.println(line);
			}
			reader.close();
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		}
	}
}