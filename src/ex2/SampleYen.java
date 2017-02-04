package ex2;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class SampleYen extends JFrame {

	private SamplePanel sp;

    @SuppressWarnings("unused")
	public static void main(String args[]){
    	SampleYen sm = new SampleYen();
    }
    public SampleYen(){
    	super("サンプル");
    	setDefaultCloseOperation(EXIT_ON_CLOSE);
    	setSize(300, 300);
    	sp = new SamplePanel();
    	add(sp, BorderLayout.CENTER);
    	setVisible(true);
    }
    public class SamplePanel extends JPanel{
    	private ArrayList<Yen> Namelist = new ArrayList<Yen>();
    	public SamplePanel(){
    		addMouseListener(new SampleMouseListener());
    	}
    	public void paint(Graphics g){
    		super.paint(g);
    		Iterator<Yen> it = Namelist.iterator();
    		while(it.hasNext()) {
    			Yen c = it.next();
    			c.draw(g);
    		}
    	}
    	public class SampleMouseListener extends MouseAdapter{
    		public void mousePressed(MouseEvent e){
    			Namelist.add(new Yen(e.getX(), e.getY()));
    			repaint();
    		}
    	}
    }
}
