package com.bhans.cloud.graphics;

import com.bhans.cloud.entity.Entity;
import com.bhans.cloud.entity.Lightning;
import com.bhans.cloud.entity.Rain;
import com.bhans.cloud.level.Tile;

public class Screen {

	//VARIABLES 
	public int width, height;
	public int[] pixels;
	public int xOffset, yOffset;

	//CONSTRUCTOR
	//	constructs a screen in which everything will be rendered to. 
	//this screen is the size of the JFrame and is made up of a
	//color number pixel array
	public Screen(int width, int height){
		this.width = width;
		this.height = height;
		pixels = new int[width * height];

	}


	//RENDER METHODS
	
	//	TILE
	public void renderTile(int xp, int yp, Tile tile) {
		xp -= xOffset;
		yp -= yOffset;
		for(int y = 0; y < tile.sprite.HEIGHT; y++){
			int ya = y + yp;
			for (int x = 0; x < tile.sprite.WIDTH; x++){
				int xa = x + xp;
				if (xa < -tile.sprite.WIDTH || xa >= width|| ya < 0 || ya >= height) break;
				if (xa < 0) xa = 0;
				pixels[(xa + ya * width)] = tile.sprite.pixels[x + y * tile.sprite.WIDTH];
			}
		}
	}

	//	PLAYER
	public void renderPlayer(int xp, int yp, Sprite sprite){
		xp -= xOffset;		
		yp -= yOffset;
		for( int y = 0; y < sprite.HEIGHT; y++){
			int ya = y + yp;
			for (int x = 0; x < sprite.WIDTH; x++){
				int xa = x + xp;
				if (xa >= width|| ya < 0 || ya >= height) break;
				if (xa < 0) xa = 0;
				int col = sprite.pixels[x + y * sprite.WIDTH];
				if (col != Sprite.VOID_COLOR_PLAYER) pixels[(xa + ya * width)] = col;
			}
		}

	}
	//	RAIN
	public void renderRain(int xp, int yp, Rain r) {
		xp -= xOffset;
		yp -= yOffset;
		for( int y = 0; y < r.getSprite().HEIGHT; y++){
			int ya = y + yp;
			for (int x = 0; x < r.getSprite().WIDTH; x++){
				int xa = x + xp;
				if (xa < -r.getSprite().WIDTH || xa >= width|| ya < 0 || ya >= height) break;
				if (xa < 0) xa = 0;
				int col = r.getSprite().pixels[x + y * r.getSprite().WIDTH];
				if (col != Sprite.VOID_COLOR) pixels[(xa + ya * width)] = r.getSprite().pixels[x + y * r.getSprite().WIDTH];
			}
		}
	}	

	//	LIGHTNING
	public void renderLighting(int xp, int yp, Lightning l){
		xp -= xOffset;
		yp -= yOffset;
		for( int y = 0; y < l.getSprite().HEIGHT; y++){
			int ya = y + yp;
			for( int x = 0; x < l.getSprite().WIDTH; x++){
				int xa = x + xp;
				if ( xa < 0) xa = 0;
				int col = l.getSprite().pixels[x + y * l.getSprite().WIDTH];
				if (col != Sprite.VOID_COLOR) pixels[(xa + ya * width)] = l.getSprite().pixels[x + y * l.getSprite().WIDTH];
			}
		}
	}
	// ENTINTY
	public void renderEntity(int xp, int yp, Entity e) {
		xp -= xOffset;
		yp -= yOffset;
		for(int y = 0; y < e.sprite.HEIGHT; y++){
			int ya = y + yp;
			for (int x = 0; x < e.sprite.WIDTH; x++){
				int xa = x + xp;
				if (xa < -e.sprite.WIDTH || xa >= width|| ya < 0 || ya >= height) break;
				if (xa < 0) xa = 0;
				int col = e.sprite.pixels[x + y * e.sprite.WIDTH];
				if (col != Sprite.VOID_COLOR) pixels[(xa + ya * width)] = e.sprite.pixels[x + y * e.sprite.WIDTH];
			}
		}
	}	
	
	//CLEAR
	//resets all the pixels
	public void clear() {
		for (int i = 0; i < pixels.length; i++){
			pixels[i] = 0;
		}
	}
	
	//BACKGROUND COLOR
	public void renderColor(int color){
		for (int i = 0; i < pixels.length; i++){
			pixels[i] = color;
		}
	}
	
	//SET OFFSET (REPOSITION SCREEN)
	public void setOffset(int xOffset, int yOffset){
		//this.xOffset = xOffset;
		//this.yOffset = yOffset;
	}

}



