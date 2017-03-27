package entities.creatures;

import java.awt.geom.AffineTransform;

import entities.Entity;
import entities.Vector2D;
import main.Handler;

public abstract class Creature extends Entity{
	
	
	protected Vector2D velocity;
	protected double maxVelocity;
	protected AffineTransform at;
	protected double angle;
	protected int health;
	
	
	public Creature(Handler handler, Vector2D position, int width, int height, double maxVelocity, int health) {
		super(handler, position, width, height);
		this.velocity = new Vector2D();
		this.maxVelocity = maxVelocity;
		angle = 0;
		this.health = health;
	}

	public Vector2D getVelocity() {
		return velocity;
	}


	public void setVelocity(Vector2D velocity) {
		this.velocity = velocity;
	}

	public double getMaxVelocity() {
		return maxVelocity;
	}


	public void setMaxVelocity(double maxVelocity) {
		this.maxVelocity = maxVelocity;
	}


	public int getHealth() {
		return health;
	}


	public void setHealth(int health) {
		this.health = health;
	}
}
