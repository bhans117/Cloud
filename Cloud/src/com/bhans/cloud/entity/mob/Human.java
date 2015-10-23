package com.bhans.cloud.entity.mob;

import com.bhans.cloud.entity.Score;
import com.bhans.cloud.graphics.Screen;
import com.bhans.cloud.graphics.Sprite;
import com.bhans.cloud.level.Level;

public class Human extends Mob {

	private int health, anim, deathCount, speed; 
	private boolean isDead;
	public boolean remove;
	private Sprite sprite;

	public Human(Level level){
		x = Level.WIDTH * 16;
		y = Level.GROUND_HEIGHT * 16 - 40;
		health = 1;
		this.level = level;
		isDead = false;
		remove = false;
		sprite = Sprite.humanStill;
		speed = 3;
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
			move(-(speed - 2),0);
			deathCount--;
		}
		if(health <= 0) isDead = true;
		if(deathCount < 0) remove = true;
		
	}

	public void render(Screen screen){
		if(!isDead){
			if(anim % 10 > 5) {
				sprite = Sprite.humanStill;
			}else{
				sprite = Sprite.humanMove;
			}
		}else{
			if(anim % 10 > 5) {
				sprite = Sprite.humanDead1;
			}else{
				sprite = Sprite.humanDead2;
			}
		}
		screen.renderPlayer(x, y, sprite);
	}


	public void hit(int x){
		health -= x;
	}

	public int getX() { return x; }
	public int getY() { return y; }
	public int getHealth(){ return health; }
	public boolean isDead(){ return isDead; }
	public Sprite getSprite(){ return sprite; }
}
