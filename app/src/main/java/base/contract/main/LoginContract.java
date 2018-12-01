package base.contract.main;

import base.BasePresenter;
import base.BaseView;

/**
 * Created by Administrator on 2017/10/27.
 */

public interface LoginContract {

    interface View extends BaseView{
       void initView(String content);
    }

    interface Presenter extends BasePresenter<View>{
        void initData();
        void Login();
    }



}
