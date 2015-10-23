package com.bhans.cloud.entity;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.bhans.cloud.entity.mob.Player;
import com.bhans.cloud.graphics.Screen;
import com.bhans.cloud.graphics.Sprite;
import com.bhans.cloud.level.Level;


public class Lightning extends Entity {

	//VARIABLES
	private int x, y;
	protected int count, placement, ran, lightX;
	protected double speed, damage;
	protected List<Sprite> yellowLightning = new ArrayList<Sprite>();
	protected Random random = new Random();
	private Player player;
	public static final int DAMAGE = 1;

	//CONSTRUCTOR
	public Lightning(Player player, int y, Level level){
		this.player = player;
		this.x = player.x;
		this.y = y - 20;
		this.level = level;
		count = 0;
		yellowLightning.add(Sprite.lightningYellow1);
		yellowLightning.add(Sprite.lightningYellow2);
		yellowLightning.add(Sprite.lightningYellow3);
		yellowLightning.add(Sprite.lightningYellow4);
		placement = 256 - 15;
		lightX = player.x + placement;
		speed = 10;
		damage = 10;
	}

	//UPDATE
	public void update(){
		count++;
		if (count > speed){
			remove();
			count = 0;
		}
		ran = random.nextInt(4);
		lightX = player.x + placement;
		collision();
	}

	public void collision(){
		for ( int i = 0; i < level.getHuman().size(); i++){
			for( int j = 0; j < level.humans.get(i).getSprite().WIDTH; j ++ )
			if (level.humans.get(i).getX() + j < player.x + 280 && level.humans.get(i).getX() + j > player.x + 200 ) level.humans.get(i).hit(DAMAGE);
		}
		for ( int i = 0; i < level.getCar().size(); i++){
			for( int j = 0; j < level.cars.get(i).getSprite().WIDTH; j ++ )
			if (level.cars.get(i).getX() + j < player.x + 280 && level.cars.get(i).getX()+ j > player.x + 240){
				level.cars.get(i).hit(DAMAGE);
			}
		}

	}


	//RENDER
	public void render(Screen screen){
		screen.renderLighting(lightX , y, this);
	}

	//GET
	public Sprite getSprite() {
		return yellowLightning.get(ran);
	}
	public int getX(){ return x;}
	public int getY(){ return y;}
}
