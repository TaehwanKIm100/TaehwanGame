package com.example.taehwan.taehwangame.View;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;

import com.example.taehwan.taehwangame.BugsMakeThread;
import com.example.taehwan.taehwangame.BugsObject;
import com.example.taehwan.taehwangame.GameObject;
import com.example.taehwan.taehwangame.HeartMakeThread;
import com.example.taehwan.taehwangame.HeartObject;
import com.example.taehwan.taehwangame.PlayerObject;
import com.example.taehwan.taehwangame.R;

import java.util.ArrayList;
/**
 * Created by Taehwan on 2015-12-12.
 */
public class GameView extends View {
    private Context context;
    private int width, height, score;
    private ArrayList<GameObject> gameObjects;
    private Bitmap playerImage;
    private ArrayList<Bitmap> bugImages;
    private Bitmap heartImage;
    private GameObject tempObject;
    private BugsMakeThread bugsMakeThread;
    private HeartMakeThread heartMakeThread;
    private Handler handler;
    private Handler currentHandler;
    private Handler maxHandler;

    private boolean isFinished;
    private boolean stageChanged;
    private int numOfBugs;
    private int bugTerm;
    private double velocity;
    public GameView(Context context){
        this(context, null);
    }
    public GameView(Context context, AttributeSet attrs){
        super(context, attrs);
        init();
    }

    public void init(){
        gameObjects = new ArrayList<GameObject>();
        bugImages = new ArrayList<Bitmap>();
        stageChanged = true;
        isFinished = false;
        numOfBugs = 30;
        velocity = -5;
        bugTerm = 1000;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
        playerImage = BitmapFactory.decodeResource(getResources(), R.drawable.seolhyun);
        playerImage = Bitmap.createScaledBitmap(playerImage, 120, 120, true);
        playerImage = getRoundBitmap(playerImage);
        Bitmap bugImage_1 = BitmapFactory.decodeResource(getResources(), R.drawable.taehwanbug);
        bugImage_1 = Bitmap.createScaledBitmap(bugImage_1, 100, 100, true);
        bugImage_1 = getRoundBitmap(bugImage_1);
        bugImages.add(bugImage_1);
        heartImage = BitmapFactory.decodeResource(getResources(), R.drawable.heart);
        heartImage = Bitmap.createScaledBitmap(heartImage, 100, 100 ,true);
        heartImage = getRoundBitmap(heartImage);
        gameObjects.add(new PlayerObject(playerImage, width, height));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if(!isFinished) {
            for (int i = 0; i < gameObjects.size(); i++) {
                tempObject = gameObjects.get(i);
                canvas.drawBitmap(tempObject.getBitmap(), tempObject.getCoordinate().getX(), tempObject.getCoordinate().getY(), null);
            }

            for (int i = 0; i < gameObjects.size(); i++) {
                gameObjects.get(i).move();
            }

            if (stageChanged) {
                synchronized (gameObjects) {
                    bugsMakeThread = new BugsMakeThread(gameObjects, numOfBugs, bugTerm, bugImages.get(0), width, height, velocity);
                    bugsMakeThread.start();

                    heartMakeThread = new HeartMakeThread(gameObjects, 10, (numOfBugs * bugTerm) / 10, heartImage, width, height, -5);
                    heartMakeThread.start();
                    stageChanged = false;
                }
            }

            synchronized (gameObjects) {
                gameObjects.notifyAll();
            }

            for (int i = 1; i < gameObjects.size(); i++) {
                if (gameObjects.get(0).isCollided(gameObjects.get(i))) {
                    if (gameObjects.get(i) instanceof BugsObject) {
                        Message message = new Message();
                        message.obj = score;
                        handler.sendMessage(message);
                        isFinished = true;
                        return;
                    } else if (gameObjects.get(i) instanceof HeartObject) {
                        score += 20;
                        gameObjects.remove(i--);
                        Message message = new Message();
                        message.obj = score;
                        currentHandler.sendMessage(message);
                    }
                }
            }

            for (int i = 1; i < gameObjects.size(); i++) {
                if (gameObjects.get(i).getCoordinate().getX() + gameObjects.get(i).getSize() / 2 < 0) {
                    if(gameObjects.get(i) instanceof BugsObject) {
                        gameObjects.remove(i--);
                        score++;
                    }
                    else if(gameObjects.get(i) instanceof HeartObject){
                        gameObjects.remove(i--);
                        score -= 30;
                    }
                    Message message = new Message();
                    message.obj = score;
                    currentHandler.sendMessage(message);
                }
            }

            if (gameObjects.size() == 1) {
                stageChanged = true;
                bugTerm = Math.max(500, bugTerm - 25);
                numOfBugs = Math.min(500, numOfBugs + 10);
                velocity = Math.max(-10, velocity - 1);
            }

            invalidate();
        }
    }
    public void jump(){
        gameObjects.get(0).jump();
    }

    private Bitmap getRoundBitmap(Bitmap bitmap){
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),bitmap.getHeight(),Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        Paint paint = new Paint();
        Rect rect = new Rect(0,0,bitmap.getWidth(),bitmap.getHeight());
        RectF rectF = new RectF(rect);
        paint.setAntiAlias(true);
        canvas.drawOval(rectF, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap,rect,rect,paint);
        bitmap.recycle();
        return output;
    }

    public void setFinishHandler(Handler handler){
        this.handler = handler;
    }
    public void setCurrentHandler(Handler handler){this.currentHandler = handler;};
    public void setMaxHandler(Handler handler){this.maxHandler = handler;}
}
