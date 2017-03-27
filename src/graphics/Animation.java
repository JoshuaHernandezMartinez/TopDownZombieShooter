package graphics;

import java.awt.image.BufferedImage;

public class Animation {
	
	private BufferedImage[] frames;
	private int velocity, index;
	
	private long time, lastTime;
	
	
	
	public Animation(BufferedImage[] frames, int velocity){
		this.frames = frames;
		this.velocity = velocity;
		
		time = 0;
		lastTime = 0;
		index = 0;
		
	}
	
	public void update(){
		
		time += System.currentTimeMillis() - lastTime;
		
		lastTime = System.currentTimeMillis();
		
		if(time > velocity){
			time = 0;
			index ++;
			if(index == frames.length){
				index = 0;
			}
				
		}
		
	}
	
	public BufferedImage getCurrentFrame(){
		return frames[index];
	}
	
	public void setIndex(){
		index = 0;
	}
	
	public int getIndex(){
		return index;
	}
	
	public int getLenght(){
		return frames.length;
	}
	
	public int getVelocity(){
		return velocity;
	}
}
