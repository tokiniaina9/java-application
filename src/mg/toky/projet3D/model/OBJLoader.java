/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mg.toky.projet3D.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import  mg.toky.projet3D.math.Vector3f;

/**
 *
 * @author Toky
 */
public class OBJLoader {

    public static Model loadModel(File f) throws FileNotFoundException, IOException {
        BufferedReader reader = new BufferedReader(
                new FileReader(f));
        Model model = new Model("");
        String line;
        while ((line = reader.readLine()) != null) {
            if (line.startsWith("v ")) {
                float x = Float.valueOf(line.split(" ")[1]);
                float y = Float.valueOf(line.split(" ")[2]);
                float z = Float.valueOf(line.split(" ")[3]);
                model.verts.add(new Vector3f(x, y, z));
            } else if (line.startsWith("vn ")) {
                float x = Float.valueOf(line.split(" ")[1]);
                float y = Float.valueOf(line.split(" ")[2]);
                float z = Float.valueOf(line.split(" ")[3]);
                model.norms.add(new Vector3f(x, y, z));
            } else if (line.startsWith("f ")) {
                Vector3f vi = new Vector3f(Float.valueOf(line.split(" ")[0].split("/")[0]),
                        Float.valueOf(line.split(" ")[1].split("/")[0]),
                        Float.valueOf(line.split(" ")[2].split("/")[0]));
                Vector3f ni = new Vector3f(Float.valueOf(line.split(" ")[0].split("/")[2]),
                        Float.valueOf(line.split(" ")[1].split("/")[2]),
                        Float.valueOf(line.split(" ")[2].split("/")[2]));
                model.faces.add(new Face(vi, ni));
            }
        }
        return model;
    }

}
