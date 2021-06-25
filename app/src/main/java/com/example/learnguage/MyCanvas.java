package com.example.learnguage;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class MyCanvas extends View {

    private int x,y,z;
    private Paint paint;
    public MyCanvas(Context context) {
        super(context);
    }

    public void getColor(int a,int b,int c){
        x = a;
        y = b;
        z = c;
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.rgb(x,y,z));
        paint.setAntiAlias(true);

        canvas.drawCircle(getWidth()/2,getHeight()/2,75,paint);
    }
}
