package di.component;

import android.app.Activity;

import com.wtoe.cn.ui.activity.mian.ComprehensiveActivity;
import com.wtoe.cn.ui.activity.mian.LoginActivity;
import com.wtoe.cn.ui.activity.mian.MainActivity;
import com.wtoe.cn.ui.activity.more.CustomActivity;

import dagger.Component;
import di.module.ActivityModule;
import di.scope.ActivityScope;

/**
 * Created by Administrator on 2017/10/27.
 */
@ActivityScope
@Component(modules = ActivityModule.class, dependencies = AppComponent.class)
public interface ActivityComponent {
    Activity getActivity();

    void inject(LoginActivity loginActivity);

    void inject(ComprehensiveActivity comprehensiveActivity);

    void inject(MainActivity mainActivity);

    void inject(CustomActivity customActivity);
}
