package graphic;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;

public class MyFrame1 extends JFrame {
	public MyFrame1() {
		
		setTitle("그래픽출력");
		setBounds(0,0,300,300);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	//화면에 출력될 때 호출되는 메소드
	@Override
	public void paint(Graphics g) {
		//g.setColor(Color.blue);
		g.drawString("안녕하세요^ㅁ^", 100, 90);
		//g.setColor(new Color(100,200,200));
		g.drawRect(100, 100, 100, 50);
	}
}
