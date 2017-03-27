package entities;
import java.awt.Graphics;
import java.awt.Rectangle;

import main.Handler;


public abstract class Entity {
	
	protected Vector2D position;
	protected int width, height;
	protected Rectangle bounds;
	protected Handler handler;
	
	public Entity(Handler handler, Vector2D position, int width, int height){
		this.position = position;
		this.width = width;
		this.height = height;
		
		bounds = new Rectangle((int)position.getX(), (int)position.getY(), width, height);
		this.handler = handler;
		
	}
	
	public abstract void update();
	
	public abstract void render(Graphics g);
	
	public Vector2D getPosition(){
		return position;
	}
	
	public Rectangle getBounds(){
		return bounds;
	}
		
	public Rectangle getDimensions(double xMove, double yMove){
		return new Rectangle((int)(bounds.x + xMove), (int)(bounds.y + yMove), bounds.width, bounds.height);
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	
}
