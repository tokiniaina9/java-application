/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mg.toky.projet3D.main.game;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import mg.toky.projet3D.main.game.blocks.Block;
import mg.toky.projet3D.main.game.blocks.SolidBlock;
import mg.toky.projet3D.main.render.Renderer;
import mg.toky.projet3D.main.render.Texture;
import mg.toky.projet3D.math.Vector3f;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.glEnd;

/**
 *
 * @author Toky
 */
public class Level {

    int renderignList;

    private int width, height;
    Block[] blocks;

    public Level(String level) {
        compile(level);
    }

    public void compile(String level) {
        BufferedImage map = null;
        try {
            map = ImageIO.read(Level.class.getResource("/mg/toky/projet3D/model/maodely/" + level + ".png"));
        } catch (IOException ex) {
            Logger.getLogger(Level.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        width = map.getWidth();
        height = map.getHeight();
        int[] pixles = new int[width * height];
        map.getRGB(0, 0, width, height, pixles, 0, width);

        blocks = new Block[pixles.length];

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int i = x + y * width;
                if (pixles[i] == 0xFF000000) {
                    blocks[i] = new Block(x, y);
                }

                if (pixles[i] == 0xFFffffff) {
                    blocks[i] = new SolidBlock(x, y);
                }
            }
        }

        compileRendering();
    }

    private void compileRendering() {
        renderignList = glGenLists(1);

        glNewList(renderignList, GL_COMPILE);
        glBegin(GL_QUADS);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Block block = getBlock(x, y);

                if (!block.isSolid()) {
                    Renderer.setFloorData(x, y, new Vector3f(1, 1, 1));
                    Renderer.setCeilingData(x, y, new Vector3f(1, 1, 1));
                }

                Block left = getBlock(x + 1, y);
                Block down = getBlock(x, y + 1);

                if (block.isSolid()) {
                    if (!left.isSolid()) {
                        Renderer.setWallData(x + 1, y + 1, x + 1, y, new Vector3f(0.9f, 0.9f, 0.9f));
                    }

                    if (!down.isSolid()) {
                        Renderer.setWallData(x, y + 1, x + 1, y + 1, new Vector3f(0.8f, 0.8f, 0.8f));
                    }
                } else {
                    if (left.isSolid()) {
                        Renderer.setWallData(x + 1, y, x + 1, y + 1, new Vector3f(0.9f, 0.9f, 0.9f));
                    }

                    if (down.isSolid()) {
                        Renderer.setWallData(x + 1, y + 1, x, y + 1, new Vector3f(0.8f, 0.8f, 0.8f));
                    }
                }
            }
        }
        glEnd();
        glEndList();
    }

    public Block getBlock(int x, int y) {
        if (x < 0 || y < 0 || x >= width || y >= height) {
            return new SolidBlock(x, y);
        }

        return blocks[x + y * width];
    }

    public void update() {

    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    
    public void render() {
        Texture.teste.bind();
        glCallList(renderignList);
    }
}
