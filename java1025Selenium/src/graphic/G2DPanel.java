package graphic;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

public class G2DPanel extends JPanel {
	//화면에 보여질 때나 repaint 메소드를 호출하면 
	//화면에 출력되는 메소드 
	@Override
	//상위 클래스에 있는 메소드를 재정의 한다는 어노테이션
	//상위 클래스에 메소드가 존재하지 않으면 컴파일 에러 발생 
	public void paint(Graphics g) {
		//Image 객체 만들기 
		Image image = 
			Toolkit.getDefaultToolkit().
			getImage("/Users/tjoeun-304/Desktop/주말/2.png");
		//그림 출력 - 좌표만 입력해서 출력 : 원본 크기 그대로 
		//g.drawImage(image, 100, 100, this); 
		
		//100,100에 200,200 크기로 원본의 크기를 변경해서 출력 
		//g.drawImage(image, 100, 100, 400, 400, this);
		
		//0,0에서 100,100 만큼을 분할해서 100,100부터 200,200까지에 출력 
		//g.drawImage(image, 100, 100, 200, 200, 0, 0, 800, 800, this);	
		
		//출력 영역을 수정 
		g.setClip(0, 0, 150, 300);
		
		g.drawImage(image, 100, 100, 200, 200, 0, 0, 800, 800, this);	
		g.drawImage(image, 100, 300, 200, 200, 0, 0, 800, 800, this);
		
		
		/*
		//그림 그리기 
		//g.fillRect(100, 100, 50, 50);
		
		//그래픽스 객체 변환 
		Graphics2D g2 = (Graphics2D)g;
		//색상설정
		//g2.setColor(Color.orange);
		
		//그라데이션 설정
		g2.setPaint(new GradientPaint(5,30,Color.orange,
				10,50,Color.pink,true));
		g2.fill(new Rectangle2D.Double(200,200,85,95)); //네모
		
		//선 두께 수정
		g2.setStroke(new BasicStroke(10)); 
		g2.draw(new Ellipse2D.Double(300,300,100,100)); //원
		
		//색상 변경 
		g2.setPaint(Color.MAGENTA);
		float [] dashes = {10};
		g2.setStroke(new BasicStroke(
				1, BasicStroke.CAP_ROUND,
				BasicStroke.JOIN_ROUND,
				10, dashes, 0));
		g2.draw(new Ellipse2D.Double(100,300,100,100));
		
		//g2.setColor(Color.WHITE);
		//g2.fill(new Ellipse2D.Double(300,300,30,30));
		
		//g2.setColor(Color.RED);
		//g2.draw(new Ellipse2D.Double(300,300,30,30));
		*/
	}
}
