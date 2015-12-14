package com.example.taehwan.taehwangame;


/**
 * Created by Taehwan on 2015-12-13.
 */
public class BugsHorizontalMovement implements Movement {
    private double velocity;

    public BugsHorizontalMovement(double velocity){
        this.velocity = velocity;
    }

    @Override
    public void move(Coordinate coordinate, int width, int height, int size) {
        coordinate.setX((int)(coordinate.getX() + velocity));
    }

    @Override
    public void jump() {

    }
}
