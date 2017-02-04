package ex4;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;

import javax.swing.Icon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ImageLabel02B extends JFrame {

	  public static void main(String[] args) {
	    ImageLabel02B w = new ImageLabel02B( "ImageLabel02B" );
	    w.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
	    w.setSize( 150, 250 );
	    w.setVisible( true );
	  }
	  public ImageLabel02B( String title ){
	    super( title );
	    JLabel panel = new JLabel( new DrawIcon() );
	    getContentPane().add( panel );
	  }
	  class DrawIcon implements Icon {
	    static final int width  = 50;
	    static final int height = 50;

	    public void paintIcon( Component c, Graphics g, int x, int y ) {
	      g.setColor( Color.white );
	      g.fillRect( x - 20, y - 65, 90, 180 );
	      g.setColor( Color.blue );
	      g.fillOval( x, y - 50, width, height );
	      int xPoints[] = { x - 10, x + 25, x + 60 };
	      int yPoints[] = { y + 5, y + 105, y + 5 };
	      g.fillPolygon( xPoints, yPoints, 3 );
	    }
	    public int getIconWidth() {
	      return this.width;
	    }
	    public int getIconHeight() {
	      return this.height;
	    }
	  }
	}
