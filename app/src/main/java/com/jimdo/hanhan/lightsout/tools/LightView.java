package com.jimdo.hanhan.lightsout.tools;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build;
import android.view.View;
import android.widget.GridLayout;

import java.util.ArrayList;

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
    protected ArrayList<Rect> rectArrayList;
    protected Rect player;


    public LightView(Context context, Level level) {
        super(context);
        this.level = level;
        paint.setColor(Color.argb(100, 255, 255, 255));
    }


    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawLightCone(canvas, centerX, centerY, rad);
        drawWall(canvas);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    protected void drawLightCone(Canvas canvas, float centerX, float centerY, float rad) {
        float left = centerX - rad;
        float top = centerY - rad;
        float right = centerX + rad;
        float bottom = centerY + rad;
        paint.setColor(Color.RED);
        player=new Rect((int)centerX-5,(int)centerY-5,(int)centerX+5,(int)centerY+5);
        canvas.drawRect(player,paint);
        paint.setColor(Color.WHITE);
        paint.setAlpha(100);
        canvas.drawArc(left, top, right, bottom, startangle, angle, true, paint);
    }

    protected void drawWall(Canvas canvas) {
        ArrayList<Wall> wallArrayList = level.getWalls();
        rectArrayList=new ArrayList<Rect>(wallArrayList.size());
        for (int i = 0; i < wallArrayList.size(); i++) {
            canvas.drawLine(wallArrayList.get(i).startcoords.x, wallArrayList.get(i).startcoords.y, wallArrayList.get(i).endcoords.x, wallArrayList.get(i).endcoords.y, paint);
        }
    }

    protected boolean isCollision() {
        for(int i=0;i<rectArrayList.size();i++) {
            if(rectArrayList.get(i).intersect(player)) {
                return true;
            }else if(player.intersect(rectArrayList.get(i))) {
                return true;
            }
        }
        return false;
    }

    /*
    Getters and Setters
     */

    public void setCenterX(float x) {
        this.centerX = x;
        this.invalidate();
        isCollision();
    }

    public void setCenterY(float y) {
        this.centerY = y;
        this.invalidate();
        isCollision();

    }

    public void setRad(float r) {
        this.rad = r;
        this.invalidate();
        isCollision();
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

    public void moveUp() {
        setCenterY(getCenterY() - 10);
        setAngles(225, 90);
    }

    public void moveDown() {
        setCenterY(getCenterY() + 10);
        setAngles(45, 90);
    }

    public void moveRight() {
        setCenterX(getCenterX() + 10);
        setAngles(315, 90);
    }

    public void moveLeft() {
        setCenterX(getCenterX() - 10);
        setAngles(135, 90);
    }


}
