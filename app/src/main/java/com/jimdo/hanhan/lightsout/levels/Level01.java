package com.jimdo.hanhan.lightsout.levels;

import android.graphics.Point;

import com.jimdo.hanhan.lightsout.tools.Level;
import com.jimdo.hanhan.lightsout.tools.Wall;

import java.util.ArrayList;

/**
 * Created by David Wu on 17.08.2016.
 */
public class Level01 extends Level{
    ArrayList<Wall> arrayList;
    final static Point GOAL = new Point(400,400);

    public Level01() {
        arrayList=new ArrayList<Wall>(1);
        arrayList.add(new Wall(new Point(50,50),new Point(200,50)));
    }

    @Override
    public ArrayList<Wall> getWalls() {
        return arrayList;
    }

    @Override
    public Point getGoal() {
        return GOAL;
    }
}
