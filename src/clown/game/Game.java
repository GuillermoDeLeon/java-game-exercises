package clown.game;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable{

    Thread thread;
    private boolean isRunning = false;

    public Game(){
        new Window(640, 480, "Java Clown Course", this);
        start();
    }

    private void start(){
        isRunning = true;
        thread = new Thread(this);
        thread.start();
    }

    private void stop(){
        isRunning = false;
        try {
            thread.join();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void run(){
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while(isRunning){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >= 1){
                tick();
                delta--;
            }
            if(isRunning)
                render();
            frames++;

            if(System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                System.out.println("FPS: " + frames);
                frames =0;
            }
        }
    }
//  this method is where we update all our game events, such as increasing a score, or changing or moving a variable around
    public void tick(){

    }

//  this is where we draw all of our game's graphics
    public void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();

        g.setColor(Color.green);
        g.fillRect(0,0, 640,480);
        g.setColor(Color.yellow);
        g.fillRect(0,240,640, 240);

        g.dispose();
        bs.show();

    }
    public static void main(String args[]){
        new Game();

    }
}
