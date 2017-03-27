package states;

import java.awt.Graphics;

import main.Handler;

public abstract class State {
	
	public static State currentState = null;
	
	protected Handler handler;
	
	public State(Handler handler){
		this.handler = handler;
	}
	
	public abstract void update();
	public abstract void render(Graphics g);
	
}
