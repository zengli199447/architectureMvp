package base;

/**
 * Created by Administrator on 2017/10/27.
 */

public interface BasePresenter<T extends BaseView> {

    void attachView(T view);

    void detachView();


}
