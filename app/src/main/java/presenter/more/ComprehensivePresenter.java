package presenter.more;

import javax.inject.Inject;

import base.RxPresenter;
import base.contract.about.ComprehensiveContract;
import model.DataManager;
import model.event.CommonEvent;
import rxtools.RxBus;
import rxtools.RxUtil;
import widget.CommonSubscriber;

/**
 * Created by Administrator on 2018/2/11.
 */

public class ComprehensivePresenter extends RxPresenter<ComprehensiveContract.View> implements ComprehensiveContract.Presenter{

    private DataManager mDataManager;

    @Inject
    public ComprehensivePresenter(DataManager mDataManager) {
        this.mDataManager = mDataManager;
    }

    @Override
    public void initData() {

    }

    @Override
    public void setCurrentItem(int index) {

    }

    @Override
    public int getCurrentItem() {
        return 0;
    }

    @Override
    public void attachView(ComprehensiveContract.View view) {
        super.attachView(view);
        registerEvent();
    }

    private void registerEvent() {
        addSubscribe(RxBus.getDefault().toFlowable(CommonEvent.class)
                .compose(RxUtil.<CommonEvent>rxSchedulerHelper())
                .subscribeWith(new CommonSubscriber<CommonEvent>(mView, null) {
                    @Override
                    public void onNext(CommonEvent commonevent) {
                        switch (commonevent.getCode()) {

                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        registerEvent();
                    }
                })
        );
    }




}
