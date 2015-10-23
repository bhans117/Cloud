package com.bhans.cloud.entity.mob;

import com.bhans.cloud.entity.Entity;

public class Mob extends Entity {
	
	public void move(int xa, int ya){
		y += ya;
		x += xa;
	}
	
	
	public void update() {
		
	}
	
	public void render(){
		
	}
	
}
