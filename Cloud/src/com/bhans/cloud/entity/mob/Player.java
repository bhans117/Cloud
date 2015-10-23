package com.bhans.cloud.entity.mob;


import com.bhans.cloud.Game;
import com.bhans.cloud.entity.Entity;
import com.bhans.cloud.entity.Lightning;
import com.bhans.cloud.entity.Rain;
import com.bhans.cloud.graphics.Screen;
import com.bhans.cloud.graphics.Sprite;
import com.bhans.cloud.input.Keyboard;

public class Player extends Mob {

	//VARIABLES
	private Keyboard input;
	private int anim = 0;
	public static Sprite sprite;
	private int lightningRate;
	private final int LIGHTNING_RATE = 30;
	private int actualY;

	//CONSTRUCTOR
	public Player(int x, int y, Keyboard input){
		this.input = input;
		this.x = x;
		this.y = y;
		lightningRate = LIGHTNING_RATE;
	}
	
	//UPDATE
	public void update(){
		if (anim < 1000000) anim++;
		actualY = y + sprite.HEIGHT;
		clear();
		updateWheather();
		updatePosition();
	}
	
	//UPDATE (HELPER)
	private void updateWheather(){
		lightningRate--;
		//Rain
		/*if (input.space && level.getRain().size() < 500){
			Rain r = new Rain(x, actualY, level);
			level.addRain(r);
		}*/
		
		//Lightning
		if (input.down && lightningRate < 0){ 
			lightningRate = LIGHTNING_RATE;
			Entity e = new Lightning(this, actualY, level);
			level.addEntity(e);
		}
	}
	
	//UPDATE (HELPER)
	private void updatePosition(){
		if (input.right && x < Game.WIDTH - 270)  move(10,0);
		if (input.left && x > -250) move (-10,0);
	}
	
	//UPDATE (HELPER) - CLEAR
	private void clear() {
		for (int i = 0; i < level.getEntities().size(); i ++){
			Entity e = level.getEntities().get(i);
			if (e.isRemoved()){
				level.getEntities().remove(i);
			}
		}
		for (int i = 0; i < level.getRain().size(); i ++){
			Rain r = level.getRain().get(i);
			if (r.isRemoved()){
				level.getRain().remove(i);
			}
		}
	}
	
	//RENDER
	public void render(Screen screen){
		if(anim % 20 > 10) {
			sprite = Sprite.playerIdle1;
		}else{
			sprite = Sprite.playerIdle2;
		}
		if(input.down){
			if(anim % 20 > 10) {
				sprite = Sprite.playerRain1;
			}else{
				sprite = Sprite.playerRain2;
			}
		}

		screen.renderPlayer(x, y, sprite);
	}

}
