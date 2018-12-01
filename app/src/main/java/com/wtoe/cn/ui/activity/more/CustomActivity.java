package com.wtoe.cn.ui.activity.more;

import android.view.View;

import com.wtoe.cn.ui.R;
import com.wtoe.cn.ui.view.CheckView;
import com.wtoe.cn.ui.view.canvastools.MyCanvasRadarView;
import com.wtoe.cn.ui.view.data.PieData;

import java.util.ArrayList;

import base.BaseActivity;
import base.contract.about.CustomContract;
import butterknife.BindView;
import presenter.more.CustomPresenter;

/**
 * Created by Administrator on 2018/2/23.
 */

public class CustomActivity extends BaseActivity<CustomPresenter> implements CustomContract.View, View.OnClickListener {
//    @BindView(R.id.my_view)
//    MyPieView my_view;
    @BindView(R.id.check_view)
    CheckView checkView;
    @BindView(R.id.radar_view)
    MyCanvasRadarView radar_view;

    private ArrayList<PieData> mData;

    @Override
    public void initClass() {

    }

    @Override
    public void initView() {

    }

    @Override
    protected int getLayout() {
        return R.layout.activity_custom;
    }

    @Override
    protected void initEventAndData() {
//        mData = new ArrayList<>();
//        mData.add(new PieData("sloop", 60));
//        mData.add(new PieData("sloop", 30));
//        mData.add(new PieData("sloop", 40));
//        mData.add(new PieData("sloop", 20));
//        mData.add(new PieData("sloop", 20));
//        my_view.setData(mData);
        checkView.setOnClickListener(this);

        String[] titles= {"一","二","三","四","五","六"};
        double[] doubles= {60,20,19,36,80,66};
        radar_view.setTitles(titles);
        radar_view.setData(doubles);
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    public void onClick(View v) {
        checkView.check();
    }
}
