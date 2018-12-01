package com.wtoe.cn.ui.view.canvastools;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2018/2/24.
 */

public class MyCanvasToolsView extends View {

    private Paint paint;

    public MyCanvasToolsView(Context context) {
        super(context);
    }

    public MyCanvasToolsView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint = initPaint();
//        paint.setColor(Color.GREEN);
//        canvas.translate(100,100);
//        canvas.drawCircle(0,0,100,paint);
//        paint.setColor(Color.YELLOW);
//        canvas.translate(100,100);
//        canvas.drawCircle(0,0,100,paint);
        final int width = getWidth();
        final int height = getHeight();
        canvas.translate(width / 2, height / 2);
        final RectF rectF = new RectF(0, -400, 400, 0);
//        paint.setColor(Color.BLACK);
//        canvas.drawRect(rectF,paint);
//        canvas.scale(-0.5f,-0.5f);
//        canvas.rotate(180);
        paint.setColor(Color.GREEN);
//        canvas.drawRect(rectF,paint);

        canvas.drawCircle(0, 0, 400, paint);
        canvas.drawCircle(0, 0, 380, paint);
        for (int i = 0; i <= 360; i += 10) {
            canvas.drawLine(0, 360, 0, 380, paint);
            canvas.rotate(10);
        }

    }

    private Paint initPaint() {
        final Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10f);
        paint.setAntiAlias(true);
        return paint;
    }


}
