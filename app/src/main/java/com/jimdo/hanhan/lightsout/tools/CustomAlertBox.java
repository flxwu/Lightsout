package com.jimdo.hanhan.lightsout.tools;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.Toast;

/**
 * Created by David Wu on 20.08.2016(c)
 */

public class CustomAlertBox {
    protected String text;
    protected final static int TYPE_TOAST = 0;
    protected final static int TYPE_ALERT = 1;

    public CustomAlertBox(Context context,int type) {
        type(context,type);
    }

    public CustomAlertBox(Context context,int type,String text) {
        setText(text);
        type(context,type);
    }

    public void setText(String s) {
        text=s;
    }



    protected void type(Context context,int type) {
        switch (type) {
            case TYPE_TOAST:
                Toast.makeText(context,text,Toast.LENGTH_SHORT).show();
                break;
            case TYPE_ALERT:
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Attention")
                        .setMessage(text)
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
                break;
        }
    }

}
