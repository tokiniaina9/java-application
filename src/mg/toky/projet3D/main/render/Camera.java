/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mg.toky.projet3D.main.render;

import mg.toky.projet3D.main.game.Level;
import mg.toky.projet3D.main.game.blocks.Block;
import mg.toky.projet3D.math.Vector3f;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.util.glu.GLU;

/**
 *
 * @author Toky
 */
public class Camera {

    private float fov;
    private float zMin;
    private float zMax;

    private Block bloc_a,bloc_d,bloc_r,bloc_l;

    private Vector3f last_position;

    private Vector3f position;
    private Vector3f rotation;

    public void setPosition(Vector3f position) {
        this.position = position;
    }

    public Vector3f getPosition() {
        return position;
    }

    public Camera(Vector3f position) {
        this.position = position;
        this.last_position = position;
        this.rotation = new Vector3f(0, 135, 0);
    }

    public Camera setPerspectiveProjeciton(float fov, float zMin, float zMax) {
        this.zMax = zMax;
        this.zMin = zMin;
        this.fov = fov;
        return this;
    }

    public Vector3f getForward() {
        Vector3f r = new Vector3f();
        Vector3f rot = new Vector3f(rotation);

        float cosY = (float) Math.cos(Math.toRadians(rot.getY() + 90));
        float sinY = (float) Math.sin(Math.toRadians(rot.getY() + 90));
        float cosP = (float) Math.cos(Math.toRadians(rot.getX()));
        float sinP = (float) Math.sin(Math.toRadians(rot.getX()));

        r.setX(cosY * cosP);
        r.setY(sinP);
        r.setZ(sinY * cosP);
        return r;
    }

    public Vector3f getDirection() {
        Vector3f r = new Vector3f();
        Vector3f rot = new Vector3f(rotation);

        float cosY = (float) Math.cos(Math.toRadians(rot.getY()));
        float sinY = (float) Math.sin(Math.toRadians(rot.getY()));
        r.setX(cosY);
        r.setZ(sinY);
        return r;
    }

    public void update() {
        glPushAttrib(GL_TRANSFORM_BIT);
        glRotatef(rotation.getX(), 1, 0, 0);
        glRotatef(rotation.getY(), 0, 1, 0);
        glRotatef(rotation.getZ(), 0, 0, 1);
        glTranslatef(-position.getX(), -position.getY(), -position.getZ());
        glPopMatrix();
    }

    float speed = 0.1f;

    public void input(Level level) {
        //ajouter la vitesse du souris getDY
        rotation.addX(-Mouse.getDY());
        rotation.addY(Mouse.getDX());

        if (rotation.getX() > 90) {
            rotation.setX(90);
        }
        if (rotation.getX() < -60) {
            rotation.setX(-60);
        }

        if (position.getY() > 2f) {
            position.setY(2f);
        }
        if (position.getY() < 1.2f) {
            position.setY(1.2f);
        }

        /**
         * idee teste contre mur
         *
         */
        //bloc = level.getBlock((int) position.getX(), (int) position.getZ());
        int xl = (int) position.getX() + 1;
        int xr = (int) position.getX() - 1;
        int xn  = (int) position.getX();
        int za = (int) position.getZ() - 1;
        int zd = (int) position.getZ() + 1;
        int zn = (int) position.getZ();
        bloc_a = level.getBlock(xn, za);
        bloc_d =  level.getBlock(xn, zd);
        bloc_l =  level.getBlock(xl, zn);
        bloc_r =  level.getBlock(xr, zn);
        if (bloc_a.isSolid() || bloc_d.isSolid() || bloc_l.isSolid() || bloc_r.isSolid()) {
            position = new Vector3f(last_position);
        } else {
            last_position = new Vector3f(position);
        }
        /**
         * idee teste contre mur
         *
         */

        if (Keyboard.isKeyDown(Keyboard.KEY_Z)) {
            position.add(getForward().mult(new Vector3f(-speed, -speed, -speed)));
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
            position.add(getForward().mult(new Vector3f(speed, speed, speed)));
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_Q)) {
            position.add(getDirection().mult(new Vector3f(-speed, -speed, -speed)));
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
            position.add(getDirection().mult(new Vector3f(speed, speed, speed)));
        }

        if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
            position.add(new Vector3f(0, speed, 0));
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_LCONTROL)) {
            position.add(new Vector3f(0, -speed, 0));
        }
    }

    public void getPerspectiveProjeciton() {
        glEnable(GL_PROJECTION);
        glLoadIdentity();
        GLU.gluPerspective(fov, (float) Display.getWidth() / (float) Display.getHeight(), zMin, zMax);
        glEnable(GL_MODELVIEW);
    }
}
