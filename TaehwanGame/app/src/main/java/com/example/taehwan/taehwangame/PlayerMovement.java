package com.example.taehwan.taehwangame;

/**
 * Created by Taehwan on 2015-12-13.
 */
public class PlayerMovement implements Movement{
    private final double accelate = 0.2;
    private double velocity;

    public PlayerMovement(double velocity){
        this.velocity = velocity;
    }
    @Override
    public void move(Coordinate coordinate, int width, int height, int size) {
        int tempY = coordinate.getY();
        tempY = tempY + velocity < 0 ? 0 : (int)(tempY + velocity);
        tempY = tempY + velocity + size > height ? height - size : (int)(tempY + velocity);
        coordinate.setY(tempY);

        velocity = velocity + accelate > 5 ? 5 : velocity + accelate;
        velocity = velocity + accelate < -5 ? -5 : velocity + accelate;
    }
    public void jump(){
        velocity = -5;
    }
}
