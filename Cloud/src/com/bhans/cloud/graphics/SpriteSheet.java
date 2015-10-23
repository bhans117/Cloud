package com.bhans.cloud.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {

	//VARIABLES
	private String path;
	public final int SIZE;
	public int [] pixels;
	
	//SPRITE SHEETS
	public static SpriteSheet cloud = new SpriteSheet("/textures/sheets/cloud.png", 1024);
	public static SpriteSheet rain = new SpriteSheet("/textures/sheets/rain.png", 32);
	public static SpriteSheet lightning = new SpriteSheet("/textures/sheets/lightning.png", 512);
	public static SpriteSheet humans = new SpriteSheet("/textures/sheets/humans.png", 192);
	public static SpriteSheet cars = new SpriteSheet("/textures/sheets/cars.png", 192);
	public static SpriteSheet score = new SpriteSheet("/textures/sheets/score.png", 64);
	
	//CONSTRUCTOR
	public SpriteSheet(String path, int size) {
		this.path = path;
		SIZE = size;
		pixels = new int[SIZE * SIZE];
		load();
	}
	
	//LOAD SPRITE SHEETS
	//	loading the sprite sheets into a color number pixel array from the buffered image.
	private void load() {
		try {
			BufferedImage image = ImageIO.read(SpriteSheet.class.getResource(path));
			int w = image.getWidth();
			int h = image.getHeight();
			image.getRGB(0, 0, w, h, pixels, 0, w);
		}catch(IOException e){
			e.printStackTrace();
			System.out.println("Error! Could not read SpriteSheet!");
		}
	}
}