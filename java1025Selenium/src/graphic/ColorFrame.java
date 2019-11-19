package graphic;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ColorFrame extends JFrame {
	public ColorFrame() {
		JButton btn1 = new JButton("빨강");
		JButton btn2 = new JButton("노랑");
		JButton btn3 = new JButton("초록");
		
		final MyPanel myPanel = new MyPanel();
		add("Center", myPanel);
		
		btn1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				myPanel.color = Color.red;
				//화면을 다시 그려주는 메소드 
				myPanel.repaint();	
			}
		});
		
		btn2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				myPanel.color = Color.YELLOW;
				//화면을 다시 그려주는 메소드 
				myPanel.repaint();	
			}
		});
		
		btn3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				myPanel.color = Color.GREEN;
				//화면을 다시 그려주는 메소드 
				myPanel.repaint();	
			}
		});
		
		
		JPanel nortPanel = new JPanel();
		nortPanel.add(btn1);
		nortPanel.add(btn2);
		nortPanel.add(btn3);
		
		add("North", nortPanel);
		
		
		setTitle("색상변경");
		setBounds(0,0,300,300);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
}
