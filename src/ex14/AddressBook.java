package ex14;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class AddressBook {
	ArrayList<Address> book = new ArrayList<Address>();
	public AddressBook() {

	}
	public void add(Address address) {
		book.add(address);
	}
	public void showAddress() {
		for(int i = 0;i < book.size();i++){
			System.out.println(book.get(i));
		}
	}
	public void open(String filename) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(filename));
			String line;
			while((line = reader.readLine()) != null) {
				String[] aaa = line.split(",");
				for(int i = 0;i < aaa.length-3; i++) {
					book.add(new Address(aaa[i],aaa[i+1],aaa[i+2],aaa[i+3]));
				}
			}
			reader.close();
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		}
	}
	public void save(String filename) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(filename));
			PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(filename)));
			for(int i = 0;i < book.size();i++) {
				writer.println(book.get(i));
			}
			reader.close();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
	    AddressBook book = new AddressBook();
	    System.out.println("ファイルから読み込む。");
	    book.open("address.txt");
	    System.out.println("一覧の表示。");
	    book.showAddress();
	    System.out.println("新規データの追加。");
	    Address taroAddress = new Address("電大太郎", "東京都千代田区", "03-5280-XXXX", "taro@dendai.ac.jp");
	    book.add( taroAddress );
	    System.out.println("一覧の表示。");
	    book.showAddress();
	    System.out.println("ファイルに書き出す。");
	    book.save("address2.txt");
	}
}
