package ex11;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;

public class JTextFieldTest3A extends JFrame {

    ActionListener actionListener = new TextFieldTestAction(); // リスナの生成
    JTextField tf1, tf2;

    public static void main(String[] args) {
        JFrame w = new JTextFieldTest3A("JTextFieldTest3A");
        w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        w.setSize(150, 120);
        w.setVisible(true);
        w.setLocationRelativeTo(null);
    }

    public JTextFieldTest3A(String title) {
        super(title);
        JPanel pane = (JPanel) getContentPane();
        pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));

        tf1 = new JTextField();
        tf1.setBorder(new TitledBorder("コピー元"));
        tf1.addActionListener(actionListener);
        pane.add(tf1);

        tf2 = new JTextField();
        tf2.setBorder(new TitledBorder("コピー先"));
        tf2.addActionListener(actionListener);
        pane.add(tf2);
    }

    class TextFieldTestAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JTextField source = (JTextField) e.getSource();
            String string = source.getText();
            tf2.setText(string);
            tf1.setText("");
        }
    }
}