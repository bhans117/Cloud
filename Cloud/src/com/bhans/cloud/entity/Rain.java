package com.bhans.cloud.entity;

import java.util.Random;

import com.bhans.cloud.entity.Entity;
import com.bhans.cloud.graphics.Screen;
import com.bhans.cloud.graphics.Sprite;
import com.bhans.cloud.level.Level;

public class Rain extends Entity {
	
	//VARIABLES
	protected final int xOrgin, yOrgin;
	protected Sprite sprite;
	protected double nx, ny;
	protected double speed, range, damage;
	protected double x, y;
	protected double distance;
	protected final Random random = new Random();
	private boolean humanHit, carHit;
	public static final int DAMAGE = 5;
	
	//CONSTRUCTOR
	public Rain(int x, int y, Level level){
		xOrgin = x;
		yOrgin = y;
		this.x = x + random.nextInt(300) + 100;
		this.y = y;
		this.level = level;
		humanHit = false;
		carHit = false;
		range = Level.GROUND_HEIGHT * 16 - y;
		damage = 20;
		speed = 10;
		sprite = Sprite.rainDrop;	
	}

	//UPDATE
	public void update(){	
		move();	
		//collision();
	}
	
	public void collision(){
		for ( int i = 0; i < level.getHuman().size(); i++){
		if (level.humans.get(i).getX() < this.x + 280 && level.humans.get(i).getX() > this.x + 240 && !humanHit) level.humans.get(i).hit(DAMAGE);
			humanHit = true;
		if (level.cars.get(i).getX() < this.x + 4 && level.cars.get(i).getX() > this.x - 4 && !carHit) level.cars.get(i).hit(DAMAGE);
			carHit = true;
		}
	}

	//RENDER
	public void render(Screen screen){
		screen.renderRain( (int)x , (int)y , this);
	}
	
	//MOVE
	protected void move(){
		 y += speed;
		 if (distance() > range) remove();
	}
	
	//CALCULATE DISTANCE
	private double distance(){
		double dist = 0;
		dist =  Math.sqrt(Math.abs((xOrgin - x) * (xOrgin - x)) + Math.abs((yOrgin - y) * (yOrgin - y))); 
		return dist;
	}
	
	//GET
	public Sprite getSprite() { return sprite; }
}

