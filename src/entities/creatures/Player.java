package entities.creatures;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import entities.Bullet;
import entities.Entity;
import entities.Gun;
import entities.Vector2D;
import graphics.Animation;
import graphics.Assets;
import graphics.Sound;
import input.KeyManager;
import input.MouseManager;
import tiles.World;

public class Player extends Creature{
	
	public static final int PLAYERSIZE = 112;
	public static final int MAXHEALTH = 400;
	
	// current gun
	
	private static Gun currentGun;
	
	// pistol animations
	
	private Animation pistolIdle, pistolReload, pistolShoot;
	
	// rifle animations
	
	private Animation rifleIdle, rifleReload, rifleShoot;
	
	// guns
	
	private Gun pistol, rifle;
	
	// sounds
	
	private Sound rifleShootSound, rifleReloadSound;
	private Sound pistolShootSound, pistolReloadSound;
	
	// others
	
	private Vector2D shootPoint;
	private Vector2D creatureCenter;
	private Vector2D newPoint;
	private double xMove, yMove;
	private World world;
	
	
	public Player(Vector2D position, double maxVelocity, World world) {
		super(world.getHandler(), position, PLAYERSIZE - 40, PLAYERSIZE - 40, maxVelocity, MAXHEALTH);
		this.world = world;
		this.velocity = new Vector2D(maxVelocity, maxVelocity);
		pistolIdle = new Animation(Assets.pistolIdle, 20);
		pistolReload = new Animation(Assets.pistolReload, 100);
		pistolShoot = new Animation(Assets.pistolShootAnim, 80);
		pistolShootSound = new Sound(Assets.pistolShoot);
		pistolShootSound.changeVolume(-10);
		pistolReloadSound = new Sound(Assets.pistolReloadSound);
		
		rifleIdle = new Animation(Assets.rifleIdle, 20);
		rifleReload = new Animation(Assets.rifleReload, 100);
		rifleShoot = new Animation(Assets.rifleShootAnim, 80);
		rifleShootSound = new Sound(Assets.rifleShoot);
		rifleShootSound.changeVolume(-5);
		rifleReloadSound = new Sound(Assets.rifleReloadSound);
		
		shootPoint = new Vector2D();
		
		pistol = new Gun(Assets.pistolSkin, pistolIdle, pistolReload, pistolShoot,pistolShootSound,
				pistolReloadSound, this, 700,
				9, 2700);
		rifle = new Gun(Assets.ak47, rifleIdle, rifleReload, rifleShoot, rifleShootSound,
				rifleReloadSound, this, 100,
				30, 9000);
		
		currentGun = pistol;
		
	}
	
	@Override
	public void update() {
		
		int x = (int) handler.getWindow().getCamera().getOffset().getX();
		int y = (int) handler.getWindow().getCamera().getOffset().getY();
		
		
		bounds.x = (int)(position.getX() - x + 15);
		bounds.y = (int)(position.getY() - y + 15);
		
		xMove = 0;
		yMove = 0;
		
		
		if(KeyManager.up)
			yMove = - velocity.getY();
		if(KeyManager.left)
			xMove = -velocity.getX();
		if(KeyManager.right)
			xMove = velocity.getX();
		if(KeyManager.down)
			yMove = velocity.getY();
		if(KeyManager.one)
			currentGun = pistol;
		if(KeyManager.two)
			currentGun = rifle;
		if(KeyManager.reload && currentGun.getRound() != currentGun.getBulletsPerRound() &&
				currentGun.getTotalBullets() > 0)
			currentGun.reload();
		
		double width = MouseManager.position.getX() - (position.getX()  - x + PLAYERSIZE /2);
		double height = MouseManager.position.getY() - (position.getY() - y + PLAYERSIZE / 2);
		
		angle = Math.atan(height / width);
		
		if(width < 0)
			angle = -Math.PI + angle;
		
		creatureCenter = new Vector2D(shootPoint.getX() - (position.getX() + PLAYERSIZE/2), 
				shootPoint.getY() - (position.getY() + PLAYERSIZE/2));
		
		newPoint = new Vector2D(creatureCenter.getX()*Math.cos(angle) - creatureCenter.getY()*Math.sin(angle), 
				creatureCenter.getX()*Math.sin(angle) + creatureCenter.getY()*Math.cos(angle));
		
		shootPoint.setX(newPoint.getX() - x + position.getX() + PLAYERSIZE/2);
		shootPoint.setY(newPoint.getY() - y +  position.getY() + PLAYERSIZE/2);
		
		if(MouseManager.leftMouseButton){
			
			
			Vector2D bulletDirection = new Vector2D(Math.cos(angle), Math.sin(angle));
			bulletDirection = bulletDirection.normalize();
			
			Bullet bullet = new Bullet(world.getHandler(), shootPoint, bulletDirection, world);
			
			currentGun.shoot(bullet);
			
		}
		
		// update  shooting point
		shootPoint = new Vector2D(position.getX() + PLAYERSIZE - 7, position.getY() + PLAYERSIZE - 27);
		
		getInput();
		handler.getWindow().getCamera().centerOnEntity(this);
		currentGun.update();
		
	}
	
	private void getInput(){
		
		if(collision(0, yMove) == null)
			yMove();
		if(collision(xMove, 0) == null)
			xMove();
	}
	private void yMove(){
		position.setY(position.getY()+ yMove);
	}
	private void xMove(){
		position.setX(position.getX()+ xMove);
	}
	
	public Entity collision(double xMove, double yMove){
		for(int i = 0; i < world.getObstacles().size(); i++)
		{
			Entity e = world.getObstacles().get(i);
			
			if(e.getDimensions(0, 0).intersects(this.getDimensions(xMove, yMove)))
				return e;
		}
		return null;
		
	}
	
	
	@Override
	public void render(Graphics g) {
		
		int x = (int) handler.getWindow().getCamera().getOffset().getX();
		int y = (int) handler.getWindow().getCamera().getOffset().getY();
		
		at = AffineTransform.getTranslateInstance(position.getX() - x, position.getY() - y);
		
		
		at.rotate(angle, PLAYERSIZE/2, PLAYERSIZE/2);
		
		Graphics2D g2d = (Graphics2D)g;
		
		g2d.drawImage(currentGun.getCurrentAnimation().getCurrentFrame(), at, null);
		
		if(health > 300)
			g2d.setColor(Color.GREEN);
		else if(health > 200)
			g2d.setColor(Color.YELLOW);
		else if(health > 100)
			g2d.setColor(Color.ORANGE);
		else if(health > 50)
			g2d.setColor(Color.RED);
		else if(health > 0)
			g2d.setColor(Color.BLACK);
		
		g2d.fillRect(50, 25, health, 25);
		
	}
	
	public void hit(){
		health -= 20;
	}
	
	public World getWorld(){
		return world;
	}
	
	public Gun getCurrentGun(){
		return currentGun;
	}
}
