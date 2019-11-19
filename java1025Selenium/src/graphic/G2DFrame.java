package graphic;

import javax.swing.JFrame;

public class G2DFrame extends JFrame {
	public G2DFrame() {
		G2DPanel panel = new G2DPanel();
		add("Center", panel);

		setTitle("2D 객체를 이용한 출력");
		setBounds(100,100,500,500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
}
