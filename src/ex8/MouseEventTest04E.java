package ex8;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;

public class MouseEventTest04E extends JFrame {
  int x0 = 0;
  int y0 = 0;
  JPanel panel;
  JButton bt;

  public static void main(String[] args) {
    MouseEventTest04E w = new MouseEventTest04E( "MouseEventTest04E" );
    w.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
    w.setSize( 350, 250 );
    w.setVisible( true );
  }
  public MouseEventTest04E( String title ){
    super( title );
    panel = (JPanel)getContentPane();
    panel.setBackground(Color.white);
    panel.addMouseListener( new MouseCheck() );
    panel.addMouseMotionListener( new MouseCheck() );
    bt = new JButton( "クリア" );
    bt.addMouseListener( new ButtonCheck() );
    panel.add( bt, BorderLayout.NORTH );
  }
  class MouseCheck extends MouseInputAdapter {
    public void mousePressed( MouseEvent e ){
      x0 = e.getX();
      y0 = e.getY();
    }
    public void mouseDragged( MouseEvent e ){
      Graphics g = panel.getGraphics();
      int x = e.getX();
      int y = e.getY();
      g.drawLine( x0, y0, x, y );
      x0 = x;
      y0 = y;
    }
  }
  class ButtonCheck extends MouseAdapter {
    public void mouseClicked( MouseEvent e ){
      Graphics g = panel.getGraphics();
      Dimension size = getSize();

      g.setColor(Color.white);
      g.fillRect(0, 0, size.width, size.height);
   }
  }
}
