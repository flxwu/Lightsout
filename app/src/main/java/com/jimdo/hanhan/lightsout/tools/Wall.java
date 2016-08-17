package com.jimdo.hanhan.lightsout.tools;

import android.graphics.Point;
import android.graphics.Rect;

/**
 * Created by David Wu on 16.08.2016.
 */

public class Wall {
    Point startcoords,endcoords;

    public Wall(int x1,int y1,int x2,int y2) {
        startcoords=new Point(x1,y1);
        endcoords=new Point(x2,y2);
    }

    public Wall(Point topleft,Point bottomright) {
        startcoords=topleft;
        endcoords=bottomright;
    }

    public Rect getRect() {
        return new Rect(startcoords.x,startcoords.y,endcoords.x,endcoords.y);
    }
}
