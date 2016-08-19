package com.jimdo.hanhan.lightsout;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.jimdo.hanhan.lightsout.levels.Level01;
import com.jimdo.hanhan.lightsout.levels.Level02;
import com.jimdo.hanhan.lightsout.tools.Level;
import com.jimdo.hanhan.lightsout.tools.LightView;


/**
 * Created by David Wu on 13.08.2016.
 */

public class MainLevel extends Activity {
    protected LightView lightView;
    protected Button buttonUp, buttonDown, buttonRight, buttonLeft;
    protected Level level;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int level=getIntent().getExtras().getInt("level");
        switch (level) {
            case 1:
                this.level=new Level01();
                break;
            case 2:
                this.level=new Level02();
                break;
        }

        setContentView(R.layout.activity_level01);

        RelativeLayout layout = (RelativeLayout) findViewById(R.id.layout);
        buttonDown = (Button) findViewById(R.id.btnDown);
        buttonLeft = (Button) findViewById(R.id.btnLeft);
        buttonRight = (Button) findViewById(R.id.btnRight);
        buttonUp = (Button) findViewById(R.id.btnUp);

        /*
        * BUTTONLISTENERS
        *
        */

        buttonDown.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN || event.getAction() == MotionEvent.ACTION_MOVE) {
                    lightView.turnDown();
                    return true;
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    return true;
                } else {
                    return false;
                }
            }
        });

        buttonLeft.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN || event.getAction() == MotionEvent.ACTION_MOVE) {
                    lightView.turnLeft();
                    return true;
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    return true;
                } else {
                    return false;
                }
            }
        });

        buttonRight.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN || event.getAction() == MotionEvent.ACTION_MOVE) {
                    lightView.turnRight();
                    return true;
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    return true;
                } else {
                    return false;
                }
            }
        });

        buttonUp.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN || event.getAction() == MotionEvent.ACTION_MOVE) {
                    lightView.turnUp();
                    return true;
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    return true;
                } else {
                    return false;
                }
            }
        });


        lightView = new LightView(this,this.level);
        layout.addView(lightView);
    }


    /*
    Move lightView Events
     */
    public void turnUp(View view) {
        lightView.turnUp();
    }

    public void turnDown(View view) {
        lightView.turnDown();
    }

    public void turnRight(View view) {
        lightView.turnRight();
    }

    public void turnLeft(View view) {
        lightView.turnLeft();
    }
}

