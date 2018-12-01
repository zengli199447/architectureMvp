package base.contract.main;

import base.BasePresenter;
import base.BaseView;

/**
 * Created by Administrator on 2017/10/27.
 */

public interface MainContract {

    interface View extends BaseView {
        void initView();
        void initListener();
    }

    interface Presenter extends BasePresenter<View> {
        void initData(String id);

    }


}
