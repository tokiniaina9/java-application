/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mg.toky.projet3D.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static org.lwjgl.opengl.GL11.*;
import mg.toky.projet3D.math.Vector3f;

/**
 *
 * @author Toky
 */
public class Model {

    public List<Vector3f> verts = new ArrayList<Vector3f>();
    public List<Vector3f> norms = new ArrayList<Vector3f>();
    public List<Face> faces = new ArrayList<Face>();

    public static Model getModel(String s) throws IOException {
        return new Model(s);
    }

    public Model(String path) throws IOException {
        verts = new ArrayList<Vector3f>();
        norms = new ArrayList<Vector3f>();
        faces = new ArrayList<Face>();
        new ModelLoader(this, path);
    }

    public void render(Vector3f position) {
        glPolygonMode(GL_FRONT_AND_BACK, GL_AUTO_NORMAL);
        glBegin(GL_TRIANGLES);
        for (Face f : faces) {
            Vector3f v1 = verts.get((int) f.verts.getX() - 1);
            Vector3f n1 = verts.get((int) f.norms.getX() - 1);
            Vector3f v2 = verts.get((int) f.verts.getY() - 1);
            Vector3f n2 = verts.get((int) f.norms.getY() - 1);
            Vector3f v3 = verts.get((int) f.verts.getZ() - 1);
            Vector3f n3 = verts.get((int) f.norms.getZ() - 1);

            glNormal3f(n1.getX() + position.getX(), n1.getY() + position.getY(), n1.getZ() + position.getZ());
            glVertex3f(v1.getX() + position.getX(), v1.getY() + position.getY(), v1.getZ() + position.getZ());
            glNormal3f(n2.getX() + position.getX(), n2.getY() + position.getY(), n2.getZ() + position.getZ());
            glVertex3f(v2.getX() + position.getX(), v2.getY() + position.getY(), v2.getZ() + position.getZ());
            glNormal3f(n3.getX() + position.getX(), n3.getY() + position.getY(), n3.getZ() + position.getZ());
            glVertex3f(v3.getX() + position.getX(), v3.getY() + position.getY(), v3.getZ() + position.getZ());
        }
        glEnd();
    }

    public void positionner(Vector3f position, Vector3f rotation) {
        glPushAttrib(GL_TRANSFORM_BIT);
        glRotatef(rotation.getX(), 1, 0, 0);
        glRotatef(rotation.getY(), 0, 1, 0);
        glRotatef(rotation.getZ(), 0, 0, 1);
        render(position);
        glPopAttrib();
    }
}
