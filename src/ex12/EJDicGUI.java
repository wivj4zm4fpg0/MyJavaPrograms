package ex12;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;
import java.awt.event.*;
import java.io.File;

public class EJDicGUI extends JFrame {
    JTextField english, japanese;
    JList list;
    JButton addButton, removeButton, updateButton;
    JPanel pane;
    EJDic dictionary;

    public static void main(String[] args) {
        JFrame w = new EJDicGUI("EJDicGUI");
        w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        w.setSize(300, 300);
        w.setLocationRelativeTo(null);
        w.setVisible(true);
    }

    public EJDicGUI(String title) {
        super(title);
        dictionary = new EJDic();
        dictionary.open("dic.txt");
        pane = (JPanel) getContentPane();

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        JMenu fileMenu = new JMenu("ファイル");
        menuBar.add(fileMenu);
        JMenuItem item;
        item = new JMenuItem(new OpenAction());
        fileMenu.add(item);
        item = new JMenuItem(new SaveAction());
        fileMenu.add(item);
        fileMenu.addSeparator();
        item = new JMenuItem(new ExitAction());
        fileMenu.add(item);

        JPanel fields = new JPanel(new GridLayout(1, 2));
        english = new JTextField();
        english.setBorder(new TitledBorder("英語"));
        fields.add(english);
        japanese = new JTextField();
        japanese.setBorder(new TitledBorder("日本語"));
        fields.add(japanese);
        pane.add(fields, BorderLayout.SOUTH);

        DefaultListModel listModel = new DefaultListModel();
        list = new JList(listModel);
        list.addListSelectionListener(new WordSelect());
        JScrollPane sc = new JScrollPane(list);
        sc.setBorder(new TitledBorder("項目一覧"));
        pane.add(sc, BorderLayout.CENTER);

        for (String entry : dictionary.keySet()) {
            listModel.addElement(entry);
        }

        JPanel buttons = new JPanel();
        buttons.setLayout(new GridLayout(1, 3));
        addButton = new JButton(new AddAction());
        buttons.add(addButton);
        updateButton = new JButton(new UpdateAction());
        buttons.add(updateButton);
        removeButton = new JButton(new RemoveAction());
        buttons.add(removeButton);
        pane.add(buttons, BorderLayout.NORTH);
    }

    class WordSelect implements ListSelectionListener {
        public void valueChanged(ListSelectionEvent e) {
            JList list = (JList) e.getSource();
            if (e.getValueIsAdjusting() == false) {
                int index = list.getSelectedIndex();
                if (index < 0) {
                    return;
                }
                DefaultListModel model = (DefaultListModel) list.getModel();
                english.setText((String) model.get(index));
                japanese.setText(dictionary.get((String) model.get(index)));
            }
        }
    }

    class OpenAction extends AbstractAction {
        OpenAction() {
            putValue(Action.NAME, "開く");
            putValue(Action.SHORT_DESCRIPTION, "開く");
        }

        public void actionPerformed(ActionEvent e) {
            JFileChooser filechooser = new JFileChooser(".");
            int selected = filechooser.showOpenDialog(pane);
            if (selected == JFileChooser.APPROVE_OPTION) {
                DefaultListModel model = (DefaultListModel) list.getModel();
                File file = filechooser.getSelectedFile();
                dictionary.open(filechooser.getName(file));
                model.removeAllElements();
                for (String entry : dictionary.keySet()) {
                    model.addElement(entry);
                }
            } else if (selected == JFileChooser.CANCEL_OPTION) {
                System.out.println(e);
            } else if (selected == JFileChooser.ERROR_OPTION) {
                System.out.println(e);
            }
        }
    }

    class SaveAction extends AbstractAction {
        SaveAction() {
            putValue(Action.NAME, "保存");
            putValue(Action.SHORT_DESCRIPTION, "保存");
        }

        public void actionPerformed(ActionEvent e) {
            JFileChooser filechooser = new JFileChooser(".");
            int selected = filechooser.showSaveDialog(pane);
            if (selected == JFileChooser.APPROVE_OPTION) {
                File file = filechooser.getSelectedFile();
                dictionary.save(filechooser.getName(file));
            } else if (selected == JFileChooser.CANCEL_OPTION) {
                System.out.println(e);
            } else if (selected == JFileChooser.ERROR_OPTION) {
                System.out.println(e);
            }
        }
    }

    class ExitAction extends AbstractAction {
        ExitAction() {
            putValue(Action.NAME, "終了");
            putValue(Action.SHORT_DESCRIPTION, "終了");
        }

        public void actionPerformed(ActionEvent e) {
            int ans = JOptionPane.showConfirmDialog(EJDicGUI.this, "ほんとうに終了しますか？");
            if (ans == 0) {
                System.exit(0);
            }
        }
    }

    class AddAction extends AbstractAction {
        AddAction() {
            putValue(Action.NAME, "追加");
            putValue(Action.SHORT_DESCRIPTION, "追加");
        }

        public void actionPerformed(ActionEvent e) {
            if (!(english.getText().equals("") || japanese.getText().equals(""))) {
                String str = english.getText();
                dictionary.put(english.getText(), japanese.getText());
                DefaultListModel model = (DefaultListModel) list.getModel();
                model.addElement(str);
                english.setText("");
                japanese.setText("");
                int pos = model.getSize() - 1;
                list.ensureIndexIsVisible(pos);
            }
        }
    }

    class UpdateAction extends AbstractAction {
        UpdateAction() {
            putValue(Action.NAME, "更新");
            putValue(Action.SHORT_DESCRIPTION, "更新");
        }

        public void actionPerformed(ActionEvent e) {
            int existing = 0;
            for (String entry : dictionary.keySet()) {
                if (english.getText().equals(entry)) {
                    existing = 1;
                }
            }
            if (existing != 1) {
                return;
            }
            int index = list.getSelectedIndex();
            if (index < 0) {
                return;
            }
            DefaultListModel model = (DefaultListModel) list.getModel();
            String select = (String) model.get(index);
            dictionary.put(select, japanese.getText());
        }
    }

    class RemoveAction extends AbstractAction {
        RemoveAction() {
            putValue(Action.NAME, "削除");
            putValue(Action.SHORT_DESCRIPTION, "削除");
        }

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
                dictionary.remove(model.get(index).toString());
                model.remove(index);
                english.setText("");
                japanese.setText("");
            }
        }
    }

}