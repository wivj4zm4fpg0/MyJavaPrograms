package ex10;
import java.awt.*;
import javax.swing.*;

public class JListTest01B extends JFrame {
    public static void main(String[] args) {
        JListTest01B w = new JListTest01B( "JListTest01" );
        w.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        w.setSize( 300, 220 );
        w.setLocationRelativeTo(null);
        w.setVisible( true );
    }
    public JListTest01B( String title ) {
        setTitle( title );
        String[] choice = { "0000", "1111", "2222", "3333", "4444", "5555", "6666", "7777", "8888", "9999" };
        JList list = new JList( choice );
        Container contentPane = getContentPane();
        contentPane.add(list, BorderLayout.CENTER);
    }
}
