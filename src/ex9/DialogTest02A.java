package ex9;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;

public class DialogTest02A extends JFrame {
  JPanel pane;
  public static void main(String[] args) {
    DialogTest02A w = new DialogTest02A( "DialogTest02" );
    w.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
    w.setSize( 550, 300 );
    w.setVisible( true );
  }
  public DialogTest02A( String title ) {
    super( title );
    pane = (JPanel)getContentPane();
    JToolBar tool = new JToolBar();
    pane.add( tool, BorderLayout.NORTH );
    tool.add( new Dialog02A( "Input Dialog ComboBox type" ) );
  }
  class Dialog02A extends AbstractAction {
    Dialog02A( String text ){ super( text ); }
    public void actionPerformed( ActionEvent e ){

      Object[] msg = { "血液型を選んでください" };
      Object[] choice = { "A", "B", "O", "AB" };
      Object ans = JOptionPane.showInputDialog( pane, msg, "Input Dialog",
                         JOptionPane.PLAIN_MESSAGE, new ImageIcon( "" ),
                         choice, choice[0] );
      if(ans != null) {
        System.out.println("あなたの血液型は" + ans + "型です" );
      } else {
        System.out.println("取り消しました");
      }
    }
  }
}
