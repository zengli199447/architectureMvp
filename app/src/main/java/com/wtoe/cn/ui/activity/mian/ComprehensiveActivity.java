package com.wtoe.cn.ui.activity.mian;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.wtoe.cn.ui.R;
import com.wtoe.cn.ui.fragment.HistoryFragment;
import com.wtoe.cn.ui.fragment.SkyFragment;

import base.BaseActivity;
import base.contract.about.ComprehensiveContract;
import butterknife.BindView;
import me.yokeyword.fragmentation.SupportFragment;
import presenter.more.ComprehensivePresenter;
import widget.Constants;

/**
 * Created by Administrator on 2018/2/12.
 */

public class ComprehensiveActivity extends BaseActivity<ComprehensivePresenter> implements ComprehensiveContract.View, RadioGroup.OnCheckedChangeListener{

    @BindView(R.id.img_btn_black)
    ImageButton img_btn_black;
    @BindView(R.id.video_radio)
    RadioGroup video_radio;
    @BindView(R.id.real_time_video)
    RadioButton real_time_video;
    @BindView(R.id.skynet_video)
    RadioButton skynet_video;
    @BindView(R.id.fl_main_content)
    FrameLayout fl_main_content;
//    @BindView(R.id.nested_scroll_view)
//    ScrollView nested_scroll_view;
    private int showFragment = Constants.TYPE_SKYNET;
    private int hideFragment = Constants.TYPE_SKYNET;
    private SkyFragment skyFragment;
    private HistoryFragment historyFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            showFragment = mPresenter.getCurrentItem();
            hideFragment = Constants.TYPE_SKYNET;
            showHideFragment(getTargetFragment(showFragment), getTargetFragment(hideFragment));
            hideFragment = showFragment;
        }
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_comprehensive;
    }

    @Override
    protected void initEventAndData() {
        skyFragment = new SkyFragment();
        historyFragment = new HistoryFragment();
        loadMultipleRootFragment(R.id.fl_main_content, 0, skyFragment, historyFragment);
        video_radio.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        switch (checkedId) {
            case R.id.real_time_video:
                showFragment = Constants.TYPE_SKYNET;
                break;
            case R.id.skynet_video:
                showFragment = Constants.TYPE_THEHISTORY;
                break;
        }
        mPresenter.setCurrentItem(showFragment);
        video_radio.check(checkedId);
        showHideFragment(getTargetFragment(showFragment), getTargetFragment(hideFragment));
        hideFragment = showFragment;
    }

    private SupportFragment getTargetFragment(int item) {
        switch (item) {
            case Constants.TYPE_SKYNET:
                return skyFragment;
            case Constants.TYPE_THEHISTORY:
                return historyFragment;
        }
        return skyFragment;
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    public void initClass() {

    }

    @Override
    public void initView() {

    }


}
