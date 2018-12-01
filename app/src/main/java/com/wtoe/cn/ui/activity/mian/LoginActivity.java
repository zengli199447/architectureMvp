package com.wtoe.cn.ui.activity.mian;

import android.view.View;
import android.widget.TextView;

import com.wtoe.cn.ui.R;

import base.BaseActivity;
import base.contract.main.LoginContract;
import butterknife.BindView;
import model.event.CommonEvent;
import model.event.EventCode;
import presenter.main.LoginPresenter;
import rxtools.RxBus;

/**
 * Created by Administrator on 2017/10/27.
 * nicezengli@163.com
 * RxBus使用  以及操作符相关请求上传文件
 * 这里只是简单的示例，需要其他操作的可以查看对应版本的操作符列表
 */

public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.View, View.OnClickListener {
    String TAG = "LoginActivity";
    @BindView(R.id.tv_view)
    TextView tv_view;

    @Override
    public void initView(String content) {
        tv_view.setText(content);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initEventAndData() {
        mPresenter.initData();
        mPresenter.Login();
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }


    @Override
    public void onClick(View v) {
        RxBus.getDefault().post(new CommonEvent(EventCode.ONSTART, "bingo"));
    }


}
