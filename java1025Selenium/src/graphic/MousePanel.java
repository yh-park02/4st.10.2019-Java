package graphic;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class MousePanel extends JPanel {
	//선을 그릴 좌표를 저장할 List를 생성
	
	//시작점의 좌표를 가지고 있을 List 생성 
	List<Point> vs = new ArrayList<>();
	//끝점의 좌표를 가지고 있을 List 생성
	List<Point> ve = new ArrayList<>();
	
	//임시로 저장할 시작점과 끝점의 좌표를 임시로 
	//가지고 있을 변수
	Point startP, endP;
	
	//생성자
	public MousePanel() {
		this.addMouseMotionListener(new MouseMotionListener(){
			@Override
			public void mouseDragged(MouseEvent e) {
				endP = e.getPoint();
				repaint();
			}

			@Override
			public void mouseMoved(MouseEvent e) {
			}
			
		});
		this.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				
			}
			//마우스를 눌렀을 때 
			@Override
			public void mousePressed(MouseEvent e) {
				//임시변수에 누른 좌표를 저장 
					startP = e.getPoint();
			}
			//마우스에서 손을 뗐을 때 
			@Override
			public void mouseReleased(MouseEvent e) {
				//손을 뗀 좌표를 저장
				endP = e.getPoint();
				//임시좌표를 List에 저장
				vs.add(startP);
				ve.add(endP);
				//다시 그려달라고 요청
				repaint();
				
			}
			//마우스가 영역에 들어왔을 때 
			@Override
			public void mouseEntered(MouseEvent e) {
			
			}
			//마우스가 영역에서 벗어났을 때 
			@Override
			public void mouseExited(MouseEvent e) {
				
			}
		});
	}
	@Override
	public void paint(Graphics g) {
		//List의 좌표를 가지고 선을 그리기 
		for(int i=0; i<vs.size(); i=i+1) {
			Point s = vs.get(i);
			Point e = ve.get(i);
			
			g.drawLine(
					(int)s.getX(),(int)s.getY(),
					(int)e.getX(),(int)e.getY());		
		}
		
		if(startP != null) {
			g.drawLine(
					(int)startP.getX(),
					(int)startP.getY(),
					(int)endP.getX(),
					(int)endP.getY());
		}
	}
}
