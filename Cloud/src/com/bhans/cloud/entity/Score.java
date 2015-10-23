package com.bhans.cloud.entity;

import com.bhans.cloud.graphics.Screen;
import com.bhans.cloud.graphics.Sprite;

public class Score extends Entity {
	
	private int x, y, screenCount, anim;
	public boolean remove;
	private Sprite sprite;
	
	public Score(int x, int y){
		this.x = x;
		this.y = y;
		remove = false;
		screenCount = 100;
		sprite = Sprite.scoreRed;
	}
	
	public void update() {
		if (anim < 1000000) anim++;
		if(screenCount > 0){
			x--;
			y--;
			screenCount--;
		}
		else{
			remove = true;
		}
	}

	public void render(Screen screen){
		if(anim % 10 > 5) {
			sprite = Sprite.scoreRed;
		}else{
			sprite = Sprite.scoreBlue;
		}
		screen.renderPlayer(x, y, sprite);
	}

}

