package presenter.main;

import android.util.Log;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import base.RxPresenter;
import base.contract.main.LoginContract;
import model.DataManager;
import model.bean.LoginInfoBean;
import model.bean.OuterLayerEntity;
import model.db.entity.LoginUserInfo;
import model.event.CommonEvent;
import model.event.EventCode;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import rxtools.RxBus;
import rxtools.RxUtil;
import utils.LogUtil;
import widget.CommonSubscriber;

/**
 * Created by Administrator on 2017/10/27.
 */

public class LoginPresenter extends RxPresenter<LoginContract.View> implements LoginContract.Presenter {
    private String TAG = "LoginPresenter";
    private DataManager dataManager;

    @Inject
    public LoginPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public void attachView(LoginContract.View view) {
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
                            case EventCode.ONSTART:
                                String temp_str = commonevent.getTemp_str();
                                mView.initView(temp_str);
                                break;
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
    public void initData() {
        final List<LoginUserInfo> loginUserInfos = dataManager.loadLoginUserInfo();
        if (loginUserInfos != null && loginUserInfos.size() > 0) {
            final LoginUserInfo loginUserInfo = loginUserInfos.get(0);
            LogUtil.e(TAG, "loginUserInfo : " + loginUserInfo.getUsername());
        }
    }

    /**
     * 网络请求，post表单提交形式
     * 插入顺序即是拼接顺序，建议使用LinkedHashMap
     */
    @Override
    public void Login() {
        Map map = new HashMap();
        map.put("cmd", "CLIENT_USER_LOGIN");
        map.put("userid", "admin");
        map.put("pwd", "admin123456");
        map.put("deviceType", "mobile");
        addSubscribe(dataManager.fetchLogin(map)
                .compose(RxUtil.<LoginInfoBean>rxSchedulerHelper())
                .subscribeWith(new CommonSubscriber<LoginInfoBean>(mView) {
                    @Override
                    public void onNext(LoginInfoBean loginInfoBean) {
                        Log.e(TAG, "LoginInfoBean : " + loginInfoBean);
                        LoginInfoBean.DataBean data = loginInfoBean.getData();
                        String result = loginInfoBean.getResult();
                        String sid = data.getSid();
                        int status = data.getStatus();
                        dataManager.insertLoginUserInfo(new LoginUserInfo("admin", "123456"));
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "Throwable : " + e.toString());
                        super.onError(e);

                    }
                }));

    }

    /**
     * 相关文件类型具体可查看retrofit2 操作符
     * 注意 : 服务端在接受的位置需要做处理，可在网上查询相关（后台处理）
     */
    private void upPicData(ArrayList<String> picList) {
        if (picList.size() < 1) {
            return;
        }
        MultipartBody.Builder builder = new MultipartBody.Builder();
        String key = "";
        for (int i = 0; i < picList.size(); i++) {
            File file = new File(picList.get(i));
            RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), file);
            key = String.valueOf(i);
            builder.addFormDataPart(key, file.getName(), requestBody);
        }
        builder.setType(MultipartBody.FORM);
        MultipartBody build = builder.build();
        addSubscribe(dataManager.UpCasePicData(build)
                .compose(RxUtil.<OuterLayerEntity>rxSchedulerHelper())
                .subscribeWith(new CommonSubscriber<OuterLayerEntity>(mView) {
                    @Override
                    public void onNext(OuterLayerEntity outerLayerEntity) {
                        LogUtil.e(TAG, "OuterLayerEntity - outerLayerEntity : " + outerLayerEntity);
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        LogUtil.e(TAG, "e : " + e.toString());
                    }
                }));
    }

}
