package graphic;

import javax.swing.JFrame;

public class MouseFrame extends JFrame {
	public MouseFrame() {
		MousePanel p = new MousePanel();
		add(p);
		
		setTitle("선 그리기");
		setBounds(100,100,500,500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
}
