package com.example.taehwan.taehwangame.Controller;


import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.example.taehwan.taehwangame.Main2Activity;

/**
 * Created by Taehwan on 2015-12-12.
 */
public class StartButtonOnClickListener implements View.OnClickListener{

    private Context context;

    public StartButtonOnClickListener(Context context){
        this.context = context;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(context, Main2Activity.class);
        context.startActivity(intent);
    }
}
