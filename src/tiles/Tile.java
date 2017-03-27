package tiles;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import entities.Vector2D;

public abstract class Tile {
	public static Tile[] tiles = new Tile[10];
	public static final int TILESIZE = 128;
	public static Tile grassTile = new GrassTile();
	public static Tile dirtTile = new DirtTile();
	
	protected BufferedImage texture;
	protected int id;
	protected static int idCount = 0;
	
	public Tile(BufferedImage texture){
		this.texture = texture;
		tiles[idCount] = this;
		this.id = idCount;
		idCount ++;
		
	}
	
	public void update(){
		
	}
	
	public  void render(Graphics g, Vector2D position){
		g.drawImage(texture, (int)position.getX(), (int)position.getY(), null);
	}
	
	public boolean isSolid(){
		return false;
	}
	
	public BufferedImage getTexture(){
		return texture;
	}
	
	public int getID(){
		return id;
	}
	
}
