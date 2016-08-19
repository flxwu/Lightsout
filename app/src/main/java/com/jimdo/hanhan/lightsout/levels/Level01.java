package com.jimdo.hanhan.lightsout.levels;

import android.graphics.Point;
import android.graphics.Rect;

import com.jimdo.hanhan.lightsout.tools.Level;
import com.jimdo.hanhan.lightsout.tools.Wall;

import java.util.ArrayList;

/**
 * Created by David Wu on 17.08.2016.
 */
public class Level01 extends Level{
    ArrayList<Wall> arrayList;
    final static Rect GOAL = new Rect(600,900,700,1000);

    public Level01() {
        arrayList=new ArrayList<Wall>(4);
        arrayList.add(new Wall(new Point(300,400),new Point(600,650)));
        arrayList.add(new Wall(new Point(150,100),new Point(200,300)));
        arrayList.add(new Wall(new Point(250,200),new Point(260,350)));
        arrayList.add(new Wall(new Point(270,700),new Point(460,950)));

    }

    @Override
    public ArrayList<Wall> getWalls() {
        return arrayList;
    }

    @Override
    public Rect getGoal() {
        return GOAL;
    }
}
