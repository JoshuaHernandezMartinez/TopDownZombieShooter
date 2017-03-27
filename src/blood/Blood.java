package blood;
import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;

import entities.Vector2D;
import graphics.Assets;
import main.Handler;
import tiles.World;

public class Blood {
	
	private Vector2D position;
	private float alpha;
	private World world;
	
	public Blood(Vector2D position, World world){
		this.position = position;
		this.world = world;
		alpha = 1;
	}
	
	public void render(Graphics g){
		
		int x = (int) world.getHandler().getWindow().getCamera().getOffset().getX();
		int y = (int) world.getHandler().getWindow().getCamera().getOffset().getY();
		
		Graphics2D g2d = (Graphics2D)g;
		
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
		
		g2d.drawImage(Assets.blood1, (int)(position.getX() - x), (int)(position.getY() - y), null);
		
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
		
		alpha -= 0.001f;
		if(alpha < 0)
			world.getBloodSplats().remove(this);
	}
	
}
