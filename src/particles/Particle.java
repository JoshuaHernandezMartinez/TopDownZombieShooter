package particles;

import java.awt.Color;
import java.awt.Graphics;

import entities.Vector2D;

public class Particle {
	private Vector2D position;
	private int size, life;
	private int speed;
	private int alpha = 130;
	private final Color color;
	private Vector2D direction;
	
	public Particle(Vector2D position, int size, int life, int speed, double angle, Color color){
		this.color = new Color(color.getRed(), color.getGreen(), color.getBlue(), alpha);
		this.size = size;
		this.life = life;
		this.speed = speed;
		angle = Math.toRadians(angle);
		direction = new Vector2D(Math.cos(angle), Math.sin(angle));
		direction.normalize();
		this.position = position;
	}
	
	
	public boolean update(){
		position.setX(position.getX() + direction.getX()*speed);
		position.setY(position.getY() + direction.getY()*speed);
		life --;
		if(life <= 0)
			return true;
		
		return false;
		
	}
	
	public void render(Graphics g){
		g.setColor(color);
		g.fillRect((int)position.getX() - size/2, (int)position.getY() - size/2, size, size);
	}
	
	
}
