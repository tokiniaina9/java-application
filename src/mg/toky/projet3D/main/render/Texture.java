/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mg.toky.projet3D.main.render;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.IntBuffer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import org.lwjgl.BufferUtils;
import static org.lwjgl.opengl.GL11.*;

/**
 *
 * @author Toky
 */
public class Texture {

    public static Texture teste = new Texture("biriky.jpg", GL_NEAREST);
    public static Texture bas = new Texture("Wood texture.jpg", GL_NEAREST);
    
    private int id;
    private int width, height;

    public Texture(String path, int filter) {
        int pixels[] = null;
        try {
            BufferedImage image = ImageIO.read(Texture.class.getResource("/mg/toky/projet3D/model/textures/" + path));
            width = image.getWidth();
            height = image.getHeight();
            pixels = new int[width * height];
            image.getRGB(0, 0, width, height, pixels, 0, width);
        } catch (IOException ex) {
            Logger.getLogger(Texture.class.getName()).log(Level.SEVERE, null, ex);
        }

        int[] data = new int[width * height];
        for (int i = 0; i < data.length; i++) {
            int a = (pixels[i] & 0xff000000) >> 24;
            int r = (pixels[i] & 0xff0000) >> 16;
            int g = (pixels[i] & 0xff00) >> 8;
            int b = (pixels[i] & 0xff);

            data[i] = a << 24 | b << 16 | g << 8 | r;
        }

        int id = glGenTextures();
        glBindTexture(GL_TEXTURE_2D, id);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, filter);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, filter);

        /**
         * créer un buffer car en gros openGl lis seulement les buffer au lieu
         * de int directement (il y a les IntBuffer,ByteBuffer,floatBUffer) et
         * que ça sert à placer les donnés, (placer une liste de int dans le
         * buffer)
         */
        IntBuffer buffer = BufferUtils.createIntBuffer(data.length);
        //mettre data dans le buffer
        buffer.put(data);
        //ordonné les données (int) àl'interieur
        buffer.flip();
        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width, height, 0, GL_RGBA, GL_UNSIGNED_BYTE, buffer);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    /**
     * ici on utilise la texture
     */
    public void bind(){
        glBindTexture(GL_TEXTURE_2D, id);
    }
}
