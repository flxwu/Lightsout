package com.jimdo.hanhan.lightsout;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jimdo.hanhan.lightsout.tools.Level;

/**
 * Created by David Wu on 18.08.2016(c)
 *
 */

public class LoseScreen extends Activity {
    protected Level level;
    protected int levelInt;

    public LoseScreen() {
    }
    public LoseScreen(int levelInt) {
        this.levelInt=levelInt;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lose_screen);


        RelativeLayout layout = (RelativeLayout) findViewById(R.id.layout);

        Button btnTryAgain = (Button) findViewById(R.id.btnTryAgain);
        Button btnHome = (Button) findViewById(R.id.btnHome);

        btnTryAgain.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainLevel.class);
                intent.putExtra("level",levelInt);
                startActivity(intent);
            }
        });

        btnHome.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Home.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
    }
}
