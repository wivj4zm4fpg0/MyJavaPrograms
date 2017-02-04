package ex14;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.*;

class AddressTable extends JFrame {
	String[] columnNames = { 
			"名前", "住所", "電話", "メール"
	};
	String[][] data = new String[4][4];

	public void open(String filename) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(filename));
			String  line;
			int n = 0;
			while((line = reader.readLine()) != null) {
				String[] table = line.split(",");
				for(int i = 0; i < table.length; i++) {
					data[n][i] = table[i];
				}
				n++;
			}
			reader.close();
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	AddressTable( String title ) {
		super( title );
		open("address.txt");
		JTable table = new JTable( data, columnNames );
		JScrollPane scroll = new JScrollPane( table );
		getContentPane().add( scroll );
		setSize( 600, 200 );
		setVisible( true );
	}        

	public static void main(String[] args) {
		JFrame frame = new AddressTable( "AddressTable" );
		frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
	}
}
