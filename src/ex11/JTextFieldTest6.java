package ex11;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;
import javax.swing.text.*;

public class JTextFieldTest6 extends JFrame {

    JTextField left, right, answer;
    JButton plus, minus, multiple, divide;

    public static void main( String[] args ){
        JFrame w = new JTextFieldTest6( "JTextFieldTest6" );
        w.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        w.setSize( 300, 180 );
        w.setVisible( true );
    }

    public JTextFieldTest6( String title ){
        super( title );
        JPanel pane = (JPanel)getContentPane();
        pane.setLayout( new BorderLayout() );

        left = new JTextField( 5 );
        left.setBorder( new TitledBorder( "整数1" ) );
        left.setDocument( new NumericDocument() );
        pane.add( left, BorderLayout.WEST );

        right = new JTextField( 5 );
        right.setBorder( new TitledBorder( "整数2" ) );
        right.setDocument( new NumericDocument() );
        pane.add( right, BorderLayout.EAST );

        answer = new JTextField( 10 );
        answer.setBorder( new TitledBorder( "答" ) );
        pane.add( answer, BorderLayout.SOUTH );

        JPanel panel = new JPanel();
        panel.setLayout( new GridLayout(4, 1) );
        plus = new JButton( new PlusAction() );
        panel.add( plus );
        minus = new JButton( new MinusAction() );
        panel.add( minus );
        multiple = new JButton( new MultipleAction() );
        panel.add( multiple );
        divide = new JButton( new DivideAction() );
        panel.add( divide );
        pane.add( panel, BorderLayout.CENTER );
    }
    class PlusAction extends AbstractAction {
        PlusAction() {
            putValue( Action.NAME, "+" );
            putValue( Action.SHORT_DESCRIPTION, "足す");
        }
        public void actionPerformed( ActionEvent e ) {
            int ans = Integer.parseInt( left.getText() ) + Integer.parseInt( right.getText() );
            answer.setText( String.valueOf( ans ) );
        }
    }
    class MinusAction extends AbstractAction {
        MinusAction() {
            putValue( Action.NAME, "-" );
            putValue( Action.SHORT_DESCRIPTION, "引く");
        }
        public void actionPerformed( ActionEvent e ) {
            int ans = Integer.parseInt( left.getText() ) - Integer.parseInt( right.getText() );
            answer.setText( String.valueOf( ans ) );
        }
    }
    class MultipleAction extends AbstractAction {
        MultipleAction() {
            putValue( Action.NAME, "*" );
            putValue( Action.SHORT_DESCRIPTION, "掛ける");
        }
        public void actionPerformed( ActionEvent e ) {
            int ans = Integer.parseInt( left.getText() ) * Integer.parseInt( right.getText() );
            answer.setText( String.valueOf( ans ) );
        }
    }
    class DivideAction extends AbstractAction {
        DivideAction() {
            putValue( Action.NAME, "/" );
            putValue( Action.SHORT_DESCRIPTION, "割る");
        }
        public void actionPerformed( ActionEvent e ) {
            int ans = Integer.parseInt( left.getText() ) / Integer.parseInt( right.getText() );
            answer.setText( String.valueOf( ans ) );
        }
    }

    class NumericDocument extends PlainDocument {
        String validValues = "0123456789.+-";

        public void insertString( int offset, String str, AttributeSet a ) {
            if( validValues.indexOf( str ) == -1 ){
                return;
            }
            try{
                super.insertString( offset, str, a );
            }
            catch( BadLocationException e ) {
                System.out.println( e );
            }
        }
    }
}