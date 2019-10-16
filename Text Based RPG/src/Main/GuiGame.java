package Main;

import java.awt.Canvas;
import java.awt.Graphics;

import GFX.Window;

public class GuiGame extends Canvas implements Runnable {

    private static final long serialVersionUID = 1L;
    public static final int WIDTH = 1024, HEIGHT = WIDTH / 12 * 9;

    private boolean running = false;
    
    private Thread thread;
    private String title;

    public GuiGame(String title) {
	this.title = title;
	new Window(WIDTH, HEIGHT, title, this);
    }

    public synchronized void start() {
	thread = new Thread(this);
	thread.start();
	running = true;
    }

    public synchronized void stop() {
	try {
	    thread.join(); // this kills the thread. Remember this
	    running = false;
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public void tick() {

    }

    public void render() {

    }

    @Override
    public void run() {
	this.requestFocus();
	long lastTime = System.nanoTime();
	float amountOfTicks = 60.0f;
	float ns = 1000000000 / amountOfTicks;
	float delta = 0f;
	long timer = System.currentTimeMillis();
	// int frames = 0;
	while (running) {
	    long now = System.nanoTime();
	    delta += (now - lastTime) / ns;
	    lastTime = now;
	    while (delta >= 1) {
		tick();
		delta--;
	    }
	    if (running)
		render();
	    // frames++;

	    if (System.currentTimeMillis() - timer > 1000) {
		timer += 1000;
		// System.out.println("FPS: " + frames);
		// frames = 0;
	    }
	}
	stop();

    }
}
