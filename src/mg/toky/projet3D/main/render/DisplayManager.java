/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mg.toky.projet3D.main.render;

import java.nio.FloatBuffer;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import static org.lwjgl.opengl.GL11.*;

/**
 *
 * @author Toky
 */
public class DisplayManager {
    public static void create(int width,int height, String title){
        try {
            Display.setDisplayMode(new DisplayMode(width, height));
            Display.setTitle(title); 
            Display.setResizable(true);
            Display.create();
            
            //une maniere pour evité de voir l"autre face à travers de l'autre face            
            glEnable(GL_DEPTH_TEST);

            //active la teste de la face interne pour qu'il ne se vois pas de l'exterieur
            glEnable(GL_CULL_FACE);
            
            //activer l'ombre ou noircie au loin comme un ombre
            glEnable(GL_FOG);
            
            //activer la texture
            glEnable(GL_TEXTURE_2D);
            
            FloatBuffer fogColor = (FloatBuffer) BufferUtils.createFloatBuffer(4);
            //vision de loin comme un ombre 
            fogColor.put(new float[]{
               0,0,0,1 //couleur noir 0,0,0 avec alpha 1
            });
            fogColor.flip();
            glFogi(GL_FOG_MODE, GL_EXP);
            //densité 0.2f pour être assez claire mais on peut mettre à 0.9f pour qu'il soit plus sombre
            glFogf(GL_FOG_DENSITY, 0.6f);
            glFog(GL_FOG_COLOR, fogColor);
            
        } catch (LWJGLException ex) {
            Logger.getLogger(DisplayManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void update(){
        Display.update();
    }
    
    public static void clearBuffers(){
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    }
    
    public static boolean isClosed(){
        return Display.isCloseRequested();
    }
    
    public static void dispose(){
        Display.destroy();
    }
}
