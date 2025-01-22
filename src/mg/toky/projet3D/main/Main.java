/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mg.toky.projet3D.main;

import java.util.logging.Level;
import java.util.logging.Logger;
import mg.toky.projet3D.main.game.Game;
import mg.toky.projet3D.math.Vector3f;
import mg.toky.projet3D.main.render.Camera;
import mg.toky.projet3D.main.render.DisplayManager;
import mg.toky.projet3D.main.render.Renderer;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import static org.lwjgl.opengl.GL11.*;

/**
 *
 * @author Toky
 */
public class Main {

    public static final float FRAM_CAP = 60;
    boolean running = false;
    
    
    Camera cam = null;
    Game game;

    public Main() {
        DisplayManager.create(720, 480, "Toky projet");
        cam = new Camera(new Vector3f(10, 1, 30));
        cam.setPerspectiveProjeciton(70.0f, 0.1f, 1000.0f);
        
        game = new Game();
    }

    public void start() {
        running = true;
        loop();
    }

    public void stop() {
        running = false;
    }

    public void loop() {
        long lastTickerTime = System.nanoTime();
        long lastRenderTime = System.nanoTime();

        double tickTime = 1000000000.0 / 60.0;
        double renderTime = 1000000000.0 / FRAM_CAP;

        int ticks = 0;
        int frames = 0;

        long timer = System.currentTimeMillis();

        while (running) {
            if (DisplayManager.isClosed()) {
                stop();
            }
            boolean rendered = false;
            if (System.nanoTime() - lastTickerTime > tickTime) {
                update();
                lastTickerTime += tickTime;
                ticks++;
            } else if (System.nanoTime() - lastRenderTime > renderTime) {
                render();
                rendered = true;
                DisplayManager.update();
                lastRenderTime += renderTime;
                frames++;
            } else {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println(ticks + " ticks et " + frames + " frams");
                ticks = 0;
                frames = 0;
            }
        }
        exit();
    }

    public void update() {
        //
        if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
            Mouse.setGrabbed(false);
        }
        if (Mouse.isButtonDown(0)) {
            Mouse.setGrabbed(true);
        }
        //si la souris n"est pas sur la fenetre
        if (!Mouse.isGrabbed()) {
            return;
        }

        cam.input(game.getLevel());
        game.update();
    }

    /**
     * 3--2 | | 0--1
     *
     */
    public void render() {
        if (Display.wasResized()) {
            glViewport(0, 0, Display.getWidth(), Display.getHeight());
        }

        DisplayManager.clearBuffers();
        cam.getPerspectiveProjeciton();
        cam.update();
        
        game.render();
    }

    public void exit() {
        DisplayManager.dispose();
        System.exit(0);
    }
    /**
     * @param args the command line arguments
     */

}
