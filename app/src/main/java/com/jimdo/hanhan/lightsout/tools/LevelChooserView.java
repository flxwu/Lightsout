package com.jimdo.hanhan.lightsout.tools;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.graphics.Paint;


/**
 * Created by David Wu on 19.08.2016(c)
 */
public class LevelChooserView extends View {
    protected Paint paint;
    public LevelChooserView(Context context) {
        super(context);
        paint=new Paint();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onDraw(Canvas canvas) {
        paint.setColor(Color.WHITE);
        paint.setAlpha(50);
        canvas.drawArc(300,300,500,500,14,90,true,paint);
    }
}
