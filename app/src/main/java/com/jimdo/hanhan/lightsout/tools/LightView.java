package com.jimdo.hanhan.lightsout.tools;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Build;
import android.view.View;
import android.widget.GridLayout;

/**
 * Created by David Wu on 15.08.2016.
 */
public class LightView extends View {
    Paint paint = new Paint();
    protected float centerX = 40, centerY = 200, rad = 200, angle = 90, startangle=315;
    protected int position_x, position_y;
    protected GridLayout.LayoutParams params;
    protected Canvas canvas;


    public LightView(Context context,Level level) {
        super(context);
        paint.setColor(Color.argb(100, 255, 255, 255));
    }


    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.canvas=canvas;
        drawLightCone(canvas, centerX, centerY, rad);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    protected void drawLightCone(Canvas canvas, float centerX, float centerY, float rad) {
        float left = centerX - rad;
        float top = centerY - rad;
        float right = centerX + rad;
        float bottom = centerY + rad;
        paint.setColor(Color.RED);
        canvas.drawCircle(centerX, centerY, 5, paint);
        paint.setColor(Color.WHITE);
        paint.setAlpha(100);
        canvas.drawArc(left, top, right, bottom,startangle,angle, true, paint);
    }


    /*
    Getters and Setters
     */

    public void setCenterX(float x) {
        this.centerX = x;
        this.invalidate();
    }

    public void setCenterY(float y) {
        this.centerY = y;
        this.invalidate();
    }

    public void setRad(float r) {
        this.rad = r;
        this.invalidate();

    }

    public void setAngles(float startangle,float angle) {
        this.startangle=startangle;
        this.angle=angle;
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
        setCenterY(getCenterY()-10);
        setAngles(225,90);
    }

    public void moveDown() {
        setCenterY(getCenterY()+10);
        setAngles(45,90);
    }

    public void moveRight() {
        setCenterX(getCenterX()+10);
        setAngles(315,90);
    }

    public void moveLeft() {
        setCenterX(getCenterX()-10);
        setAngles(135,90);
    }

    public void addWall(Point start,Point end) {
        paint.setColor(Color.rgb(255,0,0));
        this.canvas.drawLine(start.x,start.y,end.x,end.y,paint);
        this.invalidate();
    }
}
