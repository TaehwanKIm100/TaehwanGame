package com.example.taehwan.taehwangame;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.taehwan.taehwangame.Controller.JumpButtonOnClickListener;
import com.example.taehwan.taehwangame.View.GameView;

import info.hoang8f.widget.FButton;

public class Main2Activity extends Activity {
    private FButton jumpButton;
    private JumpButtonOnClickListener jumpButtonOnClickListener;
    private GameView gameView;
    private Handler scoreHandler, maxScoreHandler, currenScoreHandler;
    private Context context;
    private TextView maxScore;
    private TextView currentScore;
    private int max;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
    }

    @Override
    protected void onResume() {
        super.onResume();
        setContentView(R.layout.activity_main2);
        max = 0;
        gameView = (GameView)findViewById(R.id.gameView);
        jumpButtonOnClickListener = new JumpButtonOnClickListener(gameView);
        jumpButton = (FButton)findViewById(R.id.jumpButton);
        jumpButton.setOnClickListener(jumpButtonOnClickListener);

        scoreHandler = new Handler(){
            public void handleMessage(Message message){
                Intent intent = new Intent(context, Main3Activity.class);
                intent.putExtra("Score",message.obj.toString());
                startActivity(intent);
            }
        };
        gameView.setFinishHandler(scoreHandler);
        currentScore = (TextView)findViewById(R.id.currentScore);
        maxScore = (TextView)findViewById(R.id.maxScore);

        currentScore.setText("Current Score : "+ "0");
        maxScore.setText("Max Score : " + max);

        currenScoreHandler = new Handler(){
            public void handleMessage(Message message){
                currentScore.setText("Current Score : " + message.obj.toString());
                if(Integer.parseInt(message.obj.toString()) > max){
                    Message newMessage = new Message();
                    newMessage.obj = message.obj;
                    maxScoreHandler.sendMessage(newMessage);

                }
            }
        };
        maxScoreHandler = new Handler(){
           public void handleMessage(Message message){
                maxScore.setText("Max Score : " + message.obj.toString());
           }
        };

        gameView.setCurrentHandler(currenScoreHandler);
        gameView.setMaxHandler(maxScoreHandler);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
