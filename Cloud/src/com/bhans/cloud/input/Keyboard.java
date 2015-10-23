package com.bhans.cloud.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class Keyboard implements KeyListener {

	//VARIABLES
	private boolean[] keys = new boolean[120];
	public boolean up, down, left, right, space;

	//UPDATE
	//	check boolean of each concerned key
	public void update(){
		up = keys[KeyEvent.VK_UP] || keys[KeyEvent.VK_W];
		down = keys[KeyEvent.VK_DOWN] || keys[KeyEvent.VK_S];
		left = keys[KeyEvent.VK_LEFT] || keys[KeyEvent.VK_A];
		right = keys[KeyEvent.VK_RIGHT] || keys[KeyEvent.VK_D];
		space = keys[KeyEvent.VK_SPACE];

	}
	
	public void keyPressed(KeyEvent k){
		keys[k.getKeyCode()] = true;
	}
	
	public void keyReleased(KeyEvent k){
		keys[k.getKeyCode()] = false;
	}
	
	public void keyTyped(KeyEvent k) {
		
	}
}
