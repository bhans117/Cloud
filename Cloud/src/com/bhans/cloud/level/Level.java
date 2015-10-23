package com.bhans.cloud.level;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;



import com.bhans.cloud.entity.Entity;
import com.bhans.cloud.entity.Lightning;
import com.bhans.cloud.entity.Rain;
import com.bhans.cloud.entity.Score;
import com.bhans.cloud.entity.mob.Car;
import com.bhans.cloud.entity.mob.Human;
import com.bhans.cloud.graphics.Screen;
import com.bhans.cloud.graphics.Sprite;

public class Level {

	//VARIABLES
	public static final int WIDTH = 80;
	public static final int HEIGHT = 45;
	public static final int GROUND_HEIGHT = HEIGHT - 7;
	private int humanCount, carCount, skyCount;
	protected int widthChange;
	private int[] tiles;
	protected Random random = new Random();

	//LISTS
	public List<Entity> entities = new ArrayList<Entity>();
	public List<Rain> rain = new ArrayList<Rain>();
	public List<Lightning> lightning = new ArrayList<Lightning>();
	//	enemies
	public List<Human> humans = new ArrayList<Human>();
	public List<Car> cars = new ArrayList<Car>();
	public List<Score> scores = new ArrayList<Score>();

	//Constructor 
	public Level(){
		tiles = new int[WIDTH * HEIGHT];
		humanCount = 100;
		carCount = 300;
		skyCount = 0;
		generateHuman();
		generateCar();
		generateLevel();
	}
	
	//RANDOM LEVEL GENERATION
	public void generateLevel(){
		for (int y = 0; y < Level.GROUND_HEIGHT ; y++){
			for (int x = 0; x < Level.WIDTH; x++){
				tiles[x + y * Level.WIDTH] = Sprite.COLOR_SKY;
			}
		}

		for (int y = Level.GROUND_HEIGHT; y < Level.HEIGHT; y++){
			for (int x = 0; x < Level.WIDTH; x++){
				tiles[x + y * Level.WIDTH] = 0xFF3A801F;
			}
		}
	}
	
	//UPDATE
	public void update(){
		generateHuman();
		generateCar();
		//Attacks
		//	entity
		for (int i = 0; i < entities.size(); i++){
			entities.get(i).update();
		}
		//	rain
		for (int i = 0; i < rain.size(); i++){
			rain.get(i).update();
		}

		//	lightning
		for (int i = 0; i < lightning.size(); i++){
			lightning.get(i).update();
		}
		
		//Enemies
		//	Human
		for (int i = 0; i < humans.size(); i++){
			humans.get(i).update();
			if (humans.get(i).remove || humans.get(i).getX() < 0 - humans.get(i).getSprite().WIDTH ) humans.remove(i);
		}
		//	Car
		for (int i = 0; i < cars.size(); i++){
			cars.get(i).update();
			if (cars.get(i).remove || cars.get(i).getX() < 0 - cars.get(i).getSprite().WIDTH ) cars.remove(i);
		}
		//Score
		for (int i = 0; i < scores.size(); i++){
			scores.get(i).update();
			if (scores.get(i).remove) scores.remove(i);
		}
		
		//Sky
		
		if(!entities.isEmpty()) {
			skyCount++;
			if(skyCount % 2 == 0)generateSky();
			if(skyCount % 5 == 0)generateLevel();
		}else{
			generateLevel();
		}
	}
	
	//UPDATE HELPERS
	public void generateHuman(){
		if ( humans.size() < 20 && humanCount > 100){
		humans.add(new Human(this));
		humanCount = random.nextInt(100); //reset count
		}else{
			humanCount++;
		}
	}
	public void generateCar(){
		if ( cars.size() < 5 && carCount > 300){
		cars.add(new Car(this));
		carCount = random.nextInt(100) + 150;
		}else{
			carCount++;
		}
	}
	
	public void generateSky(){
		for (int y = 0; y < Level.GROUND_HEIGHT ; y++){
			for (int x = 0; x < Level.WIDTH; x++){
				tiles[x + y * Level.WIDTH] = Sprite.COLOR_LIGHT_SKY;
			}
		}
	}

	//RENDER
	public void render(int xScroll, Screen screen){
		screen.setOffset(xScroll, 0);
		for (int y = 0; y < HEIGHT; y++){
			for(int x = 0; x < WIDTH; x++){

				getTile(tiles[x + y * WIDTH]).render(x, y, screen);
			}
		}
		//Attacks
		//	entity
		for (int i = 0; i < entities.size(); i++){
			entities.get(i).render(screen);
		}
		//	rain
		for (int i = 0; i < rain.size(); i++){
			rain.get(i).render(screen);
		}
		//	lightning
		for (int i = 0; i < lightning.size(); i++){
			lightning.get(i).render(screen);
		}
		
		//Enemies
		//	Human
		for (int i = 0; i < humans.size(); i++){
			humans.get(i).render(screen);
		}
		//	Car
		for (int i = 0; i < cars.size(); i++){
			cars.get(i).render(screen);
		}
		
		//Score
		for (int i = 0; i < scores.size(); i++){
			scores.get(i).render(screen);
		}
		
	}

	//ADD
	//entity
	public void addEntity(Entity e) {
		entities.add(e);
	}
	//rain
	public void addRain(Rain r) {
		rain.add(r);
	}
	//lightning
	public void addLightning(Lightning l) {
		lightning.add(l);
	}
	//score
	public void addScore(Score s) {
		scores.add(s);
	}
	
	
	//GET
	public static Tile getTile(int i) {
		return new Tile(new Sprite(16, 16, i));
	} 
	public List<Entity> getEntities() { return entities; }
	public List<Rain> getRain() { return rain; }
	public List<Lightning> getLightning() { return lightning; }	
	public List<Human> getHuman() { return humans; }	
	public List<Car> getCar() { return cars; }	
	public List<Score> getScores() { return scores; }

}
