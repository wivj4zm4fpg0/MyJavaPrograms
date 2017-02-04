package ex13;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;
import java.awt.event.*;

public class JListTest10D extends JFrame {
    JTextField tf;
    JList list;
    JButton addButton, modifyButton, removeButton;
    JPanel pane;

    public static void main(String[] args) {
        JFrame w = new JListTest10D("JListTest10D");
        w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        w.setSize(240, 240);
        w.setVisible(true);
    }

    public JListTest10D(String title) {
        super(title);
        pane = (JPanel) getContentPane();

        tf = new JTextField();
        tf.setBorder(new TitledBorder("項目名"));
        ActionListener addAction = new AddAction();
        tf.addActionListener(addAction);
        pane.add(tf, BorderLayout.SOUTH);

        DefaultListModel listModel = new DefaultListModel();
        list = new JList(listModel);
        list.addListSelectionListener(new MyListSelect());
        JScrollPane sc = new JScrollPane(list);
        sc.setBorder(new TitledBorder("項目一覧"));
        pane.add(sc, BorderLayout.CENTER);

        JPanel buttons = new JPanel();
        buttons.setLayout(new BoxLayout(buttons, BoxLayout.X_AXIS));
        addButton = new JButton("追加");
        addButton.addActionListener(addAction);
        buttons.add(addButton);
        modifyButton = new JButton("変更");
        modifyButton.addActionListener(new ModifyAction());
        buttons.add(modifyButton);
        removeButton = new JButton("削除");
        removeButton.addActionListener(new RemoveAction());
        buttons.add(removeButton);
        pane.add(buttons, BorderLayout.NORTH);
    }

    class AddAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String str = tf.getText();
            DefaultListModel model = (DefaultListModel) list.getModel();
            model.addElement(str);
            tf.setText("");
            int pos = model.getSize() - 1;
            //	list.setSelectedIndex( pos );
            list.ensureIndexIsVisible(pos);
        }
    }

    class MyListSelect implements ListSelectionListener {
        public void valueChanged(ListSelectionEvent e) {
            JList li = (JList) e.getSource();
            if (e.getValueIsAdjusting() == false) {
                int index = li.getSelectedIndex();
                if (index < 0) {
                    return;
                }
                DefaultListModel model = (DefaultListModel) list.getModel();
                String select = (String) model.get(index);
                tf.setText(select);
            }
        }
    }

    class ModifyAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String str = tf.getText();
            int index = list.getSelectedIndex();
            DefaultListModel model = (DefaultListModel) list.getModel();
            model.set(index, str);
        }
    }

    class RemoveAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int index = list.getSelectedIndex();
            if (index < 0) {
                return;
            }
            DefaultListModel model = (DefaultListModel) list.getModel();
            String select = (String) model.get(index);
            Object[] msg = {select, "を削除します"};
            int ans = JOptionPane.showConfirmDialog(pane, msg, "はい・いいえ・取消し",
                    JOptionPane.YES_NO_CANCEL_OPTION);
            if (ans == 0) {
                model.remove(index);
            }
        }
    }
}
