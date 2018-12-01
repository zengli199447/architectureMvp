package base.contract.about;

import base.BasePresenter;
import base.BaseView;

/**
 * Created by Administrator on 2018/2/11.
 */

public interface ComprehensiveContract {
    interface View extends BaseView {
        void initClass();

        void initView();
    }

    interface Presenter extends BasePresenter<View> {
        void initData();

        void setCurrentItem(int index);

        int getCurrentItem();

    }

}
