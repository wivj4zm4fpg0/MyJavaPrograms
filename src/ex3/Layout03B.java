package ex3;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Layout03B extends JFrame {

  public static void main(String[] args) {
    Layout03B w = new Layout03B( "Layout03B" );
    w.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
    w.setSize( 400, 600 );
    w.setVisible( true );
  }
  public Layout03B( String title ){
    super( title );
    JPanel pane = (JPanel)getContentPane();
    pane.setLayout( new GridLayout( 10, 5 ) );

    for( int i=0 ; i<50 ; i++ ){
      pane.add( new JButton( Integer.toString(i) ) );
    }
  }
}
