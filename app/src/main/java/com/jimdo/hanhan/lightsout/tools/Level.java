package com.jimdo.hanhan.lightsout.tools;

import android.graphics.Point;

import com.jimdo.hanhan.lightsout.tools.Wall;

import java.util.ArrayList;

/**
 * Created by David Wu on 17.08.2016.
 */
public abstract class Level {
    public abstract ArrayList<Wall> getWalls();
    public abstract Point getGoal();
}
