package com.bhans.cloud.graphics;

public class Sprite {

	//VARIABLES
	public final int WIDTH, HEIGHT;
	private int x, y; //location of Sprite on SpriteSheet
	public int[] pixels; // array of color values the size of the Sprite Tile
	private SpriteSheet sheet;
	
	//SPRITES
	//	Void Color
	public static final int VOID_COLOR = 0xFFFF19E0;
	public static final int VOID_COLOR_PLAYER = 0xFF1A3556;
	public static final int VOID_COLOR_ENIMIES = 0xFF1775C6;
	public static final int COLOR_SKY = 0xFF1A3556;
	public static final int COLOR_LIGHT_SKY = 0xFFFFFFE1;
	
	//	Color Blocks
	public static Sprite black = new Sprite(16, 16, 0xFF000000);
	public static Sprite white = new Sprite(16, 16, 0xFFFFFFFF);
	public static Sprite blueSky = new Sprite(16, 16, 0xFF70BCFF);
	
	//	Cloud
	public static Sprite playerIdle1 = new Sprite(512, 256, 0, 0, SpriteSheet.cloud);
	public static Sprite playerIdle2 = new Sprite(512, 256, 0, 2, SpriteSheet.cloud);
	public static Sprite playerRain1 = new Sprite(512, 256, 512, 0, SpriteSheet.cloud);
	public static Sprite playerRain2 = new Sprite(512, 256,  512, 1, SpriteSheet.cloud);
	
	//	Whether
	public static Sprite rainDrop = new Sprite(8, 8, 0, 0, SpriteSheet.rain);
	
	public static Sprite grass = new Sprite(16, 16, 16, 0, SpriteSheet.rain);
	
		//		lightning 
	public static Sprite lightningYellow1 = new Sprite(64, 400, 0, 0, SpriteSheet.lightning);
	public static Sprite lightningYellow2 = new Sprite(64, 400, 64, 0, SpriteSheet.lightning);
	public static Sprite lightningYellow3 = new Sprite(64, 400, 128, 0, SpriteSheet.lightning);
	public static Sprite lightningYellow4 = new Sprite(64, 400, 192, 0, SpriteSheet.lightning);
	
	//Enemies 
	public static Sprite humanStill = new Sprite(48, 48, 0, 0, SpriteSheet.humans);
	public static Sprite humanMove = new Sprite(48, 48, 48, 0, SpriteSheet.humans);
	public static Sprite humanDead1 = new Sprite(48, 48, 96, 0, SpriteSheet.humans);
	public static Sprite humanDead2 = new Sprite(48, 48, 144, 0, SpriteSheet.humans);
	public static Sprite car1 = new Sprite(96, 48, 0, 0, SpriteSheet.cars);
	public static Sprite car2 = new Sprite(96, 48, 0, 50, SpriteSheet.cars);
	public static Sprite carDead1 = new Sprite(96, 48, 0, 96, SpriteSheet.cars);
	public static Sprite carDead2 = new Sprite(96, 48, 0, 144, SpriteSheet.cars);
	
	//Score
	public static Sprite scoreRed = new Sprite(64, 32, 0, 0, SpriteSheet.score);
	public static Sprite scoreBlue = new Sprite(64, 32, 0, 32, SpriteSheet.score);
	
	
	
	//CONSTRUCTOR (FROM SPRITE SHEET)
	public Sprite(int width, int height, int x, int y, SpriteSheet sheet){
		this.x = x;
		this.y = y;
		WIDTH = width;
		HEIGHT = height;
		pixels = new int [WIDTH * HEIGHT];
		this.sheet = sheet;
		load();
	}
	
	//CONSTRUCTOR (SPECIFIED COLOR)
	public Sprite(int width, int height, int color){
		WIDTH = width;
		HEIGHT = height;
		pixels = new int [WIDTH * HEIGHT];
		setColor(color);
	}
	//helper method for setting color of sprite
	private void setColor(int color){
		for (int i = 0; i < pixels.length; i++){
			pixels[i] = color;
		}
	}
	
	//LOAD
	//	At the selected coordinates of the spriteSheet, those
	//pixels are loaded into the sprite pixels.
	public void load() {
		for (int y = 0; y < HEIGHT; y++) {
			for (int x = 0; x < WIDTH; x++) {
				pixels[x + y * WIDTH] = sheet.pixels[(this.x + x) + (this.y + y) * sheet.SIZE];
			}
		}
	}
}
