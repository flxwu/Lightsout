package com.jimdo.hanhan.lightsout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.jimdo.hanhan.lightsout.tools.CustomAlertBox;
import com.jimdo.hanhan.lightsout.tools.Level;

/**
 * Created by David Wu on 18.08.2016(c)
 */
public class WinScreen extends Activity {
    protected Level level;

    public WinScreen() {

    }

    public WinScreen(Level level) {
        this.level = level;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win_screen);


        RelativeLayout layout = (RelativeLayout) findViewById(R.id.layout);

        Button btnHome = (Button) findViewById(R.id.btnHome);
        Button btnNextLevel = (Button) findViewById(R.id.btnNextLevel);
        Button btnTryAgain = (Button) findViewById(R.id.btnTryAgain);

        btnHome.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Home.class);
                startActivity(intent);
            }
        });
        int i=getIntent().getExtras().getInt("level");
        if (i+1 <=MainLevel.MAX_LEVEL) {
            btnNextLevel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), MainLevel.class);
                    intent.putExtra("level", (getIntent().getIntExtra("level", 1)) + 1);
                    startActivity(intent);
                }
            });
            btnNextLevel.setVisibility(View.VISIBLE);
        } else {
            btnNextLevel.setVisibility(View.INVISIBLE);
        }

        btnTryAgain.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainLevel.class);
                intent.putExtra("level", getIntent().getIntExtra("level", 1));
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
    }
}
