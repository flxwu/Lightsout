package com.jimdo.hanhan.lightsout.tools;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.GridLayout;

import com.jimdo.hanhan.lightsout.LoseScreen;
import com.jimdo.hanhan.lightsout.MainLevel;
import com.jimdo.hanhan.lightsout.WinScreen;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by David Wu on 15.08.2016(c)
 */
public class LightView extends View {
    Paint paint = new Paint();
    protected float centerX = 20, centerY = 200, rad = 150, angle = 90, startangle = 315;
    protected int position_x, position_y;
    protected GridLayout.LayoutParams params;
    protected Canvas canvas;
    protected Level level;
    protected Rect player = new Rect(35, 195, 45, 205);
    protected MainLevel activity;
    protected Timer timer = new Timer();
    protected WindowManager window;
    protected boolean isFirst = true;
    public int direction = DIRECTION_RIGHT;
    public final static int DIRECTION_LEFT = 1, DIRECTION_RIGHT = 2, DIRECTION_UP = 3, DIRECTION_DOWN = 4;

    public LightView(MainLevel activity, Level level, WindowManager window) {
        super(activity);
        this.activity = activity;
        this.level = level;
        this.window = window;
        paint.setColor(Color.argb(100, 255, 255, 255));
    }

    /*
    DRAWING METHODS
     */

    @Override
    public void onDraw(final Canvas canvas) {
        super.onDraw(canvas);
        drawCredit(canvas);
        drawLightCone(canvas, centerX, centerY, rad);
        drawWall(canvas);
        drawGoal(canvas);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    protected void drawLightCone(Canvas canvas, float centerX, float centerY, float rad) {
        float left = centerX - rad;
        float top = centerY - rad;
        float right = centerX + rad;
        float bottom = centerY + rad;
        paint.setColor(Color.RED);
        player = new Rect((int) centerX - 5, (int) centerY - 5, (int) centerX + 5, (int) centerY + 5);
        canvas.drawRect(player, paint);
        paint.setColor(Color.WHITE);
        paint.setAlpha(100);
        canvas.drawArc(left, top, right, bottom, startangle, angle, true, paint);
    }

    protected void drawWall(Canvas canvas) {
        paint.setColor(Color.rgb(3, 0, 0));
        ArrayList<Wall> wallArrayList = level.getWalls();
        for (int i = 0; i < wallArrayList.size(); i++) {
            canvas.drawRect(wallArrayList.get(i).startcoords.x, wallArrayList.get(i).startcoords.y, wallArrayList.get(i).endcoords.x, wallArrayList.get(i).endcoords.y, paint);
        }
    }

    protected void drawGoal(Canvas canvas) {
        paint.setColor(Color.rgb(50, 0, 0));
        paint.setAlpha(50);
        canvas.drawRect(level.getGoal(), paint);
    }

    protected void drawCredit(Canvas canvas) {
        paint.setColor(Color.RED);
        canvas.drawText("Lightsout by David Wu", 2, 10, paint);
    }

    //--------------------------------------------
    /*
    Win/Lose Checks&Methods
     */
    protected boolean isCollision() {
        ArrayList<Wall> wallArrayList = level.getWalls();

        for (int i = 0; i < wallArrayList.size(); i++) {
            if (wallArrayList.get(i).getRect().intersect(player)) {
                return true;
            } else if (player.intersect(wallArrayList.get(i).getRect())) {
                return true;
            }
        }
        return false;
    }

    protected boolean isOutOfBorder() {
        //get root display edges
        Display display = window.getDefaultDisplay();
        Point displaySize = new Point();
        display.getSize(displaySize);
        int maxX = displaySize.x;
        int maxY = displaySize.y;
        //--------------------
        if ((int) player.centerX() >= maxX
                || (int) player.centerY() >= maxY
                || (int) player.centerX() <= 0
                || (int) player.centerY() <= 0) {
            return true;
        }
        return false;
    }

    protected boolean isWin() {
        if (player.intersect(level.getGoal())) {
            return true;
        } else if (level.getGoal().intersect(player)) {
            return true;
        }
        return false;
    }

    protected void win() {
        timer.cancel();
        Intent intent = new Intent(activity, WinScreen.class);
        intent.putExtra("level", level.getLevel());
        activity.startActivity(intent);
    }

    protected void lose() {
        timer.cancel();
        Intent intent = new Intent(activity, LoseScreen.class);
        intent.putExtra("level", level.getLevel());
        activity.startActivity(intent);
    }


    //timer
    public void startTimer() {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                move(direction);
            }
        }, 20, 20);
    }

    //reloading after one move all in 1 method
    protected void reload() {
        this.postInvalidate();
        if (isCollision()) {
            lose();
        }
        if (isOutOfBorder()) {
            lose();
        }
        if (isWin()) {
            win();
        }
    }
    /*
    Getters and Setters
     */

    public void setCenterX(float x) {
        this.centerX = x;
        reload();
    }

    public void setCenterY(float y) {
        this.centerY = y;
        reload();
    }

    public void setRad(float r) {
        this.rad = r;
    }

    public void setAngles(float startangle, float angle) {
        this.startangle = startangle;
        this.angle = angle;
    }

    public float getCenterX() {
        return centerX;
    }

    public float getCenterY() {
        return centerY;
    }

    public float getRad() {
        return rad;
    }

    /*
    Methods for turning direction
     */

    public void turnUp() {
        setAngles(225, 90);
        direction = DIRECTION_UP;
        if (isFirst) {
            startTimer();
            isFirst = false;
        }
    }

    public void turnDown() {
        setAngles(45, 90);
        direction = DIRECTION_DOWN;
        if (isFirst) {
            startTimer();
            isFirst = false;
        }
    }

    public void turnRight() {
        setAngles(315, 90);
        direction = DIRECTION_RIGHT;
        if (isFirst) {
            startTimer();
            isFirst = false;
        }
    }

    public void turnLeft() {
        setAngles(135, 90);
        direction = DIRECTION_LEFT;
        if (isFirst) {
            startTimer();
            isFirst = false;
        }
    }

    /*
    Auto-Moving
     */
    public void move(int direction) {
        switch (direction) {
            case DIRECTION_DOWN:
                setCenterY(getCenterY() + 5);
                break;
            case DIRECTION_LEFT:
                setCenterX(getCenterX() - 5);
                break;
            case DIRECTION_RIGHT:
                setCenterX(getCenterX() + 5);
                break;
            case DIRECTION_UP:
                setCenterY(getCenterY() - 5);
                break;
        }
    }

}
