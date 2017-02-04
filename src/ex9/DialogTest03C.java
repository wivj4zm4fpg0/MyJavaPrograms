package ex9;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;

public class DialogTest03C extends JFrame {
  JPanel pane;
  public static void main(String[] args) {
    DialogTest03C w = new DialogTest03C( "DialogTest03" );
    w.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
    w.setSize( 550, 300 );
    w.setVisible( true );
  }
  public DialogTest03C( String title ) {
    super( title );
    pane = (JPanel)getContentPane();
    JToolBar tool = new JToolBar();
    pane.add( tool, BorderLayout.NORTH );
    tool.add( new Dialog03( "Confirm Dialog" ) );
  }
  class Dialog03 extends AbstractAction {
    Dialog03( String text ){ super( text ); }
    public void actionPerformed( ActionEvent e ){

      Object[] msg = { "Javaは得意ですか?" };
      int ans = JOptionPane.showConfirmDialog( pane, msg, "Java Question", JOptionPane.YES_NO_OPTION );
      if(ans == 0) {
        Object[] msg2 = { "Javaは得意です" };
        int ans2 = JOptionPane.showConfirmDialog( pane, msg2, "Java Answer", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE );
      } else if(ans == 1) {
        Object[] msg2 = { "Javaは苦手です" };
        int ans2 = JOptionPane.showConfirmDialog( pane, msg2, "Java Answer", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE );
      }
    }
  }
}
