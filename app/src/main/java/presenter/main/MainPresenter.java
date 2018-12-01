package presenter.main;

import javax.inject.Inject;

import base.RxPresenter;
import base.contract.main.MainContract;
import model.DataManager;
import model.event.CommonEvent;
import rxtools.RxBus;
import rxtools.RxUtil;
import widget.CommonSubscriber;

/**
 * Created by Administrator on 2017/10/27.
 */

public class MainPresenter extends RxPresenter<MainContract.View> implements MainContract.Presenter {
    private String TAG = "MainPresenter";
    private DataManager dataManager;

    @Inject
    public MainPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public void attachView(MainContract.View view) {
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
                    }
                })
        );


    }

    @Override
    public void initData(String id) {

    }


}
