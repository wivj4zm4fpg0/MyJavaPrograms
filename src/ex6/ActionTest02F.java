package ex6;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

public class ActionTest02F extends JFrame {

    private int i = 0;

    public static void main(String[] args) {
        ActionTest02F w = new ActionTest02F("ActionTest02F");
        w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        w.setSize(300, 200);
        w.setVisible(true);
        System.out.println("初期値は0");
    }

    public ActionTest02F(String title) {
        super(title);
        JButton button = new JButton(new Action02());
        getContentPane().add(button, BorderLayout.WEST);
        JButton button2 = new JButton(new Action03());
        getContentPane().add(button2, BorderLayout.EAST);
        JButton button3 = new JButton(new Action04());
        getContentPane().add(button3, BorderLayout.CENTER);

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu file = new JMenu("操作");
        menuBar.add(file);

        file.add(new Action02());
        file.add(new Action03());
        file.addSeparator();
        file.add(new Action04());
    }

    class Action02 extends AbstractAction {
        Action02() {
            putValue(Action.NAME, "増やす");
            putValue(Action.SHORT_DESCRIPTION, "ツールチップ");
        }

        public void actionPerformed(ActionEvent e) {
            i++;
            System.out.println("現在値は" + i);
        }
    }

    class Action03 extends AbstractAction {
        Action03() {
            putValue(Action.NAME, "減らす");
            putValue(Action.SHORT_DESCRIPTION, "ツールチップ");
        }

        public void actionPerformed(ActionEvent e) {
            i--;
            System.out.println("現在値は" + i);
        }
    }

    class Action04 extends AbstractAction {
        Action04() {
            putValue(Action.NAME, "クリア");
            putValue(Action.SHORT_DESCRIPTION, "ツールチップ");
        }

        public void actionPerformed(ActionEvent e) {
            i = 0;
            System.out.println("現在値は" + i);
        }
    }
}
