package com.jimdo.hanhan.lightsout.tools;

import android.graphics.Point;
import android.graphics.Rect;

import com.jimdo.hanhan.lightsout.tools.Wall;

import java.util.ArrayList;

/**
 * Created by David Wu on 17.08.2016(c)
 *
 */

public abstract class Level {
    public abstract ArrayList<Wall> getWalls();
    public abstract Rect getGoal();
    public abstract Level getLevel();
    public abstract int getLevelInt();
    public int getNextLevel() {
        return getLevelInt()+1;
    }
}
