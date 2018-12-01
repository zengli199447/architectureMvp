package com.wtoe.cn.ui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2018/2/23.
 */

public class MyCanvasView extends View {

    private Paint paint;

    public MyCanvasView(Context context) {
        super(context);
    }

    public MyCanvasView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint = initPaint();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.YELLOW);

//        canvas.drawPoint(200, 200, paint);点
//        canvas.drawLine(200,200,600,200,paint);线
        final RectF rectF = new RectF(100, 100, 800, 400);
        canvas.drawRect(rectF,paint);//面
//        canvas.drawRoundRect(rectF, 36, 36, paint);圆角矩形
//        canvas.drawOval(100,100,800,400,paint);椭圆
//        canvas.drawCircle(500,500,400,paint);圆
        paint.setColor(Color.GRAY);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawArc(rectF,0,90,true,paint);



    }


    private Paint initPaint() {
        final Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(10f);
        return paint;
    }


}
