package com.jimdo.hanhan.lightsout.levels;

import android.graphics.Point;
import android.graphics.Rect;

import com.jimdo.hanhan.lightsout.tools.Level;
import com.jimdo.hanhan.lightsout.tools.Wall;

import java.util.ArrayList;

/**
 * Created by daviwu16 on 19.08.2016.
 */
public class Level02 extends Level {
    ArrayList<Wall> arrayList;
    final static Rect GOAL = new Rect(200,600,300,700);

    public Level02() {
        arrayList=new ArrayList<Wall>(6);
        arrayList.add(new Wall(new Point(500,400),new Point(600,650)));
        arrayList.add(new Wall(new Point(150,100),new Point(200,300)));
        arrayList.add(new Wall(new Point(250,200),new Point(260,350)));
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
}
