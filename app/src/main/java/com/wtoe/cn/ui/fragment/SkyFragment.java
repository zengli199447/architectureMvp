package com.wtoe.cn.ui.fragment;

import com.wtoe.cn.ui.R;

import base.BaseFragment;
import base.contract.about.SkyFragmentContract;
import presenter.fragment.SkyFragmentPresenter;

/**
 * Created by Administrator on 2018/2/11.
 */

public class SkyFragment extends BaseFragment<SkyFragmentPresenter> implements SkyFragmentContract.View {

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_sky;
    }

    @Override
    protected void initEventAndData() {

    }

    @Override
    public void initClass() {

    }

    @Override
    public void initView() {

    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }


}
