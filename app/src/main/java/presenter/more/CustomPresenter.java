package presenter.more;

import javax.inject.Inject;

import base.RxPresenter;
import base.contract.about.CustomContract;
import model.DataManager;
import model.event.CommonEvent;
import rxtools.RxBus;
import rxtools.RxUtil;
import widget.CommonSubscriber;

/**
 * Created by Administrator on 2018/2/11.
 */

public class CustomPresenter extends RxPresenter<CustomContract.View> implements CustomContract.Presenter{

    private DataManager mDataManager;

    @Inject
    public CustomPresenter(DataManager mDataManager) {
        this.mDataManager = mDataManager;
    }

    @Override
    public void initData() {

    }

    @Override
    public void attachView(CustomContract.View view) {
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
