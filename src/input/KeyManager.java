package input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener{
	
	public static boolean[] keys;
	public static boolean up,left,right, down, one, two, reload;
	
	
	public KeyManager(){
		keys = new boolean[256];
		up = false;
		left = false;
		right = false;
		down = false;
	}
	public void update(){
		up = keys[KeyEvent.VK_W];
		left = keys[KeyEvent.VK_A];
		right = keys[KeyEvent.VK_D];
		down = keys[KeyEvent.VK_S];
		one = keys[KeyEvent.VK_1];
		two = keys[KeyEvent.VK_2];
		reload = keys[KeyEvent.VK_R];
	}
	
	
	@Override
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {}
}
