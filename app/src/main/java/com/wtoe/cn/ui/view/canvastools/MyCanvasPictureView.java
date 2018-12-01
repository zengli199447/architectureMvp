package com.wtoe.cn.ui.view.canvastools;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Picture;
import android.graphics.Rect;
import android.graphics.drawable.PictureDrawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.wtoe.cn.ui.R;

import global.MyApplication;

/**
 * Created by Administrator on 2018/2/24.
 */

public class MyCanvasPictureView extends View {

    private Paint paint;
    private Picture picture;
    private int height;
    private int width;
    private Bitmap okBitmap;

    public MyCanvasPictureView(Context context) {
        super(context);
    }

    public MyCanvasPictureView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        height = h;
        width = w;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        initPaint();
        recording();
//        picture.draw(canvas);
//        canvas.drawPicture(picture,new RectF(0,0,picture.getWidth(),200));
        final PictureDrawable pictureDrawable = new PictureDrawable(picture);
        pictureDrawable.setBounds(0, 0, picture.getHeight(), picture.getWidth());
        pictureDrawable.draw(canvas);
    }

    private void recording() {
        final Canvas canvas = picture.beginRecording(height, width);
        canvas.drawColor(Color.WHITE);
        paint.setColor(Color.BLUE);
//        canvas.translate(250, 250);
        okBitmap = BitmapFactory.decodeResource(MyApplication.getInstance().getResources(), R.drawable.bar_background);
//        canvas.drawCircle(0, 0, 100, paint);
        final Rect rect = new Rect(0, 0, okBitmap.getWidth() / 10, okBitmap.getHeight() / 10); //选取片段
        final Rect dst = new Rect(0, 0, 200, 400);
        canvas.drawBitmap(okBitmap, rect, dst, paint);
        picture.endRecording();
    }

    private Paint initPaint() {
        picture = new Picture();
        paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(10f);
        paint.setAntiAlias(true);
        return paint;
    }


}
