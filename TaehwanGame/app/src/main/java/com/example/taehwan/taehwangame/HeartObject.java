package com.example.taehwan.taehwangame;

import android.graphics.Bitmap;

/**
 * Created by Taehwan on 2015-12-13.
 */
public class HeartObject extends GameObject {
    private final int DEFAULT_SIZE = 100;
    private final double velocity = -5;

    public HeartObject(Bitmap bitmap, int width, int height, double velocity) {
        this.size = DEFAULT_SIZE;
        this.width = width;
        this.height = height;
        this.bitmap = bitmap;
        coordinate = new Coordinate(width - size, Math.min((int)(Math.random() * height), height - size));
        this.movement = new BugsHorizontalMovement(velocity);
    }

    public void jump(){
        movement.jump();
    }
}


