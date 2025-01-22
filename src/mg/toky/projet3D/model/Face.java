/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mg.toky.projet3D.model;
import mg.toky.projet3D.math.Vector3f;

/**
 *
 * @author Toky
 */
public class Face {
    public Vector3f norms, verts; 

    public Face(Vector3f norms, Vector3f verts) {
        this.norms = norms;
        this.verts = verts;
    }
    
    
}
