package com.example.taehwan.taehwangame;

/**
 * Created by Taehwan on 2015-12-13.
 */
public class Coordinate {
    private int x;
    private int y;

    public Coordinate(){
    }

    public Coordinate(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setX(int x){
        this.x = x;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
