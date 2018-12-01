package di.component;

import android.app.Activity;

import com.wtoe.cn.ui.fragment.HistoryFragment;
import com.wtoe.cn.ui.fragment.SkyFragment;

import dagger.Component;
import di.module.FragmentModule;
import di.scope.FragmentScope;


@FragmentScope
@Component(dependencies = AppComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {

    Activity getActivity();

    void inject(SkyFragment skyFragment);

    void inject(HistoryFragment historyFragment);
}
