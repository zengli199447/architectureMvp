package com.wtoe.cn.ui.activity.more;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.wtoe.cn.ui.R;
import com.wtoe.cn.ui.view.data.PieData;

import java.util.ArrayList;

/**
 * Created by Administrator on 2018/2/23.
 */

public class MyPieActivity extends Activity {

    private ArrayList<PieData> mData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom);
//        final MyPieView my_view = (MyPieView) findViewById(R.id.my_view);

        mData = new ArrayList<>();
        mData.add(new PieData("sloop", 60));
        mData.add(new PieData("sloop", 30));
        mData.add(new PieData("sloop", 40));
        mData.add(new PieData("sloop", 20));
        mData.add(new PieData("sloop", 20));
//        view.setData(mData);
//        my_view.setData(mData);

    }
}
