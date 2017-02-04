package ex4;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Layout01D extends JFrame{
	  public static void main(String[] args) {
		    Layout01D www = new Layout01D( "Layout01D" );
		    www.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		    www.setSize( 350, 200 );
		    www.setVisible( true );
		  }
		  public Layout01D( String title ){
		    super( title );

		    JPanel pane = (JPanel)getContentPane();

		    JButton buttonNorth = new JButton( "North" );
		    pane.add( buttonNorth, BorderLayout.NORTH );

		    JButton buttonSouth = new JButton( "South" );
		    pane.add( buttonSouth, BorderLayout.SOUTH );

		    JButton buttonWest = new JButton( "West" );
		    pane.add( buttonWest, BorderLayout.WEST );

		    JButton buttonEast = new JButton( "East" );
		    pane.add( buttonEast, BorderLayout.EAST );

		    JPanel pane2 = new JPanel();
		    pane2.setLayout( new BorderLayout() );
		    pane.add( pane2, BorderLayout.CENTER );

		    JButton buttonNorth2 = new JButton( "north" );
		    pane2.add( buttonNorth2, BorderLayout.NORTH );

		    JButton buttonSouth2 = new JButton( "south" );
		    pane2.add( buttonSouth2, BorderLayout.SOUTH );

		    JButton buttonWest2 = new JButton( "west" );
		    pane2.add( buttonWest2, BorderLayout.WEST );

		    JButton buttonEast2 = new JButton( "east" );
		    pane2.add( buttonEast2, BorderLayout.EAST );

		    JButton buttonCenter2  = new JButton( "center" );
		    pane2.add( buttonCenter2, BorderLayout.CENTER );
		  }


}
