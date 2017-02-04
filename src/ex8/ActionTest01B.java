package ex8;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.geom.Line2D;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class ActionTest01B extends JFrame {
  double s = 0;
  TestPanel testPanel; // 作成したパネルを入れる
  Timer timer; // タイマーを入れる
  final float r = 40;
  final float r2 = 80;
  final float angle = 40;

  public static void main(String[] args) {
    ActionTest01B w = new ActionTest01B( "ActionTest01B" );
    w.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
    w.setSize( 400, 300 );
    w.setVisible( true );
  }
  public ActionTest01B( String title ){
    super( title );
    JPanel panel = (JPanel)getContentPane();

    testPanel = new TestPanel(); // 描画用パネルの生成

    panel.add(testPanel);

    timer = new Timer( 100, new Tick() ); // タイマを生成
    timer.start(); // タイマ起動
  }
  class TestPanel extends JPanel {
    public void paintComponent(Graphics g) { // ここに描画したい内容を書く
      super.paintComponent(g); // 背景を再描画する
      final float x = getWidth() / 2;
      final float y = getHeight() / 2;
      double rad = s * (Math.PI / 180);
      Graphics2D g2 = (Graphics2D)g;
      BasicStroke superwideStroke = new BasicStroke(8.0f);
      g2.setStroke(superwideStroke);
      g.setColor(Color.gray);
      for(int i = 0; i < 360 / angle; i++){
        double rad2 = i * angle * (Math.PI / 180);
        g2.draw(new Line2D.Double(x+r*Math.cos(rad2),y+r*Math.sin(rad2),x+r2*Math.cos(rad2),y+r2*Math.sin(rad2)));
      }
      g.setColor(Color.black);
      g2.draw(new Line2D.Double(x+r*Math.cos(rad),y+r*Math.sin(rad),x+r2*Math.cos(rad),y+r2*Math.sin(rad)));
    }
  }
  class Tick extends AbstractAction {
    public void actionPerformed( ActionEvent e ){ // 一定時間ごとに呼び出される
      s += angle;
      testPanel.repaint(); // 再描画
    }
  }
}
