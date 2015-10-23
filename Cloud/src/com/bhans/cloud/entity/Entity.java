package com.bhans.cloud.entity;

import com.bhans.cloud.graphics.Screen;
import com.bhans.cloud.graphics.Sprite;
import com.bhans.cloud.level.Level;


public class Entity {

	//VARIABLES
	public int x, y, score;
	private boolean removed;
	public Sprite sprite;
	protected Level level;
	
	public void update(){
			
	}
	
	public void render(Screen screen){
		
	}
	
	public void remove(){
		removed = true;
	}
	
	public boolean isRemoved(){
		return removed;
	}
	
	//INITIALIZE
	//	give the entities a level to use
	public void init(Level level){
		this.level = level;
	}
}

