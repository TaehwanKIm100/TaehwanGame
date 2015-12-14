package com.example.taehwan.taehwangame;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.taehwan.taehwangame.Controller.StartButtonOnClickListener;

import info.hoang8f.widget.FButton;

public class MainActivity extends Activity {

    private FButton startButton;
    private StartButtonOnClickListener startButtonOnClickListener;
    private Typeface milkywayTypeface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
        milkywayTypeface = Typeface.createFromAsset(this.getAssets(),"milkyway.TTF");
        TextView textView = (TextView)findViewById(R.id.mainText);
        textView.setTypeface(milkywayTypeface);
        startButton = (FButton)findViewById(R.id.startButton);
        startButtonOnClickListener = new StartButtonOnClickListener(this);
        startButton.setOnClickListener(startButtonOnClickListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
