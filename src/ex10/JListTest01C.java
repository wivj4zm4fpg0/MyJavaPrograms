package ex10;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class JListTest01C extends JFrame {
    public static void main(String[] args) {
        JListTest01C w = new JListTest01C("JListTest01");
        w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        w.setSize(300, 220);
        w.setLocationRelativeTo(null);
        w.setVisible(true);
    }

    public JListTest01C(String title) {
        setTitle(title);
        String[] choice = {"0000", "1111", "2222", "3333", "4444", "5555", "6666", "7777", "8888", "9999"};
        JList list = new JList(choice);
        list.addListSelectionListener(new MyListSelect());
        JScrollPane sp = new JScrollPane();
        sp.getViewport().setView(list);
        Container contentPane = getContentPane();
        contentPane.add(sp, BorderLayout.CENTER);
    }

    class MyListSelect implements ListSelectionListener {
        public void valueChanged(ListSelectionEvent e) {
            JList li = (JList) e.getSource();
            if (e.getValueIsAdjusting() == false) {
                String select = (String) li.getSelectedValue();
                System.out.println(select + "が選択されました");
            }
        }
    }
}
