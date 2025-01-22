/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mg.toky.projet3D.main.game;

import java.io.IOException;
import java.util.logging.Logger;
import mg.toky.projet3D.math.Vector3f;
import mg.toky.projet3D.model.Model;

/**
 *
 * @author Toky
 */
public class Game {
    Level level;
    Model model;
    public Game() {
        try {
            level = new Level("map");
            model = Model.getModel("C:\\Users\\Toky\\Documents\\NetBeansProjects\\projetM21\\src\\TABLE.obj");
        } catch (IOException ex) {
            Logger.getLogger(Game.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
    }

    public void update() {
        level.update();
    }

    public void render() {
        level.render(); 
        model.render(new Vector3f(10, 1, 30));
        //model.positionner(new Vector3f(10, 1, 30), new Vector3f(0, 90, 0));
    }

    public Level getLevel() {
        return level;
    }
    
    

}
