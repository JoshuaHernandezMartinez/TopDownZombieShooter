package graphics;

import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class Sound {
	private Clip sound;
	private FloatControl volume;
	
	public Sound(Clip sound){
		this.sound = sound;
		volume =  (FloatControl) sound.getControl(FloatControl.Type.MASTER_GAIN);
	}
	
	public void playSound(){
		sound.setFramePosition(0);
		sound.start();
	}
	public void loopSound(){
		sound.loop(Clip.LOOP_CONTINUOUSLY);
	}
	public void stopSound(){
		sound.stop();
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public int getFramePosition(){
		return sound.getFramePosition();
	}
	public int getFrameLenght(){
		return sound.getFrameLength();
	}
	
	public void changeVolume(float value){
		volume.setValue(value);
	}
	public boolean isRunning(){
		return sound.isRunning();
	}
}
