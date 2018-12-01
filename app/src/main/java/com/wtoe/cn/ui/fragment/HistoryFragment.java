package com.wtoe.cn.ui.fragment;

import com.wtoe.cn.ui.R;

import base.BaseFragment;
import base.contract.about.HistoryFragmentContract;
import presenter.fragment.HistoryFragmentPresenter;

/**
 * Created by Administrator on 2018/2/11.
 */

public class HistoryFragment extends BaseFragment<HistoryFragmentPresenter> implements HistoryFragmentContract.View {

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_history;
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
