package com.wtoe.cn.ui.activity.mian;

import android.support.v4.content.ContextCompat;
import android.support.v4.widget.NestedScrollView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.wtoe.cn.ui.R;
import com.wtoe.cn.ui.adapter.MainAutoGridViewAdapter;
import com.wtoe.cn.ui.adapter.MainListViewAdapter;
import com.wtoe.cn.ui.view.AutoGridView;
import com.wtoe.cn.ui.view.AutoListView;
import com.yanzhenjie.sofia.Sofia;

import java.util.ArrayList;

import base.BaseActivity;
import base.contract.main.MainContract;
import butterknife.BindView;
import global.DataClass;
import model.bean.GridViewToolsBean;
import presenter.main.MainPresenter;

public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.View, AdapterView.OnItemClickListener, NestedScrollView.OnScrollChangeListener {

    @BindView(R.id.nested_scroll_view)
    NestedScrollView nested_scroll_view;
    @BindView(R.id.bar_img)
    ImageView bar_img;
    @BindView(R.id.auto_grid_view)
    AutoGridView auto_grid_view;
    @BindView(R.id.info_title_bar)
    RelativeLayout info_title_bar;
    @BindView(R.id.listview)
    AutoListView listview;

    @Override
    protected int getLayout() {
        return R.layout.activity_main_tools;
    }

    @Override
    protected void initEventAndData() {
        final ArrayList<GridViewToolsBean> barGridList = new DataClass().getBarGridList();
        final ArrayList<GridViewToolsBean> contentList = new DataClass().getContentList();
        final MainAutoGridViewAdapter mainAutoGridViewAdapter = new MainAutoGridViewAdapter(this, barGridList);
        final MainListViewAdapter mainListViewAdapter = new MainListViewAdapter(this, contentList);
        auto_grid_view.setAdapter(mainAutoGridViewAdapter);
        listview.setAdapter(mainListViewAdapter);
        initListener();
        SofiaTools();
    }

    @Override
    public void initView() {

    }

    @Override
    public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
        int headerHeight = bar_img.getHeight();
        int scrollDistance = Math.min(scrollY, headerHeight);
        int statusAlpha = (int) ((float) scrollDistance / (float) headerHeight * 255F);
        setAnyBarAlpha(statusAlpha);
    }

    @Override
    public void initListener() {
        auto_grid_view.setOnItemClickListener(this);
        nested_scroll_view.setOnScrollChangeListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    private void SofiaTools() {
        Sofia.with(this)
                .statusBarBackground(ContextCompat.getColor(this, R.color.white))
                .navigationBarBackground(ContextCompat.getDrawable(this, R.color.white))
                .invasionStatusBar()
                .fitsSystemWindowView(info_title_bar);
        setAnyBarAlpha(0);
    }

    private void setAnyBarAlpha(int alpha) {
        info_title_bar.getBackground().mutate().setAlpha(alpha);
        Sofia.with(this).statusBarBackgroundAlpha(alpha);
    }


}
