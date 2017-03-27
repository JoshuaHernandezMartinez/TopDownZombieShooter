package main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import entities.Vector2D;
import graphics.Assets;
import graphics.Camera;
import input.KeyManager;
import input.MouseManager;
import states.GameState;
import states.State;

public class Window implements Runnable{
	public static final int WIDTH = 1000, HEIGHT = 700;
	private final String title = "Top Down Zombie Shooter by Joshua Hernandez";
	private JFrame window;
	private Canvas canvas;
	private Thread thread;
	private boolean running = false;
	
	private Graphics g;
	private BufferStrategy bs;
	private GameState gameState;
	private MouseManager mouseManager;
	private KeyManager keyManager;
	private Camera camera;
	private Handler handler;
	
	private long averageFPS = 0;
	
	public Window(){
		window = new JFrame(title);
		window.setSize(WIDTH, HEIGHT);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setLocationRelativeTo(null);
		window.setResizable(false);
		window.setVisible(true);
		
		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		canvas.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		canvas.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		canvas.setFocusable(true);
		
		window.add(canvas);
		window.pack();
		
		mouseManager = new MouseManager();
		keyManager = new KeyManager();
		
		window.addMouseMotionListener(mouseManager);
		window.addMouseListener(mouseManager);
		canvas.addMouseMotionListener(mouseManager);
		canvas.addMouseListener(mouseManager);
		canvas.addKeyListener(keyManager);
		
	}
	
	
	private void init(){
		Assets.init();
		camera = new Camera(new Vector2D());
		handler = new Handler(this, mouseManager, keyManager);
		gameState = new GameState(handler);
		State.currentState = gameState;
		
	}
	
	private void update(){
		keyManager.update();
		if(State.currentState != null)
			State.currentState.update();
		
	}
	Color color = new Color(0, 0, 0, 125);
	private void render(){
		bs = canvas.getBufferStrategy();
		
		if(bs == null){
			canvas.createBufferStrategy(3);
			return;
		}
		
		g = bs.getDrawGraphics();
		
		if(State.currentState != null)
			State.currentState.render(g);
		
		g.drawString("FPS: "+averageFPS, 600, 25);
		
		g.dispose();
		bs.show();
		
	}
	
	@Override
	public void run() {
		
		init();
		int fps = 60;
		double timePerTick = 1000000000/fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long ticks = 0;
		long timer = 0;
		
		while(running){
			now = System.nanoTime();
			delta += (now - lastTime)/timePerTick;
			timer += now - lastTime;
			lastTime = now;
			if(delta >= 1){
				update();
				render();
				delta --;
				ticks ++;
			}
			
			if(timer >= 1000000000){
				averageFPS = ticks;
				ticks = 0;
				timer = 0;
			}
		}
		stop();
	}
	
	private synchronized void start(){
		if(running)
			return;
		
		running = true;
		thread = new Thread(this);
		thread.start();
		
	}
	private synchronized void stop(){
		if(!running)
			return;
		
		try {
			thread.join();
			running = false;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	public Camera getCamera(){
		return camera;
	}
	
	public static void main(String[] args) {
		new Window().start();
	}
}