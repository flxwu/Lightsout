package com.jimdo.hanhan.lightsout;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.jimdo.hanhan.lightsout.levels.Level01;
import com.jimdo.hanhan.lightsout.tools.Level;
import com.jimdo.hanhan.lightsout.tools.LightView;


/**
 * Created by David Wu on 13.08.2016.
 */

public class MainLevel extends AppCompatActivity {
    protected LightView lightView;
    protected Button buttonUp, buttonDown, buttonRight, buttonLeft;
    protected Level level=new Level01();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
                    lightView.moveDown();
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
                    lightView.moveLeft();
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
                    lightView.moveRight();
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
                    lightView.moveUp();
                    return true;
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    return true;
                } else {
                    return false;
                }
            }
        });


        lightView = new LightView(this,level);
        layout.addView(lightView);
    }


    /*
    Move lightView Events
     */
    public void moveUp(View view) {
        lightView.moveUp();
    }

    public void moveDown(View view) {
        lightView.moveDown();
    }

    public void moveRight(View view) {
        lightView.moveRight();
    }

    public void moveLeft(View view) {
        lightView.moveLeft();
    }
}

