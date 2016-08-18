package com.jimdo.hanhan.lightsout.tools;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.view.View;
import android.widget.GridLayout;

import com.jimdo.hanhan.lightsout.LoseScreen;
import com.jimdo.hanhan.lightsout.MainLevel;
import com.jimdo.hanhan.lightsout.WinScreen;

import java.util.ArrayList;
import java.util.TimerTask;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

/**
 * Created by David Wu on 15.08.2016.
 */
public class LightView extends View {
    Paint paint = new Paint();
    protected float centerX = 40, centerY = 200, rad = 200, angle = 90, startangle = 315;
    protected int position_x, position_y;
    protected GridLayout.LayoutParams params;
    protected Canvas canvas;
    protected Level level;
    protected Rect player;
    protected MainLevel activity;
    public final static int DIRECTION_LEFT = 1, DIRECTION_RIGHT = 2, DIRECTION_UP = 3, DIRECTION_DOWN = 4;

    public LightView(MainLevel activity, Level level) {
        super(activity);
        this.activity = activity;
        this.level = level;
        paint.setColor(Color.argb(100, 255, 255, 255));
    }


    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
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
        paint.setColor(Color.rgb(30, 0, 0));
        canvas.drawRect(level.getGoal(), paint);
    }

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

    protected void lose() {
        Intent intent = new Intent(activity, LoseScreen.class);
        activity.startActivity(intent);
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
        Intent intent = new Intent(activity, WinScreen.class);
        activity.startActivity(intent);
    }
    /*
    Getters and Setters
     */

    public void setCenterX(float x) {
        this.centerX = x;
        this.invalidate();
        if (isCollision()) {
            lose();
        }
        if (isWin()) {
            win();
        }
    }

    public void setCenterY(float y) {
        this.centerY = y;
        this.invalidate();
        if (isCollision()) {
            lose();
        }
        if (isWin()) {
            win();
        }
    }

    public void setRad(float r) {
        this.rad = r;
        this.invalidate();
        if (isCollision()) {
            lose();
        }
        if (isWin()) {
            win();
        }
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
    Methods for moving
     */

    public void turnUp() {
        setAngles(225,90);
    }

    public void turnDown() {
        setAngles(45, 90);
    }

    public void turnRight() {
        setAngles(315, 90);
    }

    public void turnLeft() {
        setAngles(135, 90);
    }

    public void move(int direction) {
        switch (direction) {
            case DIRECTION_DOWN:
                setCenterY(getCenterY() + 10);
                break;
            case DIRECTION_LEFT:
                setCenterX(getCenterX() - 10);
                break;
            case DIRECTION_RIGHT:
                setCenterX(getCenterX() + 10);
                break;
            case DIRECTION_UP:
                setCenterY(getCenterY() - 10);
                break;
        }
    }

    class Timer extends TimerTask {

        @Override
        public void run() {
            
        }
    }
}
