/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mg.toky.projet3D.math;

/**
 *
 * @author Toky
 */
public class Vector3f {

    private float x, y, z;

    public Vector3f(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3f(Vector3f vec) {
        this.x = vec.x;
        this.y = vec.y;
        this.z = vec.z;
    }

    public Vector3f() {
        this.x = 0;
        this.y = 0;
        this.z = 0;
    }

    public Vector3f add(Vector3f vec) {
        x += vec.getX();
        y += vec.getY();
        z += vec.getZ();
        return this;
    }

    public Vector3f addX(float v) {
        x += v;
        return this;
    }

    public Vector3f addY(float v) {
        y += v;
        return this;
    }

    public Vector3f addZ(float v) {
        z += v;
        return this;
    }

    public Vector3f subX(float v) {
        x -= v;
        return this;
    }

    public Vector3f subY(float v) {
        y -= v;
        return this;
    }

    public Vector3f subZ(float v) {
        z -= v;
        return this;
    }

    public float length() {
        return (float) Math.sqrt(x * x + y * y + z * z);
    }

    public Vector3f normalize() {
        x /= length();
        y /= length();
        z /= length();
        return this;
    }

    public Vector3f sub(Vector3f vec) {
        x -= vec.getX();
        y -= vec.getY();
        z -= vec.getZ();
        return this;
    }

    public Vector3f mult(Vector3f vec) {
        x *= vec.getX();
        y *= vec.getY();
        z *= vec.getZ();
        return this;
    }

    public Vector3f div(Vector3f vec) {
        x /= vec.getX();
        y /= vec.getY();
        z /= vec.getZ();
        return this;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getZ() {
        return z;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setZ(float z) {
        this.z = z;
    }

}
