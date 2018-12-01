package base.contract.about;

import base.BasePresenter;
import base.BaseView;

/**
 * Created by Administrator on 2018/2/11.
 */

public interface SkyFragmentContract {
    interface View extends BaseView {
        void initClass();

        void initView();
    }

    interface Presenter extends BasePresenter<View> {
        void initData();

    }

}
