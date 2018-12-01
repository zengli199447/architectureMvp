package com.wtoe.cn.ui.activity.more;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.wtoe.cn.ui.R;
import com.wtoe.cn.ui.view.MyScrollView;


/**
 * Created by Administrator on 2018/3/8 0008.
 */

public class SocllMoreActivity extends Activity implements MyScrollView.OnScrollListener{

    private EditText search_edit;
    private MyScrollView myScrollView;
    private LinearLayout search01;
    private LinearLayout search02;
    private RelativeLayout rlayout;
    private int searchLayoutTop;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_socllmore);
        init();


    }

    private void init() {
        search_edit = (EditText)findViewById(R.id.search_edit);
        myScrollView = (MyScrollView)findViewById(R.id.myScrollView);
        search01 = (LinearLayout)findViewById(R.id.search01);
        search02 = (LinearLayout)findViewById(R.id.search02);
        rlayout = (RelativeLayout)findViewById(R.id.rlayout);
        myScrollView.setOnScrollListener(this);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus){
            searchLayoutTop = rlayout.getBottom();//获取searchLayout的顶部位置
        }
    }



    @Override
    public void onScroll(int scrollY) {
        if(scrollY >= searchLayoutTop){
            if (search_edit.getParent()!=search01) {
                search02.removeView(search_edit);
                search01.addView(search_edit);
            }
        }else{
            if (search_edit.getParent()!=search02) {
                search01.removeView(search_edit);
                search02.addView(search_edit);
            }
        }
    }



}
