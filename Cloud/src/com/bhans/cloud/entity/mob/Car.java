package com.bhans.cloud.entity.mob;


import com.bhans.cloud.entity.Score;
import com.bhans.cloud.graphics.Screen;
import com.bhans.cloud.graphics.Sprite;
import com.bhans.cloud.level.Level;

public class Car extends Mob {

	private int health, anim, deathCount, speed;
	private boolean isDead;
	public boolean remove;
	private Sprite sprite;

	public Car(Level level){
		x = Level.WIDTH * 16;
		y = Level.GROUND_HEIGHT * 16 - 30;
		health = 1;
		this.level = level;
		isDead = false;
		remove = false;
		sprite = Sprite.car1;
		speed = 20;
		anim = 0;
		deathCount = 100;
	}

	public void update(){
		if	(isDead && deathCount == 100){
			Score s = new Score(x,y);
			level.addScore(s);
		}
		if (anim < (1000000)) anim++;
		if (!isDead){
			move(-speed,0);
		}
		else{ 
			move(-1,0);
			deathCount--;
		}
		if(health <= 0) isDead = true;
		if(deathCount < 0) remove = true;
	}

	public void render(Screen screen){
		if(!isDead){
			if(anim % 10 > 5) {
				sprite = Sprite.car1;
			}else{
				sprite = Sprite.car2;
			}
		}else{
			if(anim % 10 > 5) {
				sprite = Sprite.carDead1;
			}else{
				sprite = Sprite.carDead2;
			}
		}
		screen.renderPlayer(x, y, sprite);
	}

	public void hit(int x){
		health -= x;
	}

	public int getX() { return x; }
	public int getHealth(){ return health; }
	public boolean isDead(){ return isDead; }
	public Sprite getSprite(){ return sprite; }
}


