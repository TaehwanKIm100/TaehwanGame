package com.example.taehwan.taehwangame;

import android.graphics.Bitmap;

import java.util.ArrayList;

/**
 * Created by Taehwan on 2015-12-13.
 */
public class HeartMakeThread extends Thread {
    private int numOfBugs;
    private int term;
    private ArrayList<GameObject> gameObjects;
    private Bitmap bitmap;
    private int width;
    private int height;
    private double velocity;

    public HeartMakeThread(ArrayList<GameObject> gameObjects, int numOfBugs, int term, Bitmap bitmap, int width, int height, int velocity){
        this.numOfBugs = numOfBugs;
        this.term = term;
        this.gameObjects = gameObjects;
        this.width = width;
        this.height = height;
        this. velocity = velocity;
        this.bitmap = bitmap;
    }
    @Override
    public void run() {
        int count = 0;
            synchronized (this) {
                while(count < numOfBugs){
                try {
                    this.wait((long) ((Math.random() * term) + term));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (count < numOfBugs) {
                    this.gameObjects.add(new HeartObject(bitmap, width, height, velocity));
                    count++;
                }
            }
                this.interrupt();
        }
    }
}
