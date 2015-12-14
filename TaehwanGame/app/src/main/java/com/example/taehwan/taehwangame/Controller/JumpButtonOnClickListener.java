package com.example.taehwan.taehwangame.Controller;

import android.view.View;

import com.example.taehwan.taehwangame.View.GameView;

/**
 * Created by Taehwan on 2015-12-13.
 */
public class JumpButtonOnClickListener implements View.OnClickListener{
    private GameView gameView;

    public JumpButtonOnClickListener(GameView gameView){
        this.gameView = gameView;
    }

    @Override
    public void onClick(View v) {
        gameView.jump();
    }
}
