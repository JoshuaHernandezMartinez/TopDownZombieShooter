package main;

import input.KeyManager;
import input.MouseManager;

public class Handler {
	private MouseManager mouseManager;
	private KeyManager keyManager;
	private Window window;
	
	public Handler(Window window, MouseManager mouseManager, KeyManager keyManager){
		this.window = window;
		this.mouseManager = mouseManager;
		this.keyManager = keyManager;
	}

	public MouseManager getMouseManager() {
		return mouseManager;
	}

	public void setMouseManager(MouseManager mouseManager) {
		this.mouseManager = mouseManager;
	}

	public KeyManager getKeyManager() {
		return keyManager;
	}

	public void setKeyManager(KeyManager keyManager) {
		this.keyManager = keyManager;
	}

	public Window getWindow() {
		return window;
	}

	public void setWindow(Window window) {
		this.window = window;
	}
	
	
}
