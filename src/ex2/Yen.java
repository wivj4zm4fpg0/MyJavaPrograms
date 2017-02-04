package ex2;

import java.awt.Color;
import java.awt.Graphics;

public class Yen {
    private int x;
    private int y;

    public Yen(int x, int y)
    {
	this.x = x;
	this.y = y;
    }
    public void draw(Graphics g)
    {
	g.setColor(Color.black);
	g.drawString("円", x, y);
    }

}
