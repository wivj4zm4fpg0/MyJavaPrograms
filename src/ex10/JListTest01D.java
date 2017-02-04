package ex10;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class JListTest01D extends JFrame {
    public static void main(String[] args) {
        JListTest01D w = new JListTest01D("JListTest01");
        w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        w.setSize(300, 220);
        w.setLocationRelativeTo(null);
        w.setVisible(true);
    }

    public JListTest01D(String title) {
        setTitle(title);
        String[] choice = {"0000", "1111", "2222", "3333", "4444", "5555", "6666", "7777", "8888", "9999"};
        DefaultListModel listModel = new DefaultListModel();
        for (int i = 0; i < choice.length; i++) {
            listModel.addElement(choice[i]);
        }
        JList list = new JList(listModel);
        list.addListSelectionListener(new MyListSelect());
        JScrollPane sp = new JScrollPane();
        sp.getViewport().setView(list);
        Container contentPane = getContentPane();
        contentPane.add(sp, BorderLayout.CENTER);
    }

    class MyListSelect implements ListSelectionListener {
        public void valueChanged(ListSelectionEvent e) {
            JList list = (JList) e.getSource();
            if (e.getValueIsAdjusting() == false) {

                DefaultListModel model = (DefaultListModel) list.getModel();

                int index = list.getSelectedIndex();
                String select = (String) model.get(index);
                System.out.println(select + "が選択されました");
            }
        }
    }
}
