package com.wtoe.cn.ui.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.wtoe.cn.ui.R;

/**
 * Created by Administrator on 2018/2/24.
 */

public class CheckView extends View {

    private static final int ANIM_NULL = 0;         //动画状态-没有
    private static final int ANIM_CHECK = 1;        //动画状态-开启
    private static final int ANIM_UNCHECK = 2;      //动画状态-结束

    private Context mContext;           // 上下文
    private int mWidth, mHeight;        // 宽高
    private Handler mHandler;           // handler

    private Paint mPaint;
    private Bitmap okBitmap;

    private int animCurrentPage = -1;       // 当前页码
    private int animMaxPage = 13;           // 总页数
    private int animDuration = 500;         // 动画时长
    private int animState = ANIM_NULL;      // 动画状态

    private boolean isCheck = false;        // 是否只选中状态

    public CheckView(Context context) {
        super(context);
    }

    public CheckView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(mWidth / 2, mHeight / 2);
        canvas.drawCircle(0, 0, 100, mPaint);

        // 得出图像边长(正方形高度切片)
        final int sideLength = okBitmap.getHeight();

        // 得到图像选区 和 实际绘制位置
        final Rect rect = new Rect(sideLength * animCurrentPage, 0, sideLength * (animCurrentPage + 1), sideLength);
        final Rect dst = new Rect(-80, -80, 80, 80);
        canvas.drawBitmap(okBitmap, rect, dst, null);

    }

    private void init(Context context) {
        mContext = context;
        mPaint = new Paint();
        mPaint.setColor(0xffFF5317);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);

        okBitmap = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.checkmark);

        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (animCurrentPage < animMaxPage && animCurrentPage >= 0) {
                    invalidate();
                    if (animState == ANIM_NULL)
                        return;
                    if (animState == ANIM_CHECK) {
                        animCurrentPage++;
                    } else if (animState == ANIM_UNCHECK) {
                        animCurrentPage--;
                    }

                    this.sendEmptyMessageDelayed(0, animDuration / animMaxPage);
                    Log.e("AAA", "Count=" + animCurrentPage);
                } else {
                    if (isCheck) {
                        animCurrentPage = animMaxPage - 1;
                    } else {
                        animCurrentPage = -1;
                    }
                    invalidate();
                    animState = ANIM_NULL;
                }
            }
        };

    }

    public void check() {
        if (animState != ANIM_NULL || isCheck)
            return;
        animState = ANIM_CHECK;
        animCurrentPage = 0;
        mHandler.sendEmptyMessageDelayed(0, animDuration / animMaxPage);
        isCheck = true;
    }

    public void unCheck() {
        if (animState != ANIM_NULL || (!isCheck))
            return;
        animState = ANIM_UNCHECK;
        animCurrentPage = animMaxPage - 1;
        mHandler.sendEmptyMessageDelayed(0, animDuration / animMaxPage);
        isCheck = false;
    }

    public void setAnimDuration(int animDuration) {
        if (animDuration <= 0)
            return;
        this.animDuration = animDuration;
    }

    public void setBackgroundColor(int color) {
        mPaint.setColor(color);
    }
}
