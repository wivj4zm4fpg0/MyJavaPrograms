package ex8;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class KeyEventTest02D extends JFrame {

  public static void main(String[] args) {
    KeyEventTest02D w = new KeyEventTest02D( "KeyEventTest02D" );
    w.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
    w.setSize( 300, 120 );
    w.setVisible( true );
  }
  public KeyEventTest02D( String title ){
    super( title );
    JPanel panel = (JPanel)getContentPane();
    panel.setLayout( new GridLayout( 4, 2 ) );

    JTextField tf1 = new JTextField();
    tf1.setName( "TextField 1" );
    JTextField tf2 = new JTextField();
    tf2.setName( "TextField 2" );
    JTextField tf3 = new JTextField();
    tf3.setName( "TextField 3" );
    JTextField tf4 = new JTextField();
    tf4.setName( "TextField 4");

    tf1.addKeyListener( new KeyCheck() );
    tf2.addKeyListener( new KeyCheck() );
    tf3.addKeyListener( new KeyCheck() );
    tf4.addKeyListener( new KeyCheck());

    panel.add( new JLabel( "名前" ) );
    panel.add( tf1 );
    panel.add( new JLabel( "学籍番号" ) );
    panel.add( tf2 );
    panel.add( new JLabel( "学部" ) );
    panel.add( tf3 );
    panel.add( new JLabel( "学科名" ) );
    panel.add( tf4 );

  }
  class KeyCheck extends KeyAdapter {
    public void keyPressed( KeyEvent e ){
      JTextField com = (JTextField)e.getSource();
      System.out.println( com.getName() );
      char keyChar = e.getKeyChar();
      System.out.print(keyChar  + "\n");
      if( e.isShiftDown() )   System.out.print( " +Shift" );
      if( e.isControlDown() ) System.out.print( " +Control" );
      if( e.isAltDown() )     System.out.print( " +Alt" );
    }
  }
}
