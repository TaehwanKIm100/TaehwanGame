package com.example.taehwan.taehwangame;

import android.graphics.Bitmap;

/**
 * Created by Taehwan on 2015-12-12.
 */
public abstract class GameObject {

    protected int size;
    protected int xSpeed;
    protected int ySpeed;
    protected Movement movement;
    protected Bitmap bitmap;
    protected int width;
    protected int height;

    protected Coordinate coordinate;

    public void setSize(int size){
        this.size = size;
    }
    public void move(){
        movement.move(coordinate, width, height, size);
    };

    public void setBitmap(Bitmap bitmap){
        this.bitmap = bitmap;
    }

    public Bitmap getBitmap(){
        return this.bitmap;
    }

    public void setCoordinate(Coordinate coordinate){
        this.coordinate = coordinate;
    }

    public Coordinate getCoordinate(){
        return this.coordinate;
    }

    public void jump(){};

    public int getSize(){
        return size;
    }

    public boolean isCollided(GameObject gameObject){
        double distance = 0;
        distance = (coordinate.getX() - gameObject.getCoordinate().getX()) * (coordinate.getX() - gameObject.getCoordinate().getX());
        distance += (coordinate.getY() - gameObject.getCoordinate().getY()) * (coordinate.getY() - gameObject.getCoordinate().getY());
        distance = Math.sqrt(distance);

        if(distance < (size + gameObject.size) / 2)
            return true;
        return false;
    }
}
