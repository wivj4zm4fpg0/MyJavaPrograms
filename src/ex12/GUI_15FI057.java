package ex12;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.MouseInputAdapter;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;

public class GUI_15FI057 extends JFrame {
    private int x0;
    private int y0;
    private JPanel mainPanel = new JPanel();
    private Color color;
    private BufferedImage image;
    private boolean white;
    private int strokeWeight = 3;
    private JTextField strokeWeightField;

    public static void main(String[] args) {
        GUI_15FI057 w = new GUI_15FI057("GUI_15FI057");
        w.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        w.setSize(800, 600);
        w.setVisible(true);
        w.setLocationRelativeTo(null);
        w.setResizable(false);
    }

    private GUI_15FI057(String title) {
        super(title);
        JPanel panel = (JPanel) getContentPane();

        JPanel sidePanel = new JPanel();
        sidePanel.setLayout(new GridLayout(10, 1, 0, 10));
        panel.add(sidePanel, BorderLayout.WEST);

        JButton bt = new JButton("クリア");
        bt.addMouseListener(new ButtonCheck());
        sidePanel.add(bt);

        JButton bt2 = new JButton("色の選択");
        bt2.addMouseListener(new ColorSelect());
        sidePanel.add(bt2);

        JButton bt3 = new JButton("塗り潰す");
        bt3.addMouseListener(new AllFill());
        sidePanel.add(bt3);

        JButton bt4 = new JButton("線の拡大");
        bt4.addMouseListener(new LineBold());
        sidePanel.add(bt4);

        JButton bt5 = new JButton("線の縮小");
        bt5.addMouseListener(new LineThin());
        sidePanel.add(bt5);

        strokeWeightField = new JTextField(String.valueOf(strokeWeight));
        strokeWeightField.setBorder(new TitledBorder("線の太さ"));
        sidePanel.add(strokeWeightField);

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        JMenu fileMenu = new JMenu("ファイル");
        menuBar.add(fileMenu);
        JMenuItem item;
        item = new JMenuItem(new OpenAction());
        fileMenu.add(item);
        item = new JMenuItem(new SaveAction());
        fileMenu.add(item);
        item = new JMenuItem(new ExitAction());
        fileMenu.add(item);

        mainPanel.setBackground(Color.WHITE);
        mainPanel.addMouseListener(new MouseCheck());
        mainPanel.addMouseMotionListener(new MouseCheck());
        panel.add(mainPanel);
    }

    class AllFill extends MouseAdapter {
        public void mousePressed(MouseEvent e) {
            if (!white) {
                white = true;
            }
            if (image == null) {
                image = (BufferedImage) createImage(mainPanel.getWidth(), mainPanel.getHeight());
            }
            Graphics2D g = image.createGraphics();
            g.setColor(color);
            g.fillRect(0, 0, mainPanel.getWidth(), mainPanel.getHeight());
            Graphics g2 = mainPanel.getGraphics();
            g2.setColor(color);
            g2.fillRect(0, 0, mainPanel.getWidth(), mainPanel.getHeight());
        }
    }

    class LineBold extends MouseAdapter {
        public void mousePressed(MouseEvent e) {
            strokeWeight++;
            strokeWeightField.setText(String.valueOf(strokeWeight));
        }
    }

    class LineThin extends MouseAdapter {
        public void mousePressed(MouseEvent e) {
            if (strokeWeight > 1) {
                strokeWeight--;
                strokeWeightField.setText(String.valueOf(strokeWeight));
            }
        }
    }

    class MouseCheck extends MouseInputAdapter {
        public void mousePressed(MouseEvent e) {
            if (!white) {
                if (image == null) {
                    image = (BufferedImage) createImage(mainPanel.getWidth(), mainPanel.getHeight());
                }
                Graphics2D g = image.createGraphics();
                g.setColor(Color.white);
                g.fillRect(0, 0, mainPanel.getWidth(), mainPanel.getHeight());
                white = true;
            }
            x0 = e.getX();
            y0 = e.getY();
        }

        public void mouseDragged(MouseEvent e) {
            int x = e.getX();
            int y = e.getY();
            if (image == null) {
                image = (BufferedImage) createImage(mainPanel.getWidth(), mainPanel.getHeight());
            }
            Graphics2D g = image.createGraphics();
            if (!white) {
                g.setColor(Color.white);
                g.fillRect(0, 0, mainPanel.getWidth(), mainPanel.getHeight());
                white = true;
            }
            g.setStroke(new BasicStroke(strokeWeight));
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g.setColor(color);
            g.drawLine(x0, y0, x, y);
            Graphics2D g2 = (Graphics2D) mainPanel.getGraphics();
            g2.setStroke(new BasicStroke(strokeWeight));
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(color);
            g2.drawLine(x0, y0, x, y);
            x0 = x;
            y0 = y;
        }
    }

