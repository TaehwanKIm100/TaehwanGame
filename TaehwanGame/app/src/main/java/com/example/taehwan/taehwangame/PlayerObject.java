package com.example.taehwan.taehwangame;

import android.graphics.Bitmap;

/**
 * Created by Taehwan on 2015-12-13.
 */
public class PlayerObject extends GameObject {
    private final int DEFAULT_SIZE = 120;

    public PlayerObject(Bitmap bitmap, int width, int height) {
        this.size = DEFAULT_SIZE;
        this.width = width;
        this.height = height;
        this.bitmap = bitmap;
        coordinate = new Coordinate(200, (height / 2) - (size / 2));
        this.movement = new PlayerMovement(0);
    }

    public void jump(){
        movement.jump();
    }
}
