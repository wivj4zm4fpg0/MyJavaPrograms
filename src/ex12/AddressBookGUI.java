package ex12;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;
import java.awt.event.*;
import java.io.File;

public class AddressBookGUI extends JFrame {
    JTextField nameField, addressField, telField, emailField;
    DefaultListModel model;
    JList list;
    JButton addButton, removeButton, updateButton;
    JPanel pane;
    AddressBook book;

    public static void main(String[] args) {
        JFrame w = new AddressBookGUI("AddressBookGUI");
        w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        w.setSize(400, 300);
        w.setVisible(true);
        w.setLocationRelativeTo(null);
    }

    public AddressBookGUI(String title) {
        super(title);
        book = new AddressBook();
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

        model = new DefaultListModel();
        list = new JList(model);
        list.addListSelectionListener(new NameSelect());
        JScrollPane sc = new JScrollPane(list);
        sc.setBorder(new TitledBorder("名前一覧"));
        pane.add(sc, BorderLayout.CENTER);

        book.open( "address.txt");
        for (String entry : book.getNames()) {
            model.addElement(entry);
        }

        JPanel fields = new JPanel();
        fields.setLayout(new BoxLayout(fields, BoxLayout.Y_AXIS));
        nameField = new JTextField(20);
        nameField.setBorder(new TitledBorder("名前"));
        fields.add(nameField);
        addressField = new JTextField(20);
        addressField.setBorder(new TitledBorder("住所"));
        fields.add(addressField);
        telField = new JTextField(20);
        telField.setBorder(new TitledBorder("電話"));
        fields.add(telField);
        emailField = new JTextField(20);
        emailField.setBorder(new TitledBorder("メール"));
        fields.add(emailField);

        addButton = new JButton(new AddAction());
        fields.add(addButton);
        updateButton = new JButton(new UpdateAction());
        fields.add(updateButton);
        removeButton = new JButton(new RemoveAction());
        fields.add(removeButton);

        pane.add(fields, BorderLayout.EAST);
    }

    class NameSelect implements ListSelectionListener {
        public void valueChanged(ListSelectionEvent e) {
            JList list = (JList) e.getSource();
            if (e.getValueIsAdjusting() == false) {
                int index = list.getSelectedIndex();
                if (index < 0) {
                    return;
                }
                DefaultListModel model = (DefaultListModel) list.getModel();
                String select = model.get(index).toString();
                nameField.setText(select);
                addressField.setText(book.getAddress(index));
                telField.setText(book.getTel(index));
                emailField.setText(book.getEmail(index));
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
                book.open(filechooser.getName(file));
                model.removeAllElements();
                for (String entry : book.getNames()) {
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
                book.save(filechooser.getName(file));
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
            int ans = JOptionPane.showConfirmDialog(AddressBookGUI.this, "ほんとうに終了しますか？");
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
            if (!(nameField.getText().equals("") || addressField.getText().equals("") || telField.getText().equals("") || emailField.getText().equals(""))) {
                String str = nameField.getText();
                book.add(new Address(nameField.getText(), addressField.getText(), telField.getText(), emailField.getText()));
                DefaultListModel model = (DefaultListModel) list.getModel();
                model.addElement(str);
                nameField.setText("");
                addressField.setText("");
                telField.setText("");
                emailField.setText("");
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
            for (String entry : book.getNames()) {
                if (nameField.getText().equals(entry)) {
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
            book.setAddress(index, select, addressField.getText(), telField.getText(), emailField.getText());
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
                book.remove(book.findName(select));
                model.remove(index);
                nameField.setText("");
                addressField.setText("");
                telField.setText("");
                emailField.setText("");
            }
        }
    }
}