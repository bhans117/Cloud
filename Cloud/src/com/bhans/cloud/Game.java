package com.bhans.cloud;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import com.bhans.cloud.entity.mob.Player;
import com.bhans.cloud.graphics.Screen;
import com.bhans.cloud.input.Keyboard;
import com.bhans.cloud.level.Level;

public class Game extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;

	//VARIABLES
	//	game size preferences
	public static int HEIGHT = 720;
	public static int WIDTH = HEIGHT * 16 / 9;
	
	//	game title
	public static String title = "Cloud";
	
	// the view
	private JFrame frame;
	private Screen screen;
	
	//	the run variables
	private boolean running = false;
	private Thread thread;
	
	//	the display
	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();
	
	//	Game parts (level... etc.)
	Level level;
	Player cloud;
	Keyboard key;
	
	
	//CONSTRUCTOR
	public Game(){
		Dimension size = new Dimension(getWidth(), getHeight());
		setPreferredSize(size);
		
		frame = new JFrame();
		screen = new Screen(WIDTH, HEIGHT);
		
		//Adding Game Parts
		key = new Keyboard();
		
		level = new Level();
		cloud = new Player(200, 0, key);
		cloud.init(level);
		addKeyListener(key);
	}

	//SYNCHRONIZATION
	//	Starting Method
	public synchronized void start() {
		running = true;
		thread = new Thread(this, "Display");
		thread.start();
	}
	//	Stopping Method
	public synchronized void stop() {
		running = false;
		try{
			thread.join();
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}
	
	
	//RUN
	public void run() {
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		final double ns = 1000000000.0 / 60.0;
		double delta = 0;
		int frames = 0;
		int updates = 0;
		requestFocus();
		while(running){
			long now = System.nanoTime();
			
			delta += (now - lastTime) / ns; 
			//delta = difference between lastTime and Now
			//in nanoseconds
			lastTime = now;
			/*while difference >= 1, update
			this controls the updates to 
			happen 60 times a second
			*/
			while(delta >= 1){
				update();
				updates++;
				delta--;
			}
			render();
			frames++;
			
			// after each second display frames and updates
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				frame.setTitle(title + "   |   FPS: " + frames + "  | Updates: " + updates);
				updates = 0;
				frames = 0;
			}
		}
		
	}
	
	//UPDATE
	public void update(){
		level.update();
		cloud.update();
		key.update();
	}
	
	//RENDER
	public void render(){
		//	Buffer Strategy (3 buffered images)
		BufferStrategy bs = getBufferStrategy();
		if ( bs == null) {
			createBufferStrategy(3);
			return;
		}
		
		//	Render Game Parts
		screen.clear();
		level.render(cloud.x - 200, screen);
		cloud.render(screen);
	
		//	Take pixels from screen to be rendered
		for (int i = 0; i < screen.pixels.length; i++){
			pixels[i] = screen.pixels[i];
		}
		
		//	Graphics
		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.black);
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null); // draw the pixel array
		g.dispose();
		bs.show();	
	}
	
	//	Get	Methods
	public int getWidth(){ return WIDTH; }
	public int getHeight() { return HEIGHT; }
	
	//MAIN
	public static void main(String[] args){
		Game game = new Game();
		game.frame.setResizable(false);
		game.frame.setTitle(title);
		game.frame.add(game);
		game.frame.pack();
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.frame.setLocationRelativeTo(null);
		game.frame.setVisible(true);
		
		game.start();
	}

	
	
	
	
}
