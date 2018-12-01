package base;

import android.view.ViewGroup;

import javax.inject.Inject;

import di.component.ActivityComponent;
import di.component.DaggerActivityComponent;
import global.MyApplication;
import di.module.ActivityModule;
import utils.SnackbarUtil;
import utils.ToastUtil;

/**
 * Created by Administrator on 2017/10/27.
 */

public abstract class BaseActivity<T extends BasePresenter> extends SimpleActivity implements BaseView {
    @Inject
    public T mPresenter;

    @Inject
    public ToastUtil toastUtil;

    protected ActivityComponent getActivityComponent() {
        return DaggerActivityComponent.builder()
                .appComponent(MyApplication.getAppComponent())
                .activityModule(new ActivityModule(this))
                .build();
    }


    @Override
    protected void onViewCreated() {
        super.onViewCreated();
        initInject();
        if (mPresenter != null)
            mPresenter.attachView(this);
    }

    @Override
    protected void onDestroy() {
        if (mPresenter != null)
            mPresenter.detachView();
        super.onDestroy();
    }

    @Override
    public void showErrorMsg(String msg) {
        SnackbarUtil.show(((ViewGroup) findViewById(android.R.id.content)).getChildAt(0), msg);
    }


    @Override
    public void stateError() {

    }

    protected abstract void initInject();


}
