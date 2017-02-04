package ex14;

import javax.swing.*;

class JTableExample extends JFrame {
	String[] columnNames = { 
			"品目コード", "品目名", "販売単価", "仕入単価", "メーカーコード"
	};
	String[][] data = {
			{ "CP001",   "コピー用紙A4", "600", "420", "M01" },
			{ "CP002",   "コピー用紙B5", "500", "350", "M01" },
			{ "LL001", "ルーズリーフA4", "300", "210", "M03" },
			{ "LL002", "ルーズリーフB5", "300", "210", "M03" },
			{ "NT001",       "ノートA4", "200", "140", "M02" },
			{ "NT002",       "ノートB5", "200", "140", "M02" },
			{ "NT003",       "ノートA5", "180", "130", "M02" },
	};

	JTableExample( String title ) {
		super( title );
		JTable table = new JTable( data, columnNames );
		JScrollPane scroll = new JScrollPane( table );
		getContentPane().add( scroll );
		setSize( 600, 200 );
		setVisible( true );
	}        

	public static void main(String[] args) {
		JFrame frame = new JTableExample( "JTableExample" );
		frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
	}
}
