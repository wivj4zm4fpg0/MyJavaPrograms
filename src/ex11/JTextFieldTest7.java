package ex11;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JTextFieldTest7 extends JFrame {
    ActionListener actionListener = new TextFieldTestAction();
    JTextField tf;
    DefaultListModel model;

    public static void main(String[] args) {
        JTextFieldTest7 w = new JTextFieldTest7("JTestFieldTest7");
        w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        w.setSize(300, 220);
        w.setLocationRelativeTo(null);
        w.setVisible(true);
    }

    public JTextFieldTest7(String title) {
        setTitle(title);
        model = new DefaultListModel();
        JList list = new JList(model);
        JScrollPane sp = new JScrollPane();
        sp.getViewport().setView(list);
        Container contentPane = getContentPane();
        contentPane.add(sp, BorderLayout.CENTER);
        list.setBorder(new TitledBorder("項目一覧"));
        JPanel pane = (JPanel) getContentPane();
        pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));

        tf = new JTextField();
        tf.setBorder(new TitledBorder("項目入力"));
        tf.addActionListener(actionListener);
        pane.add(tf);
    }

    class TextFieldTestAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JTextField source = (JTextField) e.getSource();
            String string = source.getText();
            model.addElement(string);
            tf.setText("");
        }
    }
}
