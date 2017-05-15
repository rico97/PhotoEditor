package com.example.rico.photoeditor;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.Random;

/**
 * Created by Rico on 5/11/2017.
 */

public class MyView extends View {
    private Paint paint;
    private PointF[] points;
    private int red, green, blue;
    private Random random;
    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        random = new Random();
        red = 255;
        green = 255;
        blue = 255;
        paint.setColor(Color.BLACK);
    }

    public void setPoints(PointF[] points) {
        this.points = points;
    }

    public void draw(Canvas canvas){
        super.draw(canvas);

        if (points == null) return;

        for(PointF point : points) {
            canvas.drawCircle(point.x, point.y, 10 ,paint );
        }
        System.out.println(String.format("%d %d %d ", red, green, blue));
    }

}
