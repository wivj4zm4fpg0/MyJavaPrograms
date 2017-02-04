package ex13;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JListTest10B extends JFrame {
    ActionListener actionListener = new TextFieldTestAction();
    JTextField tf;
    DefaultListModel model;

    public static void main(String[] args) {
        JListTest10B w = new JListTest10B("JListTest10B");
        w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        w.setSize(300, 220);
        w.setLocationRelativeTo(null);
        w.setVisible(true);
    }

    public JListTest10B(String title) {
        setTitle(title);
        model = new DefaultListModel();
        JList list = new JList(model);
        JScrollPane sp = new JScrollPane();
        sp.getViewport().setView(list);
        Container contentPane = getContentPane();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        JButton btn = new JButton(new ButtonTest("追加"));
        panel.add(btn);
        contentPane.add(panel);
        contentPane.add(sp);
        list.setBorder(new TitledBorder("項目一覧"));
        list.addListSelectionListener(new MyListSelect());
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

    class ButtonTest extends AbstractAction {
        ButtonTest(String text) {
            super(text);
        }

        public void actionPerformed(ActionEvent e) {
            model.addElement(tf.getText());
            tf.setText("");
        }
    }

    class MyListSelect implements ListSelectionListener {
        public void valueChanged(ListSelectionEvent e) {

            JList li = (JList) e.getSource();
            ListModel model = li.getModel();

            if (e.getValueIsAdjusting() == false) {
                int[] selects = li.getSelectedIndices();

                int n = li.getSelectedIndices().length;

                for (int i = 0; i < n; i++) {
                    String select1 = (String) model.getElementAt(selects[i]);
                    tf.setText(select1);
                }
            }
        }
    }
}
