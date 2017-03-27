package input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import entities.Vector2D;

public class MouseManager implements MouseMotionListener, MouseListener{
	
	public static Vector2D position;
	public static boolean leftMouseButton = false;
	public static boolean rightMouseButton = false;
	
	
	public MouseManager(){
		position = new Vector2D();
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		position.setX(e.getX());
		position.setY(e.getY());
	}


	@Override
	public void mouseMoved(MouseEvent e) {
		position.setX(e.getX());
		position.setY(e.getY());
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1)
			leftMouseButton = true;
		if(e.getButton() == MouseEvent.BUTTON3)
			rightMouseButton = true;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1)
			leftMouseButton = false;
		if(e.getButton() == MouseEvent.BUTTON3)
			rightMouseButton = false;
	}

	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
	@Override
	public void mouseClicked(MouseEvent e) {}
}
