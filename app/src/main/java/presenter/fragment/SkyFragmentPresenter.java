package presenter.fragment;

import javax.inject.Inject;

import base.RxPresenter;
import base.contract.about.SkyFragmentContract;
import model.DataManager;
import model.event.CommonEvent;
import rxtools.RxBus;
import rxtools.RxUtil;
import widget.CommonSubscriber;

/**
 * Created by Administrator on 2018/2/11.
 */

public class SkyFragmentPresenter extends RxPresenter<SkyFragmentContract.View> implements SkyFragmentContract.Presenter{

    private DataManager mDataManager;

    @Inject
    public SkyFragmentPresenter(DataManager mDataManager) {
        this.mDataManager = mDataManager;
    }

    @Override
    public void initData() {

    }

    @Override
    public void attachView(SkyFragmentContract.View view) {
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
