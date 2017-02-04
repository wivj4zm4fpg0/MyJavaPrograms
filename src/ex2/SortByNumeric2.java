package ex2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;

public class SortByNumeric2 {
	public static void main(String[] args) {
		final int n = 50000;
		ArrayList <String> list = new ArrayList<String>();
		String file1 = "rand2.txt";
		String file2 = "result_ex2-5.txt";
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file1));
			String line;
			while((line = reader.readLine()) != null) {
				list.add(line);
			}
			reader.close();
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		}

	    Collections.sort(list,new NumericComparator());

		try {
			BufferedReader reader = new BufferedReader(new FileReader(file2));
			PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(file2)));
			for(int i = 0;i < n;i++) {
				writer.println(list.get(i));
			}
			reader.close();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
