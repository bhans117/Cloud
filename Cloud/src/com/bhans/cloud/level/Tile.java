package com.bhans.cloud.level;

import com.bhans.cloud.graphics.Screen;
import com.bhans.cloud.graphics.Sprite;

public class Tile {
	
	public Sprite sprite;
	
	//TILES
	public static Tile blackTile = new Tile(Sprite.black);
	public static Tile whiteTile = new Tile(Sprite.white);
	public static Tile blueSky = new Tile(Sprite.blueSky);
	
	//CONSTRUCTOR
	public Tile(Sprite sprite){
		this.sprite = sprite;
	}
	
	public void render(int x, int y, Screen screen){
		screen.renderTile(x * sprite.WIDTH, y * sprite.HEIGHT , this);
	}
	
}
