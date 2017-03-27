package graphics;

import entities.Entity;
import entities.Vector2D;
import main.Window;
import tiles.Tile;
import tiles.World;

public class Camera {
	private Vector2D offSet;
	
	public Camera(Vector2D offSet){
		this.offSet = offSet;
	}
	
	public void centerOnEntity(Entity e){
		offSet.setX(e.getPosition().getX() - Window.WIDTH/2 + e.getWidth()/2);
		offSet.setY(e.getPosition().getY() - Window.HEIGHT/2 + e.getHeight()/2);
		
		
		checkBlankSpace();
	}
	
	public void checkBlankSpace(){
		if(offSet.getX() < 0)
			offSet.setX(0);
		if(offSet.getY() < 0)
			offSet.setY(0);
		
		if(offSet.getX() > World.WIDTH*Tile.TILESIZE - Window.WIDTH)
			offSet.setX(World.WIDTH*Tile.TILESIZE - Window.WIDTH);
		if(offSet.getY() > World.HEIGHT*Tile.TILESIZE - Window.HEIGHT)
			offSet.setY(World.HEIGHT*Tile.TILESIZE - Window.HEIGHT);
		
	}
	
	
	public void move(Vector2D v){
		offSet.setX(offSet.getX() +  v.getX());
		offSet.setY(offSet.getY() +  v.getY());
	}
	
	
	public Vector2D getOffset(){
		return offSet;
	}
	
	public void setOffSet(Vector2D offSet){
		this.offSet = offSet;
	}
	
}
