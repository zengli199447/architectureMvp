package com.wtoe.cn.ui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.wtoe.cn.ui.view.data.PieData;

import java.util.ArrayList;

/**
 * Created by Administrator on 2018/2/23.
 */

public class MyPieView extends View {

    private int[] mColors = {0xFFCCFF00, 0xFF6495ED, 0xFFE32636, 0xFFFF00FF, 0xFFFF0000, 0xFF00FF00};
    //初始化绘制角度
    private float mStartAngle = 0;
    //数据
    private ArrayList<PieData> mData;
    //宽高
    private int mWidth, mHeight;
    //画笔
    private Paint paint;

    public MyPieView(Context context) {
        super(context);
    }

    public MyPieView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint = initPaint();
        if (null == mData)
            return;
        float currentStartAngle = this.mStartAngle;  //当前坐标起点
        canvas.translate(mWidth / 2, mHeight / 2);         //坐标原点移动到中心位置
        final float r = (float) (Math.min(mWidth, mHeight) / 2 * 0.8); //半径
        final RectF rectF = new RectF(-r, -r, r, r);
        for (int i = 0; i < mData.size(); i++) {
            final PieData pieData = mData.get(i);
            paint.setColor(pieData.getColor());
            canvas.drawArc(rectF, currentStartAngle, pieData.getAngle(), true, paint);
            currentStartAngle += pieData.getAngle();
            paint.setColor(Color.BLACK);
            canvas.drawText("pieData.getAngle()",100,200,paint);
        }

    }

    //设置初始角度
    public void setStartAngle(int mStartAngle) {
        this.mStartAngle = mStartAngle;
        invalidate(); //刷新
    }

    //初始化数据
    public void setData(ArrayList<PieData> mData) {
        this.mData = mData;
        initData(mData);
        invalidate(); //刷新
    }

    private void initData(ArrayList<PieData> mData) {
        if (null == mData || mData.size() == 0)   // 数据有问题 直接返回
            return;

        float sumValue = 0;
        for (int i = 0; i < mData.size(); i++) {
            PieData pie = mData.get(i);
            sumValue += pie.getValue();       //计算数值和
            int j = i % mColors.length;       //设置颜色
            pie.setColor(mColors[j]);
        }

        float sumAngle = 0;
        for (int i = 0; i < mData.size(); i++) {
            PieData pie = mData.get(i);
            float percentage = pie.getValue() / sumValue;   // 百分比
            float angle = percentage * 360;                 // 对应的角度
            pie.setPercentage(percentage);                  // 记录百分比
            pie.setAngle(angle);                            // 记录角度大小
            sumAngle += angle;
            Log.i("angle", "" + pie.getAngle());
        }
    }


    private Paint initPaint() {
        final Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(10f);
        paint.setAntiAlias(true);
        return paint;
    }

    private void initData() {
        mData = new ArrayList<>();
        mData.add(new PieData("sloop", 60));
        mData.add(new PieData("sloop", 30));
        mData.add(new PieData("sloop", 40));
        mData.add(new PieData("sloop", 20));
        mData.add(new PieData("sloop", 20));
    }


}