    class ButtonCheck extends MouseAdapter {
        public void mouseClicked(MouseEvent e) {
            if (image == null) {
                image = (BufferedImage) createImage(mainPanel.getWidth(), mainPanel.getHeight());
            }
            Graphics2D g = image.createGraphics();
            g.setColor(Color.white);
            g.fillRect(0, 0, mainPanel.getWidth(), mainPanel.getHeight());
            Graphics g2 = mainPanel.getGraphics();
            g2.setColor(Color.white);
            g2.fillRect(0, 0, mainPanel.getWidth(), mainPanel.getHeight());
        }
    }

    class ColorSelect extends MouseAdapter {
        public void mouseClicked(MouseEvent e) {
            color = JColorChooser.showDialog(GUI_15FI057.this, "色の選択", Color.white);
        }
    }

    class OpenAction extends AbstractAction {
        OpenAction() {
            putValue(Action.NAME, "開く");
            putValue(Action.SHORT_DESCRIPTION, "開く");
        }

        public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser = new JFileChooser(".");
            fileChooser.setFileFilter(new ImageFileFilter());
            int selected = fileChooser.showOpenDialog(mainPanel);
            String fileName = null;
            try {
                fileName = fileChooser.getSelectedFile().getAbsolutePath();
            } catch (NullPointerException ignored) {

            }
            JLabel label = new JLabel();
            mainPanel.add(label);
            BufferedImage readImage = null;
            if (selected == JFileChooser.APPROVE_OPTION) {
                try {
                    assert fileName != null;
                    readImage = ImageIO.read(new File(fileName));
                } catch (Exception error) {
                    error.printStackTrace();
                }
                Graphics2D g2 = (Graphics2D) mainPanel.getGraphics();
                g2.setColor(Color.white);
                g2.fillRect(0, 0, mainPanel.getWidth(), mainPanel.getHeight());
                g2.drawImage(readImage, 0, 0, GUI_15FI057.this);
                image = (BufferedImage) createImage(mainPanel.getWidth(), mainPanel.getHeight());
                Graphics2D g = image.createGraphics();
                if (!white) {
                    white = true;
                }
                g.setColor(Color.white);
                g.fillRect(0, 0, mainPanel.getWidth(), mainPanel.getHeight());
                g.drawImage(readImage, 0, 0, GUI_15FI057.this);
            }
        }
    }

    class SaveAction extends AbstractAction {
        SaveAction() {
            putValue(Action.NAME, "保存");
            putValue(Action.SHORT_DESCRIPTION, "保存");
        }

        public void actionPerformed(ActionEvent e) {
            if (!white) {
                if (image == null) {
                    image = (BufferedImage) createImage(mainPanel.getWidth(), mainPanel.getHeight());
                }
                Graphics2D g = image.createGraphics();
                g.setColor(Color.white);
                g.fillRect(0, 0, mainPanel.getWidth(), mainPanel.getHeight());
                white = true;
            }
            JFileChooser fileChooser = new JFileChooser(".");
            fileChooser.setFileFilter(new ImageFileFilter());
            int selected = fileChooser.showSaveDialog(mainPanel);
            if (selected == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                try {
                    ImageIO.write(image, "jpg", file);
                } catch (Exception error) {
                    error.printStackTrace();
                }
            }
        }
    }

    class ExitAction extends AbstractAction {
        ExitAction() {
            putValue(Action.NAME, "終了");
            putValue(Action.SHORT_DESCRIPTION, "終了");
        }

        public void actionPerformed(ActionEvent e) {
            int ans = JOptionPane.showConfirmDialog(GUI_15FI057.this, "ほんとうに終了しますか？");
            if (ans == 0) {
                System.exit(0);
            }
        }
    }

    class ImageFileFilter extends FileFilter {
        String[] extensions = {"jpg"};
        String description = "JPG (*.jpg)";

        public boolean accept(File file) {
            if (file.isDirectory()) return true;
            String name = file.getName().toLowerCase();
            for (String extension : extensions) {
                if (name.endsWith(extension)) {
                    return true;
                }
            }
            return false;
        }

        public String getDescription() {
            return description;
        }
    }
}
