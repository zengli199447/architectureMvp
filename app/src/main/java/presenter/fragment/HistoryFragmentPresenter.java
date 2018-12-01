package presenter.fragment;

import javax.inject.Inject;

import base.RxPresenter;
import base.contract.about.HistoryFragmentContract;
import model.DataManager;
import model.event.CommonEvent;
import rxtools.RxBus;
import rxtools.RxUtil;
import widget.CommonSubscriber;

/**
 * Created by Administrator on 2018/2/11.
 */

public class HistoryFragmentPresenter extends RxPresenter<HistoryFragmentContract.View> implements HistoryFragmentContract.Presenter{

    private DataManager mDataManager;

    @Inject
    public HistoryFragmentPresenter(DataManager mDataManager) {
        this.mDataManager = mDataManager;
    }

    @Override
    public void initData() {

    }

    @Override
    public void attachView(HistoryFragmentContract.View view) {
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
