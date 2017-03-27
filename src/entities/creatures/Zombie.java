package entities.creatures;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;

import javax.swing.Timer;

import blood.Blood;
import entities.Vector2D;
import graphics.Animation;
import graphics.Assets;
import graphics.Sound;

public class Zombie extends Creature{
	
	public static final int ZOMBIESIZE = 128;
	public static final int MAXHEALTH = 100;
	
	private double mass;
	private Steering steering;
	private Player player;
	private Vector2D acceleration, heading;
	private double maxForce;
	
	private Animation walkAnimation, attackAnimation;
	
	private Animation currentAnimation;
	
	private Timer attackDelay;
	
	private boolean attacking = false;
	
	private double distanceToPlayer = 0;
	
	private Sound zombieBite;
	
	public Zombie(Vector2D position, double maxVelocity, Player player) {
		super(player.getWorld().getHandler(), position, ZOMBIESIZE, ZOMBIESIZE, maxVelocity, MAXHEALTH);
		this.player = player;
		zombieBite = new Sound(Assets.zombieBite);
		steering = new Steering(this, player, handler);
		acceleration = new Vector2D();
		heading = new Vector2D();
		mass = 5;
		maxForce = 5;
		
		attackDelay = new Timer(350, new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				if(distanceToPlayer < Player.PLAYERSIZE)
				{
					zombieBite.playSound();
					player.hit();
				}
					
				attackDelay.stop();
				attacking = false;
				currentAnimation = walkAnimation;
			}
			
		});
		walkAnimation = new Animation(Assets.zombie, 20);
		attackAnimation = new Animation(Assets.zombieAttack, 35);
		currentAnimation = walkAnimation;
		
		
		
	}

	@Override
	public void update() {
		int x = (int) handler.getWindow().getCamera().getOffset().getX();
		int y = (int) handler.getWindow().getCamera().getOffset().getY();
		
		bounds = new Rectangle((int)position.getX() - x + 25, (int)position.getY() - y + 25,
				ZOMBIESIZE - 50, ZOMBIESIZE - 50);
		
		Vector2D seekForce = steering.seek(new Vector2D(player.getPosition().getX(), player.getPosition().getY()));
		Vector2D avoidanceForce = steering.obstacleAvoidance();
		Vector2D separationForce = steering.separation();
		
		Vector2D steeringForce = new Vector2D(); 
		
		
		
		if(!avoidanceForce.isZero())
			seekForce = new Vector2D();	
		
		
		steeringForce.setX(seekForce.getX() + avoidanceForce.getX() + separationForce.getX());
		steeringForce.setY(seekForce.getY() + avoidanceForce.getY() + separationForce.getY());
		
		steeringForce.divideByScalar(10);
		
		steeringForce.truncate(maxForce);
		
		acceleration.setX(acceleration.getX() + steeringForce.getX());
		acceleration.setY(acceleration.getY() + steeringForce.getY());
		
		acceleration.divideByScalar(mass);
		
		velocity.setX(velocity.getX() + acceleration.getX());
		velocity.setY(velocity.getY() + acceleration.getY());
		
		velocity.truncate(maxVelocity);
		
		
		Vector2D toPlayer = player.getPosition().substract(position);
		distanceToPlayer = toPlayer.getMagnitude();
		
		
		if(distanceToPlayer < Player.PLAYERSIZE/2 && !attackDelay.isRunning())
		{
			attacking = true;
			attackDelay.start();
			currentAnimation = attackAnimation;
			currentAnimation.setIndex();
		}
		
		if(attacking)
			velocity.zero();
		
		
		position.setX(position.getX() + velocity.getX());
		position.setY(position.getY() + velocity.getY());
		
		if(velocity.getMagnitude() > 0){
			heading = velocity.normalize();
			
			angle = Math.acos(heading.dot(new Vector2D(1,0)));
			if(heading.getY() < 0)
				angle *= -1;
		}
		
		currentAnimation.update();
	}

	@Override
	public void render(Graphics g) {
		
		int x = (int) handler.getWindow().getCamera().getOffset().getX();
		int y = (int) handler.getWindow().getCamera().getOffset().getY();
		
		at = AffineTransform.getTranslateInstance(position.getX() - x,
				position.getY() - y);
		
		at.rotate(angle, ZOMBIESIZE/2, ZOMBIESIZE/2);	
		
		Graphics2D g2d = (Graphics2D)g;
		
		g2d.drawImage(currentAnimation.getCurrentFrame(), at, null);
		
		//steering.render(g2d);
	}
	
	public Vector2D getHeading(){
		return heading;
	}
	
	public double getMaxForce(){
		return maxForce;
	}
	public double getRadius(){
		return bounds.width/2;
	}
	
	public Vector2D getCenter(){
		return new Vector2D(bounds.x + bounds.width/2, bounds.y + bounds.height/2);
	}
	public void hit(){
		health -= 20;
		if(health <= 0){	
			player.getWorld().getBloodSplats().add(new Blood(position, player.getWorld()));
			player.getWorld().getZombiesAndBullets().remove(this);
		}
			
	}
}
