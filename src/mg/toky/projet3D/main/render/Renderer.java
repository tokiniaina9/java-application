/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mg.toky.projet3D.main.render;

import mg.toky.projet3D.math.Vector3f;
import static org.lwjgl.opengl.GL11.*;

/**
 *
 * @author Toky
 */
public class Renderer {

    public static void setFloorData(float x, float z, Vector3f color) {
        glColor3f(color.getX(), color.getY(), color.getZ());

        //gltexCoord : coordonnés de la texture
//        glTexCoord2f(0, 0); glVertex3f(x + 1, 0, z);
//        glTexCoord2f(1, 0); glVertex3f(x, 0, z);
//        glTexCoord2f(1, 1); glVertex3f(x, 0, z + 1);
//        glTexCoord2f(0, 1); glVertex3f(x + 1, 0, z + 1);
        
        glVertex3f(x + 1, 0, z);
        glVertex3f(x, 0, z);
        glVertex3f(x, 0, z + 1);
        glVertex3f(x + 1, 0, z + 1);
    }

    public static void setCeilingData(float x, float z, Vector3f color) {
        glColor3f(color.getX(), color.getY(), color.getZ());
        //gltexCoord : coordonnés de la texture
        glVertex3f(x, 3, z);
        glVertex3f(x + 1, 3, z);
        glVertex3f(x + 1, 3, z + 1);
        glVertex3f(x, 3, z + 1);
    }
    
    public static void setWallData(float x0, float z0,float x1, float z1, Vector3f color){
        glColor3f(color.getX(), color.getY(), color.getZ());
        //gltexCoord : coordonnés de la texture
        glTexCoord2f(0, 0); glVertex3f(x0, 0, z0);
        glTexCoord2f(1, 0); glVertex3f(x1, 0, z1);
        glTexCoord2f(1, 1); glVertex3f(x1, 3, z1);
        glTexCoord2f(0, 1); glVertex3f(x0, 3, z0);
    }

}
