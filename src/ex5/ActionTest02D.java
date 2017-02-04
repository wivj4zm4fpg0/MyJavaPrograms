package ex5;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;

public class ActionTest02D extends JFrame {

  private int i = 0;
  public static void main(String[] args) {
    ActionTest02D w = new ActionTest02D( "ActionTest02D" );
    w.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
    w.setSize( 300, 200 );
    w.setVisible( true );
    System.out.println("初期値は0");
  }
  public ActionTest02D( String title ){
    super( title );
    JButton button = new JButton( new Action02() );
    getContentPane().add( button, BorderLayout.WEST );
    JButton button2 = new JButton( new Action03() );
    getContentPane().add( button2, BorderLayout.EAST );
  }
  class Action02 extends AbstractAction{
    Action02(){
      putValue( Action.NAME, "増やす" );
      putValue( Action.SHORT_DESCRIPTION, "ツールチップ" );
    }
    public void actionPerformed( ActionEvent e ){
      i++;
      System.out.println( "現在値は" + i );
    }
  }
  class Action03 extends AbstractAction{
    Action03(){
      putValue( Action.NAME, "減らす" );
      putValue( Action.SHORT_DESCRIPTION, "ツールチップ" );
    }
    public void actionPerformed( ActionEvent e ){
      i--;
      System.out.println( "現在値は" + i );
    }
  }
}
