package com.jimdo.hanhan.lightsout.levels;

import android.graphics.Point;
import android.graphics.Rect;

import com.jimdo.hanhan.lightsout.tools.Level;
import com.jimdo.hanhan.lightsout.tools.Wall;

import java.util.ArrayList;

/**
 * Created by David Wu on 19.08.2016(c)
 */
public class Level02 extends Level {
    ArrayList<Wall> arrayList;
    final static Rect GOAL = new Rect(200,600,300,700);

    public Level02() {
        arrayList=new ArrayList<Wall>(8);
        arrayList.add(new Wall(new Point(500,400),new Point(600,650)));
        arrayList.add(new Wall(new Point(150,100),new Point(200,300)));
        arrayList.add(new Wall(new Point(250,200),new Point(260,350)));
        arrayList.add(new Wall(new Point(170,500),new Point(360,550)));
        arrayList.add(new Wall(new Point(160,570),new Point(195,750)));
        arrayList.add(new Wall(new Point(270,700),new Point(460,950)));
        arrayList.add(new Wall(new Point(570,800),new Point(650,950)));
        arrayList.add(new Wall(new Point(670,1000),new Point(800,1150)));
    }

    @Override
    public ArrayList<Wall> getWalls() {
        return arrayList;
    }

    @Override
    public Rect getGoal() {
        return GOAL;
    }

    @Override
    public int getLevel() {
        return 2;
    }
}
