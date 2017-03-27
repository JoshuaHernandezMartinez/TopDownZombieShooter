package states;

import java.awt.Graphics;

import graphics.Assets;
import graphics.Sound;
import main.Handler;
import tiles.World;

public class GameState extends State{
	
	
	private Sound background;
	private World world;
	
	
	public GameState(Handler handler){
		super(handler);
		background = new Sound(Assets.background);
		background.loopSound();
		world = new World(handler);
	}
	
	@Override
	public void update() {
		world.update();
		
	}
	
	
	@Override
	public void render(Graphics g) {
		world.render(g);
	}	
}
