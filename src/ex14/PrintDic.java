package ex14;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class PrintDic {
	public static void main(String[] args) {
		String filename = "dic.txt";
		try {
			BufferedReader reader = new BufferedReader(new FileReader(filename));
			String line;
			while((line = reader.readLine()) != null) {
				String[] aaa = line.split(",");
				for(int i = 0;i < aaa.length-1; i++) {
					System.out.println("英単語["+aaa[i]+"]は、日本語の["+aaa[i + 1]+"]という意味です。");
				}
			}
			reader.close();
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		}
	}
}
